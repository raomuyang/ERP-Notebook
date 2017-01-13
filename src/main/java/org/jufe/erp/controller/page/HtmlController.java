package org.jufe.erp.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Raomengnan on 2016/11/19.
 */
@Controller
public class HtmlController {
    @RequestMapping("/admin/*.html")
    public String admin(HttpServletRequest request, HttpServletResponse response){
        String url = request.getRequestURI();
        Cookie cookie = new Cookie("header", "token");
        response.addCookie(cookie);
        return url.replace(".jsp", "").replace(".html", ".jsp");
    }

    @RequestMapping("/admin/")
    public String admin(){
        return "/admin/index.jsp";
    }
}
