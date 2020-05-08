package com.kisen.mms.wx.push;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2020/3/26
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class EventPushInfo extends BasePushInfo {
    @XmlJavaTypeAdapter(EventType.EventTypeAdapter.class)
    private EventType Event;
    @XmlElement
    private Long MsgID;
    @XmlElement
    private String Status;
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

    public Float getLatitude() {
        return Latitude;
    }

    public EventPushInfo setLatitude(Float latitude) {
        Latitude = latitude;
        return this;
    }

    public Float getLongitude() {
        return Longitude;
    }

    public EventPushInfo setLongitude(Float longitude) {
        Longitude = longitude;
        return this;
    }

    public Float getPrecision() {
        return Precision;
    }

    public EventPushInfo setPrecision(Float precision) {
        Precision = precision;
        return this;
    }

    public String getTicket() {
        return Ticket;
    }

    public EventPushInfo setTicket(String ticket) {
        Ticket = ticket;
        return this;
    }

    public String getEventKey() {
        return EventKey;
    }

    public EventPushInfo setEventKey(String eventKey) {
        EventKey = eventKey;
        return this;
    }

    public EventType getEvent() {
        return Event;
    }

    public EventPushInfo setEvent(EventType event) {
        Event = event;
        return this;
    }

    public Long getMsgID() {
        return MsgID;
    }

    public EventPushInfo setMsgID(Long msgID) {
        MsgID = msgID;
        return this;
    }

    public String getStatus() {
        return Status;
    }

    public EventPushInfo setStatus(String status) {
        Status = status;
        return this;
    }
}
