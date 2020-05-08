package com.kisen.mms.wx.api.msg;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2019/12/24
 */
public class TextMessage extends com.kisen.mms.wx.api.msg.Message<TextMessage> {
    private TextInfo text;

    public TextMessage() {
        super(com.kisen.mms.wx.api.msg.MsgType.text);
    }

    public TextInfo getText() {
        return text;
    }

    public TextMessage setText(TextInfo text) {
        this.text = text;
        return this;
    }

    public static class TextInfo {
        private String content;

        public String getContent() {
            return content;
        }

        public TextInfo setContent(String content) {
            this.content = content;
            return this;
        }
    }

}
