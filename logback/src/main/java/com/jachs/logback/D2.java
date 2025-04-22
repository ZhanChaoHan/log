package com.jachs.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/***
 * @author zhanchaohan
 */
public class D2 {
    private final static Logger logger = LoggerFactory.getLogger("Async_FileLog");
    
    public static void main ( String[] args ) {
        MDC.put("application.name", "jachs_0");
        
        logger.info ( "info——一段话" );
        logger.debug( "debug——一段话" );
        logger.error( "error——一段话" );
        logger.trace ( "trace——一段话");
        logger.warn ( "warn——一段话");
        
        
        MDC.put("application.name", "jachs_1");
        logger.info ( "info——测试日志打印" );
        logger.debug( "debug——测试日志打印" );
        logger.error( "error——测试日志打印" );
        logger.trace ( "trace——测试日志打印");
        logger.warn ( "warn——测试日志打印");
        
        
        MDC.put("application.name", "jachs_0");
        logger.info ( "info——测试会不会覆盖前面打印" );
        logger.debug( "debug——测试会不会覆盖前面打印" );
        logger.error( "error——测试会不会覆盖前面打印" );
        logger.trace ( "trace——测试会不会覆盖前面打印");
        logger.warn ( "warn——测试会不会覆盖前面打印");
    }
}
