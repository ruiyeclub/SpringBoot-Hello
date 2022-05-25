package cn.ruiyeclub.util;

import cn.ruiyeclub.entity.SysUser;
import org.apache.shiro.SecurityUtils;

public class ShiroUtil {

    public static SysUser getProfile() {
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }

}
