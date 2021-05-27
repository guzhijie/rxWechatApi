package com.kisen.mms.wx.push;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2020/3/26
 */
@Setter
@Getter
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class EventPushInfo extends BasePushInfo {
  @XmlJavaTypeAdapter(EventType.EventTypeAdapter.class)
  private EventType Event;

  @XmlElement private Long MsgID;
  @XmlElement private String Status;

  @XmlJavaTypeAdapter(EventPushExtraInfo.XMLAdapter.class)
  @XmlElements({
    @XmlElement(name = "EventKey"),
    @XmlElement(name = "Ticket"),
    @XmlElement(name = "Latitude"),
    @XmlElement(name = "Longitude"),
    @XmlElement(name = "Precision"),
    @XmlElement(name = "FilterCount"),
    @XmlElement(name = "TotalCount"),
    @XmlElement(name = "SentCount"),
    @XmlElement(name = "ErrorCount"),
    @XmlElement(name = "CopyrightCheckResult", type = CopyrightCheckResult.class),
    @XmlElement(name = "ArticleUrlResult", type = ArticleUrlResult.class),
  })
  private EventPushExtraInfo eventPushExtraInfo;

  @XmlTransient
  public EventPushInfo setEventPushExtraInfo(EventPushExtraInfo eventPushExtraInfo) {
    this.eventPushExtraInfo = eventPushExtraInfo;
    return this;
  }
}
