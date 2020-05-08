package com.kisen.mms.wx.api.account;

import com.alibaba.fastjson.JSONObject;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2019/12/30
 */
public final class ActionInfo {
    private JSONObject scene;

    public ActionInfo() {
    }

    public ActionInfo(Long scene_id) {
        this.scene = new JSONObject();
        this.scene.put("scene_id", scene_id);
    }

    public ActionInfo(String scene_str) {
        this.scene = new JSONObject();
        this.scene.put("scene_str", scene_str);
    }

    public JSONObject getScene() {
        return scene;
    }

    public ActionInfo setScene(JSONObject scene) {
        this.scene = scene;
        return this;
    }
}
