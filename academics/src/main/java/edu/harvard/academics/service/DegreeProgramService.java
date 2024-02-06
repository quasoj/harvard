package edu.harvard.academics.service;

import edu.harvard.academics.entity.DegreeProgram;
import edu.harvard.academics.entity.DegreeProgramDisplay;
import edu.harvard.utils.PageBean;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;

public interface DegreeProgramService {
    PageBean page(Integer pageNum, Integer pageSize, ArrayList<String> schools, String degreeLevel, String nameBySearch);

    void add(DegreeProgramDisplay degreeProgramDisplay);

    void edit(DegreeProgramDisplay degreeProgramDisplay);

    void remove(List<Integer> ids);

    DegreeProgram getById(Integer id);
    DegreeProgram getByName(String name);
    void removeByName(String name);
}
