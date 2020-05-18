package com.kisen.mms.wx.push;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2020/5/14
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Item {
    @XmlElement
    private int ArticleIdx;
    @XmlElement
    private String ArticleUrl;

    public int getArticleIdx() {
        return ArticleIdx;
    }

    public Item setArticleIdx(int articleIdx) {
        ArticleIdx = articleIdx;
        return this;
    }

    public String getArticleUrl() {
        return ArticleUrl;
    }

    public Item setArticleUrl(String articleUrl) {
        ArticleUrl = articleUrl;
        return this;
    }
}
