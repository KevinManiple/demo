package lang;

import java.security.SecureRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @author Kai.Zhao
 * @version 1.0
 * @see
 */
public class LockDemo {

	private final Lock	lock	= new ReentrantLock();
	private static int	times	= 0;

	public static void main(String[] args) {
		new Thread(new LockDemo().new LockA()).start();
		new Thread(new LockDemo().new LockB()).start();
	}

	public void work() {
		try {
			lock.lock();
			Thread.sleep(new SecureRandom().nextInt(1000));
			times++;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void say() {
		try {
			lock.lock();
			Thread.sleep(new SecureRandom().nextInt(1000));
			times--;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	class LockA implements Runnable {

		@Override
		public void run() {
			for (int i = 0; i < 50; i++) {
				say();
				System.out.println(Thread.currentThread().getName() + ":" + times);
			}
		}

	}

	class LockB implements Runnable {

		@Override
		public void run() {
			for (int i = 0; i < 50; i++) {
				work();
				System.out.println(Thread.currentThread().getName() + ":" + times);
			}
		}

	}
}
