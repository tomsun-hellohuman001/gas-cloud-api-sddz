package com.sddz.gasstation.utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class JSONUtils {

    /**
     * java对象转字符串
     *
     * @param obj
     * @return
     */
    public static String toJsonWithGson(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    /**
     * json字符串转java对象
     *
     * @param json
     * @param typeOfT
     * @return
     */
    public static Object toObjectFromString(String json, Type typeOfT) {
        Gson gson = new Gson();
        Object obj = gson.fromJson(json, typeOfT);
        return obj;
    }

    /**
     * java复杂类型对象转字符串
     *
     * @param obj
     * @param type
     * @return
     */
    public static String toJsonWithGson(Object obj, Type type) {
        Gson gson = new Gson();
        return gson.toJson(obj, type);
    }

    /**
     * 非泛型List json 返回
     *
     * @param list
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static String getSimpleListJson(List list) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        String listToJson = gson.toJson(list);
        return listToJson;
    }

    /**
     * 非泛型 Map json 返回
     *
     * @param map
     * @return
     */
    public static String getSimpleMapJson(Map map) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        String listToJson = gson.toJson(map);
        return listToJson;
    }

    /**
     * 泛型约束List 转换
     *
     * @param list
     * @return
     */
    public static String getGenericList(List<?> list) {
        Type type = new TypeToken<List<?>>() {
        }.getType();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        return gson.toJson(list, type);
    }



    /**
     * 对象转Json
     *
     * @return
     */
    public static String getJAVABeanJSON(Object o) {
        // Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new
        // UtilDateSerializer()).registerTypeAdapter(Calendar.class, new
        // UtilCalendarSerializer()).registerTypeAdapter(GregorianCalendar.class,
        // new
        // UtilCalendarSerializer()).setDateFormat(DateFormat.LONG).setPrettyPrinting().create();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls()
                .create();

        String ObjtToJson = gson.toJson(o);
        return ObjtToJson;
    }

    /**
     * Json 转map 结构
     *
     * @param data
     * @return
     */
    public static Map<String, String> jsonToMap(String data) {
        GsonBuilder gb = new GsonBuilder();
        Gson g = gb.create();
        Map<String, String> map = g.fromJson(data,
                new TypeToken<Map<String, String>>() {
                }.getType());
        return map;
    }

    @SuppressWarnings("unused")
    private static class UtilDateSerializer implements JsonSerializer<Date>,
            JsonDeserializer<Date> {

        public JsonElement serialize(Date date, Type type,
                                     JsonSerializationContext context) {
            return new JsonPrimitive(date.getTime());
        }

        public Date deserialize(JsonElement element, Type type,
                                JsonDeserializationContext context) throws JsonParseException {
            return new Date(element.getAsJsonPrimitive().getAsLong());
        }

    }

    @SuppressWarnings("unused")
    private static class UtilCalendarSerializer implements
            JsonSerializer<Calendar>, JsonDeserializer<Calendar> {
        public JsonElement serialize(Calendar cal, Type type,
                                     JsonSerializationContext context) {
            return new JsonPrimitive(Long.valueOf(cal.getTimeInMillis()));
        }

        public Calendar deserialize(JsonElement element, Type type,
                                    JsonDeserializationContext context) throws JsonParseException {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(element.getAsJsonPrimitive().getAsLong());
            return cal;
        }
    }

    /**
     * java对象转字符串
     *
     * @param <T> 泛型占位符
     * @param obj T
     * @return String
     */
    public static <T> String toJson(T obj) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").enableComplexMapKeySerialization().create();
        return gson.toJson(obj);
    }


    /**
     * java对象转字符串
     *
     * @param obj           被转对象
     * @param dateFormatter 自定义日期格式
     * @return
     */
    public static <T> String toJson(T obj, String dateFormatter) {
        Gson gson = new GsonBuilder().setDateFormat(dateFormatter).enableComplexMapKeySerialization().create();
        return gson.toJson(obj);
    }

    /**
     * <p>
     * json字符串转java对象
     * </p>
     * <p/>
     * <p>
     * 例子1: 假设发序列化普通类Person
     * </p>
     * <p>
     * <code>
     * JsonUtils.fromJson("{id:1,name:'a'}", Person.class);
     * </code>
     * </p>
     * <p/>
     * <p>
     * 例子2: 假设反序列化List&lt;Person&gt;类
     * </p>
     * <p>
     * <code>
     * JsonUtils.fromJson("[{id:1,name:'a'},{id:2,name:'b'}]", new TypeToken&lt;List&lt;Person&gt;&gt;(){}.getType());
     * </code>
     * </p>
     *
     * @param <T>  泛型占位符
     * @param json String 字符串
     * @param type Type
     * @return T 转换后的结果对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T fromJson(String json, Type type) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        Object obj = gson.fromJson(json, type);
        return (T) obj;
    }

    /**
     * json字符串转java对象
     *
     * @param json       json 数据串
     * @param type       Gson type 类型
     * @param dateFormat 日期格式
     * @return T
     */
    @SuppressWarnings("unchecked")
    public static <T> T fromJson(String json, Type type, String dateFormat) {
        Gson gson = new GsonBuilder().setDateFormat(dateFormat).create();
        Object obj = gson.fromJson(json, type);
        return (T) obj;
    }

    /**
     * json 转 map
     *
     * @param json json 数据串
     * @return Map
     */
    public static Map json2Map(String json) {
        GsonBuilder gb = new GsonBuilder();
        Gson g = gb.create();
        Map<String, String> map = g.fromJson(json, new TypeToken<Map<String, Object>>() {
        }.getType());
        return map;
    }


    /**
     * 解析JSON为原始JsonElement
     *
     * @param json json串
     * @return JsonElement
     */
    public static JsonElement fromJson(String json) {
        return new JsonParser().parse(json);
    }


}

/**
 * JsonUtilTestSuper
 *
 * @author Elwin
 */
class JsonUtilTestSuper {
    List<JsonUtilTestA> lst;
}

/**
 * JsonUtilTestA
 *
 * @author Elwin
 */
class JsonUtilTestA {
    Integer id;
    String name;
    List<JsonUtilTestB> bs;
}

/**
 * JsonUtilTestA
 *
 * @author Elwin
 */
class JsonUtilTestB {
    String code;
}

