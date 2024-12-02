package com.ruoyi.common.core.domain.entity;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 微信用户实体
 *
 * @author Ricky
 */
public class WxUser extends BaseEntity
{
  // 用户ID
  private Long id;

  // 真实姓名
  private String realName;

  // 昵称
  private String nickname;

  // openid
  private String openId;

  // session_key
  private String sessionKey;

  // 性别：0 - 未知, 1 - 男, 2 - 女
  private int gender;

  // 头像URL
  private String avatarUrl;

  // 城市
  private String city;

  // 省份
  private String province;

  // 国家
  private String country;

  // 用户所在的语言（如中文、英文）
  private String language;

  // 用户手机号
  private String phoneNumber;

  // 用户身份证号
  private String idCardNumber;

  // 用户邮箱地址
  private String email;

  // 用户所在的地区（如 "广东"）
  private String region;

  // Getter 和 Setter 方法
  public Long getId() {
	return id;
  }

  public void setId(Long id) {
	this.id = id;
  }

  public String getRealName() {
	return realName;
  }

  public void setRealName(String realName) {
	this.realName = realName;
  }

  public String getNickname() {
	return nickname;
  }

  public void setNickname(String nickname) {
	this.nickname = nickname;
  }

  public String getOpenId() {
	return openId;
  }

  public void setOpenId(String openId) {
	this.openId = openId;
  }

  public int getGender() {
	return gender;
  }

  public void setGender(int gender) {
	this.gender = gender;
  }

  public String getAvatarUrl() {
	return avatarUrl;
  }

  public void setAvatarUrl(String avatarUrl) {
	this.avatarUrl = avatarUrl;
  }

  public String getCity() {
	return city;
  }

  public void setCity(String city) {
	this.city = city;
  }

  public String getProvince() {
	return province;
  }

  public void setProvince(String province) {
	this.province = province;
  }

  public String getCountry() {
	return country;
  }

  public void setCountry(String country) {
	this.country = country;
  }

  public String getLanguage() {
	return language;
  }

  public void setLanguage(String language) {
	this.language = language;
  }

  public String getPhoneNumber() {
	return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
  }

  public String getIdCardNumber() {
	return idCardNumber;
  }

  public void setIdCardNumber(String idCardNumber) {
	this.idCardNumber = idCardNumber;
  }

  public String getEmail() {
	return email;
  }

  public void setEmail(String email) {
	this.email = email;
  }

  public String getRegion() {
	return region;
  }

  public void setRegion(String region) {
	this.region = region;
  }

  public String getSessionKey() {
	return sessionKey;
  }

  public void setSessionKey(String sessionKey) {
	this.sessionKey = sessionKey;
  }
}