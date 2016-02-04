package proxy.cglib;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class ProxyExampleCGLib {

    public static void main(String[] args) {
        ProxyImage handler = new ProxyImage();
        RealImage image1 = (RealImage) handler.newProxyInstance(new RealImage("HiRes_10MB_Photo1"));
        image1.displayImage();
        RealImage image2 = (RealImage) handler.newProxyInstance(new RealImage("HiRes_10MB_Photo2"));
        image2.displayImage();
    }
}

interface Image {
    void displayImage();
}

/**
 * 没有实现接口
 */
class RealImage {

    private String filename;

    public RealImage() {
        this("TEST");
    }

    public RealImage(String filename) {
        this.filename = filename;
        loadImageFromDisk();
    }

    private void loadImageFromDisk() {
        System.out.println("Loading   " + filename);
    }

    public void displayImage() {
        System.out.println("Displaying " + filename);
    }
}

class ProxyImage implements MethodInterceptor {

    public Object newProxyInstance(Object target) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        // 回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object target, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        methodProxy.invokeSuper(target, objects);
        after();
        return null;
    }

    private void before() {
        System.out.println("before");
    }

    private void after() {
        System.out.println("after");
    }
}





