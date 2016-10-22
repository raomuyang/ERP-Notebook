package org.jufe.erp.utils;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.jufe.erp.entity.TokenInfo;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by raomengnan on 16-9-21.
 * token验证和存储的相关操作
 * 一般以objectId生成的MD5为token
 * 当token为对象时，指的是整个token中包含的信息TokenInfo
 */
public class TokenOptions {

    public static int MAX_TOKEN;

    private static Map<String, TokenInfo> tokenMap = new ConcurrentHashMap<>();

    private static Logger getLog(){
        return Logger.getLogger(TokenOptions.class);
    }

    public static TokenInfo create(String userId){
        String id = new ObjectId().toHexString();
        String md5 = MD5.getMD5(id);
        TokenInfo tokenInfo = new TokenInfo();

        //初始化
        tokenInfo.setId(id);
        tokenInfo.setUserId(userId);
        tokenInfo.setToken(md5);
        tokenInfo.setLastTime(new Date(System.currentTimeMillis()));

        put(tokenInfo);
        return tokenInfo;
    }

    public static Date getDate(String id){
        getLog().debug("get token date:" + id);

        try {

            ObjectId objectId = new ObjectId(id);
            if (objectId == null)
                return null;
            return objectId.getDate();
        }catch (Exception e){
            return null;
        }
    }

    public static boolean isTokenValid(TokenInfo tokenInfo){
        getLog().debug("isTokenValid:" + tokenInfo);
        if(tokenInfo == null)
            return false;
        try {
            ObjectId id = new ObjectId(tokenInfo.getId());
            long validDays = tokenInfo.getValidDays() ;
            Date invalidDate = DateTools.getDateAfter(id.getDate(), validDays);
            Date current = new Date(System.currentTimeMillis());
            return current.before(invalidDate);
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 将tokenput到内存的map中
     * @param tokenInfo
     */
    public static void put(TokenInfo tokenInfo){

        if(tokenInfo == null)
            return;

        String token = MD5.getMD5(tokenInfo.getId());
        if(get(token) != null)
            return;
        if(tokenMap.values().size() > 500){
            //token数量超过上限就触发一次清理
            List<TokenInfo> tokenInfos = new ArrayList<TokenInfo>();
            tokenInfos.addAll(tokenMap.values());
            sort(tokenInfos);
            for (int i = 0; i < 200; i++)
                tokenMap.remove(MD5.getMD5(tokenInfos.get(i).getId()));
        }
        tokenMap.put(token, tokenInfo);
    }

    public static TokenInfo pop(String token){
        try {
            TokenInfo tokenInfo = tokenMap.get(token);
            tokenMap.remove(token);
            return tokenInfo;
        }catch (Exception e){
            return null;
        }
    }

    /**
     * MD5值
     * @param tocken
     * @return
     */
    public static TokenInfo get(String tocken){
        return tokenMap.get(tocken);
    }

    private static void sort(List<TokenInfo> tokenInfos){
        if(tokenInfos != null)
        quickSort(tokenInfos, 0, tokenInfos.size() - 1);
    }

    private static void quickSort(List<TokenInfo> tokenInfos, int left, int right){
        if(right > left && left >= 0 && right < tokenInfos.size()){
            int p = position(tokenInfos, left, right);
            quickSort(tokenInfos, left,p - 1);
            quickSort(tokenInfos, p + 1, right);
        }
    }

    private static int position(List<TokenInfo> tokenInfos, int left, int right){
        TokenInfo p = tokenInfos.get(left);

        while(left < right){
            while (tokenInfos.get(right).getLastTime().after(p.getLastTime()) && right > left)
                --right;
            tokenInfos.set(left, tokenInfos.get(right));

            while (tokenInfos.get(left).getLastTime().before(p.getLastTime()) && left < right)
                ++left;
            tokenInfos.set(right, tokenInfos.get(left));
        }

        int piv = left;
        tokenInfos.set(left, p);
        return piv;
    }

    public static void main(String[] args) {
        long t0 = System.currentTimeMillis();
        List<TokenInfo> tokenInfos = new ArrayList<>();
        for(int i = 0; i < 100000; i++){
            Date d = new Date(Math.abs(new Random().nextLong()));
            TokenInfo t = new TokenInfo();
            t.setLastTime(d);
            tokenInfos.add(t);
        }
        long t1 = System.currentTimeMillis();
        sort(tokenInfos);
        long t2 = System.currentTimeMillis();
        System.out.println(t1-t0);
        System.out.println(t2-t1);
//        tokenInfos.forEach(o->{
//            System.out.println(o.getLastTime().toGMTString());
//        });

    }




}
