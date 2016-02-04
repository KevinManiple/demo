package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyExample {

   /* public static void main(String[] args) {
        ProxyImage handler = new ProxyImage();
        Image image1 = (Image) handler.newProxyInstance(new RealImage("HiRes_10MB_Photo1"));
        image1.displayImage();
        Image image2 = (Image) handler.newProxyInstance(new RealImage("HiRes_10MB_Photo2"));
        image2.displayImage();
    }*/

}

/*
interface Image {
    void displayImage();
}

class RealImage implements Image {

    private String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadImageFromDisk();
    }

    private void loadImageFromDisk() {
        System.out.println("Loading   " + filename);
    }

    @Override
    public void displayImage() {
        System.out.println("Displaying " + filename);
    }
}

class ProxyImage implements InvocationHandler {

    private Object targetObject;

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

}*/
