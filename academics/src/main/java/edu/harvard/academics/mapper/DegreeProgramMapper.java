package edu.harvard.academics.mapper;

import edu.harvard.academics.entity.DegreeProgram;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface DegreeProgramMapper {

    List<DegreeProgram> list(ArrayList<String> schools, String degreeLevel, String nameBySearch);
    void add(DegreeProgram degreeProgram);

    void edit(DegreeProgram degreeProgram);

    void remove(List<Integer> ids);

    void removeBySchoolId(List<Integer> ids);

    @Select("select * from degree_program where id = #{id}")
    DegreeProgram getById(Integer id);

    @Select("select * from degree_program where name = #{name}")
    DegreeProgram getByName(String name);

    @Delete("delete from degree_program where name = #{name}")
    void removeByName(String name);

}
