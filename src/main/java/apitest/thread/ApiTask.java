package apitest.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.beanutils.BeanUtils;

import com.github.checkpoint.CheckPointUtils;
import com.github.checkpoint.JsonCheckResult;
import com.github.crab2died.ExcelUtils;

import apitest.HttpClientUtils;
import apitest.MapUtils;
import apitest.ParamBean;
import apitest.ParamUtils;
import apitest.TestCase;
import apitest.TestResult;
import apitest.db.DbCheckUtils;

public class ApiTask extends Thread {
	private ParamBean paramBean;
	
	private CountDownLatch latch;
	
	
	public ApiTask(ParamBean paramBean, CountDownLatch latch, int priority) {
		super();
		this.paramBean = paramBean;
		this.latch = latch;
		this.setPriority(priority);
	}

	public ApiTask(ParamBean paramBean) {
		super();
		this.paramBean = paramBean;
	}

	@Override
	public void run() {
		try {
		// addmap
		ParamUtils.addFromObject(paramBean);
		// 读原始数据(上一组数据被改掉了)
		List<TestCase> list = ExcelUtils.getInstance().readExcel2Objects(ApiThreadTest.path, TestCase.class);
		List<TestResult> runTestResults =new ArrayList<TestResult>();
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
					result = HttpClientUtils.doPost(testCase.getUrl(), MapUtils.covertStringToMap1(testCase.getParams()));
				} else if ("postjson".equals(testCase.getType())) {
					result = HttpClientUtils.doPostJson(testCase.getUrl(), testCase.getParams(), MapUtils.covertStringToMap2(testCase.getHeaders()));
				}

				String testReulst_str = CheckPointUtils.check(result, testCase.getCheckpoint()).getMsg();
				JsonCheckResult jsonCheck = CheckPointUtils.check(result, testCase.getCheckpoint());
				
				ParamUtils.addFromJson(result, testCase.getCorrelation());
				
				//数据检查
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
		ParamUtils.clear();
		ApiThreadTest.listResult.addAll(runTestResults);
		runTestResults.clear();
		if(latch!=null) {
			latch.countDown();
		}
		}catch (Exception e) {
		}
	}

}
