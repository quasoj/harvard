package edu.harvard.academics.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Certification {

    private Integer id;

    private String name;
    private String fullname;
    private String degreeLevel;

}
