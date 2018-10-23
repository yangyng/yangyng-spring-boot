# yangyng-test
### 多线程 ###
#### 1.1 线程的五种状态 ####
1. new 线程创建
2. runnable  调用start进入可执行状态
3. running 此时执行run()方法中的业务代码
4. blocked 中断状态 sleep wait yield 等
5. terminated 线程的最终状态

tip:

1. Thread 中的run和start -> 模板设计模式
2. runnable -> 策略模式

sleep方法不会去主动释放锁资源

#### 1.2线程的实现方式 ####
1. 继承Thread
2. 实现Runnable
3. 实现Callable

**注意**

1. 重复启动线程会抛出异常
2. 线程启动后会被加入到线程组中
3. Thread被构造后的NEW状态,threadStatus 内部属性值为0
4. 线程一旦进入terminated 状态,即一个线程的生命周期结束时,不允许再调用start()方法

tip:

1. 类中的final方法无法被重写
2. 一个线程的创建的肯定是有另一个线程完成的
3. 被创建线程的父线程是创建它的线程
4. 构造线程时如果没有显示的指定一个ThreadGroup,那么子线程会被加入父线程所在的线程组


#### 1.3 线程与线程组 ####
1. 线程的创建肯定是由另一个线程完成的
2. 被创建线程的父线程是创建它的线程
3. main线程所在的ThreadGroup成为Main
4. 构造线程时如果没有显式声明所在的线程组,那么它会和父线程同属一个线程组

tips:

jvm内存结构 :

1. 程序计数器
2. Java虚拟机栈
	- 线程私有
	- 生命周期与线程相同
	- 在JVM运行时创建
3. 本地方法栈
	- 线程私有
4. 堆内存(GC堆)
	- 所有线程共享
	- 运行期间创建
	- 新生代老年代(EDEN|FROM SURVIVOR|TO SURVIVOR)
5. 方法区(非堆)
6. java8元空间





