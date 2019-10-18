package com.dmd.sms;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * <p>
 *
 * </p>
 *
 * @author 王海成
 * @since
 */
public class SmsCodeUtil {
    private static ObjectMapper objectMapper=new ObjectMapper();
    final static String urlStr = "http://api.mix2.zthysms.com/v2/sendSms";
    public static SmsCodeResponse sendMsg(SmsCode smsCode) throws IOException{
        String body = "";
        //创建httpclient对象
        CloseableHttpClient client = HttpClients.createDefault();
        //创建post方式请求对象
        HttpPost httpPost = new HttpPost(urlStr);

        //装填参数
        StringEntity s = new StringEntity(objectMapper.writeValueAsString(smsCode), "utf-8");
        s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
                "application/json"));
        //设置参数到请求对象中
        httpPost.setEntity(s);
        System.out.println("请求地址："+urlStr);

        //设置header信息
        //指定报文头【Content-type】、【User-Agent】
        httpPost.setHeader("Content-type", "application/json");

        //执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = client.execute(httpPost);
        //获取结果实体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            //按指定编码转换结果实体为String类型
            body = EntityUtils.toString(entity, "utf-8");
        }
        //关闭资源
        EntityUtils.consume(entity);
        //释放链接
        response.close();
        return objectMapper.readValue(body,SmsCodeResponse.class);
    }
}
