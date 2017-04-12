package com.team.goyea.web.ftl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
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
import com.yea.core.remote.struct.CallFacadeDef;

@Controller
public class AuthorizationController {
	@Autowired
	private AbstractEndpoint nettyClient;
	
	@RequestMapping("/")
    public String index(ModelMap model) throws Throwable {
		return "redirect:/index.html";
	}
	
	@RequestMapping("/login.html")
    public String loginInit(ModelMap model) throws Throwable {
		if(SecurityUtils.getSubject().isAuthenticated() || SecurityUtils.getSubject().isRemembered()) {
			return "redirect:/index.html";
		}
		return "/login";
	}
	@RequestMapping("/logout.html")
    public String logout(ModelMap model) throws Throwable {
		return "redirect:/login.html";
	}
	
	@RequestMapping("/authenticed.html")
	public String authenticed(ModelMap model, HttpServletRequest request) throws Throwable {
		// 如果登录失败从request中获取认证异常信息，shiroLoginFailure就是shiro异常类的全限定名
		// 根据shiro返回的异常类路径判断，抛出指定异常信息
		String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
		if (exceptionClassName != null) {
			if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
				throw new Throwable("用户名不存在");
			} else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
				throw new Throwable("用户名/密码不正确");
			} else {
				throw new Exception();// 最终在异常处理器生成未知错误
			}
		}
		// 此方法不处理登陆成功（认证成功），shiro认证成功会自动跳转到上一个请求路径
		// 登陆失败还到login页面
		return "/login";
	}
	
	@RequestMapping("/authorization/role/query.html")
    public String queryRole(ModelMap model) throws Throwable {
		CallFacadeDef facade = new CallFacadeDef();
		facade.setCallFacadeName("queryRoleFacade");
		Promise<List<RoleInfo>> promise = nettyClient.send(facade);
		List<RoleInfo> listRole = promise.awaitObject(10000);
		model.put("roles", listRole);
		
		return "authorization/roles";
	}
	
	@RequestMapping("/authorization/role/load.html")
    public String loadRole(ModelMap model, RoleInfoPK roleInfoPk) throws Throwable {
		RoleInfo roleInfo = null;
		if(roleInfoPk != null && roleInfoPk.getRoleId() != null) {
			CallFacadeDef facade = new CallFacadeDef();
			facade.setCallFacadeName("loadRoleFacade");
			Promise<RoleInfo> promise = nettyClient.send(facade, roleInfoPk);
			roleInfo = promise.awaitObject(10000);
		} else {
			roleInfo = new RoleInfo();
		}
		
		model.put("role", roleInfo);
		return "authorization/role";
	}
	
	@RequestMapping("/authorization/role/save.html")
    public String saveRole(ModelMap model, RoleInfo roleInfo) throws Throwable {
		if (!StringUtils.isEmpty(roleInfo.getRoleInfoEntity().getRoleName())) {
			CallFacadeDef facade = new CallFacadeDef();
			facade.setCallFacadeName("saveRoleFacade");
			Promise<List<Long>> promiseId = nettyClient.send(facade, roleInfo);
			promiseId.awaitObject(10000);
		}
		
		return "redirect:/authorization/role/query.html";
	}
	
	@RequestMapping("/authorization/role/permission/load.html")
    public String loadRolePermission(ModelMap model, Long roleId) throws Throwable {
		CallFacadeDef facade = new CallFacadeDef();
		facade.setCallFacadeName("queryRolePermissionWildcardsFacade");
		Promise<RoleInfo> permissionPromise = nettyClient.send(facade, new RoleInfoPK(roleId));
		RoleInfo roleInfo = permissionPromise.awaitObject(10000);
		model.put("role", roleInfo);
		
		return "authorization/givepermission";
	}
	
	@RequestMapping("/authorization/role/permission/save.html")
    public String saveRolePermission(ModelMap model, Long roleId, Long resourceId, Long operationId) throws Throwable {
		CallFacadeDef facade = new CallFacadeDef();
		facade.setCallFacadeName("saveRolePermissionFacade");
		Promise<?> promise = nettyClient.send(facade, roleId, resourceId, operationId);
		promise.awaitObject(10000);
		
		return "redirect:/authorization/role/permission/load.html?roleId="+roleId;
	}
	
	@RequestMapping("/authorization/role/permission/delete.html")
    public String deleteRolePermission(ModelMap model, Long rolePermissionId, Long roleId) throws Throwable {
		CallFacadeDef facade = new CallFacadeDef();
		facade.setCallFacadeName("deleteRolePermissionFacade");
		Promise<?> promise = nettyClient.send(facade, new RolePermissionRelaPK(rolePermissionId));
		promise.awaitObject(10000);
		
		return "redirect:/authorization/role/permission/load.html?roleId="+roleId;
	}
	
	@RequestMapping("/authorization/user/query.html")
    public String queryUser(ModelMap model) throws Throwable {
		CallFacadeDef facade = new CallFacadeDef();
		facade.setCallFacadeName("queryUserFacade");
		Promise<List<UserInfo>> promise = nettyClient.send(facade);
		List<UserInfo> listUser = promise.awaitObject(10000);
		model.put("users", listUser);
		
		return "authorization/users";
	}
	
	@RequestMapping("/authorization/user/load.html")
    public String loadUser(ModelMap model, UserInfoPK userInfoPk) throws Throwable {
		UserInfo userInfo = null;
		if(userInfoPk != null && userInfoPk.getPartyId() != null) {
			CallFacadeDef facade = new CallFacadeDef();
			facade.setCallFacadeName("loadUserFacade");
			Promise<UserInfo> promise = nettyClient.send(facade, userInfoPk);
			userInfo = promise.awaitObject(10000);
		} else {
			userInfo = new UserInfo();
		}
		
		model.put("user", userInfo);
		return "authorization/user";
	}
	
	@RequestMapping("/authorization/user/save.html")
    public String saveUser(ModelMap model, UserInfo userInfo) throws Throwable {
		if (userInfo.getUserInfoPK().getPartyId() != null || (userInfo.getUserInfoPK().getPartyId() == null && !StringUtils.isEmpty(userInfo.getUsername()) && !StringUtils.isEmpty(userInfo.getPassword()))) {
			CallFacadeDef facade = new CallFacadeDef();
			facade.setCallFacadeName("saveUserFacade");
			Promise<List<Long>> promiseId = nettyClient.send(facade, userInfo);
			promiseId.awaitObject(10000);
		}
		
		return "redirect:/authorization/user/query.html";
	}
	
	@RequestMapping("/authorization/user/role/load.html")
    public String loadUserRole(ModelMap model, UserInfoPK userInfoPk) throws Throwable {
		CallFacadeDef facade = new CallFacadeDef();
		facade.setCallFacadeName("loadUserRoleFacade");
		Promise<UserInfo> promise = nettyClient.send(facade, userInfoPk);
		UserInfo userInfo = promise.awaitObject(10000);
		
		facade = new CallFacadeDef();
		facade.setCallFacadeName("queryRoleFacade");
		Promise<List<RoleInfo>> promiseRole = nettyClient.send(facade);
		List<RoleInfo> listRole = promiseRole.awaitObject(10000);
		
		List<Long> userRoleId = new ArrayList<Long>();
		for(PartyRoleRelaEntity entity : userInfo.getListPartyRoleRelaEntity()) {
			userRoleId.add(entity.getRoleId());
		}
		
		List<RoleInfo> selectRole = new ArrayList<RoleInfo>();
		List<RoleInfo> unselectRole = new ArrayList<RoleInfo>();
		for(RoleInfo role : listRole) {
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
		CallFacadeDef facade = new CallFacadeDef();
		facade.setCallFacadeName("saveUserRoleFacade");
		Promise<?> promise = nettyClient.send(facade, partyId, roleIds);
		promise.awaitObject(10000);
		
		return "redirect:/authorization/user/role/load.html?partyId="+partyId;
	}
}
