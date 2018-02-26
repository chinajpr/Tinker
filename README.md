# Tinker
Java类加载机制,Dex分包实现热更新

#### 基本原理
java运行时是通过ClassLoader来加载类的.一个ClassLoader包含多个Dex文件,多个Dex文件存储在一个Element[]中.当加载A类(一个有bug的类)时,ClassLoader会从Elemnet数组中从0开始查找A类.我们将修复好的A类放在数组的最前面,这样ClassLoader会优先查询到修复好的A类,而不会继续向下查询有bug的A类了,以此达到修复bug的目的.

#### ClassLoader
- PathClassLoader 用来加载程序中的dex(有bug的那个类)
- DexClassLoader  用于加载指定的dex文件(该dex文件必须要在应用程序的目录下面)

[BaseDexClassLoader源码](http://androidxref.com/4.4.2_r1/xref/libcore/dalvik/src/main/java/dalvik/system/BaseDexClassLoader.java#pathList)

[DexPathList源码](http://androidxref.com/4.4.2_r1/xref/libcore/dalvik/src/main/java/dalvik/system/DexPathList.java)

#### 实现
1. 线上版本(有问题的版本):classes.dex
2. 修复后的补丁:classes2.dex(主要包含我们的修复好的bug类)

注:这里的dex分包没有用官方的multiDex方案,因为不成功,所以我手动给分的包.后面介绍

3. 合并classes.dex 和 classes2.dex文件
4. 将合并好的dex文件通过BaseDexClassLoader调用findClass,并将修复好的dex插入到dexElements的集合的最前面.


##### Dex手动分包
1. 找到xxx.class(需要热更的class)
2. 到sdk中,找到build-tools(随便哪个版本)中的dx.bat工具
3. 命令:
dx --dex --output=D:\...\classes2.dex D:\...\dex
命令解释:
 --output=D:\...\classes2.dex 指定输出路径
 D:\...\dex 指定需要打包成dex文件的class字节文件(注意要包括全路径的文件夹，也可以有多个class)
