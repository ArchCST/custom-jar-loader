package cn.archcst.customjarloader;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author ArchCST
 * @date 2023/1/12
 */
public class JarFileClassLoaderUtils {
    public static ClassLoader getClassLoader(String url) {
        try {
            Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }
            /**
             * 双亲委派：除了BootstrapClassLoader外，每个ClassLoader都有父类 {@link java.lang.ClassLoader#parent}
             * 加载一个类的时候，会先递归往上递交，如果父类已加载这个类，则不再创新创建。当父类都无法加载类时，再自行加载 {@link java.lang.ClassLoader#loadClass(java.lang.String, boolean){
             * 目的是是保证java官方的类库 <JAVA_HOME>\lib (BootstrapCloassLoader) 和扩展类库<JAVA_HOME>\lib\ext (ExtensionClassLoader)的加载安全性，不会被开发者覆盖
             */

            // 打通主程序与插件之间的 ClassLoader ，解决把插件注册进 IOC 时的各种 ClassNotFoundException 问题。
            URLClassLoader classLoader = new URLClassLoader(new URL[]{}, ClassLoader.getSystemClassLoader());
            method.invoke(classLoader, new URL(url));
            return classLoader;
        } catch (Exception e) {
            System.out.println("创建自定义 ClassLoader 失败");
            return null;
        }
    }
}
