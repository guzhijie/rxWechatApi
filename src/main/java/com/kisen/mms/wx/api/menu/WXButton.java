package com.kisen.mms.wx.api.menu;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2020/4/1
 */
@Setter
@Getter
public class WXButton {
    public static class Adapter implements ObjectDeserializer, ObjectSerializer {

        @Override
        public List<WXButton> deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
            JSONArray jsonArray = parser.parseObject().getJSONArray("list");
            return jsonArray.toJavaList(WXButton.class);
        }

        @Override
        public int getFastMatchToken() {
            return 0;
        }

        @Override
        public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
            List<WXButton> list = (List<WXButton>) object;
            if (list != null && !list.isEmpty()) {
                serializer.write(object);
            }

        }
    }

    private WXButtonType type;
    private String name;
    private String value;
    private String url;
    private String key;
    private String media_id;
    private String pagepath; /*小程序相关*/
    private String appid;    /*小程序相关*/
    @JSONField(name = "sub_button", deserializeUsing = Adapter.class, serializeUsing = Adapter.class)
    private List<WXButton> children;
}
