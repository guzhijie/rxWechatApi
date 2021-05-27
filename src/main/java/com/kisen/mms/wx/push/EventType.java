package com.kisen.mms.wx.push;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2020/4/1
 */
public enum EventType {
  //
  subscribe,
  unsubscribe,
  SCAN,
  LOCATION,
  TEMPLATESENDJOBFINISH,
  CLICK,
  VIEW,
  qualification_verify_success,
  qualification_verify_fail,
  naming_verify_success,
  naming_verify_fail,
  annual_renew,
  verify_expired,
  MASSSENDJOBFINISH,
  ;

  public static class EventTypeAdapter extends XmlAdapter<String, EventType> {

    @Override
    public EventType unmarshal(String v) throws Exception {
      return EventType.valueOf(v);
    }

    @Override
    public String marshal(EventType v) throws Exception {
      return v.toString();
    }
  }
}
