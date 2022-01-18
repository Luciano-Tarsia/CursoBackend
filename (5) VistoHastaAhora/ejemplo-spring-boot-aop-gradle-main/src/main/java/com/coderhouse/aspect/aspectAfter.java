package com.coderhouse.aspect;

import com.coderhouse.controller.configController;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class aspectAfter {

    private static final Logger logger = LoggerFactory.getLogger(aspectAfter.class);


    @Pointcut("@annotation(com.coderhouse.annotations.CustomMethodAnnotation)")
    void customMethodAnnotation() {}

    @AfterThrowing("execution(* com.coderhouse.service.ClienteService.update(..))")
    void despuesDeNombreVacio(){
        logger.info("El nombre del cliente a actualizar estaba vacio");
    }

    @After("customMethodAnnotation()")
    void despuesDeUpdateYDelete() {
        logger.info("Advice después de update o delete/ annotation");
    }

}