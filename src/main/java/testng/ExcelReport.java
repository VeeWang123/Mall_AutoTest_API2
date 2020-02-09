package testng;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;

import com.github.crab2died.ExcelUtils;

import apitest.TestResult;
import apitest.email.EmailUtils;
import apitest.thread.ApiThreadTest;

public class ExcelReport implements IReporter{
	private static final Logger logger = LoggerFactory.getLogger(ExcelReport.class);

	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		//excel 
		try {
			String path_result = System.getProperty("user.dir") + File.separator + "result" + File.separator;
			File result_file = new File(path_result);
			if (!result_file.exists()) {
				result_file.mkdirs();
			}
			String resultFile = path_result + "result_" + ApiThreadTest.getDate() + ".xlsx";
			ExcelUtils.getInstance().exportObjects2Excel(ApiThreadTest.listResult, TestResult.class, resultFile);
			System.out.println("测试发邮件");

			EmailUtils.sendEmailsWithAttachments("测试结果", "请查收测试结果", resultFile);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("结果处理");
			logger.error("error {} {}", e, "测试");
		}
	}

}
