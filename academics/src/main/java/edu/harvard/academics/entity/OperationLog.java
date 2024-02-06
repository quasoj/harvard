package edu.harvard.academics.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationLog {

    private Integer id;

    private Integer operateUser;
    private String className;
    private String methodName;
    private String methodParams;
    private String returnValue;

    private Timestamp createAt;

}
