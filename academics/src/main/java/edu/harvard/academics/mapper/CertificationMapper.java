package edu.harvard.academics.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CertificationMapper {
    @Select("select id from certification where name = #{name}")
    Integer getIdByName(String name);

    @Select("select name from certification where id = #{id}")
    String getNameById(Integer id);

}
