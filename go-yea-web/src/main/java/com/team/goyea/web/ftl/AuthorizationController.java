package com.team.goyea.web.ftl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team.goyea.authorization.model.RoleInfo;
import com.team.goyea.authorization.model.UserInfo;
import com.team.goyea.authorization.model.entity.PartyRoleRelaEntity;
import com.team.goyea.authorization.model.pk.RoleInfoPK;
import com.team.goyea.authorization.model.pk.RolePermissionRelaPK;
import com.team.goyea.authorization.model.pk.UserInfoPK;
import com.yea.core.remote.AbstractEndpoint;
import com.yea.core.remote.promise.Promise;
import com.yea.core.remote.struct.CallAct;
import com.yea.core.shiro.model.UserPrincipal;

@Controller
public class AuthorizationController {
	@Autowired
	private AbstractEndpoint nettyClient;
	
	@RequestMapping("/authenticed.html")
	public String authenticed(ModelMap model, HttpServletRequest request) throws Throwable {
		// 如果登录失败从request中获取认证异常信息，shiroLoginFailure就是shiro异常类的全限定名
		// 根据shiro返回的异常类路径判断，抛出指定异常信息
		String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
		String message = "";
		if (exceptionClassName != null) {
			if (AccountException.class.getName().equals(exceptionClassName)) {
				message = ("提供用户名后再认证");
			} else if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
				message = ("查无此用户");
			} else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
				message = ("用户名/密码不正确");
			} else if (ExcessiveAttemptsException.class.getName().equals(exceptionClassName)){
				message = ("登录失败次数过多，请24小时后再登录");
			} else if (LockedAccountException.class.getName().equals(exceptionClassName)){
				message = ("用户账户已被锁定");
			} else {
				message = "登录遇到未知问题，请联系管理员";// 最终在异常处理器生成未知错误
			}
		}
		model.put("login_message", message);
		// 此方法不处理登陆成功（认证成功），shiro认证成功会自动跳转到上一个请求路径
		// 登陆失败还到login页面
		return "/login";
	}
	
	@RequestMapping("/authorization/role/query.html")
    public String queryRole(ModelMap model) throws Throwable {
		CallAct act = new CallAct();
		act.setActName("queryRoleAct");
		Promise<List<RoleInfo>> promise = nettyClient.send(act);
		List<RoleInfo> listRole = promise.awaitObject(10000);
		model.put("roles", listRole);
		
		return "authorization/roles";
	}
	
	@RequestMapping("/authorization/role/load.html")
    public String loadRole(ModelMap model, RoleInfoPK roleInfoPk) throws Throwable {
		RoleInfo roleInfo = null;
		if(roleInfoPk != null && roleInfoPk.getRoleId() != null) {
			CallAct act = new CallAct();
			act.setActName("loadRoleAct");
			Promise<RoleInfo> promise = nettyClient.send(act, roleInfoPk);
			roleInfo = promise.awaitObject(10000);
		} else {
			roleInfo = new RoleInfo();
		}
		
		Subject subject = SecurityUtils.getSubject();
		if (!subject.hasRole("超级管理员")) {
			if ("超级管理员".equals(roleInfo.getRoleInfoEntity().getRoleName())) {
				/*当登录用户未拥有超级管理员角色时，该用户不能更改超级管理员角色*/
				throw new Exception("用户["+((UserPrincipal)subject.getPrincipal()).getLoginName()+"]不是超级管理员，无权限操作");
			}
		}
		
		model.put("role", roleInfo);
		return "authorization/role";
	}
	
	@RequestMapping("/authorization/role/save.html")
    public String saveRole(ModelMap model, RoleInfo roleInfo) throws Throwable {
		if (!StringUtils.isEmpty(roleInfo.getRoleInfoEntity().getRoleName())) {
			CallAct act = new CallAct();
			act.setActName("saveRoleAct");
			Promise<List<Long>> promiseId = nettyClient.send(act, roleInfo);
			promiseId.awaitObject(10000);
		}
		
		return "forward:/authorization/role/query.html";
	}
	
	@RequestMapping("/authorization/role/permission/load.html")
    public String loadRolePermission(ModelMap model, Long roleId) throws Throwable {
		CallAct act = new CallAct();
		act.setActName("queryRolePermissionWildcardsAct");
		Promise<RoleInfo> permissionPromise = nettyClient.send(act, new RoleInfoPK(roleId));
		RoleInfo roleInfo = permissionPromise.awaitObject(10000);
		model.put("role", roleInfo);
		
		Subject subject = SecurityUtils.getSubject();
		if (!subject.hasRole("超级管理员")) {
			if ("超级管理员".equals(roleInfo.getRoleInfoEntity().getRoleName())) {
				/*当登录用户未拥有超级管理员角色时，该用户不能更改超级管理员角色*/
				throw new Exception("用户["+((UserPrincipal)subject.getPrincipal()).getLoginName()+"]不是超级管理员，无权限操作");
			}
		}
		
		return "authorization/givepermission";
	}
	
	@RequestMapping("/authorization/role/permission/save.html")
    public String saveRolePermission(ModelMap model, Long roleId, Long resourceId, Long operationId) throws Throwable {
		CallAct act = new CallAct();
		act.setActName("saveRolePermissionAct");
		Promise<?> promise = nettyClient.send(act, roleId, resourceId, operationId);
		promise.awaitObject(10000);
		
		return "forward:/authorization/role/permission/load.html?roleId="+roleId;
	}
	
	@RequestMapping("/authorization/role/permission/delete.html")
    public String deleteRolePermission(ModelMap model, Long rolePermissionId, Long roleId) throws Throwable {
		CallAct act = new CallAct();
		act.setActName("deleteRolePermissionAct");
		Promise<?> promise = nettyClient.send(act, new RolePermissionRelaPK(rolePermissionId));
		promise.awaitObject(10000);
		
		return "forward:/authorization/role/permission/load.html?roleId="+roleId;
	}
	
	@RequestMapping("/authorization/user/query.html")
    public String queryUser(ModelMap model) throws Throwable {
		CallAct act = new CallAct();
		act.setActName("queryUserAct");
		Promise<List<UserInfo>> promise = nettyClient.send(act);
		List<UserInfo> listUser = promise.awaitObject(10000);
		model.put("users", listUser);
		
		return "authorization/users";
	}
	
	@RequestMapping("/authorization/user/load.html")
    public String loadUser(ModelMap model, UserInfoPK userInfoPk) throws Throwable {
		UserInfo userInfo = null;
		if(userInfoPk != null && userInfoPk.getPartyId() != null) {
			CallAct act = new CallAct();
			act.setActName("loadUserAct");
			Promise<UserInfo> promise = nettyClient.send(act, userInfoPk);
			userInfo = promise.awaitObject(10000);
		} else {
			userInfo = new UserInfo();
		}
		
		Subject subject = SecurityUtils.getSubject();
		if (!subject.hasRole("超级管理员")) {
			if ("admin".equals(userInfo.getUsername())) {
				/*当登录用户未拥有超级管理员角色时，该用户不能更改超级管理员用户信息*/
				throw new Exception("用户["+((UserPrincipal)subject.getPrincipal()).getLoginName()+"]不是超级管理员，无权限操作");
			}
		}
		
		model.put("user", userInfo);
		return "authorization/user";
	}
	
	@RequestMapping("/authorization/user/save.html")
    public String saveUser(ModelMap model, UserInfo userInfo) throws Throwable {
		if (userInfo.getUserInfoPK().getPartyId() != null || (userInfo.getUserInfoPK().getPartyId() == null && !StringUtils.isEmpty(userInfo.getUsername()) && !StringUtils.isEmpty(userInfo.getPassword()))) {
			CallAct act = new CallAct();
			act.setActName("saveUserAct");
			Promise<List<Long>> promiseId = nettyClient.send(act, userInfo);
			promiseId.awaitObject(10000);
		}
		
		return "forward:/authorization/user/query.html";
	}
	
	@RequestMapping("/authorization/user/role/load.html")
    public String loadUserRole(ModelMap model, UserInfoPK userInfoPk) throws Throwable {
		Subject subject = SecurityUtils.getSubject();
		
		CallAct act = new CallAct();
		act.setActName("loadUserRoleAct");
		Promise<UserInfo> promise = nettyClient.send(act, userInfoPk);
		UserInfo userInfo = promise.awaitObject(10000);
		
		if (!subject.hasRole("超级管理员")) {
			if ("admin".equals(userInfo.getUsername())) {
				/*当登录用户未拥有超级管理员角色时，该用户不能更改超级管理员用户信息*/
				throw new Exception("用户["+((UserPrincipal)subject.getPrincipal()).getLoginName()+"]不是超级管理员，无权限操作");
			}
		}
		
		act = new CallAct();
		act.setActName("queryRoleAct");
		Promise<List<RoleInfo>> promiseRole = nettyClient.send(act);
		List<RoleInfo> listRole = promiseRole.awaitObject(10000);
		
		List<Long> userRoleId = new ArrayList<Long>();
		for(PartyRoleRelaEntity entity : userInfo.getListPartyRoleRelaEntity()) {
			userRoleId.add(entity.getRoleId());
		}
		
		List<RoleInfo> selectRole = new ArrayList<RoleInfo>();
		List<RoleInfo> unselectRole = new ArrayList<RoleInfo>();
		for(RoleInfo role : listRole) {
			if (!subject.hasRole("超级管理员")) {
				if ("超级管理员".equals(role.getRoleInfoEntity().getRoleName())) {
					/*当登录用户没有超级管理员角色时，该用户不能设置超级管理员角色给用户*/
					continue;
				}
			}
			if(userRoleId.contains(role.getRoleInfoPK().getRoleId())) {
				selectRole.add(role);
			} else {
				unselectRole.add(role);
			}
		}
		
		model.put("user", userInfo);
		model.put("selects", selectRole);
		model.put("unselects", unselectRole);
		return "authorization/giverole";
	}
	
	@RequestMapping("/authorization/user/role/save.html")
    public String saveUserRole(ModelMap model, Long partyId, Long[] roleIds) throws Throwable {
		CallAct act = new CallAct();
		act.setActName("saveUserRoleAct");
		Promise<?> promise = nettyClient.send(act, partyId, roleIds);
		promise.awaitObject(10000);
		
		return "forward:/authorization/user/role/load.html?partyId="+partyId;
	}
}
