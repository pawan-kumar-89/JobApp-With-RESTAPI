package com.example.spring_boot_rest.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAspect {

    public static final Logger LOGGER=LoggerFactory.getLogger(LoggingAspect.class);



    //@Before("execution (* com.example.spring_boot_rest.service.*.*(..))")
    @Before("execution (* com.example.spring_boot_rest.service.JobService.returnJob(..)) || execution (* com.example.spring_boot_rest.service.JobService.updateJob(..))")
    public void logMethodCall(JoinPoint jp) {
        LOGGER.info("Method Called "+ jp.getSignature().getName());
    }

    // this after executed as finally
    @After("execution (* com.example.spring_boot_rest.service.JobService.returnJob(..)) || execution (* com.example.spring_boot_rest.service.JobService.updateJob(..))")
    public void logMethodExecuted(JoinPoint jp) {
        LOGGER.info("Method Executed "+ jp.getSignature().getName());
    }

    @AfterThrowing("execution (* com.example.spring_boot_rest.service.JobService.returnJob(..)) || execution (* com.example.spring_boot_rest.service.JobService.updateJob(..))")
    public void logMethodCrash(JoinPoint jp) {
        LOGGER.info("Method has some issue "+ jp.getSignature().getName());
    }

    @AfterReturning("execution (* com.example.spring_boot_rest.service.JobService.returnJob(..)) || execution (* com.example.spring_boot_rest.service.JobService.updateJob(..))")
    public void logMethodExecutedSucces(JoinPoint jp) {
        LOGGER.info("Method executed succesfully "+ jp.getSignature().getName());
    }
}