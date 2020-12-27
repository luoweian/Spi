package com.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 自定义类加载器
 * 1、继承CLassLoader
 * 2、覆盖findClass方法(注意这里不是loadclass方法)
 */
public class MyFileClassLoader extends ClassLoader{
    private String directory; //被加载类所在目录

    public MyFileClassLoader(String directory){
        this.directory = directory;
    }

    public MyFileClassLoader(String directory, ClassLoader parent){
        super(parent);
        this.directory = directory;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            //把类名转换为目录
            String file = directory + File.separator + name.replace(".", File.separator) + ".class";
            //构建输入流
            FileInputStream in = new FileInputStream(file);
            ByteArrayOutputStream baso = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int len = -1;
            while ((len = in.read(buf)) != -1) {
                baso.write(buf, 0, len);
            }
            byte[] data = baso.toByteArray(); //读取到的字节码的二进制数据
            in.close();
            baso.close();
            return defineClass(name, data, 0, data.length);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyFileClassLoader classLoder = new MyFileClassLoader("D:\\git\\Spi\\urlClassLoader\\");
        Class<?> clazz = classLoder.loadClass("com.url.UrlClass");
        clazz.newInstance();
    }
}
