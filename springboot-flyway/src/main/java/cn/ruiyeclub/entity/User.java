package cn.ruiyeclub.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User extends Model<User> {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;

}