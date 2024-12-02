package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.entity.WxUser;

public interface WxLoginService {
  /**
   * 根据openId查询用户
   * @param openId
   * @return
   */
	public WxUser getWxUserByOpenId(String openId);

  /**
   * 新增用户
   * @param wxUser
   */
  void insertWxUser(WxUser wxUser);
}
