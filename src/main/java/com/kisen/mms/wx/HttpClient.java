package com.kisen.mms.wx;

import com.kisen.mms.wx.api.WXServerApi;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import org.apache.log4j.Logger;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jaxb.JaxbConverterFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2019/12/24
 */
public class HttpClient {
    private static final Logger logger = Logger.getLogger(HttpClient.class);
    private static final Map<String, HttpClient> HTTP_CLIENT_MAP = new Hashtable<>(8);
    private final Retrofit m_retrofit;

    private HttpClient(String baseUrl) {
        m_retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(new OkHttpClient.Builder()
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .callTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS)
                        .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                            @Override
                            public void log(String message) {
                                logger.debug(message);
                            }
                        }).setLevel(HttpLoggingInterceptor.Level.BODY))
                        .build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JsonAndXmlConverterFactory.create(JsonConverterFactory.create(), JaxbConverterFactory.create()))
                .build();
    }

    public static HttpClient getInstance(String baseUrl) {
        HttpClient httpClient = HTTP_CLIENT_MAP.get(baseUrl);
        if (httpClient == null) {
            httpClient = new HttpClient(baseUrl);
            HTTP_CLIENT_MAP.put(baseUrl, httpClient);
        }
        return httpClient;
    }

    public <I> I createApi(Class<I> clazz) {
        return m_retrofit.create(clazz);
    }
}
