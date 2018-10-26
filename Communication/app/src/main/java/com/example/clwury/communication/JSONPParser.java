package com.example.clwury.communication;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.json.*;

public class JSONPParser {

    public static Map parseJSONP(String jsonp){  
          
        int startIndex = jsonp.indexOf("(");  
        int endIndex = jsonp.lastIndexOf(")");  
        String json = jsonp.substring(startIndex+1, endIndex);  
        //System.out.println(json);
        return JSON.parseObject(json);
    }
    public static void main(String[] args){  
          
        Map map = parseJSONP("callbak({'status':'1000','msg':'ok','data':[{'id':'290','msg':'asdadasdasdasdasd','date':'2018-10-23 01:30:00'}]})");

    }
    public static String parseJSONPStr(String jsonp){

        int startIndex = jsonp.indexOf("(");
        int endIndex = jsonp.lastIndexOf(")");
        String json = jsonp.substring(startIndex+1, endIndex);
        //System.out.println(json);

        return json;

    }
    public static JSONObject parseJSONObject(String str) {
        JSONObject ob=null;
        try{
            ob=new JSONObject(str);
        }catch (Exception e){

        }
        return  ob;
    }
    /*public static Object Msg(JSONObject js){
        Gson gson= new Gson();
        MsgJson msg=gson.fromJson(js,MsgJson.class);
    }*/
}  