package com.cts.localtour.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.cts.localtour.entity.UserTable;
import com.cts.localtour.service.UserService;

public class localTourRealm extends AuthorizingRealm{
	@Autowired
	private UserService userService;
	private static final String OR_OPERATOR = " or ";  
	private static final String AND_OPERATOR = " and ";  
	private static final String NOT_OPERATOR = "not "; 
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		UserTable user = (UserTable)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(userService.getRolesByUserName(user.getUserName()));
        authorizationInfo.setStringPermissions(userService.getPermissionsByUserName(user.getUserName()));
        return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String)token.getPrincipal();

        UserTable user = userService.getByUserName(username);

        if(user == null) {
            throw new UnknownAccountException();//没找到帐号
        }

        if(Boolean.FALSE.equals(user.isEnable())) {
            throw new LockedAccountException(); //帐号锁定
        }

        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user, //用户名
                user.getPwd(), //密码
               /* ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt*/
                getName()  //realm name
        );
        return authenticationInfo;
	}
	
	 @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }
    
    /*扩展支持or and not 关键词  不支持and or混用 */
    public boolean isPermitted(PrincipalCollection principals, String permission) {  
        if(permission.contains(OR_OPERATOR)) { 
            String[] permissions = permission.split(OR_OPERATOR);  
            for(String orPermission : permissions) {  
                if(isPermittedWithNotOperator(principals, orPermission)) {  
                    return true;  
                }  
            }  
            return false;  
        } else if(permission.contains(AND_OPERATOR)) {  
            String[] permissions = permission.split(AND_OPERATOR);  
            for(String orPermission : permissions) {  
                if(!isPermittedWithNotOperator(principals, orPermission)) {  
                    return false;  
                }  
            }  
            return true;  
        } else {  
            return isPermittedWithNotOperator(principals, permission);  
        }  
    }  
      
    private boolean isPermittedWithNotOperator(PrincipalCollection principals, String permission) {  
        if(permission.startsWith(NOT_OPERATOR)) {  
            return !super.isPermitted(principals, permission.substring(NOT_OPERATOR.length()));  
        } else {  
            return super.isPermitted(principals, permission);  
        }  
    }  
}
