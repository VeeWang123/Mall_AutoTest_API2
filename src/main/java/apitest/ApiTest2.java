package apitest;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;

import com.github.crab2died.ExcelUtils;

public class ApiTest2 {
	static String pattern = "\\$\\{(.+?)\\}"; // 非贪婪
	static Pattern r = Pattern.compile(pattern);
	static Map<String, Object> map = new HashMap<String, Object>();

	public static void main(String[] args) throws Exception {
		HttpClientUtils.openProxy = true;
		String path = System.getProperty("user.dir") + File.separator + "data" + File.separator + "apitest6-2.xlsx";
		// List<TestCase> list = ExcelUtils.getInstance().readExcel2Objects(path,
		// TestCase.class);

		// 先处理参数
		map.put("id", "uuid0000");
			// 读原始数据(上一组数据被改掉了)
			List<TestCase> list = ExcelUtils.getInstance().readExcel2Objects(path, TestCase.class);
			for (TestCase testCase : list) {
				List<ParamBean> params_list = ExcelUtils.getInstance().readExcel2Objects(path, ParamBean.class, 1);
				  for (ParamBean paramBean : params_list) {
					Field[] fields = ParamBean.class.getDeclaredFields();
					for (Field field : fields) {
						// System.out.println(field.getName()+" "+BeanUtils.getProperty(paramBean,
						map.put(field.getName(), BeanUtils.getProperty(paramBean, field.getName()));
					}
					if (testCase.isRun()) {
						System.out.println("替换前--" + testCase);
						replace(testCase);
						System.out.println("替换后--" + testCase);
						// 替换变量
						if ("get".equals(testCase.getType())) {
							HttpClientUtils.doGet(testCase.getUrl());
						} else if ("post".equals(testCase.getType())) {
							HttpClientUtils.doPost(testCase.getUrl(), MapUtils.covertStringToMap1(testCase.getParams()));
						} else if ("postjson".equals(testCase.getType())) {
							HttpClientUtils.doPostJson(testCase.getUrl(), testCase.getParams(),
									MapUtils.covertStringToMap2(testCase.getHeaders()));
						}
			   }
			}
			System.out.println(" 当前结果----");
			// map.clear();
		}
	}
	
	private static void replace(TestCase testCase) {  //sheet2都参数值全部赋值到sheet1，比如 ${loginname}
		//url参数化支持
		testCase.setUrl(getPattern(testCase.getUrl()));
		//body
		testCase.setParams(getPattern(testCase.getParams()));
		//header
		testCase.setHeaders(getPattern(testCase.getHeaders()));
	}
	
	private static String getPattern(String str) {
		if(!StringUtils.isEmpty(str)) {
			Matcher matcher = r.matcher(str);
			while (matcher.find()) {
				String toReplace = matcher.group(); //${loginname}
				String value = String.valueOf(map.get(matcher.group(1)));  //shhet2 loginname的value
				str = str.replace(toReplace, value);
			}
		}
		return str;
	}

}
