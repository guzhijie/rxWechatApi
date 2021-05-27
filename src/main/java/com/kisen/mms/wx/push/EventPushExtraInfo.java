package com.kisen.mms.wx.push;

import com.sun.org.apache.xerces.internal.dom.ElementNSImpl;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2020/5/15
 */
@Setter
@Getter
@XmlAccessorType(XmlAccessType.PROPERTY)
public class EventPushExtraInfo {
  @XmlElement private String EventKey; // 扫描带参数二维码事件
  @XmlElement private String Ticket; // 扫描带参数二维码事件
  @XmlElement private Float Latitude; // 上报地理位置事件
  @XmlElement private Float Longitude; // 上报地理位置事件
  @XmlElement private Float Precision; // 上报地理位置事件
  @XmlElement private Integer FilterCount;
  @XmlElement private Integer TotalCount;
  @XmlElement private Integer SentCount;
  @XmlElement private Integer ErrorCount;

  @XmlElement(name = "CopyrightCheckResult")
  private CopyrightCheckResult copyrightCheckResult;

  @XmlElement(name = "ArticleUrlResult")
  private ArticleUrlResult articleUrlResult;

  public static class XMLAdapter extends XmlAdapter<Object, EventPushExtraInfo> {
    private final EventPushExtraInfo m_extraInfo = new EventPushExtraInfo();

    @Override
    public EventPushExtraInfo unmarshal(Object v) throws Exception {
      if (v instanceof ElementNSImpl) {
        ElementNSImpl elementNS = (ElementNSImpl) v;
        String localName = elementNS.getLocalName();
        String value = elementNS.getTextContent();
        switch (localName) {
          case "EventKey":
            m_extraInfo.setEventKey(value);
            break;
          case "Ticket":
            m_extraInfo.setTicket(value);
            break;
          case "Latitude":
            m_extraInfo.setLatitude(Float.valueOf(value));
            break;
          case "Longitude":
            m_extraInfo.setLongitude(Float.valueOf(value));
            break;
          case "Precision":
            m_extraInfo.setPrecision(Float.valueOf(value));
            break;
          case "FilterCount":
            m_extraInfo.setFilterCount(Integer.valueOf(value));
            break;
          case "TotalCount":
            m_extraInfo.setTotalCount(Integer.valueOf(value));
            break;
          case "SentCount":
            m_extraInfo.setSentCount(Integer.valueOf(value));
            break;
          case "ErrorCount":
            m_extraInfo.setErrorCount(Integer.valueOf(value));
            break;
          default:
            break;
        }
      } else if (v instanceof CopyrightCheckResult) {
        CopyrightCheckResult result = (CopyrightCheckResult) v;
        m_extraInfo.setCopyrightCheckResult(result);
      } else if (v instanceof ArticleUrlResult) {
        ArticleUrlResult result = (ArticleUrlResult) v;
        m_extraInfo.setArticleUrlResult(result);
      }
      return m_extraInfo;
    }

    @Override
    public Object marshal(EventPushExtraInfo v) throws Exception {
      return null;
    }
  }
}
