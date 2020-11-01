package com.kisen.mms.wx.api;

import lombok.Getter;
import lombok.Setter;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2020/1/8
 */
@Setter
@Getter
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
