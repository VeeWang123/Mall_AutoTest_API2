package testng;

import apitest.*;
import apitest.MapUtils;
import apitest.db.DbCheckUtils;
import apitest.thread.ApiThreadTest;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONPath;
import checkpoint.CheckPointUtils;
import com.github.crab2died.ExcelUtils;
import com.github.crab2died.exceptions.Excel4JException;
import io.qameta.allure.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.StreamHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ApiTestNgVandreamSample {

	String path = System.getProperty("user.dir") + File.separator + "data" + File.separator + "apitest12.xlsx";
	private static final Logger logger = LoggerFactory.getLogger(ApiTestNgVandreamSample.class);

	@BeforeClass
	public void beforeClass() {
		HttpClientUtils.openProxy = false;
	}  //需要设置，true：开启代理

//	@AfterClass
//	public void afterClass() {
//		try {
//			String path_result = System.getProperty("user.dir") + File.separator + "result" + File.separator;
//			File result_file = new File(path_result);
//			if (!result_file.exists()) {
//				result_file.mkdirs();
//			}
//			String resultFile = path_result + "result_" + ApiThreadTest.getDate() + ".xlsx";
//			ExcelUtils.getInstance().exportObjects2Excel(ApiThreadTest.listResult, TestResult.class, resultFile);
//			System.out.println("测试发邮件");
//
//			EmailUtils.sendEmailsWithAttachments("测试结果", "请查收测试结果", resultFile);
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("结果处理");
//			logger.error("error {} {}", e, "测试");
//		}
//	}

	@Epic("并行单元测试")//测试集
    @Story("单元测试")//测试case
    @Step("加入购物车")//测试步骤
    @Severity(SeverityLevel.CRITICAL)//设置case的优先级
	@Description("商城接口用例测试")
	@Test(dataProvider = "excelobject",threadPoolSize = 10)
	public void testcase(ParamBeanLogin paramBean) {
		System.out.println(Thread.currentThread().getName() + " " + paramBean);
		List<TestResult> runTestResults = new ArrayList<TestResult>();
		try {
			// addmap
			ParamUtils.addFromObject(paramBean);
			// 读原始数据(上一组数据被改掉了)
			List<TestCase> list = ExcelUtils.getInstance().readExcel2Objects(path, TestCase.class);
			for (TestCase testCase : list) {
				if (testCase.isRun()) {
//					System.out.println("替换前--" + testCase);
//					// 参数替换
//					ApiThreadTest.params_replace(testCase);
//					// 函数支持
//					ApiThreadTest.funtion_replace(testCase);
//					System.out.println("替换后--" + testCase);
//					// 替换变量
//					String result = "";
//					if ("get".equals(testCase.getType())) {
//						result = HttpClientUtils.doGet(testCase.getUrl());
//					} else if ("post".equals(testCase.getType())) {
//						result = HttpClientUtils.doPost(testCase.getUrl(),
//								MapUtils.covertStringToMap1(testCase.getParams()));
//					} else if ("postjson".equals(testCase.getType())) {
//						System.out.println("headers:"+testCase.getHeaders());
//						if (testCase.getHeaders()!=null){
//							System.out.println("headers convert to map: "+MapUtils.covertStringToMap2(testCase.getHeaders()).getClass());
//							System.out.println("headers convert to map: "+MapUtils.covertStringToMap2(testCase.getHeaders()));
//
//						}
//						result = HttpClientUtils.doPostJson(testCase.getUrl(), testCase.getParams(), MapUtils.covertStringToMap2(testCase.getHeaders()));
//					}
//
//					String testReulst_str = CheckPointUtils.check(result, testCase.getCheckpoint()).getMsg();
//					JsonCheckResult jsonCheck = CheckPointUtils.check(result, testCase.getCheckpoint());
//					System.out.println("********below is log********");
//					System.out.println("paramBean：" + paramBean);
//					System.out.println("testCase： " + testCase.getCaseName());
//					System.out.println("params: " + testCase.getParams());
//					System.out.println("response: " + result);
//					System.out.println("checkPoint result：" + jsonCheck.isResult() );
//					System.out.println("checkPoint message: " + jsonCheck.getMsg());
//
////					assertTrue(jsonCheck.isResult(),jsonCheck.getMsg());
//					ParamUtils.addFromJson(result, testCase.getCorrelation());
//					//update dbcheckPoint
//					testCase.setDbcheckpoint(ParamUtils.replace(testCase.getDbcheckpoint()));
//					// 数据检查
//					String dbcheckResult = DbCheckUtils.check(testCase.getDbcheckpoint());
//					// 结果处理
//					TestResult testResult = new TestResult();
//					testResult.setTestResult(testReulst_str);
//					testResult.setDbtestResult(dbcheckResult);
//					BeanUtils.copyProperties(testResult, testCase);
					if (testCase.getCaseName().contains("测试4")){
						//获取测试4的测试数据，然后循环运行
						List<Map<String,String>> datas=dataForCase4();
						for (Map<String,String> data: datas){
							ParamUtils.addFromMap(data);
							//修改testCase的循环参数：
							setcase(testCase);
							TestResult testResult=runcase(testCase);
							runTestResults.add(testResult);
//							Assert.assertTrue(testResult.isPass(),testCase.getCaseName()+": "+testResult.getTestResult()+";"+testResult.getDbtestResult());
						}
					}else{
						setcase(testCase);
						TestResult testResult=runcase(testCase);
						runTestResults.add(testResult);
//						Assert.assertTrue(testResult.isPass(),testCase.getCaseName()+": "+testResult.getTestResult()+";"+testResult.getDbtestResult());
					}
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}finally {
			System.out.println(" 当前结果----");
			System.out.println("runTestResults: " + runTestResults);
			ParamUtils.clear();
			//ApiThreadTest生命周期，同testNG运行周期。可以把testNG的afterClass，afterSuite，Listener等都看成main thread，那么ApiThreadTest属性等生命周期就贯穿整个main thread，可以被@test子线程共享，按顺序修改。para_map同理。
			ApiThreadTest.listResult.addAll(runTestResults);
			runTestResults.clear();
			for(TestResult tr:ApiThreadTest.listResult){
				Assert.assertTrue(tr.isPass());
			}
		}
	}


	public static List<Map<String,String>> dataForCase4(){
		List<Map<String,String>> datas=new ArrayList<Map<String,String>>();

		String url="http://192.168.5.14/api/searchService/search?";
		String params="{\"data\":\"Bh2SRLlpJhjLqH7lXGzJ1HH5xWWna+yDZSWpjilPJUq7KGAx/oPTyXc6m9LKqhYpEjRtt7Mf+ae3UQeUKRJtrrMG1ZF3Vz2LWsLgRte1lUUvZmyQKQU0Zi4AhElSlUFFonpY5nXivfhdgpC2q7xQkA==\",\"tk\":\"dd7e37d9f3287cd89ccb736c69074e27\"}";
		Map headers=null;
		String result = HttpClientUtils.doPostJson(url, params, headers);
		System.out.println("SearchAPI response: "+result);
		JSONArray obj1= (JSONArray) JSONPath.read(result,"$.data.itemList.itemCode");
		System.out.println(obj1);
		System.out.println(obj1.size());
		JSONArray obj2= (JSONArray) JSONPath.read(result,"$.data.itemList.minPrice");
		System.out.println(obj2.getClass());
		System.out.println(obj2.size());
		JSONArray obj3= (JSONArray) JSONPath.read(result,"$.data.itemList.itemLine.itemLineId");
		System.out.println(obj3.getClass());
		System.out.println(obj3.size());
		JSONArray obj4= (JSONArray) JSONPath.read(result,"$.data.itemList.itemLine.areaCode");
		System.out.println(obj4.getClass());
		System.out.println(obj4.size());

		for (int i=0;i<obj1.size();i++){
			Map<String,String> line=new LinkedHashMap<String,String>();
			line.put("itemCode", obj1.get(i).toString());
			line.put("minPrice",  obj2.get(i).toString());
			line.put("itemLineId",  obj3.get(i).toString());
			line.put("areaCode", obj4.get(i).toString());
			datas.add(line);
		}
		return datas;
	}

	@DataProvider(name = "excel", parallel = true)
	public Iterator<Object[]> excelProvider() {
		List<Object[]> dataProvider = new ArrayList<Object[]>();
		// 参数数据
		try {
			List<ParamBeanLogin> params_list = ExcelUtils.getInstance().readExcel2Objects(path, ParamBeanLogin.class, 1);
			for (ParamBeanLogin paramBean : params_list) {
				dataProvider.add(new Object[] { paramBean });
			}
		} catch (InvalidFormatException | Excel4JException | IOException e) {
			e.printStackTrace();
		}
		return dataProvider.iterator();
	}
	
	@DataProvider(name = "excelobject", parallel = true)
	public Object[][] excelProviderObject() {
		//List<Object[]> dataProvider = new ArrayList<Object[]>();
		// 参数数据
		 Object[][] objects = null;
		try {
			List<ParamBeanLogin> params_list = ExcelUtils.getInstance().readExcel2Objects(path, ParamBeanLogin.class, 1);
			objects=new Object[params_list.size()][1];
			for (int i = 0; i < params_list.size(); i++) {
				objects[i][0]=params_list.get(i);
			}
		} catch (InvalidFormatException | Excel4JException | IOException e) {
			e.printStackTrace();
		}
		return objects;
	}


	public static void setcase(TestCase testCase){
		System.out.println("替换前--" + testCase);
		// 参数替换
		ApiThreadTest.params_replace(testCase);
		// 函数支持
		ApiThreadTest.funtion_replace(testCase);
		System.out.println("替换后--" + testCase);
		// 替换变量
	}

	public static TestResult runcase(TestCase testCase){


		String result = "";
		if ("get".equals(testCase.getType())) {
			result = HttpClientUtils.doGet(testCase.getUrl());
		} else if ("post".equals(testCase.getType())) {
			result = HttpClientUtils.doPost(testCase.getUrl(),
					MapUtils.covertStringToMap1(testCase.getParams()));
		} else if ("postjson".equals(testCase.getType())) {
			System.out.println("headers:"+testCase.getHeaders());
			result = HttpClientUtils.doPostJson(testCase.getUrl(), testCase.getParams(), MapUtils.covertStringToMap2(testCase.getHeaders()));
		}

		String testReulst_str = CheckPointUtils.check(testCase.getCaseName(),result, testCase.getCheckpoint()).getMsg();
		checkpoint.JsonCheckResult jsonCheck = CheckPointUtils.check(testCase.getCaseName(),result, testCase.getCheckpoint());
		ParamUtils.addFromJson(result, testCase.getCorrelation());

//						assertTrue(jsonCheck.isResult(),jsonCheck.getMsg());
		//update dbcheckPoint
		testCase.setDbcheckpoint(ParamUtils.replace(testCase.getDbcheckpoint()));

		System.out.println("********below is log********");
		System.out.println("testCase： " + testCase.toString());
		System.out.println("params: " + testCase.getParams());
		System.out.println("response: " + result);
		System.out.println("checkPoint result：" + jsonCheck.isResult() );
		System.out.println("checkPoint message: " + jsonCheck.getMsg());

        // 数据检查
		String dbcheckResult = DbCheckUtils.check(testCase.getDbcheckpoint());


		// 结果处理
		TestResult testResult = new TestResult();
		testResult.setTestResult(testReulst_str);
		testResult.setDbtestResult(dbcheckResult);
		//汇总response & database校验结果：
		testResult.setPass(true);
		System.out.println("dbcheckResult: "+dbcheckResult);
		System.out.println("result get pass:"+testResult.isPass());
		System.out.println("检查点检查成功".equals(dbcheckResult));
		System.out.println("没有设置数据库检查点".equals(dbcheckResult));
		System.out.println(jsonCheck.isResult());

		if (!("检查点检查成功".equals(dbcheckResult)||"没有设置数据库检查点".equals(dbcheckResult)&&jsonCheck.isResult())) {
			testResult.setPass(false);

		}

		try {
			BeanUtils.copyProperties(testResult, testCase);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
//		runTestResults.add(testResult);
		return testResult;
	}

}
