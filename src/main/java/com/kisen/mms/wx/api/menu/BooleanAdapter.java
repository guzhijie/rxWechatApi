package com.kisen.mms.wx.api.menu;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2020/4/1
 */
public class BooleanAdapter implements ObjectDeserializer, ObjectSerializer {
  @SuppressWarnings("unchecked")
  @Override
  public Boolean deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
    Number n = parser.lexer.integerValue();
    return (null == n) ? null : n.intValue() != 0;
  }

  @Override
  public int getFastMatchToken() {
    return 0;
  }

  @Override
  public void write(
      JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features)
      throws IOException {}
}
