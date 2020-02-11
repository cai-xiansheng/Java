# BASIC-16 分解质因数


# 1. 题目
![image-20200130014012112](C:\Users\张辉\AppData\Roaming\Typora\typora-user-images\image-20200130014012112.png)
# 2. 代码

## 代码1：
```java
package BasicLQ;
/**
 * @author 张辉
 * 这儿减少了之前无用的工作。
 * 直接循环每一个数
 * 只要可以达到一个结果，那就可以确定就是素数。
 */
import java.util.Scanner;

public class Basic16x {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int a = in.nextInt();
		int b = in.nextInt();
		in.close();
		int j,k;
		for (int i = a; i <= b; i++) {
			j = 1;
			k = i;
			System.out.print(k + "=");
			while (k != 1) {
				j++;
				if (k % j == 0) {
					k /= j;
					if (k == 1) {
						System.out.print(j);
					}else {
						System.out.print(j + "*");
					}
					j = 1;
				}
			}
			System.out.println();
		}
	}
}
```

## 代码2：

```java
package BasicLQ;

import java.util.Scanner;

/**
 * 分解质因数
 * 质因数分解	循环
 * @author 张辉
 * 这个程序耗费时间太长，在测试案例中只通过了5个，其他几个都是由于时间的问题造成了出错。
 */
public class Basic16 {
	public static int num = -1;
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		Scanner in = new Scanner(System.in);
		int a = in.nextInt();
		int b = in.nextInt();
		in.close();
		mains(primse(b),a,b);
		long endTime = System.currentTimeMillis();
		System.out.println("程序运行时间：" + (endTime - startTime)/1000 + "s");
	}
	public static void mains(int[] array,int a,int b) {
		for (int i = a; i <= b; i++) {
			for (int j = 0; j <= num; j++) {
				StringBuilder str = new StringBuilder();
				str.append(i + "=");
				if (i == array[j]) {
					str.append(i);
					System.out.println(str);
					break;
				}
				int n = i / array[j];
				if (i % array[j] != 0) {
					continue;
				}
				if (in(array, n)) {
					// 再循环一次
					// true;
					str.append(array[j]);
					str.append("*" + n);
					System.out.println(str);
					break;
				}else {
					// false;
					str.append(array[j]);
					fors(array, str, n, j);
					break;
				}
			}
		}
	}
	public static void fors(int[] array,StringBuilder str,int a,int b) {
		/**
		 * array 是传递过来的素数数组
		 * str 是已经准备好的字符串，这个字符串用来存储结果
		 * a 是上一次的n。
		 * b 是上一次循环到的位置
		 */
		 for (int i = b; i <= num; i++) {
			int n = a / array[i];
			if (a % array[i] != 0) {
				continue;
			}
			if (in(array, n)) {
				str.append("*" + array[i]);
				str.append("*" + n);
				System.out.println(str);
				return;
			}else if(n > array[i]) {
				str.append("*" + array[i]);
				fors(array, str, n, i);
				return;
			}
		}
	}
	public static boolean in(int[] array,int n) {
		int flag = 0;
		for (int i = 0; i <= num; i++) {
			if (n == array[i]) {
				flag = 1;
				break;
			}
		}
		if (flag == 0) {
			return false;
		}else if (flag == 1) {
			return true;
		}
		return true;
	}

	public static int[] primse(int n) { // 求n 以内的所有素数，返回为一个数组（数组大小是n的一半）。
		int[] array = new int[(n+1)/2];
		for (int i = 2; i <= n; i++) {
			int flag = 0;
			for (int j = 1; j <= i; j++) {
				if (i % j == 0 && (j == 1 || j == i)) {
					continue;
				}else if(i % j == 0){
					flag = 1;
				}
			}
			if (flag == 0) {
				array[++num] = i;
			}
		}
		return array;
	}
}

```


# 3. 理解
首先说以下我对**代码2**的理解：<br />这段代码就会出现一个严重的问题，就是遇到大量的数据绝对会超时。<br />占用的空间：这儿因为要使用迭代，会使用很多的空间，所以回使用大量的空间。<br />占用的时间：迭代本身就会花费很多时间，再者空间的开辟也会消耗大量的时间。<br />其次**代码1：**<br />这段代码，使用的方法比较巧妙，我还是看了半天都不太明确的。他这儿对素数的理解比较深刻，直接在循环中共判断素数，说来这儿的素数方法更好一些。