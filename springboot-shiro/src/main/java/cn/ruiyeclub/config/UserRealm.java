package cn.ruiyeclub.config;

import cn.ruiyeclub.pojo.User;
import cn.ruiyeclub.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Ray。
 * @create 2020-03-11 14:30
 */
//自定义的UserRealm
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    //用户授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=>授权doGetAuthorizationInfo");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.addStringPermission("user:add"); 手动添加了权限

        //拿到当前登录的这个对象
        Subject subject= SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal(); //拿到user对象 可以设置用户权限
        //设置当前用户的权限 从数据库上面拿
        info.addStringPermission(currentUser.getPerms());
        System.out.println(currentUser.getPerms());
        return info;
    }

    //用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了=>认证doGetAuthenticationInfo");

        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        //认证用户（连接数据库）
        User user = userService.queryUserByName(userToken.getUsername());
        if(user==null){
            return null; //UnknownAccountException
        }

        //判断session是否有值，显示登录按钮
        Subject currentSubject = SecurityUtils.getSubject();
        Session session = currentSubject.getSession();
        session.setAttribute("loginUser",user);

        //可以加密：MD5 MD5盐值加密（更高级）
        //密码认证（shiro完成）
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}