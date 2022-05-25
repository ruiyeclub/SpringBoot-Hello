package cn.ruiyeclub.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * (SysPermission)表实体类
 *
 * @author Ray。
 * @since 2022-05-25 22:30:44
 */
@SuppressWarnings("serial")
public class SysPermission extends Model<SysPermission> {
    
    private Integer id;
    
    private String name;
    
    private String permission;
    
    private String url;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

