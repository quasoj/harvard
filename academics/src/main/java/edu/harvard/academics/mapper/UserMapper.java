package edu.harvard.academics.mapper;

import edu.harvard.academics.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from user where username = #{username} and password = #{password}")
    User getUserByUsernameAndPassword(User user);

}
