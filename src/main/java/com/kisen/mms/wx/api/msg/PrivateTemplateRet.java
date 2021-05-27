package com.kisen.mms.wx.api.msg;

import lombok.Getter;
import lombok.Setter;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2019/12/26
 */
@Setter
@Getter
public class PrivateTemplateRet {
  private String template_id;
  private String title;
  private String primary_industry;
  private String deputy_industry;
  private String content;
  private String example;
}
