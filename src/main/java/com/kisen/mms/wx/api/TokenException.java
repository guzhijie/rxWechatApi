package com.kisen.mms.wx.api;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class TokenException extends Exception {

  private int errcode;
}
