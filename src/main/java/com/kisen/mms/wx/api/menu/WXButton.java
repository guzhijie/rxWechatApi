package com.kisen.mms.wx.api.menu;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.kisen.mms.wx.api.WXResult;
import com.kisen.mms.wx.api.WXServerApiWrapper;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2020/4/1
 */
public class WXButton {
    public static class Adapter implements ObjectDeserializer, ObjectSerializer {

        @SuppressWarnings({"unchecked", "AlibabaClassNamingShouldBeCamel"})
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

    public String getKey() {
        return key;
    }

    public WXButton setKey(String key) {
        this.key = key;
        return this;
    }

    public String getMedia_id() {
        return media_id;
    }

    public WXButton setMedia_id(String media_id) {
        this.media_id = media_id;
        return this;
    }

    public String getPagepath() {
        return pagepath;
    }

    public WXButton setPagepath(String pagepath) {
        this.pagepath = pagepath;
        return this;
    }

    public String getAppid() {
        return appid;
    }

    public WXButton setAppid(String appid) {
        this.appid = appid;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public WXButton setUrl(String url) {
        this.url = url;
        return this;
    }

    public List<WXButton> getChildren() {
        return children;
    }

    public WXButton setChildren(List<WXButton> children) {
        this.children = children;
        return this;
    }

    public WXButtonType getType() {
        return type;
    }

    public WXButton setType(WXButtonType type) {
        this.type = type;
        return this;
    }

    public String getName() {
        return name;
    }

    public WXButton setName(String name) {
        this.name = name;
        return this;
    }

    public String getValue() {
        return value;
    }

    public WXButton setValue(String value) {
        this.value = value;
        return this;
    }
}
