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

#### 1.2 线程的实现方式 ####
1. 继承Thread
2. 实现Runnable
3. 实现Callable

**注意**

1. 重复启动线程会抛出异常
2. 线程启动后会被加入到线程组中
3. Thread被构造后的NEW状态,threadStatus 内部属性值为0
4. 线程一旦进入terminated 状态,即一个线程的生命周期结束时,不允许再调用start()方法

tips:

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
1. 守护线程(后台线程)
2. 当前线程的优先级不能高于当前所在线程组的优先级

#### 1.4 线程的sleep方法和yield方法和Join ####

1. sleep会导致当前线程停顿指定时间,没有CPU时间片的消耗
2. yield只是对CPU调度器的一个提示,类似于GC的提示,执行不执行取决于CPU
3. sleep会使线程短暂BLOCK,在给定时间内释放CPU资源
4. 一个线程sleep另一个线程调用interrupt会捕捉到中断信号,yield不会
5. B线程joinA线程,会使线程B进入等待状态,知道线程A结束生命周期,或者到达给定的时间

tips:

1. sleep方法不会释放锁资源

#### 1.5 jvm内存结构 ####

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

#### 1.6 线程interrupt ####

1. interrupt        可以打断线程的阻塞状态
2. interrupted      静态方法 会擦除线程的interrupt标识
2. isInterrupted    不会擦除线程的interrupt标识

tips:

1. interrupted和isInterrupted调用的是同一个方法 传递的参数值不一样

#### 1.7 线程的关闭 ####

1. 正常关闭
    - 线程结束生命周期正常结束
    - 捕获中断信号关闭线程

    - 使用volatile开关控制
2. 异常退出
    - 线程的执行单元中，不允许抛出checked异常的
3. 进程假死
    - 线程阻塞
    - 线程出现假死

#### 2.1 线程安全与数据同步 ####

synchronized 关键字使用方法

1. 同步方法
2. 同步代码块

tips:

synchronized关键字提供了一种互斥机制,即同一时刻只能有一个线程访问同步资源
使用误区:
1. 锁对象不能为空
2. 作用域太大 作用域越大,效率越低,甚至会丧失并发的优势
3. 不同的对象企图锁相同的方法
4. 多个锁的交叉导致死锁

#### 2.2 程序死锁原因及如何诊断 ####

1. 交叉锁可导致出现死锁
2. 内存不足
3. 一问一答式的数据交换
4. 数据库锁
5. 文件锁
6. 死循环引起的死锁

#### 2.3 wait和notify ####

1. wait是可中断方法
2. 线程执行了某个对象的wait方法后,会加入预支对应的wait set中,每一个对象的monitor都有一个与之关联的wait set
3. 必须在同步方法中使用wait和notify方法,因为执行wait和notify的前提小件是必须持有同步方法的monitor的所有权

tips:

1. wait和sleep 都可以使线程进入阻塞状态,都可被中断
2. wait 是Object的方法,sleep是线程特有的方法
3. wait方法的执行必须在同步方法中
4. wait方法会释放锁,sleep不会
5. sleep短暂时间休眠后会主动退出阻塞







