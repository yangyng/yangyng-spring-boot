package com.yangy.common.okhttp.utils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.util.CollectionUtils;

import java.util.Iterator;
import java.util.Map;

@Slf4j
public class OkHttpUtil {

    /**
     * GET 方式
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return
     */
    public static String get(String url, Map<String, String> params) {
        StringBuffer sb = new StringBuffer(url);
        if (!CollectionUtils.isEmpty(params)) {
            if (null != params && params.keySet().size() > 0) {
                boolean firstFlag = true;
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    if (firstFlag) {
                        sb.append("?" + entry.getKey() + "=" + entry.getValue());
                        firstFlag = false;
                    } else {
                        sb.append("&" + entry.getKey() + "=" + entry.getValue());
                    }
                }
            }
        }
        Request request = new Request.Builder().url(sb.toString()).build();
        return sendRequest(request);

    }

    /**
     * POST FORM方式
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return
     */
    public static String postForm(String url, Map<String, String> params) {
        FormBody.Builder builder = new FormBody.Builder();
        //添加参数
        if (params != null && params.keySet().size() > 0) {
            for (String key : params.keySet()) {
                builder.add(key, params.get(key));
            }
        }
        Request request = new Request.Builder().url(url).post(builder.build()).build();
        return sendRequest(request);
    }

    /**
     * POST 请求参数
     *
     * @param url        请求地址
     * @param jsonParams 请求的JSON串
     * @return
     */
    public static String postJson(String url, String jsonParams) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonParams);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        return sendRequest(request);
    }

    /**
     * POST 请求方式
     *
     * @param url 请求地址
     * @param xml 请求的XML
     * @return
     */
    public static String postXmlParams(String url, String xml) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/xml; charset=utf-8"), xml);
        Request request = new Request.Builder()
                .post(requestBody)
                .url(url)
                .build();
        return sendRequest(request);
    }

    /**
     * @param url    请求地址
     * @param params 请求参数
     * @return
     */
    public static String getForHeader(String url, Map<String, String> params) {
        StringBuffer sb = new StringBuffer(url);
        if (params != null && params.keySet().size() > 0) {
            boolean firstFlag = true;
            Iterator iterator = params.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry<String, String>) iterator.next();
                if (firstFlag) {
                    sb.append("?" + entry.getKey() + "=" + entry.getValue());
                    firstFlag = false;
                } else {
                    sb.append("&" + entry.getKey() + "=" + entry.getValue());
                }
            }
        }
        Request request = new Request.Builder()
                .addHeader("key", "value")
                .url(sb.toString())
                .build();
        return sendRequest(request);
    }

    /**
     * 发送请求
     *
     * @param request
     * @return
     */
    public static String sendRequest(Request request) {
        Response response = null;
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
            log.error("okhttp3 put error >> ex = {}", ExceptionUtils.getStackTrace(e));
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return null;
    }
}