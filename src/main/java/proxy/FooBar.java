package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class FooBar {

	public static void main(String[] args) {
		MyInvocationHandler handler = new MyInvocationHandler();
		Foo foo = (Foo) handler.newProxyInstance(new Bar());
		foo.sayHi();
	}

}

interface Foo {
	void sayHi();
}

class Bar implements Foo {

	@Override
	public void sayHi() {
		System.out.println("Hi");
	}

}

class MyInvocationHandler implements InvocationHandler {

	private Object	targetObject;

	public Object newProxyInstance(Object targetObject) {
		this.targetObject = targetObject;
		return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),
				targetObject.getClass().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object ref = null;
		try {
			before();
			ref = method.invoke(targetObject, args);
			after();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ref;
	}

	private void before() {
		System.out.println("before");
	}

	private void after() {
		System.out.println("after");
	}

}