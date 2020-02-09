package testng;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.github.checkpoint.CheckPointUtils;
import com.github.checkpoint.JsonCheckResult;
import com.github.crab2died.ExcelUtils;
import com.github.crab2died.exceptions.Excel4JException;

import apitest.HttpClientUtils;
import apitest.MapUtils;
import apitest.ParamBean;
import apitest.ParamUtils;
import apitest.TestCase;
import apitest.TestResult;
import apitest.db.DbCheckUtils;
import apitest.email.EmailUtils;
import apitest.thread.ApiThreadTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import static org.testng.Assert.*;


/*
note: 此处使用import com.github.checkpoint.CheckPointUtils;
但是根据项目，需要改写CheckPointUtils，改写后位于checkpoint.CheckPointUtils

 */
public class ApiTestNg {

	String path = System.getProperty("user.dir") + File.separator + "data" + File.separator + "apitest11.xlsx";
	private static final Logger logger = LoggerFactory.getLogger(ApiTestNg.class);

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
    @Step("获取城市编码")//测试步骤
    @Severity(SeverityLevel.CRITICAL)//设置case的优先级
	@Test(dataProvider = "excelobject",threadPoolSize = 10)
	public void testcase(ParamBean paramBean) {
		System.out.println(Thread.currentThread().getName());
		System.out.println("paramBean：" + paramBean);

		try {
			// addmap
			ParamUtils.addFromObject(paramBean);
			// 读原始数据(上一组数据被改掉了)
			List<TestCase> list = ExcelUtils.getInstance().readExcel2Objects(path, TestCase.class); //此处可以换成其他文件，使参数和API分为2个文件
			List<TestResult> runTestResults = new ArrayList<TestResult>();
			for (TestCase testCase : list) {
				if (testCase.isRun()) {
					System.out.println("替换前--" + testCase);
					// 参数替换
					ApiThreadTest.params_replace(testCase);
					// 函数支持
					ApiThreadTest.funtion_replace(testCase);
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

					String testReulst_str = CheckPointUtils.check(result, testCase.getCheckpoint()).getMsg();
					JsonCheckResult jsonCheck = CheckPointUtils.check(result, testCase.getCheckpoint());
					ParamUtils.addFromJson(result, testCase.getCorrelation());
//					assertTrue(jsonCheck.isResult(),jsonCheck.getMsg());
					//update dbcheckPoint
					testCase.setDbcheckpoint(ParamUtils.replace(testCase.getDbcheckpoint()));


					System.out.println("********below is log********");
					System.out.println("paramBean：" + paramBean);
					System.out.println("testCase： " + testCase.toString());
					System.out.println("checkPoint result：" + jsonCheck.isResult() );
					System.out.println("checkPoint message: " + jsonCheck.getMsg());

					// 数据检查
					String dbcheckResult = DbCheckUtils.check(testCase.getDbcheckpoint());

					// 结果处理
					TestResult testResult = new TestResult();
					testResult.setTestResult(testReulst_str);
					testResult.setDbtestResult(dbcheckResult);
					BeanUtils.copyProperties(testResult, testCase);
					runTestResults.add(testResult);
				}
			}
			System.out.println(" 当前结果----");
			System.out.println(paramBean);
			System.out.println("runTestResults: " + runTestResults);
			ParamUtils.clear();
			//ApiThreadTest生命周期，同testNG运行周期。可以把testNG的afterClass，afterSuite，Listener等都看成main thread，那么ApiThreadTest属性等生命周期就贯穿整个main thread，可以被@test子线程共享，按顺序修改。para_map同理。
			ApiThreadTest.listResult.addAll(runTestResults);
			runTestResults.clear();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}


	@DataProvider(name = "excel", parallel = true)
	public Iterator<Object[]> excelProvider() {
		List<Object[]> dataProvider = new ArrayList<Object[]>();
		// 参数数据
		try {
			List<ParamBean> params_list = ExcelUtils.getInstance().readExcel2Objects(path, ParamBean.class, 1);
			for (ParamBean paramBean : params_list) {
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
			List<ParamBean> params_list = ExcelUtils.getInstance().readExcel2Objects(path, ParamBean.class, 1);
			objects=new Object[params_list.size()][1];
			for (int i = 0; i < params_list.size(); i++) {
				objects[i][0]=params_list.get(i);
			}
		} catch (InvalidFormatException | Excel4JException | IOException e) {
			e.printStackTrace();
		}
		return objects;
	}

}
