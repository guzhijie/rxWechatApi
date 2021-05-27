package com.kisen.mms.wx.api.account;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2019/12/30
 */
@Setter
@Getter
public final class ActionInfo {
  private JSONObject scene;

  public ActionInfo() {}

  public ActionInfo(Long scene_id) {
    scene = new JSONObject();
    scene.put("scene_id", scene_id);
  }

  public ActionInfo(String scene_str) {
    scene = new JSONObject();
    scene.put("scene_str", scene_str);
  }
}
