package edu.harvard.academics.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DegreeProgramDisplay {

    private Integer id;

    private String programName;
    private String certificationName;
    private String harvardSchoolName;
    private String programDescription;
    private String image; // URL

}
