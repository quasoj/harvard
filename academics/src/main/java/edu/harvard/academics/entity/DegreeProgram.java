package edu.harvard.academics.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DegreeProgram {

    private Integer id;

    private String name;
    private Integer certification;
    private Integer harvardSchool;
    private String description;
    private String image;

    private Timestamp createAt;
    private Timestamp updateAt;

}
