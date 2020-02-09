package apitest;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.github.checkpoint.CheckPointUtils;
import com.github.checkpoint.JsonCheckResult;

/**
 * dbutils,ParamUtils,CheckPointUtils
 * @author pc
 *
 */
public class DbCheckTest {
	
	public static String check(String dbcheck) {
		if(!StringUtils.isEmpty(dbcheck)) {
			dbcheck = ParamUtils.replace(dbcheck);
			String[] dbcheck_array= dbcheck.split(";");
			String sql="";
			String checkpoint = null;
		    if(dbcheck_array.length>=1) {
		    	sql = dbcheck_array[0];
		    }
		    if(dbcheck_array.length>=2) {
		    	checkpoint = dbcheck_array[1];
		    	System.out.println(checkpoint);
		    }
		    if(dbcheck_array.length>=3) {
		    	String dbType = dbcheck_array[2];
		    	if(!StringUtils.isEmpty(checkpoint)) {
		    		DataSource dataSource = apitest.db.JDBCUtils.getDataSource(dbType);
			    	QueryRunner runner = new QueryRunner(dataSource);
			    	try {
						List<Map<String,Object>> map = runner.query(sql, new MapListHandler());
						System.out.println("map: "+map);
						JsonCheckResult jsonCheck = CheckPointUtils.check(JSON.toJSONString(map), checkpoint);
						return jsonCheck.getMsg();
					} catch (SQLException e) {
						e.printStackTrace();
					}
		    	}
		    }
		    
		}
		return "没有设置数据库检查点";
	}
	
	public static void main(String[] args) {
		ParamUtils.addMap("id", "10086");
		String dbcheck="select min(a.SALE_PRICE1) as minSalePriceDB from cmc_item_price a where a.ITEM_LINE_ID in (select cmc_item_line.ITEM_LINE_ID from cmc_item, cmc_item_line where cmc_item.ITEM_ID=cmc_item_line.ITEM_ID and cmc_item.ITEM_CODE=\"10003194\"),size>0,mysql_1";
		String result = check(dbcheck);
		System.out.println(result);
		//String dbcheck2="select * from  t_user_test where uid=${id},size()>0,mysql1";
//		String[] dbcheck_array= dbcheck.split(",");
//		String sql="";
//	    if(dbcheck_array.length>=1) {
//	    	sql = dbcheck_array[0];
//	    	sql = ParamUtils.replace(sql);
//	    	System.out.println(sql);
//	    	//QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
//	    }
//	    String checkpoint = null;
//	    if(dbcheck_array.length>=2) {
//	    	checkpoint = dbcheck_array[1];
//	    	checkpoint = ParamUtils.replace(checkpoint);
//	    	System.out.println(checkpoint);
//	    	//QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
//	    }
//	    if(dbcheck_array.length>=3) {
//	    	String dbType = dbcheck_array[2];
//	    	DataSource dataSource = apitest.db.JDBCUtils.getDataSource(dbType);
//	    	QueryRunner runner = new QueryRunner(dataSource);
//	    	try {
//	    		System.out.println(sql);
//				List<Map<String,Object>> map = runner.query(sql, new MapListHandler());
//				if(!StringUtils.isEmpty(checkpoint)&&!map.isEmpty()) {
//					CheckPointUtils.openLog=true;
//					System.out.println(JSON.toJSONString(map)+" check "+ checkpoint);
//					JsonCheckResult jsonCheck = CheckPointUtils.check(JSON.toJSONString(map), checkpoint);
//					System.out.println(jsonCheck.getMsg());
//				}else {
//					 if(!map.isEmpty()) {
//					    	System.out.println("数据库检查成功");
//					    }else {
//					    	System.out.println("数据库检查失败");
//					    }
//				}
//			   
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//	    }
//	    
	    
	}

}
