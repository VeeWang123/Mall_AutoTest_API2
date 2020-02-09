package apitest.db;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.github.checkpoint.CheckPointUtils;
import com.github.checkpoint.JsonCheckResult;

import apitest.ParamUtils;

public class DbCheckUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(DbCheckUtils.class);
	
	public static String check(String dbcheck) {

		if(!StringUtils.isEmpty(dbcheck)) {
//			dbcheck = ParamUtils.replace(dbcheck);
			System.out.println("check database: "+dbcheck);
			String[] dbcheck_array = dbcheck.split(";");
			String sql="";
			String dbcheckpoint="";
			if(dbcheck_array.length>=1) {
				sql=dbcheck_array[0];
			}
			if(dbcheck_array.length>=2) {
				dbcheckpoint=dbcheck_array[1];
			}
			if(dbcheck_array.length>=3) {
				if(!StringUtils.isEmpty(dbcheckpoint)) {
				String dbType=dbcheck_array[2];
				QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource(dbType));
					System.out.println("DB connected");
				try {
					List<Map<String,Object>> map = runner.query(sql, new MapListHandler());
					System.out.println("DB return mapToString: "+map.toString());
					CheckPointUtils.openLog =true;  //输出checkpoint 过程
					System.out.println(map);
					System.out.println(JSON.toJSONString(map));
					System.out.println(dbcheckpoint);
					JsonCheckResult result = CheckPointUtils.check(JSON.toJSONString(map), dbcheckpoint);
					return result.getMsg();
				} catch (SQLException e) {
					e.printStackTrace();
					logger.error(e.getMessage());
				}
			}
			}
			return "数据库校验过程失败，无返回结果，请检查你的excel内容";
		}
		
		return "没有设置数据库检查点";
	}
	
	
	public static void main(String[] args) {
		ParamUtils.addMap("id", "21da27ab-1237-4e22-95ee-11379a7ba333");
		String dbcheck2 ="select * from t_user_test where loginname=\"test0\",uid=9CC972DFA2D4481F89841A46FD1B3E7B,mysql1";
//		String dbcheck2="select * from  t_user_test,$.size>1, ";   //loginname[1]=test1
		System.out.println(dbcheck2);
		String checkResult = check(dbcheck2);
		System.out.println(checkResult);
	}
	

}
