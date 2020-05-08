package com.kisen.mms.wx.api.msg;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2019/12/26
 */
public class TemplateMessage {
    private final Map<String, ValueAndColor> data = new HashMap<>();
    private String touser;
    private String template_id;
    private String url;
    private MiniProgram miniprogram;
    private String color;

    private TemplateMessage() {
    }

    public String getTouser() {
        return touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public String getUrl() {
        return url;
    }

    public MiniProgram getMiniprogram() {
        return miniprogram;
    }

    public Map<String, ValueAndColor> getData() {
        return data;
    }

    public String getColor() {
        return color;
    }

    public static class Builder {
        private TemplateMessage m_templateMessage = new TemplateMessage();

        public Builder setTouser(String touser) {
            m_templateMessage.touser = touser;
            return this;
        }

        public Builder setTemplate_id(String template_id) {
            m_templateMessage.template_id = template_id;
            return this;
        }

        public Builder setUrl(String url) {
            m_templateMessage.url = url;
            return this;
        }

        public Builder setMiniprogram(MiniProgram miniprogram) {
            m_templateMessage.miniprogram = miniprogram;
            return this;
        }

        public Builder setColor(String color) {
            m_templateMessage.color = color;
            return this;
        }

        public Builder setFirst(String first) {
            m_templateMessage.data.put("first", new ValueAndColor().setColor("#173177").setValue(first));
            return this;
        }

        public Builder setRemark(String remark) {
            m_templateMessage.data.put("remark", new ValueAndColor().setColor("#173177").setValue(remark));
            return this;
        }

        public Builder setKeyValues(Map<String, String> keyValues) {
            for (Map.Entry<String, String> entry : keyValues.entrySet()) {
                setKeyValue(entry.getKey(), entry.getValue());
            }
            return this;
        }

        public Builder setKeyValue(String key, String value) {
            m_templateMessage.data.put(key, new ValueAndColor().setColor("#173177").setValue(value));
            return this;
        }

        public TemplateMessage build() {
            return this.m_templateMessage;
        }
    }

    public static class MiniProgram {
        private String appid;
        private String pagepath;

        public String getAppid() {
            return appid;
        }

        public MiniProgram setAppid(String appid) {
            this.appid = appid;
            return this;
        }

        public String getPagepath() {
            return pagepath;
        }

        public MiniProgram setPagepath(String pagepath) {
            this.pagepath = pagepath;
            return this;
        }
    }

    public static class ValueAndColor {
        private String value;
        private String color;

        public String getValue() {
            return value;
        }

        public ValueAndColor setValue(String value) {
            this.value = value;
            return this;
        }

        public String getColor() {
            return color;
        }

        public ValueAndColor setColor(String color) {
            this.color = color;
            return this;
        }
    }
}
