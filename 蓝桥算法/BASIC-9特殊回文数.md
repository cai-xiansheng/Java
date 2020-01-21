# BASIC-9 特殊回文数


# 1. 题目
![image-20200121223404529](C:\Users\张辉\AppData\Roaming\Typora\typora-user-images\image-20200121223404529.png)


# 2. 代码：
```java
import java.util.Scanner;

/**
 *   特殊回文数
 * @author 张辉
 *
 */
public class Basic9 {
	public static void main(String[] args) {
		int n;
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		in.close();
		/**
		 * 首先，只要是回文数，都得进行对称的处理
		 * 		要是奇数，就只能组成回文5位数，不能组成6位回文数
		 * 		偶数的话，就可以进行组成六位数。
		 * 	所以首先判断这个数是不是奇数，如果是奇数就直接进行5位回文数的处理。
		 * 		偶数的话就直接进行六位数处理，然后判断其大小 ，是否进行5位的处理。
		 * if (这个数是不是奇数){
		 * 		处理5位的回文数
		 * }else {
		 * 		处理六位的回文数
		 * 		if (n < 45){
		 * 			只能进行5位数的处理
		 * 		}
		 * }
		 */
		
		if (n % 2 == 1) {
			// 处理五位的回文数
			huiwen5(n);
		} else {
			if (n < 45) {
				// 处理五位的回文数
				huiwen5(n);
			}
			// 处理六位的回文数
			huiwen6(n);
		}
	}
	public static void huiwen5(int n) {
		// 特殊回文数的5位数！
		int[] a = new int[] {0,0,0,0,0};
		for (int i = 1; i < 10; i++) {
			if (n <= 1) {
				break;
			}
			a[0] = a[4] = i;
			for (int j = 0; j < 10; j++) {
				int m = n - 2*i - 2*j;
				if (m >= 0 && m < 10) {						
					a[1] = a[3] = j;
					a[2] = m;
					print(a);
				}
			}
		}
	}
	public static void huiwen6(int n) {
		// 特殊回文数的6位数！
		int[] a = new int[] {0,0,0,0,0,0};
		for (int i = 1; i < 10; i++) {
			a[0] = a[5] = i;
			for (int j = 0; j < 10; j++) {
				int m = n - 2*i - 2*j;
				if (m >= 0 && m < 20) {						
					a[1] = a[4] = j;
					a[2] = a[3] = m/2;
					print(a);
				}
			}
		}
	}
	public static void print(int[] a) {
		// 将数组直接输出成数！
		int b = 0;
		for (int k = 0; k < a.length; k++) {
			int c = 1;
			for (int l = 0; l < k; l++) {
				c *= 10;
			}
			b += a[a.length-k-1]*c;
		}
		System.out.println(b);
	}
}
```


# 3. 思路
这个挺难的，首先需要将题目的要求分开，如下：

```java
		 if (这个数是不是奇数){
		 		处理5位的回文数
		 }else {
		  	处理六位的回文数
		  	if (n < 45){
		 		只能进行5位数的处理
		  	}
		 }
```

然后再将输出的函数封装，这样就可以直接输出！