package cn.ruiyeclub.util;

import cn.ruiyeclub.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;

public class ShiroUtil {

    public static AccountProfile getProfile() {
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }

    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

}
