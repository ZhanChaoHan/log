package com.jachs.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class RunnableTest implements Runnable{
    private final static Logger logger = LoggerFactory.getLogger("Async_FileLog");
    
    private String counterName;
    
    public RunnableTest(String counterName) {
        this.counterName = counterName;
    }
 
    public void run() {
        MDC.put("application.name", counterName);
        logger.info("start counter {}", counterName);
        MDC.remove("application.name");
    }


}
