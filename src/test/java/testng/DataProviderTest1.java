package testng;

import java.util.Vector;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTest1 {
	
	
	@Test(dataProvider = "testdb")
    public void parameterIntTest(Class clzz, String str,String line3, int no) {
		System.out.println(Thread.currentThread().getName());
       System.out.println("Parameterized Number is : " + clzz);
       System.out.println("Parameterized Number is : " + str);
    }

    //This function will provide the patameter data
    @DataProvider(name = "testdb",parallel = true)
    public Object[][] parameterIntTestProvider() {
        return new Object[][]{
                   {Vector.class, "test1","a",1},
                   {String.class, "test2","b",2},
                   {Integer.class, "test3","c",3},
                   {String.class, "test3","c",4},
                   {String.class, "test3","c",4}
                  };
    }

}
