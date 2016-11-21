package org.jufe.erp.utils;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by raomengnan on 16-11-19.
 * to write API-JS for myself
 */
public class ReadDHC {
    public static void main(String[] args) throws IOException {
        File f = new File("request.json");
        System.out.println(f.exists());
        JsonReader jsonReader = new JsonReader(new FileReader(f));
        Gson gson = new Gson();
        List<LinkedTreeMap> list = gson.fromJson(jsonReader, List.class);
        List<HRequest> hRequests = new ArrayList<>();
        for(LinkedTreeMap t:list){
            HRequest hRequest = new HRequest();

            List<LinkedTreeMap> headers = (List) t.get("headers");
            Map<String, String> header = new HashMap<>();
            for(LinkedTreeMap h:headers){
                String key = (String) h.get("name");
                String value = (String) h.get("value");
                if(key.equals("token"))
                    value = "";

                if(key.equals("Content-Type"))
                    key = "contentType";
                header.put(key, value);
            }
            hRequest.setHeaders(header);

            LinkedTreeMap methods = (LinkedTreeMap) t.get("method");
            String m = null;
            m = (String) methods.get("name");
            hRequest.setMethod(m);

            LinkedTreeMap uri = (LinkedTreeMap) t.get("uri");
            String path = (String) uri.get("path");
            path = path.substring(path.indexOf("/"), path.length());
            hRequest.setPath(path);

            String name = (String) t.get("name");
            hRequest.setName(name);

            LinkedTreeMap bodys = (LinkedTreeMap) t.get("body");
            String body = (String) bodys.get("textBody");
            for(LinkedTreeMap h: headers){
                if(h.get("name").equals("Content-Type") &&
                        h.get("value").equals("multipart/form-data")){
                    body = bodys.get("formBody").toString();
                    break;
                }

            }
            hRequest.setBody(body);
            hRequests.add(hRequest);
        }

        System.out.println(hRequests.size());
        hRequests.forEach(h-> System.out.println(h.getName()+":"+h.getBody()));

        String js_model =
                "function ${f-name}(${param}, onSuccess, onError){\n" +
                        "\t\t" + "${name}\n" +
                        "\t\t" + "${todo}\n" +
                        "\n"+
                        "\t\t" + "$.ajax({\n" +
                        "\t\t" + "\t\t url: domain + \"${url}\",\n" +
                        "\t\t" + "\t\t type: \"${type}\",\n" +
                        "${header}" +
                        "\t\t" + "\t\t success: onSuccess,\n" +
                        "\t\t" + "\t\t error: onError\n" +
                        "\t\t})\n" +
                "}";

        List<String> fcs = new ArrayList<>();
        for(HRequest hr: hRequests){
            String fcName = hr.getPath();
            fcName = fcName.substring(6, fcName.length());
            fcName = fcName.replaceAll("/", "_");
            fcName = fcName.replaceAll("-", "_");

            Map<String,String> header = hr.getHeaders();
            String head = "";
            String param = "data";
            for(String k: header.keySet()){
                if(k.equals("token")){
                    param = "data, token";
                    head += "\t\t" + "\t\t token: token,\n";
                    continue;
                }
                head += "\t\t" + "\t\t " + k +": \"" + header.get(k) + "\",\n";

            }

            if(hr.getBody() != null)
                head += "\t\t" + "\t\t data: " + "data,\n";


            String todo = hr.getBody() == null?
                    "":"//[参数示例] ------- \n\t\t//" + hr.getBody().replaceAll("\n","\n\t\t//");

            String newFc = js_model.replace("${todo}", todo)
                    .replace("${param}", param)
                    .replace("${header}", head)
                    .replace("${type}", hr.getMethod())
                    .replace("${f-name}", fcName)
                    .replace("${url}", hr.getPath())
                    .replace("${name}", "//" + hr.getName());
            fcs.add(newFc);
        }

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("API.js", true);
            fcs.forEach(fcn-> System.out.println(fcn));
            for (String fc : fcs)
                fileWriter.write(fc + "\n\n\n");
            fileWriter.flush();

        }finally {
            fileWriter.close();
        }


    }


}

class HRequest{
    Map<String, String> headers;
    String path;
    String name;
    String method;
    String body;

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
