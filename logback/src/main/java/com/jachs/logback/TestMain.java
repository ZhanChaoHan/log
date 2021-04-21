package com.jachs.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author zhanchaohan
 *
 */
public class TestMain {
	private final static Logger logger = LoggerFactory.getLogger(TestMain.class);

	public static void main(String[] args) {
		logger.info("普通Info信息{}", "AAAA");
		logger.debug("调试debug信息{},{}","A","B");
		logger.error("报错error信息");
		logger.warn("警告warn信息");
		logger.trace("trace");
	}
}
