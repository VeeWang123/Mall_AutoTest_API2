package apitest;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import com.github.crab2died.ExcelUtils;
import com.github.crab2died.annotation.ExcelField;
import com.github.crab2died.exceptions.Excel4JException;

import apitest.convert.BooleanConvert;
import apitest.convert.FileConvert;

public class TestCase {
	
	@ExcelField(title = "是否开启",readConverter = BooleanConvert.class, writeConverter = BooleanConvert.class)
	private boolean run;
	
	@ExcelField(title = "用例名称")
	private String caseName;
	
	@ExcelField(title = "类型")
	private String type;
	
	@ExcelField(title = "地址")
	private String url;
	
	@ExcelField(title = "参数", readConverter = FileConvert.class)
	private String params;
	
	@ExcelField(title = "头部")
	private String headers;
	
	@ExcelField(title = "返回结果提取")
	private String correlation;
	
	@ExcelField(title = "数据检查")
	private String checkpoint;
	
	
	@ExcelField(title = "数据库检查")
	private String dbcheckpoint;

	
	public String getDbcheckpoint() {
		return dbcheckpoint;
	}

	public void setDbcheckpoint(String dbcheckpoint) {
		this.dbcheckpoint = dbcheckpoint;
	}

	public String getCheckpoint() {
		return checkpoint;
	}

	public void setCheckpoint(String checkpoint) {
		this.checkpoint = checkpoint;
	}

	public String getCorrelation() {
		return correlation;
	}

	public void setCorrelation(String correlation) {
		this.correlation = correlation;
	}

	public String getHeaders() {
		return headers;
	}

	public void setHeaders(String headers) {
		this.headers = headers;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public boolean isRun() {
		return run;
	}

	public void setRun(boolean run) {
		this.run = run;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "TestCase [run=" + run + ", caseName=" + caseName + ", type=" + type + ", url=" + url + ", params="
				+ params + ", headers=" + headers + ", correlation=" + correlation + ", checkpoint=" + checkpoint + ", dbcheckpoint=" + dbcheckpoint + "]";
	}

}
