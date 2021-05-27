package com.kisen.mms.wx.api.msg;

import lombok.Getter;
import lombok.Setter;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2019/12/24
 */
@Setter
@Getter
public class TextMessage extends com.kisen.mms.wx.api.msg.Message<TextMessage> {
  private TextInfo text;

  public TextMessage() {
    super(com.kisen.mms.wx.api.msg.MsgType.text);
  }

  @Setter
  @Getter
  public static class TextInfo {
    private String content;
  }
}
