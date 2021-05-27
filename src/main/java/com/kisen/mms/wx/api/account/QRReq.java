package com.kisen.mms.wx.api.account;

import lombok.Builder;
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
@Builder
public final class QRReq {
  private Long expire_seconds;
  private String action_name;
  private ActionInfo action_info;
}
