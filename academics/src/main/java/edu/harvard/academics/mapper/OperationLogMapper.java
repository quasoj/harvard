package edu.harvard.academics.mapper;

import edu.harvard.academics.entity.OperationLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperationLogMapper {

    @Insert("insert into operation_log(operate_user, class_name, method_name, method_params, return_value) " +
            "value (#{operateUser}, #{className}, #{methodName}, #{methodParams}, #{returnValue})")
    void add(OperationLog operationLog);

}
