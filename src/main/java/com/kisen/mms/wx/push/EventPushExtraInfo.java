package com.kisen.mms.wx.push;

import com.sun.org.apache.xerces.internal.dom.ElementNSImpl;
import com.sun.org.apache.xerces.internal.dom.ParentNode;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2020/5/15
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
public class EventPushExtraInfo {
    @XmlElement
    private String EventKey;//扫描带参数二维码事件
    @XmlElement
    private String Ticket; //扫描带参数二维码事件
    @XmlElement
    private Float Latitude;//上报地理位置事件
    @XmlElement
    private Float Longitude;//上报地理位置事件
    @XmlElement
    private Float Precision;//上报地理位置事件
    @XmlElement
    private Integer FilterCount;
    @XmlElement
    private Integer TotalCount;
    @XmlElement
    private Integer SentCount;
    @XmlElement
    private Integer ErrorCount;
    @XmlElement(name = "CopyrightCheckResult")
    private CopyrightCheckResult copyrightCheckResult ;
    @XmlElement(name = "ArticleUrlResult")
    private ArticleUrlResult articleUrlResult;

    public String getEventKey() {
        return EventKey;
    }

    public EventPushExtraInfo setEventKey(String eventKey) {
        EventKey = eventKey;
        return this;
    }

    public String getTicket() {
        return Ticket;
    }

    public EventPushExtraInfo setTicket(String ticket) {
        Ticket = ticket;
        return this;
    }

    public Float getLatitude() {
        return Latitude;
    }

    public EventPushExtraInfo setLatitude(Float latitude) {
        Latitude = latitude;
        return this;
    }

    public Float getLongitude() {
        return Longitude;
    }

    public EventPushExtraInfo setLongitude(Float longitude) {
        Longitude = longitude;
        return this;
    }

    public Float getPrecision() {
        return Precision;
    }

    public EventPushExtraInfo setPrecision(Float precision) {
        Precision = precision;
        return this;
    }

    public Integer getFilterCount() {
        return FilterCount;
    }

    public EventPushExtraInfo setFilterCount(Integer filterCount) {
        FilterCount = filterCount;
        return this;
    }

    public Integer getTotalCount() {
        return TotalCount;
    }

    public EventPushExtraInfo setTotalCount(Integer totalCount) {
        TotalCount = totalCount;
        return this;
    }

    public Integer getSentCount() {
        return SentCount;
    }

    public EventPushExtraInfo setSentCount(Integer sentCount) {
        SentCount = sentCount;
        return this;
    }

    public Integer getErrorCount() {
        return ErrorCount;
    }

    public EventPushExtraInfo setErrorCount(Integer errorCount) {
        ErrorCount = errorCount;
        return this;
    }

    public CopyrightCheckResult getCopyrightCheckResult() {
        return copyrightCheckResult;
    }

    @XmlTransient
    public EventPushExtraInfo setCopyrightCheckResult(CopyrightCheckResult copyrightCheckResult) {
        this.copyrightCheckResult = copyrightCheckResult;
        return this;
    }

    public ArticleUrlResult getArticleUrlResult() {
        return articleUrlResult;
    }

    @XmlTransient
    public EventPushExtraInfo setArticleUrlResult(ArticleUrlResult articleUrlResult) {
        this.articleUrlResult = articleUrlResult;
        return this;
    }


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
