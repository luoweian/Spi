package com.classloader;

/**
 * 自定义类加载器， 打破双亲委派机制， 实现热部署
 * 当我们调用loadClass方法加载类， 会采用双亲委派模式，即如果类已经被加载， 就从缓存中获取新加载。
 * 如果同一个class被同一个类加载器多次加载，则会报错。因此，我们需要实现热部署， 只要让同意class被
 * 不同的类加载器重复加载即可。但是不能调用loadClass方法，而应该调用findClass方法。
 * 也就可以生产环境改代码了
 */
public class HotDeployment {
    public static void main(String[] args) throws Exception {
        MyFileClassLoader classLoder1 = new MyFileClassLoader("D:\\git\\Spi\\urlClassLoader\\", null);
        MyFileClassLoader classLoder2 = new MyFileClassLoader("D:\\git\\Spi\\urlClassLoader\\", classLoder1);
//        Class<?> clazz1 = classLoder1.loadClass("com.url.UrlClass");
//        Class<?> clazz2 = classLoder2.loadClass("com.url.UrlClass");
//        System.out.println("class1: " + clazz1.hashCode());
//        System.out.println("class2: " + clazz2.hashCode());

        System.out.println("-------------------------------------------------");

        Class<?> clazz1 = classLoder1.findClass("com.url.UrlClass");
        Class<?> clazz2 = classLoder2.findClass("com.url.UrlClass");
        System.out.println("class1: " + clazz1.hashCode());
        System.out.println("class2: " + clazz2.hashCode());

    }
}
