package com.kisen.mms.wx.push;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.FileNotFoundException;
import java.io.FileReader;

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

    @XmlJavaTypeAdapter(EventPushExtraInfo.XMLAdapter.class)
    @XmlElements(
            {
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

            }
    )
    private EventPushExtraInfo eventPushExtraInfo;

    public EventPushExtraInfo getEventPushExtraInfo() {
        return eventPushExtraInfo;
    }

    @XmlTransient
    public EventPushInfo setEventPushExtraInfo(EventPushExtraInfo eventPushExtraInfo) {
        this.eventPushExtraInfo = eventPushExtraInfo;
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
