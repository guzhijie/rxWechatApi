package com.kisen.mms.wx;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2019/11/7 0007
 */
public final class JsonAndXmlConverterFactory extends Converter.Factory {
    private final Converter.Factory jsonFactory;
    private final Converter.Factory xmlFactory;

    private JsonAndXmlConverterFactory(Converter.Factory jsonFactory, Converter.Factory xmlFactory) {
        this.jsonFactory = jsonFactory;
        this.xmlFactory = xmlFactory;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        for (Annotation annotation : annotations) {
            if (annotation instanceof Json) {
                return jsonFactory.responseBodyConverter(type, annotations, retrofit);
            } else if (annotation instanceof Xml) {
                return xmlFactory.responseBodyConverter(type, annotations, retrofit);
            }
        }
        return null;
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        for (Annotation annotation : methodAnnotations) {
            if (annotation instanceof Json) {
                return jsonFactory.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
            } else if (annotation instanceof Xml) {
                return xmlFactory.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
            }
        }
        return null;
    }

    @Override
    public Converter<?, String> stringConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (type == Date.class) {
            return (Converter<Date, String>) value -> {
                for (Annotation annotation : annotations) {
                    if (annotation instanceof Format) {
                        Format format = (Format) annotation;
                        return new SimpleDateFormat(format.pattern()).format(value);
                    }
                }
                return value.toString();
            };
        } else if (type == Number.class) {
            return (Converter<Number, String>) value -> {
                for (Annotation annotation : annotations) {
                    if (annotation instanceof Format) {
                        Format format = (Format) annotation;
                        return new DecimalFormat(format.pattern()).format(value);
                    }
                }
                return value.toString();
            };
        } else {
            return super.stringConverter(type, annotations, retrofit);
        }

    }

    public static JsonAndXmlConverterFactory create(Converter.Factory jsonFactory, Converter.Factory xmlFactory) {
        return new JsonAndXmlConverterFactory(jsonFactory, xmlFactory);
    }

    @Retention(RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Json {
    }

    @Retention(RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Xml {
    }

    @Retention(RUNTIME)
    @Target(ElementType.PARAMETER)
    public @interface Format {
        String pattern();
    }
}
