package apitest;

import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

public class FunctionTest {
	static String pattern = "\\#\\{(.+?)\\}"; // 非贪婪
	static Pattern r = Pattern.compile(pattern);
	
	public static void main(String[] args) {
		String url ="http://www.baidu.com?test=#{__md5(${loginname},${loginpass})}&id=#{__UUID}&time=#{__time}&r=#{__Random(1,100,random)}";
		ParamUtils.addMap("loginname", "abc");
		ParamUtils.addMap("loginpass", "abc");
		url = ParamUtils.replace(url);
		System.out.println(url);
		//#{(.+?)}
		Matcher matcher = r.matcher(url);
		while (matcher.find()) {
//			System.out.println(matcher.group());
//			System.out.println(matcher.group(1));
			String toReplace = matcher.group();
			String funtionKey = matcher.group(1);
			url=url.replace(toReplace, getFuntionValue(funtionKey));
		}
		System.out.println(url);
		String url2 ="http://www.baidu.com?r=${random}";
		url2 = ParamUtils.replace(url2);
		System.out.println(url2);
		
	}
	
	private static String getFuntionValue(String funtionKey) {
		if("__UUID".equalsIgnoreCase(funtionKey)) {
			return UUID.randomUUID().toString();
		}else if (funtionKey.startsWith("__md5")) {
			// __md5(abc,abc)  ((.+?)) split  
			 String []funtion_arg = getFuntionArgs(funtionKey);
			 if(funtion_arg!=null&&funtion_arg.length>1) {
				return DigestUtils.md5Hex(funtion_arg[0]+funtion_arg[1]); 
			 }
//			 String pattern = "\\((.+?)\\)"; // 非贪婪
//			 Pattern r = Pattern.compile(pattern);
//			 Matcher matcher= r.matcher(funtionKey);
//			 String []funtion_arg;
//			 if(matcher.find()) {
//				 System.out.println("  funtionKey  "+funtionKey);
//				 System.out.println(matcher.group());
//				 System.out.println(matcher.group(1));
//				 funtion_arg = matcher.group(1).split(",");
//				 if(funtion_arg.length>1) {
//					return DigestUtils.md5Hex(funtion_arg[0]+funtion_arg[1]); 
//				 }
//			 }
		}else if ("__time".equalsIgnoreCase(funtionKey)) {
			return ""+System.currentTimeMillis();
		}else if (funtionKey.startsWith("__Random")) {
			 System.out.println("  funtionKey __Random  "+funtionKey);
			 String []funtion_arg = getFuntionArgs(funtionKey);
			 System.out.println(funtion_arg.length);
			 System.out.println(funtion_arg[2]);
			 int randomValue = 0;
			 if(funtion_arg.length>1) {
				 randomValue=  RandomUtils.nextInt(Integer.parseInt(funtion_arg[0]), Integer.parseInt(funtion_arg[1]));
			 }
			 if(funtion_arg.length>2) {
				 ParamUtils.addMap(funtion_arg[2], String.valueOf(randomValue));
			 }
			 return String.valueOf(randomValue);
			
		}
		return "";
	}
	
	private static String[] getFuntionArgs(String funtionKey) {
		 String pattern = "\\((.+?)\\)"; // 非贪婪
		 Pattern r = Pattern.compile(pattern);
		 Matcher matcher= r.matcher(funtionKey);
		 String []funtion_arg = null;
		 if(matcher.find()) {
//			 System.out.println("  funtionKey  "+funtionKey);
//			 System.out.println(matcher.group());
			 System.out.println(matcher.group(1));
			 funtion_arg = matcher.group(1).split(",");
		 }
		 return funtion_arg;
	}
	


}
