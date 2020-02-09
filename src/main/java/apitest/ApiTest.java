package apitest;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import com.github.checkpoint.CheckPointUtils;
import com.github.checkpoint.JsonCheckResult;
import com.github.crab2died.ExcelUtils;

public class ApiTest {

	public static void main(String[] args) throws Exception {
		HttpClientUtils.openProxy = true;
		String path = System.getProperty("user.dir") + File.separator + "data" + File.separator + "apitest10.xlsx";

		List<TestResult> listResult = new ArrayList<TestResult>();
		List<ParamBean> params_list = ExcelUtils.getInstance().readExcel2Objects(path, ParamBean.class, 1);
		System.out.println(params_list);
		// 参数覆盖
		for (ParamBean paramBean : params_list) {
			// addmap 下面方法：把excel取出的object，按照ParaBean，取出属性和值，逐一赋值给param_map
			ParamUtils.addFromObject(paramBean);
			// 读原始数据(上一组数据被改掉了)
			List<TestCase> list = ExcelUtils.getInstance().readExcel2Objects(path, TestCase.class);
			for (TestCase testCase : list) {
				if (testCase.isRun()) {
					System.out.println("替换前--" + testCase);
					//参数替换
					params_replace(testCase);
					//函数支持
					funtion_replace(testCase);
					System.out.println("替换后--" + testCase);
					// 替换变量
					String result = "";
					if ("get".equals(testCase.getType())) {
						result = HttpClientUtils.doGet(testCase.getUrl());
					} else if ("post".equals(testCase.getType())) {
						result = HttpClientUtils.doPost(testCase.getUrl(),
								MapUtils.covertStringToMap1(testCase.getParams()));
					} else if ("postjson".equals(testCase.getType())) {
						result = HttpClientUtils.doPostJson(testCase.getUrl(), testCase.getParams(),
								MapUtils.covertStringToMap2(testCase.getHeaders()));
					}

					String testReulst_str = CheckPointUtils.check(result, testCase.getCheckpoint()).getMsg();  //code=${code}
					JsonCheckResult jsonCheck = CheckPointUtils.check(result, testCase.getCheckpoint());
					ParamUtils.addFromJson(result, testCase.getCorrelation());  //id=uid 如果getCorrelation是List<map>呢？放入param_map
                    //结果处理
					TestResult testResult = new TestResult();
					testResult.setTestResult(testReulst_str);
					BeanUtils.copyProperties(testResult, testCase);
					listResult.add(testResult);
				}
			}
			System.out.println(" 当前结果----");
			ParamUtils.clear();
		}
		// 最终结果处理
		// 写excel
		ExcelUtils.getInstance().exportObjects2Excel(listResult, TestResult.class, "result_" + getDate() + ".xlsx");
	}

	private static String getDate() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
		return simpleDateFormat.format(new Date());
	}

	private static void params_replace(TestCase testCase) {
		// url参数化支持
		testCase.setUrl(ParamUtils.replace(testCase.getUrl()));
		// body
		testCase.setParams(ParamUtils.replace(testCase.getParams()));
		// header
		testCase.setHeaders(ParamUtils.replace(testCase.getHeaders()));
		//检查点参数化
		testCase.setCheckpoint(ParamUtils.replace(testCase.getCheckpoint()));
	}
	
	private static void funtion_replace(TestCase testCase) {
		// url参数化支持
		testCase.setUrl(FunctionUtils.replace(testCase.getUrl()));
		// body
		testCase.setParams(FunctionUtils.replace(testCase.getParams()));
		// header
		testCase.setHeaders(FunctionUtils.replace(testCase.getHeaders()));
	}

}
