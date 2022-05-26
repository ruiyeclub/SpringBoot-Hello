package cn.ruiyeclub.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * (SysUserRole)表实体类
 *
 * @author Ray。
 * @since 2022-05-25 22:30:44
 */
@SuppressWarnings("serial")
public class SysUserRole extends Model<SysUserRole> {

    private Integer roleId;

    @TableId
    private Integer uid;


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.roleId;
    }
}

