# Spi
Java 类加载机制以及Spi的使用

使用Java SPI机制，利用ServiceLoader来加载并实例化类。
SPI全称Service Provider Interface，是Java提供的一套用来被第三方实现或者扩展的API，它可以用来启用框架扩展和替换组件。

Java SPI 实际上是“基于接口的编程＋策略模式＋配置文件”组合实现的动态加载机制，提供了通过interface寻找implement的方法。类似于IOC的思想，将装配的控制权移到程序之外，从而实现解耦。

适应场景：调用者根据需要，使用、扩展或替换实现策略。

使用Java SPI需要符合的约定：

1. Service provider提供Interface的具体实现后，在目录META-INF/services下的文件(以Interface全路径命名)中添加具体实现类的全路径名；
2. 接口实现类的jar包存放在使用程序的classpath中；
3. 使用程序使用ServiceLoader动态加载实现类(根据目录META-INF/services下的配置文件找到实现类的全限定名并调用classloader来加载实现类到JVM);
4. SPI的实现类必须具有无参数的构造方法。

![image](https://github.com/luoweian/Spi/blob/main/class-loader/classLoaderAndSpi.png)
