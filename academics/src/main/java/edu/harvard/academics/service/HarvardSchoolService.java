package edu.harvard.academics.service;

import edu.harvard.academics.entity.HarvardSchool;

import java.util.List;

public interface HarvardSchoolService {
    List<HarvardSchool> list();

    void add(HarvardSchool harvardSchool);

    void edit(HarvardSchool harvardSchool);

    void remove(List<Integer> ids);
}
