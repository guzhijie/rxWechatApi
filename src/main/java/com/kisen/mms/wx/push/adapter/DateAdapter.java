package com.kisen.mms.wx.push.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Date;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2020/3/27
 */
public class DateAdapter extends XmlAdapter<Long, Date> {
  @Override
  public Date unmarshal(Long v) throws Exception {
    return new Date(v * 1000);
  }

  @Override
  public Long marshal(Date v) throws Exception {
    return v.getTime();
  }
}
