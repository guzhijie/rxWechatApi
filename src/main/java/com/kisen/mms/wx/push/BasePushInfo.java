package com.kisen.mms.wx.push;


import com.kisen.mms.wx.push.adapter.DateAdapter;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2020/3/27
 */
@Setter
@Getter
@XmlAccessorType(XmlAccessType.PROPERTY)
public abstract class BasePushInfo {
    @XmlRootElement(name = "xml")
    @XmlAccessorType(XmlAccessType.PROPERTY)
    public static class BasicInfo extends BasePushInfo {

    }

    @XmlElement
    private String ToUserName;
    @XmlElement
    private String FromUserName;
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date CreateTime;
    @XmlJavaTypeAdapter(MSGType.MSGTypeAdapter.class)
    private MSGType MsgType;
}
