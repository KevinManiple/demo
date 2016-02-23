package thread;

public class JoinTest {

	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(new JoinOne());
		thread.start();
		thread.join();// 暂停主线程，等待子线程执行完毕。
		System.out.println(JoinOne.a);
	}
}

class JoinOne implements Runnable {

	public static int a = 0;

	@Override
	public void run() {
		for (int k = 0; k < 5; k++) {
			a = a + 1;
		}
	}

}
