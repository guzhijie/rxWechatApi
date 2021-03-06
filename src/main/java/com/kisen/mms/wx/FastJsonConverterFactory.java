package com.kisen.mms.wx;

import com.alibaba.fastjson.JSONObject;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2019/12/25
 */
public class FastJsonConverterFactory extends Converter.Factory {
  private static final MediaType MEDIA_TYPE = MediaType.get("application/json; charset=UTF-8");

  private FastJsonConverterFactory() {}

  @Override
  public Converter<ResponseBody, ?> responseBodyConverter(
      final Type type, Annotation[] annotations, Retrofit retrofit) {
    return responseBody -> JSONObject.parseObject(responseBody.string(), type);
  }

  @Override
  public Converter<?, RequestBody> requestBodyConverter(
      Type type,
      Annotation[] parameterAnnotations,
      Annotation[] methodAnnotations,
      Retrofit retrofit) {
    return o -> RequestBody.create(MEDIA_TYPE, JSONObject.toJSONString(o));
  }

  public static FastJsonConverterFactory create() {
    return new FastJsonConverterFactory();
  }
}
