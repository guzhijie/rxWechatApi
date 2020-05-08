package com.kisen.mms.wx.api;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2020/1/8
 */
public class FileDownloadInfo {
    public final int size;
    public final long total;
    public final byte[] data;

    public FileDownloadInfo(int size, long total, byte[] data) {
        this.size = size;
        this.total = total;
        this.data = data;
    }
}
