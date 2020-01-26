# BASIC-10 十进制转十六进制


# 1. 题目
![image-20200122155857537](C:\Users\张辉\AppData\Roaming\Typora\typora-user-images\image-20200122155857537.png)



# 2. 代码

```java
import java.util.Scanner;

/**
 * 十进制转十六进制
 * 循环  整除  求余  判断
 * 样例输入
 * 30
 * 样例输出
 * 1E
 * @author 张辉
 *
 */
public class Basic10 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int q = in.nextInt();
		in.close();
		StringBuilder a = new StringBuilder();
		if (q<16) {
			appends(q, a);
			print(a);			
			return;
		}
		int p = 0;
		hexadecimal(p, q, a);
		print(a);
	}
	public static void print(StringBuilder a) {
		StringBuilder str = a.reverse();
		System.out.println(str);
	}
	public static void hexadecimal(int p,int q,StringBuilder a) {
		p = q % 16;
		appends(p, a);
		q = q / 16;
		if (q>=16) {
			hexadecimal(p, q, a);
		}else {
			appends(q, a);
			return;
		}
	}
	public static void appends(int p,StringBuilder a) {
		// 将一个数 append 进a;
		if (p>9) {
			switch (p) {
			case 10:
				a.append('A');
				break;
			case 11:
				a.append('B');
				break;
			case 12:
				a.append('C');
				break;
			case 13:
				a.append('D');
				break;
			case 14:
				a.append('E');
				break;
			case 15:
				a.append('F');
				break;
			default:
				break;
			}
		}else {
			a.append(p);
		}
	}
}
```

# 3. 理解
由十进制转十六进制，我在这儿封装了三个方法。首先是给定一个小于16的非负整数，将其处理成十六进制，也就是appends方法。然后就是将十进制处理成十六进制的主要结构。其次一个输出函数。<br />这儿用到了一个StringBuilder，用来存储数据！