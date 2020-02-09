package apitest;


public class MyRunnable implements Runnable{

	@Override
	public void run() {
		for (int i = 0; i <10; i++) {
			System.out.println(Thread.currentThread().getName() +"  "+i);
		}
		
	}
	
	public static void main(String[] args) {
		new Thread(new MyRunnable()).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
			}
		}).start();
		
		new Thread() {
			@Override
			public void run() {
			}
			
		}.start();
	}

}
