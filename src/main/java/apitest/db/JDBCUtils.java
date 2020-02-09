package apitest.db;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {
	static ComboPooledDataSource ds =new ComboPooledDataSource();
	static ComboPooledDataSource ds_mysql_1;
	static ComboPooledDataSource ds_mysql_2;
	static ComboPooledDataSource ds_oracle;

	/*
	 * 根据类型获取不同连接池资源
	 */
	public static DataSource getDataSource(String type) {

		if(StringUtils.isEmpty(type)) {
			return ds;
		}else if ("mysql_1".equalsIgnoreCase(type.trim())) {
			if(ds_mysql_1==null) {
				ds_mysql_1 = new ComboPooledDataSource("mysql_1");
			}
			return ds_mysql_1;
		} else if ("mysql_2".equalsIgnoreCase(type.trim())) {
			if(ds_mysql_2==null) {
				ds_mysql_2 = new ComboPooledDataSource("mysql_2");
			}
			return ds_mysql_2;
		} else if ("oracle".equalsIgnoreCase(type.trim())) {
			if(ds_oracle==null) {
				ds_oracle = new ComboPooledDataSource("oracle");
			}
			return ds_oracle;
		}
		return ds;
	}

	//重载
	public static DataSource getDataSource() {
		return getDataSource("");
	}

	public static DataSource getDataSourceForOracle() {
		return getDataSource("oracle");
	}



}
