package cn.ruiyeclub.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * 部门表(Dept)表实体类
 *
 * @author Ray。
 * @since 2020-10-19 16:43:42
 */
@Data
@TableName("dept")
public class Dept extends Model<Dept> {

    @TableId(value = "deptno", type = IdType.AUTO)
    private Long deptno;

    private String dname;

    private String dbSource;

}
