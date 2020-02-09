package apitest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest {

	public static void main(String[] args) {
		//可以无限开, 短任务
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		
//		//按照固定的数目
		 ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
//		 
//		 //默认一个
		 ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
		 
		
//		for (int i = 0; i < 100; i++) {
//			new Thread() {
//				@Override
//				public void run() {
//					System.out.println(Thread.currentThread().getName());
//				}
//				
//			}.start();
//		}
//		
		
//		for (int i = 0; i < 2; i++) {
//			final int index=i;
//			fixedThreadPool.execute(new Thread() {
//				@Override
//				public void run() {
//					System.out.println(Thread.currentThread().getName()+" "+index);
//				}
//			});
//		}

//		for (int i = 0; i < 10; i++) {
//			final int index = i;
//			//放了十个线程
//			cachedThreadPool.execute(new Runnable() {
//				public void run() {
//					System.out.println(index+Thread.currentThread().getName());
//				}
//			});
//		}
		//fixedThreadPool.shutdown();
		
		//延迟持续，重复执行
	    ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
		 scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName()+"  111111 "+System.currentTimeMillis()/1000);
			}
		},5,2,TimeUnit.SECONDS);
	}

}
