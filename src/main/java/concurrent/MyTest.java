package concurrent;

import java.util.concurrent.atomic.AtomicInteger;

class Counter1 {
	private int	counter	= 0;

	public int increase() {
		synchronized (this) {
			counter = counter + 1;
			return counter;
		}
	}

	public int decrease() {
		synchronized (this) {
			counter = counter - 1;
			return counter;
		}
	}

}

class Counter2 {
	private AtomicInteger	counter	= new AtomicInteger();

	public int increase() {
		return counter.incrementAndGet();
	}

	public int decrease() {
		return counter.decrementAndGet();
	}
}

public class MyTest {
	public static void main(String[] args) {
		v1();
		v2();
	}

	public static void v1() {
		long start = System.currentTimeMillis();
		Counter1 counter1 = new Counter1();
		for (int i = 0; i < 10000000; i++) {
			counter1.increase();
		}
		for (int i = 0; i < 10000000; i++) {
			counter1.decrease();
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}

	public static void v2() {
		long start = System.currentTimeMillis();
		Counter2 counter2 = new Counter2();
		for (int i = 0; i < 10000000; i++) {
			counter2.increase();
		}
		for (int i = 0; i < 10000000; i++) {
			counter2.decrease();
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
}
