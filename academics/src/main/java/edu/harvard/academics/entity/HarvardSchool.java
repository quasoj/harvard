package edu.harvard.academics.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HarvardSchool {

    private Integer id;

    private String name;
    private String abbr;
    private String phone;
    private String email;
    private String address;

}
