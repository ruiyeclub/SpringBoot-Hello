package cn.ruiyeclub.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * (SysRolePermission)表实体类
 *
 * @author Ray。
 * @since 2022-05-25 22:30:44
 */
@SuppressWarnings("serial")
public class SysRolePermission extends Model<SysRolePermission> {
    
    private Integer permissionId;
    
    private Integer roleId;


    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

}

