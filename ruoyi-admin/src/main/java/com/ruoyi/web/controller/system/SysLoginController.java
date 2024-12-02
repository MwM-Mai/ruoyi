package com.ruoyi.web.controller.system;

import java.util.List;
import java.util.Set;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.config.properties.WxProperties;
import com.ruoyi.common.core.domain.entity.WxUser;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.dto.WxLoginDto;
import com.ruoyi.system.service.WxLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysMenu;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginBody;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.service.SysLoginService;
import com.ruoyi.framework.web.service.SysPermissionService;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.system.service.ISysMenuService;

/**
 * 登录验证
 *
 * @author ruoyi
 */
@Slf4j
@Api(tags = "登录相关接口")
@RestController
public class SysLoginController {
  @Autowired
  private SysLoginService loginService;

  @Autowired
  private ISysMenuService menuService;

  @Autowired
  private SysPermissionService permissionService;

  @Autowired
  private TokenService tokenService;

  @Autowired
  private WxProperties wxProperties;

  @Autowired
  private WxMaService wxMaService;

  @Autowired
  private WxLoginService wxLoginService;

  /**
   * 登录方法
   *
   * @param loginBody 登录信息
   * @return 结果
   */
  @PostMapping("/login")
  public AjaxResult login(@RequestBody LoginBody loginBody) {
	AjaxResult ajax = AjaxResult.success();
	// 生成令牌
	String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
			loginBody.getUuid());
	ajax.put(Constants.TOKEN, token);
	return ajax;
  }

  /**
   * 小程序登录
   *
   * @param wxLoginDto 小程序登录信息
   * @return
   */
  @ApiOperation("微信小程序登录接口")
  @PostMapping("/api/wx/login")
  public AjaxResult login(@RequestBody WxLoginDto wxLoginDto) {
	log.info("微信用户信息, {}", wxLoginDto);
	String _prefix = "【小程序登录】";
	String appId = wxProperties.getConfigs().get(0).getAppid();
	WxMaJscode2SessionResult sessionResult;
	WxMaPhoneNumberInfo phoneNoInfo;

	if (StringUtils.isEmpty(appId)) {
	  return AjaxResult.error("appId为空");
	}
	// 用户登录凭证（有效期五分钟）
	if (StringUtils.isEmpty(wxLoginDto.getCode())) {
	  return AjaxResult.error("登录凭证不能为空");
	}

	try {
	  // 从微信服务器获取用户的会话信息
	  sessionResult = wxMaService.getUserService().getSessionInfo(wxLoginDto.getCode());
	  // 获取手机号
	  phoneNoInfo = wxMaService.getUserService().getNewPhoneNoInfo(wxLoginDto.getPhoneInfo().getCode());
	} catch (Exception e) {
	  throw new AuthenticationServiceException("获取微信用户信息失败!");
	}

	// 获取手机号
	String phoneNumber = phoneNoInfo.getPhoneNumber();
	// openid
	String openId = sessionResult.getOpenid();
	// session_key
	String sessionKey = sessionResult.getSessionKey();

	if (StringUtils.isEmpty(openId)) {
	  return AjaxResult.error("登录失败，无效的登录凭证");
	}

	JSONObject res = new JSONObject();
	// 生成令牌
	String token = loginService.wxLogin(openId, sessionKey, phoneNumber);
	res.put(Constants.TOKEN, token);
	log.info("{}生成的token：{}", _prefix, token);
	return AjaxResult.success().put("data", res);
  }

  /**
   * 获取用户信息
   *
   * @return 用户信息
   */
  @GetMapping("getInfo")
  public AjaxResult getInfo() {
	LoginUser loginUser = SecurityUtils.getLoginUser();
	SysUser user = loginUser.getUser();
	// 角色集合
	Set<String> roles = permissionService.getRolePermission(user);
	// 权限集合
	Set<String> permissions = permissionService.getMenuPermission(user);
	if (!loginUser.getPermissions().equals(permissions)) {
	  loginUser.setPermissions(permissions);
	  tokenService.refreshToken(loginUser);
	}
	AjaxResult ajax = AjaxResult.success();
	ajax.put("user", user);
	ajax.put("roles", roles);
	ajax.put("permissions", permissions);
	return ajax;
  }

  /**
   * 获取路由信息
   *
   * @return 路由信息
   */
  @GetMapping("getRouters")
  public AjaxResult getRouters() {
	Long userId = SecurityUtils.getUserId();
	List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
	return AjaxResult.success(menuService.buildMenus(menus));
  }
}
