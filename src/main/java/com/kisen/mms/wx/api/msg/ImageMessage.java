package com.kisen.mms.wx.api.msg;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2019/12/24
 */
public class ImageMessage extends com.kisen.mms.wx.api.msg.Message<ImageMessage> {
    private ImageInfo image;

    public ImageMessage() {
        super(com.kisen.mms.wx.api.msg.MsgType.image);
    }

    public ImageInfo getImage() {
        return image;
    }

    public ImageMessage setImage(ImageInfo image) {
        this.image = image;
        return this;
    }

    public static class ImageInfo {
        private String media_id;

        public String getMedia_id() {
            return media_id;
        }

        public ImageInfo setMedia_id(String media_id) {
            this.media_id = media_id;
            return this;
        }
    }

}
