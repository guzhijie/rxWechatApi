package com.kisen.mms.wx.push;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2020/3/27
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class MusicPushInfo extends BasePushInfo {
    @XmlElement
    private MusicData Music;

    public MusicData getMusic() {
        return Music;
    }

    public MusicPushInfo setMusic(MusicData music) {
        Music = music;
        return this;
    }
}
