package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.entity.WxUser;
import com.ruoyi.system.mapper.WxLoginServiceMapper;
import com.ruoyi.system.service.WxLoginService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxLoginServiceImpl implements WxLoginService {

  @Autowired
  private WxLoginServiceMapper wxLoginServiceMapper;

  /**
   * 根据openId查询用户
   * @param openId
   * @return
   */
  @Override
  public WxUser getWxUserByOpenId(String openId) {
	return wxLoginServiceMapper.getWxUserByOpenId(openId);
  }

  /**
   * 插入微信用户
   * @param wxUser
   */
  @Override
  public void insertWxUser(WxUser wxUser) {
    wxLoginServiceMapper.insert(wxUser);
  }
}
