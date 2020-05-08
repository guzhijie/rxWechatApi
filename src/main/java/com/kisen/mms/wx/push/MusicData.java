package com.kisen.mms.wx.push;

import javax.xml.bind.annotation.*;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2020/3/27
 */
@XmlRootElement(name = "Music")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class MusicData {
    @XmlElement
    private String Title;
    @XmlElement
    private String Description;
    @XmlElement
    private String MusicUrl;
    @XmlElement
    private String HQMusicUrl;
    @XmlElement
    private String ThumbMediaId;

    public String getTitle() {
        return Title;
    }

    public MusicData setTitle(String title) {
        Title = title;
        return this;
    }

    public String getDescription() {
        return Description;
    }

    public MusicData setDescription(String description) {
        Description = description;
        return this;
    }

    public String getMusicUrl() {
        return MusicUrl;
    }

    public MusicData setMusicUrl(String musicUrl) {
        MusicUrl = musicUrl;
        return this;
    }

    public String getHQMusicUrl() {
        return HQMusicUrl;
    }

    public MusicData setHQMusicUrl(String HQMusicUrl) {
        this.HQMusicUrl = HQMusicUrl;
        return this;
    }

    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    public MusicData setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
        return this;
    }
}
