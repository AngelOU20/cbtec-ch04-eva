package edu.cibertec.cap04tasks;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* edu.cibertec.cap04tasks.controller.TaskController.*(..))")
    public void actionBefore(JoinPoint joinPoint) {
        System.out.println("Acción: Ejecución antes del método" + joinPoint.getSignature().getName());
    }

    @After("execution(* edu.cibertec.cap04tasks.controller.TaskController.*(..))")
    public void actionAfter(JoinPoint joinPoint) {
        System.out.println("Acción: Ejecución después del método" + joinPoint.getSignature().getName());
    }

    @Around("execution(* edu.cibertec.cap04tasks.controller.TaskController.*(..))")
    public Object actionAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        Long endTime = System.currentTimeMillis();

        Long executionTime = endTime - startTime;

        System.out.println("Tiempo empleado por " + joinPoint.getSignature().getName()
                + " es de " + executionTime + " ms");
        System.out.println("**********************************************");
        return result;
    }
}
