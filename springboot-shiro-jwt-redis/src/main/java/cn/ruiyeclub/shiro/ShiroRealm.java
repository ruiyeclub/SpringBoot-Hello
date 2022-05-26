package cn.ruiyeclub.shiro;

import cn.ruiyeclub.entity.SysUser;
import cn.ruiyeclub.service.SysPermissionService;
import cn.ruiyeclub.service.SysRoleService;
import cn.ruiyeclub.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Ray。
 * @date 2017-12-01 21:25
 */
@Slf4j
@Component
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysPermissionService sysPermissionService;
    @Autowired
    JwtUtils jwtUtils;

    /**
     * 判断传入的token是否是JwtToken
     *
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 获取用户身份信息的时候 就会调用此方法 从数据库获取该用户的权限与角色信息
     *
     * @param principal
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        log.info("授权-->ShiroRealm.doGetAuthorizationInfo()");
        //shiro的对象 存储登录用户的信息
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        AccountProfile userInfo = (AccountProfile) principal.getPrimaryPrincipal();
        // 获取用户角色集
        sysRoleService.findRoleByUsername(userInfo.getUsername()).stream().forEach(
                sysRole -> {
                    authorizationInfo.addRole(sysRole.getRole());
                    sysPermissionService.findPermissionByRoleId(sysRole.getId()).stream().forEach(
                            sysPermission -> {
                                authorizationInfo.addStringPermission(sysPermission.getPermission());
                            }
                    );
                }
        );
        return authorizationInfo;
    }

    /**
     * 在这个方法 进行身份验证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        log.info("认证-->ShiroRealm.doGetAuthenticationInfo");

        // 将传入的AuthenticationToken强转JwtToken
        JwtToken jwtToken = (JwtToken) token;
        // 获取jwtToken中的userId
        String userId = jwtUtils.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();

        SysUser userInfo = sysUserService.getById(Integer.parseInt(userId));
        if (userInfo == null) {
            throw new UnknownAccountException("用户名或密码错误！");
        }
        if (userInfo.getState().equals("0")) {
            throw new LockedAccountException("账号已被锁定,请联系管理员！");
        }

        // 将可以显示的信息放在该载体中，对于密码这种隐秘信息不需要放在该载体中
        AccountProfile accountProfile = new AccountProfile();
        BeanUtils.copyProperties(userInfo, accountProfile);
        return new SimpleAuthenticationInfo(accountProfile, jwtToken.getCredentials(), getName());
    }
}
