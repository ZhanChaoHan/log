package com.jachs.log4j;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Before;
import org.junit.Test;

/***
 * 
 * @author zhanchaohan
 *
 */
public class Log4jTest {
	private static Logger logger = Logger.getLogger(Log4jTest.class);

	@Before
	public void init() {
//		BasicConfigurator.configure ();//默认的配置
		PropertyConfigurator.configure(Log4jTest.class.getResource("/log4j.properties"));
//		DOMConfigurator.configure ( String filename )// ：读取XML形式的配置文件
	}

	@Test
	public void test() {
		logger.info("普通Info信息");
		logger.debug("调试debug信息");
		logger.error("报错error信息");
		logger.warn("警告warn信息");
		logger.fatal("严重错误fatal信息");
	}
}
