package com.kisen.mms.wx.api.data;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2019/12/31
 */
@Setter
@Getter
public class DateInfo {
  @JSONField(format = "yyyy-MM-dd")
  private Date begin_date;

  @JSONField(format = "yyyy-MM-dd")
  private Date end_date;
}
