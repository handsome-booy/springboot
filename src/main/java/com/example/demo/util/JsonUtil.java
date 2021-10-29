package com.example.demo.util;

import com.example.demo.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class JsonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        //对象所有的字段都列入
        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.ALWAYS);
        //取消默认转换timestamps形式
        objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
        //忽略空Bean转json的错误
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
        //所有日期格式调整为以下的格式
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));


        //忽略在json字符串中存在，但是在java对象中不存在对应属性的情况
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> String obj2String(T obj) {
        if(obj == null){
            return null;
        }
        try{
            return obj instanceof  String ? (String)obj : objectMapper.writeValueAsString(obj);
        }  catch (Exception e) {
            log.error("error",e);
            return null;
        }
    }

    public static <T> String obj2StringPretty(T obj) {
        if(obj == null){
            return null;
        }
        try{
            return obj instanceof  String ? (String)obj : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        }  catch (Exception e) {
            log.error("error",e);
            return null;
        }
    }

    public static <T> T string2Obj(String str,Class<T> clazz){
        if(str == null || clazz == null){
            return null;
        }

        try {
            return clazz.equals(String.class) ? (T)str : objectMapper.readValue(str,clazz);
        } catch (Exception e) {
            log.error("error",e);
            return null;
        }
    }


    //当有多个类型时，使用该方法：如List<User>
    public static <T> T string2Obj(String str, TypeReference<T> typeReference){
        if(str == null || typeReference == null){
            return null;
        }
        try {
            return (T)(typeReference.getType().equals(String.class) ? str : objectMapper.readValue(str,typeReference));
        } catch (Exception e) {
            log.error("error",e);
            return null;
        }
    }

    //当有多个类型时，使用该方法：如List<User>
    public static <T> T string2Obj(String str,Class<?> collctionClass,Class<?>... elementClasses){
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collctionClass,elementClasses);
        try{
            return objectMapper.readValue(str,javaType);
        } catch (Exception e){
            log.warn("error",e);
            return null;
        }
    }

    public static void main(String[] args) {
        User u1 = new User();
        u1.setId(1);
        u1.setUsername("xxj");
        User u2 = new User();
        u1.setId(2);
        u1.setUsername("xxj2");
        String u1json = JsonUtil.obj2String(u1);
        System.out.println(u1json);
        String u1jsonpretty = JsonUtil.obj2StringPretty(u1);
        System.out.println(u1jsonpretty);
        List<User> list = new LinkedList<>();
        list.add(u1);
        list.add(u2);
        String listUser = JsonUtil.obj2String(list);
        System.out.println(listUser);
        List<User> resList = JsonUtil.string2Obj(listUser,new TypeReference<List<User>>(){});
        String listUser2 = JsonUtil.obj2String(resList);
        System.out.println(listUser2);
        List<User> resList2 = JsonUtil.string2Obj(listUser2,List.class,User.class);
        String listUser3 = JsonUtil.obj2String(resList2);
        System.out.println(listUser3);
    }
}