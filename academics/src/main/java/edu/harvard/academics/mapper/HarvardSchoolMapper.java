package edu.harvard.academics.mapper;

import edu.harvard.academics.entity.HarvardSchool;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HarvardSchoolMapper {
    List<HarvardSchool> list();

    void add(HarvardSchool harvardSchool);

    void edit(HarvardSchool harvardSchool);

    void remove(List<Integer> ids);

    @Select("select id from harvard_school where name = #{name}")
    Integer getIdBySchoolName(String name);

    @Select("select name from harvard_school where id = #{id}")
    String getNameById(Integer id);

}
