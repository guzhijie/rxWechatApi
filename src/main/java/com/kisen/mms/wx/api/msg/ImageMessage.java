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
public class ImageMessage extends com.kisen.mms.wx.api.msg.Message<ImageMessage> {
  private ImageInfo image;

  public ImageMessage() {
    super(com.kisen.mms.wx.api.msg.MsgType.image);
  }

  @Setter
  @Getter
  public static class ImageInfo {
    private String media_id;
  }
}
