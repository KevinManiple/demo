package thread;

public class NumberDemo {

	private volatile static int	prim	= 0;

	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					prim = prim + 1;
					System.out.println(Thread.currentThread().getName() + ":" + prim);
				}
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					prim = prim + 1;
					System.out.println(Thread.currentThread().getName() + ":" + prim);
				}
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					prim = prim - 1;
					System.out.println(Thread.currentThread().getName() + ":" + prim);
				}
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					prim = prim - 1;
					System.out.println(Thread.currentThread().getName() + ":" + prim);
				}
			}
		}).start();
	}

}
