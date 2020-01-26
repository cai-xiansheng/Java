# BASIC-13 数列排序


# 1. 题目
![image-20200122163900827](C:\Users\张辉\AppData\Roaming\Typora\typora-user-images\image-20200122163900827.png)


# 2. 代码

```java
import java.util.Scanner;

/**
 * 数列排序
 * @author 张辉
 *
 */
public class Basic13 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = in.nextInt();
		}
		in.close();
		maopao(n, a);
		print(n, a);
	}
	public static void maopao(int n,int a[]) {
		for (int i = 0; i < n; i++) {
			int flag = 0;
			for (int j = i+1; j < n; j++) {
				if (a[i]>a[j]) {
					flag = a[i];
					a[i] = a[j];
					a[j] = flag;
				}
			}
		}
	}
	public static void print(int n,int a[]) {
		for (int i = 0; i < n; i++) {
			System.out.print(a[i] + " ");
		}
	}
}
```


# 3. 理解
直接利用排序算法。