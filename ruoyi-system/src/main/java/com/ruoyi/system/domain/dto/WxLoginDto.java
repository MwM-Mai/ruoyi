package com.ruoyi.system.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * C端用户登录
 */
@Data
public class WxLoginDto implements Serializable {
  private String code;

  private PhoneInfo phoneInfo;

  @Data
  public static class PhoneInfo  {
    private String code;
    private String iv;
    private String encryptedData;
    private String errMsg;
  }
}
