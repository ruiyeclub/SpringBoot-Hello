package cn.ruiyeclub.mapper;

import cn.ruiyeclub.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Raychen
 * @date 2020/3/11 20:51
 */
@Mapper
public interface UserMapper {
    User queryUserByName(String name);

}
