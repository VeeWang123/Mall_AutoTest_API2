package apitest;

import com.github.crab2died.annotation.ExcelField;

public class TestResult extends TestCase{

	@ExcelField(title = "测试结果")
	private String testResult;
	
	@ExcelField(title = "数据库测试结果")
	private String dbtestResult;

	@ExcelField(title = "总结果")
	private boolean pass;

	public boolean isPass() {
		return pass;
	}

	public void setPass(boolean pass) {
		this.pass = pass;
	}


	public String getDbtestResult() {
		return dbtestResult;
	}

	public void setDbtestResult(String dbtestResult) {
		this.dbtestResult = dbtestResult;
	}

	public String getTestResult() {
		return testResult;
	}

	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}
}
