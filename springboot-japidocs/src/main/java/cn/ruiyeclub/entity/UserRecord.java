package cn.ruiyeclub.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * (UserRecord)表实体类
 *
 * @author Ray。
 * @date 2020-07-18 10:15:34
 */
@Data
public class UserRecord extends Model<UserRecord> implements Serializable {

    /**
     * 主键
     */
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 邮箱
     */
    private String email;
}