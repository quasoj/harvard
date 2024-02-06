package edu.harvard.academics.controller;

import edu.harvard.academics.entity.DegreeProgramDisplay;
import edu.harvard.academics.service.DegreeProgramService;
import edu.harvard.utils.PageBean;
import edu.harvard.utils.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/programs")
public class DegreeProgramController {
    private final DegreeProgramService degreeProgramService;

    @Autowired
    public DegreeProgramController(DegreeProgramService degreeProgramService) {
        this.degreeProgramService = degreeProgramService;
    }

    @GetMapping
    public Result page(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "8") Integer pageSize,
            ArrayList<String> schools,
            String degreeLevel,
            String nameBySearch
    ) {
        PageBean paged = degreeProgramService.page(pageNum, pageSize, schools, degreeLevel, nameBySearch);
        return Result.success(paged);
    }

    @PostMapping
    public Result add(@RequestBody DegreeProgramDisplay degreeProgramDisplay) {
        degreeProgramService.add(degreeProgramDisplay);
        return Result.success();
    }

    @PutMapping
    public Result edit(@RequestBody DegreeProgramDisplay degreeProgramDisplay) {
        degreeProgramService.edit(degreeProgramDisplay);

        return Result.success();
    }

    @DeleteMapping("/{ids}")
    public Result remove(@PathVariable List<Integer> ids) {
        degreeProgramService.remove(ids);
        return Result.success();
    }

}
