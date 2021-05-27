package com.kisen.mms.wx.api.account;

import lombok.Getter;
import lombok.Setter;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2019/12/30
 */
@Setter
@Getter
public final class QRRet {
  private String ticket;
  private String url;
  private long expire_seconds;
}
