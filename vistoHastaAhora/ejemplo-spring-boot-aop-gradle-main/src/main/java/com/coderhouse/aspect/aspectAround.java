package com.coderhouse.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class aspectAround {

    private static final Logger logger = LoggerFactory.getLogger(aspectAround.class);

    @Pointcut("within(@org.springframework.stereotype.Service *) && !execution (* com.coderhouse.service.ClienteService.deleteCliente(..))")
    void serviceClassMethods(){}

    @Around("serviceClassMethods()")
    Object mostrarTiempoDeEjecucion(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.nanoTime();
        Object cliente = pjp.proceed();
        long end = System.nanoTime();
        logger.info("Duración de la ejecución: {} ms", TimeUnit.NANOSECONDS.toMillis(end - start));
        return cliente;
    }
}