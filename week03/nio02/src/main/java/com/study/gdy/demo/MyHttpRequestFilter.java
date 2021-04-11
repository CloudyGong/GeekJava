package com.study.gdy.demo;

import io.github.kimmking.gateway.filter.HttpRequestFilter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public class MyHttpRequestFilter implements HttpRequestFilter {

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        String uri = fullRequest.uri();
        System.out.println("Original url before filter: " + uri);
        if (uri.startsWith("/happy")) {
            // 放过
        } else {
            throw new RuntimeException("I don't know this uri:" + uri);
        }
//        HttpHeaders headers = fullRequest.headers();
//        if (headers == null) {
//            headers = new DefaultHttpHeaders();
//        }
        fullRequest.headers().add("filter", "happy");
    }
}
