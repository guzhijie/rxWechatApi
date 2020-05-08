package com.kisen.mms.wx.api.msg;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2019/12/26
 */
public class PrivateTemplateRet {
    private String template_id;
    private String title;
    private String primary_industry;
    private String deputy_industry;
    private String content;
    private String example;

    public String getTemplate_id() {
        return template_id;
    }

    public PrivateTemplateRet setTemplate_id(String template_id) {
        this.template_id = template_id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public PrivateTemplateRet setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getPrimary_industry() {
        return primary_industry;
    }

    public PrivateTemplateRet setPrimary_industry(String primary_industry) {
        this.primary_industry = primary_industry;
        return this;
    }

    public String getDeputy_industry() {
        return deputy_industry;
    }

    public PrivateTemplateRet setDeputy_industry(String deputy_industry) {
        this.deputy_industry = deputy_industry;
        return this;
    }

    public String getContent() {
        return content;
    }

    public PrivateTemplateRet setContent(String content) {
        this.content = content;
        return this;
    }

    public String getExample() {
        return example;
    }

    public PrivateTemplateRet setExample(String example) {
        this.example = example;
        return this;
    }
}
