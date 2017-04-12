package com.team.goyea.web.ftl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.team.goyea.permission.model.MenuInfo;
import com.team.goyea.permission.model.OperationInfo;
import com.team.goyea.permission.model.PermissionInfo;
import com.team.goyea.permission.model.ResourceIdentifier;
import com.team.goyea.permission.model.ResourceInfo;
import com.team.goyea.permission.model.entity.MenuInfoEntity;
import com.team.goyea.permission.model.entity.OperationInfoEntity;
import com.team.goyea.permission.model.entity.PermissionInfoEntity;
import com.team.goyea.permission.model.entity.ResourceIdentifierEntity;
import com.team.goyea.permission.model.entity.ResourceInfoEntity;
import com.team.goyea.permission.model.pk.MenuInfoPK;
import com.team.goyea.permission.model.pk.OperationInfoPK;
import com.team.goyea.permission.model.pk.PermissionInfoPK;
import com.team.goyea.permission.model.pk.ResourceIdentifierPK;
import com.team.goyea.permission.model.pk.ResourceInfoPK;
import com.yea.core.remote.AbstractEndpoint;
import com.yea.core.remote.promise.Promise;
import com.yea.core.remote.struct.CallFacadeDef;
import com.yea.shiro.web.wrapper.ShiroFilterWrapper;
import com.yea.web.jsonbody.JsonBody;
import com.yea.web.jsonbody.JsonPropFilter;

@Controller
public class PermissionController {
	@Autowired
	private AbstractEndpoint nettyClient;
	
	@RequestMapping("/permission/operation/query.html")
    public String queryOperation(ModelMap model) throws Throwable {
		CallFacadeDef facade = new CallFacadeDef();
		facade.setCallFacadeName("queryOperationFacade");
		Promise<List<OperationInfo>> promise = nettyClient.send(facade);
		List<OperationInfo> listOperation = promise.awaitObject(10000);
		model.put("operations", listOperation);
		
		return "permission/operation";
	}
	
	@RequestMapping("/permission/operation/save.html")
    public String saveOperation(ModelMap model, OperationInfo operationDto) throws Throwable {
		
		if (!StringUtils.isEmpty(operationDto.getOperationInfoEntity().getOperationName())) {
			CallFacadeDef facade = new CallFacadeDef();
			facade.setCallFacadeName("saveOperationFacade");
			Promise<List<OperationInfoPK>> promiseId = nettyClient.send(facade, operationDto);
			promiseId.awaitObject(10000);
		}
		
		return "redirect:/permission/operation/query.html";
	}
	
	@RequestMapping("/permission/resource/query.html")
    public String queryResource(ModelMap model) throws Throwable {
		CallFacadeDef facade = new CallFacadeDef();
		facade.setCallFacadeName("queryResourceFacade");
		Promise<List<ResourceInfo>> promise = nettyClient.send(facade);
		List<ResourceInfo> listResource = promise.awaitObject(10000);
		model.put("resources", listResource);
		
		return "permission/resource";
	}
	
	@RequestMapping("/permission/resource/save.html")
    public String saveResource(ModelMap model, ResourceInfo resourceDto) throws Throwable {
		if (!StringUtils.isEmpty(resourceDto.getResourceInfoEntity().getResourceName())) {
			CallFacadeDef facade = new CallFacadeDef();
			facade.setCallFacadeName("saveResourceFacade");
			Promise<List<ResourceInfoPK>> promiseId = nettyClient.send(facade, resourceDto);
			promiseId.awaitObject(10000);
		}
		
		return "redirect:/permission/resource/query.html";
	}
	
	@RequestMapping("/permission/permission/query.html")
    public String queryPermission(ModelMap model) throws Throwable {
		CallFacadeDef facade = new CallFacadeDef();
		facade.setCallFacadeName("queryPermissionFacade");
		Promise<List<PermissionInfo>> promise = nettyClient.send(facade, new PermissionInfo());
		List<PermissionInfo> listPermission = promise.awaitObject(10000);
		model.put("permissions", listPermission);
		
		return "permission/permission";
	}
	
	@RequestMapping("/permission/permission/save.html")
    public String savePermission(ModelMap model, PermissionInfo resourceDto) throws Throwable {
		if (!StringUtils.isEmpty(resourceDto.getPermissionInfoEntity().getPermissionName())) {
			CallFacadeDef facade = new CallFacadeDef();
			facade.setCallFacadeName("savePermissionFacade");
			Promise<List<PermissionInfoPK>> promiseId = nettyClient.send(facade, resourceDto);
			promiseId.awaitObject(10000);
		}
		
		return "redirect:/permission/permission/query.html";
	}
	
	@RequestMapping("/permission/identifier/query.html")
    public String queryIdentifier(ModelMap model) throws Throwable {
		CallFacadeDef facade = new CallFacadeDef();
		facade.setCallFacadeName("queryResourceIdentifierFacade");
		Promise<List<ResourceIdentifier>> promise = nettyClient.send(facade, new ResourceIdentifier());
		List<ResourceIdentifier> listResourceIdentifier = promise.awaitObject(10000);
		model.put("identifiers", listResourceIdentifier);
		
		return "permission/identifier";
	}
	
	@RequestMapping("/permission/identifier/save.html")
    public String saveIdentifier(ModelMap model, ResourceIdentifier identifier, Long resourceId, Long operationId) throws Throwable {
		if (!StringUtils.isEmpty(identifier.getResourceIdentifierEntity().getIdentifierPath())) {
			CallFacadeDef facade = new CallFacadeDef();
			facade.setCallFacadeName("saveResourceIdentifierFacade");
			Promise<ResourceIdentifierPK> promise = nettyClient.send(facade, identifier, resourceId, operationId);
			promise.awaitObject(10000);
		}
		
		return "redirect:/permission/identifier/query.html";
	}
	
	@RequestMapping("/permission/identifier/effect.html")
    public String effectIdentifier(ModelMap model) throws Throwable {
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		ShiroFilterWrapper shiroFilterWrapper = (ShiroFilterWrapper) webApplicationContext.getBean(ShiroFilterWrapper.class);
		shiroFilterWrapper.reset();
		return "redirect:/permission/identifier/query.html";
	}
	
	@RequestMapping("/permission/menu/query.html")
    public String queryMenu(ModelMap model) throws Throwable {
		CallFacadeDef facade = new CallFacadeDef();
		facade.setCallFacadeName("queryMenuFacade");
		Promise<List<MenuInfo>> promise = nettyClient.send(facade, new MenuInfo());
		List<MenuInfo> listMenu = promise.awaitObject(10000);
		model.put("menus", listMenu);
		
		return "permission/menus";
	}
	
	@RequestMapping("/permission/menu/load.html")
    public String loadMenu(ModelMap model, MenuInfoPK menuId) throws Throwable {
		CallFacadeDef facade = new CallFacadeDef();
		facade.setCallFacadeName("loadMenuFacade");
		Promise<MenuInfo> promise = nettyClient.send(facade, menuId);
		MenuInfo menu = promise.awaitObject(10000);
		model.put("menu", menu);
		
		return "permission/menu";
	}
	
	@RequestMapping("/permission/menu/save.html")
    public String saveMenu(ModelMap model, MenuInfo menuInfo) throws Throwable {
		if (!StringUtils.isEmpty(menuInfo.getMenuInfoEntity().getMenuName())) {
			CallFacadeDef facade = new CallFacadeDef();
			facade.setCallFacadeName("saveMenuFacade");
			Promise<ResourceIdentifierPK> promise = nettyClient.send(facade, menuInfo);
			promise.awaitObject(10000);
		}
		
		return "redirect:/permission/menu/query.html";
	}
	
	@RequestMapping("/permission/identifier/load")
	@JsonPropFilter(type = ResourceIdentifier.class, include = "resourceIdentifierPK, resourceIdentifierEntity")
	public ResourceIdentifier loadIdentifier(ModelMap model, ResourceIdentifierPK identifier) throws Throwable {
		CallFacadeDef facade = new CallFacadeDef();
		facade.setCallFacadeName("loadResourceIdentifierFacade");
		Promise<ResourceIdentifier> promise = nettyClient.send(facade, identifier);
		return promise.awaitObject(10000);
	}
	
	@RequestMapping("/permission/resource/query/parent")
	@JsonBody(filters = { @JsonPropFilter(type = ResourceInfo.class, include = "resourceInfoPK,resourceInfoEntity"),
			@JsonPropFilter(type = ResourceInfoPK.class, include = "resourceId"),
			@JsonPropFilter(type = ResourceInfoEntity.class, include = "resourceName")
			 })
    public List<ResourceInfo> queryResource(Long parentResourceId) throws Throwable {
		ResourceInfo query = new ResourceInfo();
		query.getResourceInfoEntity().setParentResourceId(parentResourceId);
		
		CallFacadeDef facade = new CallFacadeDef();
		facade.setCallFacadeName("queryParentResourceFacade");
		Promise<List<ResourceInfo>> promise = nettyClient.send(facade, query);
		List<ResourceInfo> listResource = promise.awaitObject(10000);
		
		return listResource;
	}
	@RequestMapping("/permission/operation/query")
	@JsonBody(filters = { @JsonPropFilter(type = OperationInfo.class, include = "operationInfoPK,operationInfoEntity"),
			@JsonPropFilter(type = OperationInfoPK.class, include = "operationId"),
			@JsonPropFilter(type = OperationInfoEntity.class, include = "operationName") })
    public List<OperationInfo> queryOperation() throws Throwable {
		CallFacadeDef facade = new CallFacadeDef();
		facade.setCallFacadeName("queryOperationFacade");
		Promise<List<OperationInfo>> promise = nettyClient.send(facade);
		List<OperationInfo> listOperation = promise.awaitObject(10000);
		
		return listOperation;
	}
	
	@RequestMapping("/permission/permission/operation/query")
	@JsonBody(filters = { @JsonPropFilter(type = PermissionInfo.class, include = "permissionInfoEntity,operationName"),
			@JsonPropFilter(type = PermissionInfoEntity.class, include = "operationId") })
    public List<PermissionInfo> queryOperationOfPermission(Long resourceId) throws Throwable {
		PermissionInfo queryDto = new PermissionInfo();
		queryDto.getPermissionInfoEntity().setResourceId(resourceId);
		CallFacadeDef facade = new CallFacadeDef();
		facade.setCallFacadeName("queryPermissionFacade");
		Promise<List<PermissionInfo>> promise = nettyClient.send(facade, queryDto);
		List<PermissionInfo> listPermission = promise.awaitObject(10000);
		
		return listPermission;
	}
	
	@RequestMapping("/permission/menu/parent/query")
	@JsonBody(filters = { @JsonPropFilter(type = MenuInfo.class, include = "menuInfoPK,menuInfoEntity"),
			@JsonPropFilter(type = MenuInfoPK.class, include = "menuId"),
			@JsonPropFilter(type = MenuInfoEntity.class, include = "menuName")})
    public List<MenuInfo> queryParentOfMenu(Long parentMenuId) throws Throwable {
		MenuInfo query = new MenuInfo();
		query.getMenuInfoEntity().setParentMenuId(parentMenuId);
		CallFacadeDef facade = new CallFacadeDef();
		facade.setCallFacadeName("queryParentMenuFacade");
		Promise<List<MenuInfo>> promise = nettyClient.send(facade, query);
		List<MenuInfo> listMenu = promise.awaitObject(10000);
		
		return listMenu;
	}
	
	@RequestMapping("/permission/menu/identifier/query")
	@JsonBody(filters = { @JsonPropFilter(type = ResourceIdentifier.class, include = "resourceIdentifierPK,resourceIdentifierEntity"),
			@JsonPropFilter(type = ResourceIdentifierPK.class, include = "identifierId"),
			@JsonPropFilter(type = ResourceIdentifierEntity.class, include = "identifierPath")})
    public List<ResourceIdentifier> queryIdentifierOfMenu() throws Throwable {
		CallFacadeDef facade = new CallFacadeDef();
		facade.setCallFacadeName("queryResourceIdentifierFacade");
		Promise<List<ResourceIdentifier>> promise = nettyClient.send(facade, new ResourceIdentifier());
		List<ResourceIdentifier> listResourceIdentifier = promise.awaitObject(10000);
		
		return listResourceIdentifier;
	}
}
