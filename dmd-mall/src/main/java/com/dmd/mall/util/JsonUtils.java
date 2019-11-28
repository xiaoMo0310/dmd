package com.dmd.mall.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONArray;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Json工具类
 */
public class JsonUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        mapper.setSerializationInclusion(Include.NON_NULL);
    }

    private JsonUtils() {
    }

    /**
     * json字符串转换为类
     */
    public static <T> T toBean(String json, Class<T> clazz) {
        try {
            T bean = (T) mapper.readValue(json, clazz);
            return bean;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static HashMap<String, Object> toBean(String json) {
        return toBean(json, HashMap.class);
    }

    @SuppressWarnings("unchecked")
    public static HashMap<String,String> toBeanStr(String json) {
        return toBean(json, HashMap.class);
    }

    @SuppressWarnings("unchecked")
    public static <T> T toBean(String json, TypeReference<T> tr){
        try {
            T bean = (T) mapper.readValue(json, tr);
            return bean;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 对象转换为json字符串
     */
    public static String toJson(Object bean) throws IOException {
        String json = null;
        JsonGenerator gen = null;
        StringWriter sw = new StringWriter();
        try {
            gen = new JsonFactory().createGenerator(sw);
            mapper.writeValue(gen, bean);
            json = sw.toString();
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                if (gen != null) {
                    gen.close();
                }
                if (sw != null) {
                    sw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return json;
    }

    @SuppressWarnings("unchecked")
    public static List<Object> toList(String json) {
        return toBean(json, ArrayList.class);
    }

    /**
     * 对象转换为Map
     */
    public static Map<String, Object> transBean2Map(Object obj) {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (!key.equals("class")) {
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    map.put(key, value);
                }
            }
        } catch (Exception e) {
            System.out.println("transBean2Map Error " + e);
        }
        return map;
    }

    /**
     * json字符串转换为List
     */
    public static <T> List<T>json2ListBean(String json,Class<T>cls){
        JSONArray jArray= JSONArray.fromObject(json);
        Collection<T> collection = JSONArray.toCollection(jArray, cls);
        List<T> list = new ArrayList<T>();
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            T bean = (T) it.next();
            list.add(bean);
        }
        return list;
    }
}