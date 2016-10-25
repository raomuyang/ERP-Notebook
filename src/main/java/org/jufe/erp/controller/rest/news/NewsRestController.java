package org.jufe.erp.controller.rest.news;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.jufe.erp.entity.News;
import org.jufe.erp.entity.User;
import org.jufe.erp.repository.Page;
import org.jufe.erp.service.news.NewsImageService;
import org.jufe.erp.service.news.NewsService;
import org.jufe.erp.utils.anno.AuthRequest;
import org.jufe.erp.utils.enums.AuthLevel;
import org.jufe.erp.utils.enums.StandardStr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raomengnan on 2016/9/20.
 * 对News内容进行操作的rest接口
 * 允许普通用户进行，增删改操作，但是要在拦截器中进行对用户操作进行判断，是否只在自己写的文章上操作
 */
@RestController
@RequestMapping("/rest/news")
public class NewsRestController {
    @Autowired
    private NewsService newsService;
    @Autowired
    private NewsImageService newsImageService;

    private Logger logger = Logger.getLogger(NewsRestController.class);

    /**
     * 普通用户无法看到未完成的文章
     * @param id
     * @return
     */
    @RequestMapping("/{newsId}")
    public News getById(@PathVariable("newsId") String id, HttpServletRequest request, HttpServletResponse response){
        logger.debug("Get by id:" + id);
        if(id == null || id.isEmpty())
            return null;
        News news = newsService.findById(id);
        if(news != null || !news.getFinish()){
            User user = (User) request.getAttribute(StandardStr.USER.s());
            if(user == null || !user.getId().equals(news.getAuthorId())){
                response.setStatus(403);
                response.setHeader("msg", "ILLEGAL OPERATION");
                return null;
            }
        }
        return news;
    }

    @RequestMapping("/author/{author}")
    public List<News> getByAuthor(@PathVariable("author") String author){
        logger.debug("/get-by-author/" + author);
        if(author == null || author.isEmpty())
            return new ArrayList<>();

        return newsService.findByAuthor(author);
    }

    @RequestMapping("/authorid/{authorid}")
    public List<News> getByAuthorId(@PathVariable("authorid") String authorId){
        logger.debug("Get by authorid/" + authorId);
        if(authorId == null || authorId.isEmpty())
            return new ArrayList<>();

        return newsService.findByAuthorId(authorId);
    }

    @RequestMapping("/title/{title}")
    public List<News> getByTitle(@PathVariable("title") String title){
        logger.debug("/Get by title/" + title);
        if(title == null || title.isEmpty())
            return new ArrayList<>();

        return newsService.findByTitle(title);
    }

    @RequestMapping("/keyw/{keyw}")
    public List<News> getByKeyWord(@PathVariable("keyw") String keyw){
        logger.debug("/Get by keyword/" + keyw);
        if(keyw == null || keyw.isEmpty())
            return new ArrayList<>();

        return newsService.findByKeyword(keyw);
    }

    @RequestMapping("/page/psize/{psize}/pno/{pno}")
    public Page<News> getPage(@PathVariable("pno") int pno, @PathVariable("psize") int psize){

        logger.debug("/page/{psize}/{pno}:" + psize + "," + pno);
        return newsService.findPage(pno, psize);
    }

    @AuthRequest
    @RequestMapping("/no-finish")
    public List<News> getUserNoFinish(HttpServletRequest request){
        logger.debug("/no-finish");
        String id = "";
        try {
            User user = (User) request.getAttribute(StandardStr.USER.s());
            id = user.getId();
        }catch (Exception e){
            logger.error("Add news[Read user error]:" + e.getMessage());
        }
        return newsService.findAuthorNoFinished(id);
    }

    @AuthRequest
    @RequestMapping("/no-finish/page/psize/{psize}/pno/{pno}")
    public Page<News> getUserNoFinishPage(@PathVariable("pno") int pno, @PathVariable("psize") int psize, HttpServletRequest request){

        logger.debug("/no-finish-page/{psize}/{pno}:" + psize + "," + pno);
        String id = "";
        try {
            User user = (User) request.getAttribute(StandardStr.USER.s());
            id = user.getId();
        }catch (Exception e){
            logger.error("Add news[Read user error]:" + e.getMessage());
        }
        return newsService.findAuthorNoFinishedPage(id, pno, psize);
    }

    @AuthRequest(level = AuthLevel.USER)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<ModelMap> update(@RequestBody News news, HttpServletRequest request){
        logger.debug("update: " + news);
        boolean result = false;
        ModelMap map = new ModelMap();
        result = newsService.update(news);
        map.put("result", result);
        if(!result){
            map.put("msg", "更新出错，请重试");
            if(news == null || news.getId() == null)
                map.put("msg", "参数错误");
        }
        return new ResponseEntity<ModelMap>(map, HttpStatus.OK);
    }

    @AuthRequest(level = AuthLevel.USER)
    @RequestMapping(value = "/update-status", method = RequestMethod.POST)
    public ResponseEntity<ModelMap> updateStatus(@RequestBody News news, HttpServletRequest request){
        logger.debug("update-status: " + news==null?news:news.getId());
        boolean result = false;
        ModelMap map = new ModelMap();
        result = newsService.updateStatus(news);
        map.put("result", result);
        if(!result){
            map.put("msg", "更新出错，请重试");
            if(news == null || news.getId() == null)
                map.put("msg", "参数错误");
        }
        return new ResponseEntity<ModelMap>(map, HttpStatus.OK);
    }

    /**
     * 因新闻和图片绑定的特殊性，新闻id由用户自行初始化一个ObjectId
     * @param news
     * @return
     */
    @AuthRequest(level = AuthLevel.USER)
    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    public ResponseEntity<ModelMap> add(@RequestBody News news, HttpServletRequest request){
        logger.debug("add: " + news);
        boolean result = false;
        ModelMap map = new ModelMap();

        try {
            User user = (User) request.getAttribute(StandardStr.USER.s());
            news.setAuthor(user.getUserName());
            news.setAuthorId(user.getId());
            ObjectId id = new ObjectId();
            news.setId(id.toHexString());
            news.setDate(id.getDate());
        }catch (Exception e){
            logger.error("Add news[Read user error]:" + e.getMessage());
        }

        result = newsService.addNews(news);
        map.put("result", result);
        if(!result){
            map.put("msg", "创建出错，请重试");
            if(news == null || news.getId() == null)
                map.put("msg", "参数错误");
        }
        map.put("body", news);
        return new ResponseEntity<ModelMap>(map, HttpStatus.OK);
    }

    @AuthRequest(level = AuthLevel.USER)
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<ModelMap> delete(@RequestBody String newsId, HttpServletRequest request){
        logger.debug("delete: " + newsId);
        boolean result = false;
        ModelMap map = new ModelMap();
        result = newsService.delete(newsId);
        map.put("result", result);
        if(result){
            newsImageService.deleteByNewsId(request.getSession().getServletContext().getRealPath("/"), newsId);
        }
        else {
            map.put("msg", "更新出错，请重试");
            if(newsId == null || newsId.isEmpty())
                map.put("msg", "参数错误");
        }
        return new ResponseEntity<ModelMap>(map, HttpStatus.OK);
    }

}
