package apitest;

import com.github.crab2died.annotation.ExcelField;

/**
 * 读取excel第二个表格对象
 * @author pc
 *
 */
public class ParamBeanLogin {
	@ExcelField(title = "phoneNumber")
	private String phoneNumber;
	
	@ExcelField(title = "password")
	private String password;
	
	@ExcelField(title = "type")
	private String type;

	@ExcelField(title = "itemId")
	private String itemId;

	@ExcelField(title = "areaCode")
	private String areaCode;

	@ExcelField(title = "userId")
	private String userId;

	@ExcelField(title = "count")
	private String count;

	@ExcelField(title = "minPrice")
	private String minPrice;

	@ExcelField(title = "itemLineId")
	private String itemLineId;

	@ExcelField(title = "itemCode")
	private String itemCode;

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}

	public String getItemLineId() {
		return itemLineId;
	}

	public void setItemLineId(String itemLineId) {
		this.itemLineId = itemLineId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "ParamBeanLogin{" +
				"phoneNumber='" + phoneNumber + '\'' +
				", password='" + password + '\'' +
				", type='" + type + '\'' +
				", itemId='" + itemId + '\'' +
				", areaCode='" + areaCode + '\'' +
				", userId='" + userId + '\'' +
				", count='" + count + '\'' +
				'}';
	}
}
