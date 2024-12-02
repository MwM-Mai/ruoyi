package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.WxUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface WxLoginServiceMapper {

  /**
   * 根据openId查询用户
   * @param openId
   * @return
   */
  @Select("select real_name, nickname, open_id, session_key, gender, avatar_url, city, province, country, language, phone_number, id_card_number, email, region, create_time, update_time" +
		  " from dkd.wx_user where open_id = #{openId}")
  WxUser getWxUserByOpenId(String openId);

  void insert(WxUser wxUser);
}
