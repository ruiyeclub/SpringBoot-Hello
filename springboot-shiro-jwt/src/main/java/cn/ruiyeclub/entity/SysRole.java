package cn.ruiyeclub.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * (SysRole)表实体类
 *
 * @author Ray。
 * @since 2022-05-25 22:30:44
 */
@SuppressWarnings("serial")
public class SysRole extends Model<SysRole> {
    
    private Integer id;
    
    private String description;
    
    private String role;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
    }

