package com.jachs.log4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

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
//		DOMConfigurator.configure (Log4jTest.class.getResource("/log4j.xml"));// ：读取XML形式的配置文件
	}

	@Test
	public void test() {
		logger.info("普通Info信息");
		logger.debug("调试debug信息");
		logger.error("报错error信息");
		logger.warn("警告warn信息");
		logger.fatal("严重错误fatal信息");
	}
	
	@Test
	public void jdbcCreateTable() throws Exception {
		Connection connection=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf8&useSSL=true&serverTimezone=UTC", "root", "");
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		connection.createStatement().execute("CREATE TABLE `log_icecoldmonitor` ( "
				+ "  `Id` int(11) NOT NULL AUTO_INCREMENT, "
				+ "  `level` varchar(255) NOT NULL DEFAULT '' COMMENT '优先级', "
				+ "  `category` varchar(255) NOT NULL DEFAULT '' COMMENT '类目', "
				+ "  `thread` varchar(255) NOT NULL DEFAULT '' COMMENT '进程', "
				+ "  `time` varchar(30) NOT NULL DEFAULT '' COMMENT '时间', "
				+ "  `location` varchar(255) NOT NULL DEFAULT '' COMMENT '位置', "
				+ "  `note` text COMMENT '日志信息', "
				+ "  PRIMARY KEY (`Id`) "
				+ ")");
	}
	@Test
	public void jdbcShowTABLE() throws Exception {
		Connection connection=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf8&useSSL=true&serverTimezone=UTC", "root", "");
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		ResultSet resultSet=connection.prepareStatement("show tables").executeQuery();
		
		while(resultSet.next()) {
			System.out.println(resultSet.getString(1));
		}
	}
}
