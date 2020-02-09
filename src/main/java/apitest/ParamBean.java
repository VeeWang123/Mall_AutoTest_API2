package apitest;

import com.github.crab2died.annotation.ExcelField;

/**
 * 读取excel第二个表格对象
 * @author pc
 *
 */
public class ParamBean {
	@ExcelField(title = "loginname")
	private String loginname;
	
	@ExcelField(title = "loginpass")
	private String loginpass;
	
	@ExcelField(title = "code")
	private String code;
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getLoginpass() {
		return loginpass;
	}

	public void setLoginpass(String loginpass) {
		this.loginpass = loginpass;
	}

	@Override
	public String toString() {
		return "ParamBean [loginname=" + loginname + ", loginpass=" + loginpass + "]";
	}

}
