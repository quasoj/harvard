package edu.harvard.utils.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class TimeAspect {

    @Pointcut("execution(* edu.harvard.academics.service.*.*(..))")
    private void pointCut() {}

    @Pointcut("@annotation(edu.harvard.utils.anno.LogRecord)")
    private void pointCutAnno() {}

    // Point Cut
    @Around("execution(public * edu.harvard.academics.service.*.*(..)) throws *")
    public Object recordTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        // Advice
//        log.info("ASPECT AROUND BEFORE");
        long begin = System.currentTimeMillis();

        // Joint Point
        Object object = proceedingJoinPoint.proceed();

        long end = System.currentTimeMillis();
//        log.info("ASPECT AROUND AFTER");

        log.info(proceedingJoinPoint.getSignature() + " time cost: {}ms", end - begin);

        return object;
    }

//    @Before("pointCutAnno()")
    public void before() {
        log.info("ASPECT BEFORE @ANNO");
    }

//    @After("pointCutAnno()")
    public void after() {
        log.info("ASPECT AFTER @ANNO");
    }

//    @AfterReturning("pointCut()")
    public void afterReturning() {
        log.info("ASPECT AFTER RETURNING");
    }

//    @AfterThrowing("pointCut()")
    public void afterThrowing() {
        log.info("ASPECT AFTER THROWING");
    }

}
