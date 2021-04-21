package com.jachs.log4j;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/***
 * 
 * @author zhanchaohan
 *
 */
public class PrintTest {
	private static Logger logger = Logger.getLogger(PrintTest.class);

	public static void main(String[] args) throws Exception {
		PropertyConfigurator.configure(PrintTest.class.getResource("/log4j.properties"));
		
		logger.info("普通Info信息");
		logger.debug("调试debug信息");
		logger.error("报错error信息");
		logger.warn("警告warn信息");
		logger.fatal("严重错误fatal信息");
	}
}
