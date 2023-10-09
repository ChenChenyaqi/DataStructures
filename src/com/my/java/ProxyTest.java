package com.my.java;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author Chen Yaqi
 * @version 1.0
 */
public class ProxyTest {
    public static void main(String[] args) {
        Server server = new Server();
        new ProxyServer(server).browse();
    }
}

// ======================静态代理========================
interface NetWork {
    void browse();
}

// 被代理类
class Server implements NetWork {

    @Override
    public void browse() {
        System.out.println("真实的服务器访问网络");
    }
}

// 代理类
class ProxyServer implements NetWork {

    private NetWork work;

    public ProxyServer(NetWork work) {
        this.work = work;
    }

    private void check() {
        System.out.println("联网之前的检查工作");
    }

    @Override
    public void browse() {
        check();
        work.browse();
    }
}

// ======================动态代理========================
interface Human {
    String[] getBelief();

    void eat(String food);
}

// 被代理类
class SuperMan implements Human {

    @Override
    public String[] getBelief() {
        return new String[]{"I believe I can fly", "I am superman"};
    }

    @Override
    public void eat(String food) {
        System.out.println("我喜欢吃 " + food);
    }
}

class HumanUtil {
    public void method1() {
        System.out.println("===========================通用方法1===========================");
    }

    public void method2() {
        System.out.println("===========================通用方法2===========================");
    }
}

// 动态造代理类对象
class ProxyFactory {
    public static Object getProxyInstance(Object obj) {

        MyInvocationHandler handler = new MyInvocationHandler();

        handler.bind(obj);

        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), handler);
    }
}

class MyInvocationHandler implements InvocationHandler {

    // 被代理类的对象
    private Object obj;

    public void bind(Object obj) {
        this.obj = obj;
    }

    // 当我们通过代理类的对象，调用方法a时，就会自动的调用如下的方法：invoke()
    // 将被代理类要执行的方法a的功能就声明在invoke()中
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
// ======================AOP===========================
        HumanUtil humanUtil = new HumanUtil();
        humanUtil.method1();


        // method ：即为代理类对象调用的方法，此方法也就作为了被代理类对象要调用的方法
        // obj：被代理类对象
        Object res = method.invoke(obj, args);


        humanUtil.method2();

        // res返回值作为invoke方法的返回值
        return res;
    }
}

class TestProxy {
    public static void main(String[] args) {
        SuperMan superMan = new SuperMan();
        Human proxyInstance = (Human)ProxyFactory.getProxyInstance(superMan);
        proxyInstance.eat("麻婆豆腐");
        String[] belief = proxyInstance.getBelief();
        System.out.println(Arrays.toString(belief));

        Server server = new Server();
        NetWork proxyInstance1 = (NetWork)ProxyFactory.getProxyInstance(server);
        proxyInstance1.browse();
    }
}