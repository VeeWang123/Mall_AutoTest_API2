package testng;

import org.testng.annotations.Test;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestngTest {
  @Epic("并行单元测试")//测试集
   @Story("单元测试")//测试case
  @Test(description = "测试")
  public void f() {
      System.out.println("function");
  }

  @BeforeMethod
  public void beforeMethod() {
  }

  @AfterMethod
  public void afterMethod() {
  }

  @BeforeClass
  public void beforeClass() {
  }

  @AfterClass
  public void afterClass() {
  }

  @BeforeTest  // 在suite内都所有测试开始之前执行一次 Before Suite Method  > Before Test Method  > Before Class Method  来源https://blog.csdn.net/haiweizhourong/article/details/54926563
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }

  @BeforeSuite   //使用例子：https://blog.csdn.net/Magic_Chen2012/article/details/86665201
  public void beforeSuite() {
  }

  @AfterSuite
  public void afterSuite() {
  }

}
