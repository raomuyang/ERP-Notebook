package org.jufe.erp.service.auth.impl;

import org.apache.log4j.Logger;
import org.jufe.erp.entity.TokenInfo;
import org.jufe.erp.entity.User;
import org.jufe.erp.repository.auth.TokenRepository;
import org.jufe.erp.repository.user.UserRepository;
import org.jufe.erp.service.auth.TocknService;
import org.jufe.erp.utils.MD5;
import org.jufe.erp.utils.TokenOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by raomengnan on 16-9-21.
 */
@Service
public class TokenServiceImpl implements TocknService {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    private Logger logger = Logger.getLogger(TokenServiceImpl.class);

    /**
     * 如果需要从mongo中获取token，则说明本地内存的map中不存在这个token，而用户最近需要使用
     * 所以将其加载到本地
     * [返回前先检查是否有效]
     * @param token
     * @return
     */
    @Override
    public TokenInfo get(String token) {

        //先从本地查询
        TokenInfo tokenInfo = TokenOptions.get(token);
        if(token != null) {
            if(TokenOptions.isTokenValid(tokenInfo))
                return tokenInfo;
            else {
                delete(token);
                return null;
            }
        }


        //不存在，则从远端查询
        logger.debug("不存在本地token：" + token);
        final TokenInfo rtoken = tokenRepository.find(new Query(new Criteria("token").is(token))).get(0);
        if(!TokenOptions.isTokenValid(rtoken)){
            delete(token);
            return null;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                TokenOptions.put(rtoken);
            }
        }).start();

        return rtoken;
    }

    @Override
    public User getUser(String id) {
        TokenInfo tokenInfo = get(id);
        if(tokenInfo != null)
            return null;
        return userRepository.findById(tokenInfo.getUserId());
    }

    @Override
    public TokenInfo create(String userId) {
        TokenInfo tokenInfo = TokenOptions.create(userId);
        return tokenInfo;
    }

    /**
     * 一般在token失效时调用
     * @param token
     * @return
     */
    @Override
    public boolean delete(String token) {
        TokenInfo tokenInfo = TokenOptions.get(token);
        if(tokenInfo != null){
            TokenOptions.pop(token);
            return tokenRepository.deleteById(tokenInfo.getId());
        }

        else
            tokenInfo = tokenRepository.find(new Query(new Criteria("token").is(token))).get(0);
        if(tokenInfo != null){
            boolean res = tokenRepository.deleteById(tokenInfo.getId());
            if(!res)
                logger.error(String.format("删除远端的token[%s]失败", tokenInfo.toString()));
            return res;
        }
        return false;
    }

    @Override
    public boolean update(String token) {
        if(token == null || token.isEmpty())
            return false;

        //更新tokenInfo的最近一次操作为当前时间
        TokenInfo tokenInfo = get(token);
        if(!TokenOptions.isTokenValid(tokenInfo)){
            logger.debug(String.format("token失效[%s]", tokenInfo.toString()));
            delete(token);
            return false;
        }

        tokenInfo.setLastTime(new Date(System.currentTimeMillis()));
        return tokenRepository.update(tokenInfo.getId(), "lastTime", tokenInfo.getLastTime());
    }

}
