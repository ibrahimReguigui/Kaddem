package tn.esprit.spring.kaddem.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;



@Component
@Aspect

public class LoggingAspect {

    private static final Logger logger = LogManager.getLogger(LoggingAspect.class);

    @Before("execution(* tn.esprit.spring.kaddem.service.*.*(..))")
    public void logMethodEntry(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        logger.info("In method " + name );
    }
    @After("execution(* tn.esprit.spring.kaddem.service.*.*(..))")
    public void logMethodExit(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        logger.info("Out method " + name );
    }
    @AfterReturning("execution(* tn.esprit.spring.kaddem.service.*.*(..))")
    public void logMethodReturn(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        logger.info("Method " + name + " worked successfully");
    }
    @AfterThrowing("execution(* tn.esprit.spring.kaddem.service.*.*(..))")
    public void logMethodThrow(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        logger.error("Method " + name + " throwed an exception");
    }
    @Around("execution(* tn.esprit.spring.kaddem.service.*.*(..))")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object obj = pjp.proceed();
        long elapsedTime = System.currentTimeMillis() - start;
        logger.info("Method execution time: " + elapsedTime + " milliseconds.");
        return obj;
    }
}