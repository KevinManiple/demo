package thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class lockTest {

	public static void main(String[] args) {
		ScheduledExecutorService service = Executors.newScheduledThreadPool(2);
		service.scheduleAtFixedRate(new ThreadSyn(), 0, 1, TimeUnit.MILLISECONDS);
		service.scheduleAtFixedRate(new ThreadLock(), 0, 1, TimeUnit.MILLISECONDS);
	}

}

class ThreadSyn implements Runnable {

	private static volatile int size = 0;

	@Override
	public void run() {
		synchronized (ThreadSyn.class) {
			size++;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("synchronized:" + size);
		}
	}

}

class ThreadLock implements Runnable {

	private static volatile int size = 0;

	@Override
	public void run() {
		Lock lock = new ReentrantLock();
		try {
			lock.lock();
			size++;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Lock:" + size);
		} catch (Exception e) {

		} finally {
			lock.unlock();
		}
	}

}
