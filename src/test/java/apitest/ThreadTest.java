package apitest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

class Thread1 extends Thread{
	
	private CountDownLatch latch;
	

	public Thread1(CountDownLatch latch) {
		super();
		this.latch = latch;
	}



	@Override
	public void run() {
		//迭代 循环
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName()+"__"+i);
		}
		latch.countDown();
	}
	
}


public class ThreadTest {
	
	public static void main(String[] args) {
		System.out.println("main start:  "+Thread.currentThread().getName());
		//List<Thread> list =new ArrayList<Thread>();
		//线程计数器
		CountDownLatch latch =new CountDownLatch(10);
		for (int i = 0; i < 10; i++) {
			Thread1 thread1 =new Thread1(latch);
			thread1.start();
			//list.add(thread1);
			//thread1.start();//多个跑道 并行  异步
		}
		//latch ==0
		try {
			latch.await(1,TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("---day to second-"+TimeUnit.HOURS.toMillis(3));
//		Thread t2= new Thread() {
//			@Override
//			public void run() {
//				for (int i = 0; i < 10; i++) {
//					System.out.println(Thread.currentThread().getName()+"  "+i);
//				}
//			}
//	     };
//	     t2.setPriority(10);
//	     list.add(t2);
//		 Thread thread =new Thread(new MyRunnable());
//		 list.add(thread);
//		
//		 Thread thread3=  new Thread(new Runnable() {
//			@Override
//			public void run() {
//				for (int i = 0; i < 10; i++) {
//					System.out.println(Thread.currentThread().getName()+"  "+i);
//				}
//				
//			}
//		});
//	      list.add(thread3);
	      
//	    for (Thread thread2 : list) {
//	    	try {
//				thread2.join();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
		
//		//一步到位加重写
//		Thread thread=new Thread() {
//			@Override
//			public void run() {
//				pong();
//			}
//		};
//		
//		
//		Thread t2= new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				
//			}
//		});
		
		
		
		 System.out.print("ping");
		//main 不等了
		System.out.println(" main end ");
	}
	
	
	 static void pong() {
	        System.out.println(Thread.currentThread().getName()+"  pong");
	}

}

