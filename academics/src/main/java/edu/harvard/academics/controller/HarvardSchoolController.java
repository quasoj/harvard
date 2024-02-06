package edu.harvard.academics.controller;

import edu.harvard.academics.entity.HarvardSchool;
import edu.harvard.academics.service.HarvardSchoolService;
import edu.harvard.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schools")
public class HarvardSchoolController {

    private final HarvardSchoolService harvardSchoolService;

    @Autowired
    public HarvardSchoolController(HarvardSchoolService harvardSchoolService) {
        this.harvardSchoolService = harvardSchoolService;
    }

    @GetMapping
    public Result list() {
        List<HarvardSchool> schools = harvardSchoolService.list();
        return Result.success(schools);
    }

    @PostMapping
    public Result add(@RequestBody HarvardSchool harvardSchool) {
        harvardSchoolService.add(harvardSchool);
        return Result.success();
    }

    @PutMapping
    public Result edit(@RequestBody HarvardSchool harvardSchool) {
        harvardSchoolService.edit(harvardSchool);
        return Result.success();
    }

    @DeleteMapping("/{ids}")
    public Result remove(@PathVariable List<Integer> ids) {
        harvardSchoolService.remove(ids);
        return Result.success();
    }

}
