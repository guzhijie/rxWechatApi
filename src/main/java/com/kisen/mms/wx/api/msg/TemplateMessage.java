package com.kisen.mms.wx.api.msg;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2019/12/26
 */
@Setter
@Getter
public class TemplateMessage {
  private final Map<String, ValueAndColor> data = new HashMap<>();
  private String touser;
  private String template_id;
  private String url;
  private MiniProgram miniprogram;
  private String color;

  private TemplateMessage() {}

  @Setter
  @Getter
  public static class MiniProgram {
    private String appid;
    private String pagepath;
  }

  @Setter
  @Getter
  public static class ValueAndColor {
    private String value;
    private String color;
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
      ValueAndColor valueAndColor = new ValueAndColor();
      valueAndColor.setColor("#173177");
      valueAndColor.setValue(first);
      m_templateMessage.data.put("first", valueAndColor);
      return this;
    }

    public Builder setRemark(String remark) {
      ValueAndColor valueAndColor = new ValueAndColor();
      valueAndColor.setColor("#173177");
      valueAndColor.setValue(remark);
      m_templateMessage.data.put("remark", valueAndColor);
      return this;
    }

    public Builder setKeyValues(Map<String, String> keyValues) {
      for (Map.Entry<String, String> entry : keyValues.entrySet()) {
        setKeyValue(entry.getKey(), entry.getValue());
      }
      return this;
    }

    public Builder setKeyValue(String key, String value) {
      ValueAndColor valueAndColor = new ValueAndColor();
      valueAndColor.setColor("#173177");
      valueAndColor.setValue(value);
      m_templateMessage.data.put(key, valueAndColor);
      return this;
    }

    public TemplateMessage build() {
      return m_templateMessage;
    }
  }
}
