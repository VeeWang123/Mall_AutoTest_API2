package apitest;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ThreadLocalMapTest {
	
	//map 放同样key 
//	static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>(){
//
//		@Override
//		protected Map<String, Object> initialValue() {
//			return new HashMap<String, Object>();
//		}
//	};
	
	static ThreadLocal<Map<String, Object>> threadLocal =new ThreadLocal<Map<String, Object>>() {

		@Override
		protected Map<String, Object> initialValue() {
			return new LinkedHashMap<String, Object>();
		}
	};
	
	public static void main(String[] args) {
		
		threadLocal.get().put("1","11");
		dosomethings();
		
		new Thread() {
			@Override
			public void run() {
				threadLocal.get().put("1","22");
				dosomethings();
			}
		}.start();
		
		new Thread() {
			@Override
			public void run() {
				threadLocal.get().put("1","33");
				dosomethings();
			}
			
		}.start();
		
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				threadLocal.get().put("1","22");
//				dosomethings();
//			}
//		}).start();
		
		
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				threadLocal.get().put("1","33");
//				dosomethings();
//			}
//		}).start();
	}
	
	private static void dosomethings() {
		System.out.println(Thread.currentThread().getName() + "  " +threadLocal.get());
	}

}
