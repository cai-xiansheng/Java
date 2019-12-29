# 杨辉三角

![image-20191229214450530](C:\Users\张辉\AppData\Roaming\Typora\typora-user-images\image-20191229214450530.png)

## 我的程序

```java
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		in.close();
		int[][] a = Array(n); 
		printA(a,n);
	}
	public static int[][] Array(int n){
		int[][] a = new int[n][n]; 
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= i; j++) {
				if (i == j || j == 1) {
					a[i-1][j-1] = 1;
				}else if (i > 1){
					a[i-1][j-1] = a[i-2][j-2] + a[i-2][j-1];
				}
			}
		}
		return a;
	}
	public static void printA(int a[][],int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= i; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
	}
}
```

## 我的理解

本来以为有更好的解决办法，到头来还得用数组。使用数组创建一个我们所需要大小的二维数组，第一列和主对角线上的内容都是1，其他位置就按照杨辉三角的定义书写。再就是控制循环的次数。输出时要考虑题目中的要求。

