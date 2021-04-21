package com.jachs.log4j.datasource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.spi.LoggingEvent;

/***
 * 
 * @author zhanchaohan
 *
 */
public class DataSourceAppender extends org.apache.log4j.AppenderSkeleton implements org.apache.log4j.Appender {
	private boolean isInit = false;// 是否初始化数据源

	protected String configerfile;// 配置文件地址
	protected String dataSource;// 数据源类型
	protected String sql;// 执行SQL

	private Properties properties = new Properties();

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getConfigerfile() {
		return configerfile;
	}

	public void setConfigerfile(String configerfile) {
		this.configerfile = configerfile;
	}

	public void close() {
		System.out.println("close");
	}

	public boolean requiresLayout() {
		return true;
	}

	@Override
	protected void append(LoggingEvent event) {
		String level = event.getLevel().toString();
		String category = event.getLocationInformation().getClassName();
		String thread = event.getThreadName();
		Long timestamp = event.getTimeStamp();
		String locationInfo = event.getLocationInformation().fullInfo;
		String message = event.getMessage().toString();

		if (!isInit) {
			try {
				properties.load(DataSourceAppender.class.getResourceAsStream(configerfile));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				initDataBase();// 初始化数据源
			} catch (Exception e) {
				e.printStackTrace();
			}
			isInit = true;
		}
		executeSQL(level, category, thread, timestamp, locationInfo, message);
	}

	private void executeSQL(String level, String category, String thread, Long timestamp, String locationInfo,
			String message) {
		Connection connection = null;
		try {
			if (dataSource.equalsIgnoreCase("dbcp")) {
				connection = bds.getConnection();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, level);
			preparedStatement.setString(2, category);
			preparedStatement.setString(3, thread);
			preparedStatement.setString(4, sdf.format(new Date(timestamp)));
			preparedStatement.setString(5, locationInfo);
			preparedStatement.setString(6, message);

			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static BasicDataSource bds;

	private void initDataBase() {
		System.out.println("init");
		if (StringUtils.isBlank(configerfile)) {
			System.out.println("数据源配置文件地址为空");
			return;
		}
		if (StringUtils.isBlank(dataSource)) {
			System.out.println("数据源类型为空");
			return;
		}

		if (dataSource.equalsIgnoreCase("dbcp")) {
			BasicDataSourceFactory bdsf = new BasicDataSourceFactory(); // dbcp数据源
			try {
				bds = BasicDataSourceFactory.createDataSource(properties);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("不支持当前数据库类型");
		}
	}

}
