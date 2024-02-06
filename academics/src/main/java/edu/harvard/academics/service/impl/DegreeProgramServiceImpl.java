package edu.harvard.academics.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.Page;
import edu.harvard.academics.entity.DegreeProgramDisplay;
import edu.harvard.academics.mapper.CertificationMapper;
import edu.harvard.academics.mapper.HarvardSchoolMapper;
import edu.harvard.academics.entity.DegreeProgram;
import edu.harvard.utils.PageBean;
import edu.harvard.academics.mapper.DegreeProgramMapper;
import edu.harvard.academics.service.DegreeProgramService;
import edu.harvard.utils.anno.LogRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DegreeProgramServiceImpl implements DegreeProgramService {

    private final DegreeProgramMapper degreeProgramMapper;
    private final CertificationMapper certificationMapper;
    private final HarvardSchoolMapper harvardSchoolMapper;

    @Autowired
    public DegreeProgramServiceImpl(DegreeProgramMapper degreeProgramMapper, CertificationMapper certificationMapper, HarvardSchoolMapper harvardSchoolMapper) {
        this.degreeProgramMapper = degreeProgramMapper;
        this.certificationMapper = certificationMapper;
        this.harvardSchoolMapper = harvardSchoolMapper;
    }

    @Override
    public PageBean page(Integer pageNum, Integer pageSize, ArrayList<String> schools, String degreeLevel, String nameBySearch) {

        PageHelper.startPage(pageNum, pageSize);

        Page<DegreeProgram> page = (Page<DegreeProgram>) degreeProgramMapper.list(schools, degreeLevel, nameBySearch);

        List<DegreeProgramDisplay> result = page.getResult()
                .stream()
                .map(program -> {
                            DegreeProgramDisplay degreeProgramDisplay = new DegreeProgramDisplay();
                            degreeProgramDisplay.setId(program.getId());
                            degreeProgramDisplay.setProgramName(program.getName());
                            degreeProgramDisplay.setProgramDescription(program.getDescription());
                            degreeProgramDisplay.setImage(program.getImage());
                            degreeProgramDisplay.setCertificationName(
                                    certificationMapper.getNameById(program.getCertification())
                            );
                            degreeProgramDisplay.setHarvardSchoolName(
                                    harvardSchoolMapper.getNameById(program.getHarvardSchool())
                            );
                            return degreeProgramDisplay;
                        }
                )
                .toList();

        return new PageBean(page.getTotal(), Collections.singletonList(result));
    }

    @LogRecord
    @Override
    public void add(DegreeProgramDisplay degreeProgramDisplay) {
        degreeProgramMapper.add(transfer(degreeProgramDisplay));
    }

    @LogRecord
    @Override
    public void edit(DegreeProgramDisplay degreeProgramDisplay) {
        degreeProgramMapper.edit(transfer(degreeProgramDisplay));
    }

    @LogRecord
    @Override
    public void remove(List<Integer> ids) {
        degreeProgramMapper.remove(ids);
    }

    @Override
    public DegreeProgram getById(Integer id) {
        return degreeProgramMapper.getById(id);
    }

    @Override
    public DegreeProgram getByName(String name) {
        return degreeProgramMapper.getByName(name);
    }

    @LogRecord
    @Override
    public void removeByName(String name) {
        degreeProgramMapper.removeByName(name);
    }

    /**
     * transfer data type DegreeProgramDisplay -> DegreeProgram
     */
    private DegreeProgram transfer(DegreeProgramDisplay degreeProgramDisplay) {
        DegreeProgram degreeProgram = new DegreeProgram();

        degreeProgram.setId(degreeProgramDisplay.getId());
        degreeProgram.setName(degreeProgramDisplay.getProgramName());
        degreeProgram.setDescription(degreeProgramDisplay.getProgramDescription());
        degreeProgram.setImage(degreeProgramDisplay.getImage());

        String certificationName = degreeProgramDisplay.getCertificationName();
        Integer certificationId = certificationMapper.getIdByName(certificationName);
        degreeProgram.setCertification(certificationId);

        String schoolName = degreeProgramDisplay.getHarvardSchoolName();
        Integer schoolId = harvardSchoolMapper.getIdBySchoolName(schoolName);
        degreeProgram.setHarvardSchool(schoolId);

        return degreeProgram;
    }

}
