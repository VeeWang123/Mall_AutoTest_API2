package apitest;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

public class FunctionUtils {

	static String pattern = "\\#\\{(.+?)\\}"; // 非贪婪
	static Pattern r = Pattern.compile(pattern);

	//例子1：头部 userId=#{__md5(${loginname},${loginpass},token)}
	//例子2：参数 #{_AES{"phoneNumber":"123","password":"123","type":0}}
	public static String replace(String str) {
		if (!StringUtils.isEmpty(str)) {
			Matcher matcher = r.matcher(str);
			while (matcher.find()) {
				String toReplace = matcher.group();
				String funtionKey = matcher.group(1); //__md5(name,pwd,token)；_AES{"phoneNumber":"123","password":"123","type":0
				str = str.replace(toReplace, getFuntionValue(funtionKey));  //userId=md5(name,pwd)
			}
		}
		return str;
	}

	private static String getFuntionValue(String funtionKey) { //__md5(${loginname},${loginpass},token)
		if ("__UUID".equalsIgnoreCase(funtionKey)) {
			return UUID.randomUUID().toString();
		} else if (funtionKey.startsWith("__md5")) {  //md5应用举例 https://blog.csdn.net/weixin_42204641/article/details/82734505
			// __md5(abc,abc) ((.+?)) split
			String[] funtion_arg = getFuntionArgs(funtionKey);  //${loginname},${loginpass},token
			String md5="";
			if (funtion_arg != null && funtion_arg.length > 1) {
				md5= DigestUtils.md5Hex(funtion_arg[0] + funtion_arg[1]);
			}
			if (funtion_arg.length > 2) {
				ParamUtils.addMap(funtion_arg[2], md5); //key=token,value=md5(name,pwd)
			}
			return md5;
		} else if ("__time".equalsIgnoreCase(funtionKey)) {
			return "" + System.currentTimeMillis();
		} else if (funtionKey.startsWith("__Random")) {
			String[] funtion_arg = getFuntionArgs(funtionKey);
			int randomValue = 0;
			if (funtion_arg.length > 1) {
				randomValue = RandomUtils.nextInt(Integer.valueOf(funtion_arg[0]), Integer.valueOf(funtion_arg[1]));
			}
			if (funtion_arg.length > 2) {
				ParamUtils.addMap(funtion_arg[2], String.valueOf(randomValue));
			}
			return String.valueOf(randomValue);

		}else if(funtionKey.startsWith("_AES")){
			String originParaJson=funtionKey.substring(4)+"}";
			String AESParaJson= null;
			try {
				AESParaJson = AES.encrypt(originParaJson, "dd7e37d9f3287cd89ccb736c69074e27"); //测试环境key一样么？
				AESParaJson="{\"data\":\"" + AESParaJson + "=\",\"tk\":\"dd7e37d9f3287cd89ccb736c69074e27\"";
			} catch (Exception e) {
				e.printStackTrace();
			}
			return AESParaJson;
		}
		return "";
	}


	private static String[] getFuntionArgs(String funtionKey) { //__md5(${loginname},${loginpass},token)
		String pattern = "\\((.+?)\\)"; // 非贪婪
		Pattern r = Pattern.compile(pattern);
		Matcher matcher = r.matcher(funtionKey);
		String[] funtion_arg = null;
		if (matcher.find()) {
			System.out.println(matcher.group(1));  //${loginname},${loginpass},token
			funtion_arg = matcher.group(1).split(",");
		}
		return funtion_arg;
	}

}
