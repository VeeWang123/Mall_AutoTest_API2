package apitest.thread;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.crab2died.ExcelUtils;

import apitest.FunctionUtils;
import apitest.HttpClientUtils;
import apitest.ParamBean;
import apitest.ParamUtils;
import apitest.TestCase;
import apitest.TestResult;
import apitest.email.EmailUtils;


public class ApiThreadTest {
	
	private static final Logger logger = LoggerFactory.getLogger(ApiThreadTest.class);
	
	public static String path = System.getProperty("user.dir") + File.separator + "data" + File.separator + "apitest11.xlsx";
	public static String path_result = System.getProperty("user.dir") + File.separator + "result" + File.separator;
	
	public static List<TestResult> listResult = new ArrayList<TestResult>();
	
	public static void main(String[] args) throws Exception {
		HttpClientUtils.openProxy = false;
		List<ParamBean> params_list = ExcelUtils.getInstance().readExcel2Objects(path, ParamBean.class, 1);
		System.out.println(params_list);
		//代码
		 ExecutorService fixedThreadPool = Executors.newFixedThreadPool(100);
		// 参数覆盖
		CountDownLatch latch =new CountDownLatch(params_list.size());
		for (ParamBean paramBean : params_list) {
			// new ApiTask(paramBean,latch,RandomUtils.nextInt(1, 10)).start();
			ApiTask apiTask = new ApiTask(paramBean,latch,RandomUtils.nextInt(1, 10));
			fixedThreadPool.execute(apiTask);
		}
		//0 
		latch.await(3, TimeUnit.MINUTES);
//		//等待
//		Thread.sleep(300);
//		for (Thread thread : threads) {
//			//抢占了cpu  可以给个最大抢占时间
//			thread.join(5000);
//		}
		// 最终结果处理
		// 写excel
		File result_file= new File(path_result);
		if(!result_file.exists()) {
			result_file.mkdirs();
		}
		String resultFile =path_result+"result_" + getDate() + ".xlsx";
		ExcelUtils.getInstance().exportObjects2Excel(listResult, TestResult.class, resultFile);
		System.out.println("测试发邮件");
		try {
			EmailUtils.sendEmailsWithAttachments("测试结果", "请查收测试结果", resultFile);
		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("发送异常");
			logger.error("error {} {}",e,"测试");
		}
		fixedThreadPool.shutdown();
		
	}

	public static String getDate() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
		return simpleDateFormat.format(new Date());
	}

	public static void params_replace(TestCase testCase) {
		// url参数化支持
		testCase.setUrl(ParamUtils.replace(testCase.getUrl()));
		// body
		testCase.setParams(ParamUtils.replace(testCase.getParams()));
		// header
		testCase.setHeaders(ParamUtils.replace(testCase.getHeaders()));
		//检查点参数化
		testCase.setCheckpoint(ParamUtils.replace(testCase.getCheckpoint()));
		//数据库检查参数化
//		testCase.setDbcheckpoint(ParamUtils.replace(testCase.getDbcheckpoint()));
	}
	
	public static void funtion_replace(TestCase testCase) {
		// url参数化支持
		testCase.setUrl(FunctionUtils.replace(testCase.getUrl()));
		// body
		testCase.setParams(FunctionUtils.replace(testCase.getParams()));
		// header
		testCase.setHeaders(FunctionUtils.replace(testCase.getHeaders()));
	}

}
