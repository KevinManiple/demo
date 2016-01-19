import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.runner.notification.RunListener.ThreadSafe;
import org.redisson.Config;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@ThreadSafe
public class Counter {

	private static ClassPathXmlApplicationContext	applicationContext;

	//
	@org.springframework.context.annotation.Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "singleton")
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		Set<String> set = new HashSet<String>();
		applicationContext = new ClassPathXmlApplicationContext("");
		Config config = applicationContext.getBean("", Config.class);
		config.getCodec();
		list.add("12");
		list.add("11");
		list.add("12");
		set.add("12");
		set.add("11");
		set.add("12");
		System.out.println(list.get(0));
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		// new B();
		try {
			int a = 0;
			int b = 3;
			System.exit(-1);

			System.out.println(b / a);
		} catch (ArithmeticException e) {
			System.out.println("a");
		} catch (Exception e) {
			System.out.println("b");
		} finally {
			System.out.println("c");
		}
	}
}

class A {
	static {
		System.out.println(1);
	}

	public A() {
		System.out.println(2);
	}
}

class B extends A {
	static {
		System.out.println("a");
	}

	public B() {
		System.out.println("b");
	}
}
