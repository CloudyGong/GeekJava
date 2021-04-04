package com.study.gdy.demo;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HttpClientApp {

    public static void main(String[] args) throws IOException {
        // 请求地址
        String url = "http://localhost:8801";
        // 创建默认的客户端实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建get请求实例
        HttpGet httpGet = new HttpGet(url);
        // 设置头部信息
//        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
        CloseableHttpResponse response = null;
        try {
            // 执行GET请求 返回响应实体
            response = httpClient.execute(httpGet);

            System.out.println(response.getStatusLine());

            // 获取响应码
            int statusCode = response.getStatusLine().getStatusCode();
            // 获取全部的请求头
            Header[] allHeaders = response.getAllHeaders();
            System.out.println("响应状态码：" + statusCode);
            for (int i = 0; i < allHeaders.length; i++) {
                System.out.println("全部的请求头：" + allHeaders[i]);
            }
            // 获取响应消息实体
            HttpEntity entity = response.getEntity();
            //打印输出网页的内容(可删除)
            if (entity != null) {
                // 推荐该办法打印内容
                System.out.println("响应的内容：" + EntityUtils.toString(entity));

//            // 也可以使用这个方法
//            InputStream inputStream = entity.getContent();
//            InputStreamReader streamReader = new InputStreamReader(inputStream);
//            int line;
//            line = streamReader.read();
//            while (line != -1) {
//                System.out.print((char) line);
//                line = streamReader.read();
//            }
            }
        } finally {
            // 关闭连接
            try {
                System.out.println("response: " + response);
                if (response != null) {
                    response.close();
                }
                httpGet.releaseConnection();
                httpClient.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
