package com.kisen.mms.wx;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.apache.log4j.Logger;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jaxb.JaxbConverterFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2019/12/24
 */
public class HttpClient {
  private static final Logger logger = Logger.getLogger(HttpClient.class);
  private static final Map<String, HttpClient> HTTP_CLIENT_MAP = new ConcurrentHashMap<>(8);
  private final Retrofit m_retrofit;

  private HttpClient(String baseUrl) {
    m_retrofit =
        new Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(
                new OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .callTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(
                        new HttpLoggingInterceptor(logger::debug)
                            .setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(
                JsonAndXmlConverterFactory.create(
                    FastJsonConverterFactory.create(), JaxbConverterFactory.create()))
            .build();
  }

  public static HttpClient getInstance(String baseUrl) {
    return HTTP_CLIENT_MAP.computeIfAbsent(baseUrl, HttpClient::new);
  }

  public <I> I createApi(Class<I> clazz) {
    return m_retrofit.create(clazz);
  }
}
