package com.dmd;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TaoBaoUtil {

    public static String getCityByIpAddr(String ipAddr) {
        log.info("getCityByIpAddr - 根据IP定位. ipAddr={}", ipAddr);
        // 调用淘宝IP地址库API
        String restApi = "http://ip.taobao.com/service/getIpInfo.php";

        String returnStr = getResult(restApi, ipAddr);
        if (returnStr != null) {
            // 处理返回的省市区信息
            String[] temp = returnStr.split(",");

            String country = "";
            String area = "";
            String region = "";
            String city = "";
            String county = "";
            String isp = "";
            for (int i = 0; i < temp.length; i++) {
                switch (i) {
                    case 2:
                        country = (temp[i].split(":"))[1].replaceAll("\"", "");// 国家
                        log.info("country - 根据IP定位国家. country={}", country);
                        break;
                    case 3:
                        area = (temp[i].split(":"))[1].replaceAll("\"", "");// 地区
                        break;
                    case 4:
                        region = (temp[i].split(":"))[1].replaceAll("\"", "");// 省份
                        log.info("region - 根据IP省份. region={}", region);
                        break;
                    case 5:
                        city = (temp[i].split(":"))[1].replaceAll("\"", "");// 市区
                        log.info("city - 根据IP市区. city={}", city);
                        break;
                    case 6:
                        county = (temp[i].split(":"))[1].replaceAll("\"", "");// 县市
                        break;
                    case 7:
                        isp = (temp[i].split(":"))[1].replaceAll("\"", ""); // ISP公司
                        break;
                    default:
                        break;
                }
            }
            String address = country+region+city+isp;
            if(StringUtils.isBlank(address)){
                address = "未知";
            }
            return address;
        }
        return null;
    }

    private static String getResult(String urlStr, String ip) {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();// 新建连接实例
            connection.setConnectTimeout(5000);// 设置连接超时时间，单位毫秒
            connection.setReadTimeout(5000);// 设置读取数据超时时间，单位毫秒
            connection.setDoOutput(true);// 是否打开输出流 true|false
            connection.setDoInput(true);// 是否打开输入流true|false
            connection.setRequestMethod("POST");// 提交方法POST|GET
            connection.setUseCaches(false);// 是否缓存true|false
            connection.connect();// 打开连接端口
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());// 打开输出流往对端服务器写数据
            out.writeBytes("ip="+ip);// 写数据,也就是提交你的表单 name=xxx&pwd=xxx
            out.flush();// 刷新
            out.close();// 关闭输出流
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));// 往对端写完数据对端服务器返回数据
            // ,以BufferedReader流来读取
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();// 关闭连接
            }
        }
        return null;
    }
}
