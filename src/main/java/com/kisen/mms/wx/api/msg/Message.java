package com.kisen.mms.wx.api.msg;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2019/12/24
 */
@Setter
@Getter
public abstract class Message<T extends Message<T>> {
  private final MsgType msgtype;
  private List<String> touser;

  protected Message(MsgType msgtype) {
    this.msgtype = msgtype;
  }
}
