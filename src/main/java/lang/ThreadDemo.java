package lang;

/**
 * 
 * @author Kai.Zhao
 * @version 1.0
 * @see
 */
public class ThreadDemo {

	public static void main(String[] args) throws InterruptedException {
		new Thread(new ThreadDemo().new ThreadA()).start();
		Thread.sleep(5000);
		new Thread(new ThreadDemo().new ThreadB()).start();
	}

	public synchronized void say() {
		try {
			System.out.println("begin say");
			ThreadDemo.class.wait();// 线程进入等待状态，直到其他线程释放锁
			Thread.sleep(3000);
			System.out.println("end say");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void work() {
		try {
			System.out.println("begin work");
			ThreadDemo.class.notify();// 释放锁，让原来等待的线程继续运行
			Thread.sleep(5000);
			System.out.println("end work");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class ThreadA implements Runnable {

		@Override
		public void run() {
			synchronized (ThreadDemo.class) {
				say();
			}
		}

	}

	class ThreadB implements Runnable {

		@Override
		public void run() {
			synchronized (ThreadDemo.class) {
				work();
			}

		}

	}
}
