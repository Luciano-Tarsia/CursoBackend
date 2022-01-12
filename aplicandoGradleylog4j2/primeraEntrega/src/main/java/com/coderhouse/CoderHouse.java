package com.coderhouse;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CoderHouse {

    final static Logger logger = LogManager.getLogger(CoderHouse.class);

    public static void main(String[] args) {
        CoderHouse helloExample = new CoderHouse();
        helloExample.runMe("test");
    }

    private void runMe(String parameter) {
        if (logger.isDebugEnabled()) {
            logger.debug("This is debug : {} ", parameter);
        }

        if (logger.isInfoEnabled()) {
            logger.info("This is info : {} ", parameter);
        }

        logger.warn("This is warn : {} ", parameter);
        logger.error("This is error : {} ", parameter);
    }

}
