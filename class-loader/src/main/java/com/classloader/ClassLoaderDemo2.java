package com.classloader;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;

public class ClassLoaderDemo2 {

    static final String localPath = "D:\\git\\";  //这个路是你项目的路径的上级目录

    public static void main(String[] args) throws Exception {
        //1. 从磁盘加载
        loadFromDisk();

        //2.从网络上加载， 可以放到tomcat webapps/examples目录下， 启动tomcat
        //访问路径是localhost:8080/examples/com/url/UrlClass.class   可以下载该文件
//        loadFromTomcat();
    }

    public static void loadFromTomcat() throws Exception{
        URL url = new URL("http://localhost:8080/examples/");
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{url});
        Class<?> clazz = urlClassLoader.loadClass("com.url.UrlClass");
        clazz.newInstance();
    }


    public static void loadFromDisk() throws Exception {
        File file = new File(localPath + "Spi\\urlClassLoader");
        URI uri = file.toURI();
        URL url = uri.toURL();

        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{url});
        System.out.println("父类加载器：" + urlClassLoader.getParent());
        Class<?> clazz = urlClassLoader.loadClass("com.url.UrlClass");
        clazz.newInstance();

    }
}
