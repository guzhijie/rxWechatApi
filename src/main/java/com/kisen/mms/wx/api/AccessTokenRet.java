package com.kisen.mms.wx.api;

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
public class AccessTokenRet {
  private String access_token;
  private int expires_in;
}
