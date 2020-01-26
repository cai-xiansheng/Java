# BASIC-11 十六进制转十进制


# 1. 问题
![image-20200122233459671](C:\Users\张辉\AppData\Roaming\Typora\typora-user-images\image-20200122233459671.png)
# 2. 代码

```java

import java.util.Scanner;

/** 
 * @十六进制转十进制
 * 进制转化 字符处理 判断
 * @author 张辉
 *
 */
public class Basic11 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String str = in.nextLine();
		in.close();
		long sum = 0;
		int n = str.length();
		int m = 0;
		for (int i = 0; i < n; i++) {
			switch (str.charAt(i)) {
			case 'A':
				m = 10;
				break;
			case 'B':
				m = 11;
				break;
			case 'C':
				m = 12;
				break;
			case 'D':
				m = 13;
				break;
			case 'E':
				m = 14;
				break;
			case 'F':
				m = 15;
				break;
			default:
				m = (int)(str.charAt(i) - '0');
				break;
			}
			int b = n-i-1;
			long flag = (long)Math.pow(16, b);
			sum += m*flag;
		}
		System.out.println(sum);
	}
}
```


# 3. 理解
主要是字符的处理以及基本数据类型的使用，必须知道每个数据类型的大小以及范围。<br />还要注意charAt 的使用，这个方法在处理整数的时候尽量使用自己熟悉的套路，以免翻车！