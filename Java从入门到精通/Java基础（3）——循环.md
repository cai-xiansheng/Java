# Java循环
### while循环
```java
while(循环继续条件){
    //循环体
    语句（组）;
}
```
基础操作完全类似于C里面的操作。可以做一个猜数的小游戏。
```java
import java.util.Scanner;
public class Sthree {
    public static void main(String[] args) {
        int number = (int) (Math.random()*101);
        Scanner input = new Scanner(System.in);
        System.out.println("Guess amagic number between 0 and 100");
        int guess = -1;
        while (guess != number){
            System.out.print("\nEnter your guess:");
            guess = input.nextInt();
            if (guess == number)
                System.out.println("Yes,the number is " + number);
            else if (guess > number)
                System.out.println("You guess is too high");
            else
                System.out.println("You guess is too low");
        }
    }
}
```
##### 可以使用标志符控制循环的次数，避免出现死循环。
### 定向输入输出
就这个来说，定向输入输出是为了处理大量的数据儿而准备的，比如：输入大量数据（几千人的信息），这就很费事，我们通过定向文件输入这就很省时。输出也是如此。
>输入重定向 java Classname < input.txt

>输出重定向 java Classname < output.txt

>统一命令输入重定向和输出重定向 java Classname < input > output.txt

### do-while循环
```java
do {
    //循环体;
    语句（组）;
} while(循环继续条件);
```
do-while 循环先是执行循环体结构，最后再判断循环继续条件是否成立，如果不成立，就将跳出循环，反之，不跳出循环，继续执行循环体。

这个循环可以用于从键盘输入退出循环的过程，就比如说输入0将退出循环，就可以用输入作为循环继续条件。
### for 循环
```java
for(初始操作;循环继续条件;每次迭代后的操作){
    //循环体;
    语句（组）;
}
```
这个最直接的就是多次输出同一个类型的数据，这样就可以避免我们程序赘余的问题。
### 采用那种循环？
>while循环采用预测试循环（就是先判断条件，然后再执行循环体。），因为它的继续条件是在循环条件之前检测的。

>do-while循环采用后测试循环，因为循环条件是在循环体执行之后检测的。
### （GUI）对话框循环控制
循环控制就例如上面do-while中的输入0循环控制。

使用确认对话框就可以实现标志值得条件控制。

```java
int option = JOptionPane.YES_OPTION;
while (option = JOptionPane.YES_OPTION){
    System.out.println("continue loop");
    option = JOptionPane.showConfirmDialog(null,"Continue?");
}
```
### 关键字break和continue
>break 跳出循环体（可以用到循环体和switch语句中）

>continue 跳出本次循环（可以用到循环体和if语句中）
### 最小化数值误差
在数值计算过程中，浮点数的计算会产生误差，这样的误差不可避免，但是尽可能地规避。如果在循环中不规避就有可能造成死循环的情况！

#### 求两个数的gcd
```java
import java.util.Scanner;
public class Sthree {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter first integer:");
        int n1 = input.nextInt();
        System.out.print("Enter second integer:");
        int n2 = input.nextInt();
        int gcd = 1;
        int k = 2;
        while (k <= n1 && k <= n2){
            if (n1 % k == 0 && n2 % k ==0)
                gcd = k;
            k++;
        }
        System.out.println("The greatest common divisor for " + n1 + " and " + n2 + " is " + gcd);
    }
}
```