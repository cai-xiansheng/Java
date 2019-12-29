# Java基本程序设计
### 从控制台读取输入
*需要注意的是Java并不支持控制台的输入，然而我们可以通过Scanner创建输入的对象。*

**调用Scanner类**

>调用格式：import java.util.Scanner;

**创建Scanner对象**

>创建格式：Scanner input = new Scanner (System.in);

>new Scanner (System.in)，创建了一个Scanner类型的对象，读取来自System.in的输入。

>Scanner input ，声明input是Scanner类型的变量。

**Scanner对象调用的方法**

>double r = input.nextDouble();   这样就读取了一个double类型的数。

    方法有:
    nextByte()          读取一个byte类型的整数
    nextShort()         读取一个short类型的整数
    nextInt()           读取一个int类型的整数
    nextLong()          ...
    nextFloat()         ...
    nextDouble()        ...
    next()              读取一个字符串,该字符串在一个空格之前结束
    nextline()          读取一行文本(按下回车键结束)

**控制台输出**
>System.out.println();println会在输出玩之后将光标放到下一行。

>System.out.print();print不会将光标挪到下一行。

示例:
```java
import java.util.Scanner;
public class Second {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入：");
        double double1;
        double1 = input.nextDouble();
        double double2 = input.nextDouble();
        System.out.print(double1);
        System.out.println(double2);
    }
}
```
### 标识符

标识符只能由字母，数字，下划线，美元符号(一般不用它)构成，不能是数字开头，也不能是Java保留字。

### 变量

变量类型跟c里面的一样。变量通常都得有初始值。

-   byte     8位，1字节
-   short    16位，2字节
-   int      32位，4字节
-   long     64位，8字节
-   float在数字后边必须加f           32位，4字节。
-   double在数字后边可以不用加d        64位，8字节。
-   char 用于存储单个字符，占用16位，2个字节的内存空间。在定义时，char中''用于定义字符，String用（""）定义字符串。

```java
public class Second {
    public static void main(String[] args) {
        long totalMilliseconds = System.currentTimeMillis();
        //获取从格林威治标准时间到现在的毫秒数
        long totalSeconds = totalMilliseconds / 1000;
        //计算秒数
        long currentSecond = totalSeconds % 60;
        //计算当前秒数
        long totalMinutes = totalSeconds / 60;
        //计算分钟数
        long currentMinues = totalMinutes % 60;
        //计算当前分钟数
        long totalHours = totalMinutes / 60;
        //计算小时数
        long currentHours = totalHours % 24;
        //计算当前小时数
        System.out.println(totalHours);
        System.out.println("Current time is " + currentHours + ":" + currentMinues + ":" + currentSecond + "  GMT");
    }
}
```

上面就是利用变量，再通过System类中方法currentTimeMillis()获得的当前时间。

### 常量

常量用final声明，常量只能被赋值一次，并且常量命名时用大写字母命名。

> final datatype CONSTANTNAME = value；

### 数值类型的转化

从低精度到高精度使用隐式转化：

```java
int x = 456;
long y = x;
```

从高精度到低精度使用显式转化：

```java
int x = (long)456;
```

### Math类

Math类不用导入，因为Math类在java.lang包中，java.lang包中所有的类都被隐式的导入。

### String类型

String（字符串）实际上是Java里面预定义的一个类，String类型不是基本类型，它是引用类型。

```java
String message = "Welcome " + "to " + "java";
//message将会变成"Welcome to java"
String s = "chapter" + 2;
//s将会变成"chapter2"
String s1 = 1 + 2;
//s1将会变成"12"
```

### 使用对话框输入

**引入JOptionPane类**

这个类在javax.swing包中，这个类必须自己引入，不然被就会出错。

> import javax.swing.JOptionPane;

```java
import javax.swing.JOptionPane;
public class Second {
    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog("Enter an input");
        //这个中的"Enter an input"是提示字符串。
        String input1 = JOptionPane.showInputDialog(null,"Enter an input","Input Dialog Demo",JOptionPane.QUESTION_MESSAGE);
        //null,我也不知道什么意思，message("Enter an input")就是给对话框中输出一个提示信息，title("Input Dialog Demo")是对话框的标题，JOptionPane.QUESTION_MESSAGE这个就是给对话框里面显示的图标。
        double double1 = Integer.parseInt(input);
        //通过对话框输入的就是一个字符串，它就会返回一个字符串，所以我们需要将字符串转化为数字。
        //使用Integer类中的parseInt方法。
        System.out.println(input);
        System.out.println(input1);
        System.out.println(double1);
        String output1 = "This number is " + double1;
        //通过加号进行字符串连接。String 进行字符串连接时就是字符连接，
        JOptionPane.showMessageDialog(null,"This number is " + double1);
        //JOptionPane类中showMessageDialog()方法可以通过对话框向外输出。
        JOptionPane.showMessageDialog(null,double1);
    }
}
```

**输入JOptionPane.showInputDialog**

>String input = JOptionPane.showInputDialog("Enter an input");

**输出JOptionPane.showMessageDialog**

>JOptionPane.showMessageDialog(null,"This number is " + double1);