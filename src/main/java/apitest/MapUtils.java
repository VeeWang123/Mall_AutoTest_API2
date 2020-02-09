package apitest;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * 统一字符串转map封装
 *
 */
public class MapUtils {
     static String regx1="&";
     static String regx2=";";
	
	private static Map<String,Object> covertStringToMap(String params, String regx) {
		// "method=loginMobile&loginname=test1&loginpass=test1&testloginpass=您好"
		// String "userId=md5", String ";"
		if(!StringUtils.isEmpty(params)) {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			String[] paStrings= params.split(regx);
			//userId=""
			for (int i = 0; i < paStrings.length; i++) {
				String param = paStrings[i];
				int index=param.indexOf("=");
//				String[] params_array=param.split("=");
//				if(params_array.length>1) {
					map.put(param.substring(0,index), param.substring(index+1));
//				}
			}
			return map;
		}
		return null;
	}
	
	
	public static Map<String,Object> covertStringToMap1(String params) {
		return covertStringToMap(params,regx1);
	}
	
	public static Map<String,Object> covertStringToMap2(String params) {
		return covertStringToMap(params,regx2);
	}

	  public static void main(String[] args){
		  Map<String,Object> converted = MapUtils.covertStringToMap2("token=hZ3VGKtTKuw2KVQNTKQ2v96yLuQrf5/L/B0T+5F8tVrviF95BPSfcoaCoWtnuyOb1pRuEvMjFJRQVm5H4JcrHMbLcnSsimY+0hSEwlMNn+6y3RUj4UU9AZd4Y1G8QzPtBENj5nV3VY0okqz07a3FJg==");
		  System.out.println(converted);


	    }

}
