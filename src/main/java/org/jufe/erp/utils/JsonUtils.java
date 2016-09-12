package org.jufe.erp.utils;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raomengnan on 16-9-12.
 */
public class JsonUtils {

    public static <T> T jsonToBean(String jsonStr, Class<T> clazz){
        Gson gson = new Gson();
        return gson.fromJson(jsonStr, clazz);
    }

    public static List jsonToList(String jsonStr){
        List l = null;
        try {
            l = jsonToBean(jsonStr, List.class);
        }catch (Exception e){
            l = new ArrayList();
            l.add(jsonStr);
        }
        return l;
    }

    public static void main(String[] args) {
//        String str = "['test', 'test2', 'test3']";
        String str = "['test']";
        List<String> test = jsonToList(str);
        test.forEach(o->{
            System.out.println(o);
        });
    }
}
