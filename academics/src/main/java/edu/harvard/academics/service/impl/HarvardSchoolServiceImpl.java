package edu.harvard.academics.service.impl;

import edu.harvard.academics.entity.HarvardSchool;
import edu.harvard.academics.mapper.DegreeProgramMapper;
import edu.harvard.academics.mapper.HarvardSchoolMapper;
import edu.harvard.academics.service.HarvardSchoolService;
import edu.harvard.utils.anno.LogRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HarvardSchoolServiceImpl implements HarvardSchoolService {
    final private HarvardSchoolMapper harvardSchoolMapper;
    final private DegreeProgramMapper degreeProgramMapper;

    @Autowired
    public HarvardSchoolServiceImpl(HarvardSchoolMapper harvardSchoolMapper, DegreeProgramMapper degreeProgramMapper) {
        this.harvardSchoolMapper = harvardSchoolMapper;
        this.degreeProgramMapper = degreeProgramMapper;
    }

    @Override
    public List<HarvardSchool> list() {
        return harvardSchoolMapper.list();
    }

    @LogRecord
    @Override
    public void add(HarvardSchool harvardSchool) {
        harvardSchoolMapper.add(harvardSchool);
    }

    @LogRecord
    @Override
    public void edit(HarvardSchool harvardSchool) {
        harvardSchoolMapper.edit(harvardSchool);
    }

    @LogRecord
    @Override
    @Transactional(rollbackFor = Exception.class) // default: RuntimeException)
    public void remove(List<Integer> ids) {

        try {
            degreeProgramMapper.removeBySchoolId(ids);
            harvardSchoolMapper.remove(ids);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
