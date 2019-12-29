# Java方法
### 方法的定义
##### 例子：
```java
public static int max (int num1,int num2){
    int result;
    if (num1 > num2)
        result = num1;
    else
        result = num2;
    return result;
}
```
##### 语法
```java
修饰符  返回值类型  方法名  （形式参数）{
    方法体;
    返回值;
}
```

在Java中所有东西都可以被当作方法，方法也就是将一种可以被多次调用的内容写在一块被重复调用，这也就是Java中的封装，让使用者只能执行，不能看到内部的执行过程。也就是C中的函数。

### 调用方法

直接使用方法名跟实际参数调用，我们在这儿直接调用max()

```java
int n = max (2,8);
```

调用就是这么的简单。

但是在调用方法的过程中，可能存在相同方法名的多个方法，Java编译器就会通过参数来判断那个的效率更高，就会选择哪个方法。例如：

```java 
public static int max (int num1,int num2){
	……
}
public static int max (int num1,double num2){
	……
}
public static int max (double num1,double num2){
	……
}
int n = max(1,4);
double n = max(1.0,4);
double n = max(4.0,6.8);
```

在这儿，编译器就会选择最合适的那个方法给我们处理。

在调用方法的时候系统都会将参数，局部变量存储在一个称为堆栈的内存区域中，它使用后进先出的方式存储数据。当一个方法调用另外一个方法时，调用者的堆栈空间保持不变，开辟新的空间处理新方法的调用。一个方法结束返回调用者时，其相应的空间就会被释放。

### 重载方法

顾名思义就是多次调用一个方法。

### Math数学类

##### 三角函数方法

> Math.toDegrees(Math.PI / 2);从弧度变角度

> Math.toRadians(30);从角度变弧度

> Math.sin(0);

> Math.asin();

其他三角函数的方法都是存在的。

##### 指数函数方法

> Math.exp(1);这就是求e得次方

> Math.log();等方法

##### 取整方法

> ceil();就是求一个数的右边整数

>floor();求一个数的左边整数

> rint();数的取整（就是不管小数）

> round();数的取整（管小数，如果有小数，那么就是它绝对值加1）

##### min、max、abs

> min(1,2,3,5);求最小值

> max(1,2,5,9);求最大值

> abs(-2);求绝对值

##### random方法

```java
(int)(Math.random() * 10);
//随机在0~9之间产生一个随机整数。
a + Math.random() * b;
//返回a到a+b之间的随机数，但不包括a+b。
50 + (int)(Math.random() * 50);
//返回50到99之间的随机整数。
```

从上面可以看出，random产生的数与限定的类型有关。

不仅可以产生上面的随机数字，也可以产生随机字母。

```java
(char)('a' + Math.random() * ('z' - 'a' + 1));
```

