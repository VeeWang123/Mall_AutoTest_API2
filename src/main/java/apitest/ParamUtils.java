package apitest;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPath;

/**
 * 参数提取和替换
 *
 */
public class ParamUtils {

	private static ThreadLocal<Map<String, Object>> param_map = new ThreadLocal<Map<String,Object>>(){

		@Override
		protected Map<String, Object> initialValue() {
			return new LinkedHashMap<String, Object>();
		}


		
	};

	public static void addMap(String key, String value) {
		param_map.get().put(key, value);
	}

	/**
	 * 对象数据放到全局map 反射
	 * 
	 * @param o
	 */
	public static void addFromObject(Object o) {
		Class class1 = o.getClass();
		Field[] fields = class1.getDeclaredFields();
		for (Field field : fields) {
			String key = field.getName();
			try {
				Object value = BeanUtils.getProperty(o, key);
				param_map.get().put(key, value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 对象数据放到全局map 反射
	 *
	 * @param map
	 */
	public static void addFromMap(Map<String,String> map) {
		
		
		Class class1 = map.getClass();
		Field[] fields = class1.getDeclaredFields();
		Set<String> keySet = map.keySet();

			for (String key : keySet) {
			System.out.println(map.get(key).getClass());
			String value = (String)map.get(key);
			param_map.get().put(key+"_g", value);

		}
	}

	// String json =
	// "{\"code\":\"1\",\"data\":[{\"name\":\"testfan0\",\"pwd\":\"pwd0\"},{\"name\":\"testfan1\",\"pwd\":\"pwd1\"},{\"name\":\"testfan2\",\"pwd\":\"pwd2\"}]}";
	// id=code;names=name
	public static void addFromJson(String json, String regx) {
		if (JSON.isValid(json) && !StringUtils.isEmpty(regx)) {
			Map<String, Object> map = MapUtils.covertStringToMap2(regx);
			if (map != null && !map.isEmpty()) {
				Set<String> keySet = map.keySet();
				for (String key : keySet) {
					String regx_value = map.get(key).toString();  //id
					System.out.println("addFromJson regx_value: "+regx_value);
					Object object = JSONPath.read(json, regx_value);  //value list
					if (object == null) {
						object = JSONPath.read(json, ".." + regx_value);
					}
					System.out.println("object class: "+object.getClass());
					System.out.println("addFromJson regx_value object: "+object);
					// 多个
					if (object instanceof List) {
						List<Object> listObjects = (List<Object>) object;
						for (int i = 0; i < listObjects.size(); i++) {
							param_map.get().put(key + "_g" + i, listObjects.get(i));
						}
					} else {
						param_map.get().put(key, object);
					}
				}
				System.out.println("param_map: "+param_map.get().toString());

				map.clear();
			}
		}
	}

	public static void clear() {
		param_map.get().clear();
		System.out.println(param_map);
	}

	// http://www.baidu.com?pid=${id}&test=${names_g1}
	// ${(.+?)}
	static String pattern = "\\$\\{(.+?)\\}"; // 非贪婪
	static Pattern r = Pattern.compile(pattern);

	public static String replace(String str) {
		System.out.println("before replacing: "+str);
		System.out.println("para map: "+ param_map.get().toString());
		if (!StringUtils.isEmpty(str)) {
			Matcher matcher = r.matcher(str);
			while (matcher.find()) {
				String toReplace = matcher.group();
				String toReplaceKey = matcher.group(1);  //id
				System.out.println("toReplaceKey: "+toReplaceKey);
				System.out.println(param_map.get().toString());
				System.out.println("getValueFromMap(toReplaceKey): "+getValueFromMap(toReplaceKey));
				str = str.replace(toReplace, getValueFromMap(toReplaceKey));  //replace(pid=${id},getValueFromMap(id))
			}
		}
		System.out.println("after replacing: "+str);
		return str;
	}

	private static String getValueFromMap(String key) {
		Object object = param_map.get().get(key);
		if (object == null) {
			return "";
		}
		return object.toString();
	}

	public static void main(String[] args) {
		String json = "{\"code\":\"1\",\"data\":[{\"name\":\"testfan0\",\"pwd\":\"pwd0\"},{\"name\":\"testfan1\",\"pwd\":\"pwd1\"},{\"name\":\"testfan2\",\"pwd\":\"pwd2\"}]}";
		System.out.println(json);
		String regx = "id=code;names=name";
		addFromJson(json, regx);
		System.out.println(param_map.get());
		String url = "http://www.baidu.com?pid=${id}&test=${names_g1}";
		url = replace(url);
		System.out.println(url);

	}

}
