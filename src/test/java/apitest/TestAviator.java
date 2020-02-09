package apitest;

import com.googlecode.aviator.AviatorEvaluator;

import java.util.HashMap;
import java.util.Map;

public class TestAviator {

	public static void main(String[] args) {

		System.out.println("1+2+3+6");
		Long result = (Long) AviatorEvaluator.execute("1+2+3+6");
		System.out.println(result);

		Map<String, Object> env = new HashMap<String, Object>();
		env.put("data", 1);
		Boolean result1 = (Boolean) AviatorEvaluator.execute("data==1", env);
		//等号问题
		//result1 = (Boolean) AviatorEvaluator.execute("data=1", env);
		System.out.println(result1);
		
		//字符串问题
//		Map<String, Object> env2 = new HashMap<String, Object>();
//		env.put("data", "1");
//		Boolean result2 = (Boolean) AviatorEvaluator.execute("data=='1'", env2);
//		System.out.println(result2);
		
		//jsonpath组合问题 $.data=1
//		Map<String, Object> env2 = new HashMap<String, Object>();
//		env.put("$.data", 1);
//		Boolean result2 = (Boolean) AviatorEvaluator.execute("$.data==1", env2);
//		System.out.println(result2);
		
		
	}

}
