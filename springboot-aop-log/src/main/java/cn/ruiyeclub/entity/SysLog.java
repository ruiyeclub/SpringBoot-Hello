package cn.ruiyeclub.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (SysLog)实体类
 *
 * @author Ray。
 * @since 2020-06-07 22:04:11
 */
public class SysLog implements Serializable {
    private static final long serialVersionUID = 974370584191486768L;

    private Integer id;

    private Integer userId;

    private String userAction;

    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserAction() {
        return userAction;
    }

    public void setUserAction(String userAction) {
        this.userAction = userAction;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}