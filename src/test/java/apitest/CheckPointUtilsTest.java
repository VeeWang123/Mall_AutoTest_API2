package apitest;

import com.github.checkpoint.CheckPointUtils;

public class CheckPointUtilsTest {
	   public static void main(String[] args) {
		    //jsonobject 测试
		    String testjson ="{\"uid\":\"test\"}";
		    CheckPointUtils.openLog=true;
			System.out.println("-----json校验");
			System.out.println(CheckPointUtils.check(testjson,"uid=test").getMsg());
			
		    System.out.println("-----多值组合测试 ");
		    testjson ="{\"msg\":\"登录成功\",\"uid\":\"DAD3483647A94DBDB174C4C036CA8A80\",\"code\":\"1\",\"code2\":\"2\",\"code3\":\"3\"}";
		    System.out.println(testjson);
		    System.out.println(CheckPointUtils.check(testjson,"code=1&&code2>=1&&code3>=5").getMsg());

		    System.out.println("中文检查");
		    System.out.println(CheckPointUtils.check(testjson,"msg=登录成功").getMsg());
			
			System.out.println("-----不存在值判断");
			System.out.println(CheckPointUtils.check(testjson,"uidtest=12").getMsg());
			
			//json数组测试
    		System.out.println("-----json数组测试");
			testjson="{\"code\":\"1\",\"data\":[{\"name\":\"testfan0\",\"pwd\":\"pwd0\"},{\"name\":\"testfan1\",\"pwd\":\"pwd1\"},{\"name\":\"testfan2\",\"pwd\":\"pwd2\"}]}";
		    System.out.println(testjson);
		    //匹配自定义满足jsonpath写法
			System.out.println(CheckPointUtils.check(testjson,"..name[0]=testfan0").getMsg());
			//不满足jsonpath写法
			System.out.println(CheckPointUtils.check(testjson,"name[0]=testfan0").getMsg());
		    System.out.println("匹配第一个 -----");
			System.out.println(CheckPointUtils.check(testjson,"name=testfan0").getMsg());
//			
//			testjson = "[{\"uid\":\"e3eeb0a6-b413-4b73-9a19-f5eacc3398a10\"}]";
//			System.out.println(CheckPointUtils.check(testjson, "uid=e3eeb0a6-b413-4b73-9a19-f5eacc3398a10").getMsg());
//			
//			//函数测试 
			testjson="{\"code\":\"1\",\"data\":[{\"name\":\"testfan0\",\"pwd\":\"pwd0\"},{\"name\":\"testfan1\",\"pwd\":\"pwd1\"},{\"name\":\"testfan2\",\"pwd\":\"pwd2\"}]}";
//			System.out.println("-----函数测试 ");
//		    System.out.println(CheckPointUtils.check(testjson,"data.size()>2").getMsg());
//		    System.out.println("length ----");
//		    System.out.println(CheckPointUtils.check(testjson,"data.length()>2").getMsg());
	   }

}
