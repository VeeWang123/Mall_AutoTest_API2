package testng;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTest2 {
	
	
	@Test(dataProvider = "mytest",invocationCount = 2)
    public void parameterIntTest(TestBean bean,TestBean2 bean2) {
	   System.out.println(Thread.currentThread().getName()+" bean "+bean);
       System.out.println("Parameterized Number is : " + bean.getName());
       System.out.println("Parameterized Number is : " + bean.getMsg());
    }

    @DataProvider(name = "mytest", parallel = true)
    public Iterator<Object[]> parameterIntTestProvider() {
    	List<Object[]> dataProvider = new ArrayList<Object[]>();
    	for(int i=0;i<100;i++){
    		TestBean bean = new TestBean();
    		bean.setName("testname");
    		bean.setMsg("--"+i);
    		TestBean2 bean2 = new TestBean2();
    		dataProvider.add(new Object[] { bean,bean2});
    	}
        return dataProvider.iterator();
    }

}
