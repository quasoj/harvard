package edu.harvard.utils.aop;

import com.alibaba.fastjson.JSONObject;
import edu.harvard.academics.entity.OperationLog;
import edu.harvard.academics.service.OperationLogService;
import edu.harvard.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class LogRecordAspect {

    private final HttpServletRequest request;
    private final OperationLogService operationLogService;

    public LogRecordAspect(HttpServletRequest request, OperationLogService operationLogService) {
        this.request = request;
        this.operationLogService = operationLogService;
    }

    @Around("@annotation(edu.harvard.utils.anno.LogRecord)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {


        // get info
        String jwt = request.getHeader("token");
        Claims claims = JwtUtils.parseJwt(jwt);
        Integer userId = (Integer) claims.get("id");

        String className = joinPoint.getTarget().getClass().getName();

        String methodName = joinPoint.getSignature().getName();

        Object[] args = joinPoint.getArgs();

        // run
        Object result = joinPoint.proceed();

        OperationLog operationLog = new OperationLog();
        operationLog.setOperateUser(userId);
        operationLog.setClassName(className);
        operationLog.setMethodName(methodName);
        operationLog.setMethodParams(Arrays.toString(args));
        operationLog.setReturnValue(JSONObject.toJSONString(result));
        operationLogService.add(operationLog);
        log.info("AOP Operate Record Log" + operationLog);

        return result;
    }

}
