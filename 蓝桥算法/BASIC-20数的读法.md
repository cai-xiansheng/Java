# 1. 题目

![image-20200211110913251](C:\Users\张辉\AppData\Roaming\Typora\typora-user-images\image-20200211110913251.png)

# 2. 代码

```java
package BasicLQ;

import java.util.Scanner;
/**
 * 数的读法	判断		函数
 * @author 张辉
 *
 */
public class Basic20 {
	public static String[] nums = {"ling","yi","er","san","si","wu","liu","qi","ba","jiu"};
	public static String[] unit = {"shi","bai","qian","wan","yi"};
	public static int flag = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.close();
		StringBuilder result = new StringBuilder();
		int p = 0;
		int q = n;
		if (n == 0) {
			System.out.println(nums[0]);
		}
		if (n > 99999999) {
			p = q / 100000000; // 要使用的
			q = q % 100000000;
			add(result,p,1);
			result.append(unit[4]+" ");
		}
		if (n > 9999) {
			p = q / 10000; // 要使用的
			q = q % 10000;
			add(result,p,2);
			if ( p != 0) {
				result.append(unit[3]+" ");
			}
		}
		if (n > 0) {
			add(result,q,3);
		}
		System.out.println(result);
	}
	public static void add(StringBuilder str,int n,int io) {
//		io 用来控制10000出现的ling
		int p = 0;
		int q = n;
		if (q > 999) {
			p = q / 1000;
			q = q % 1000;
			str.append(nums[p] + " ");
			str.append(unit[2] + " ");
			flag = 1;
		}else if (flag == 1) {
			str.append(nums[0] + " ");
			flag = 0;
		}
		if (q > 99) {
			p = q / 100;
			q = q % 100;
			str.append(nums[p] + " ");
			str.append(unit[1] + " ");
			flag = 1;
		}else if (flag == 1) {
			str.append(nums[0] + " ");
			flag = 0;
		}
		if (q > 9) {
			p = q / 10;
			q = q % 10;
			if (p == 1) {
				str.append(unit[0] + " ");
			}else {
				str.append(nums[p] + " ");
				str.append(unit[0] + " ");
			}
			flag = 1;
		}else if (flag == 1) {
			str.append(nums[0] + " ");
			flag = 0;
		}
		if (q > 0) {
			str.append(nums[q] + " ");
			flag = 1;
		}else if (flag == 1) {
			str.append(nums[0] + " ");
			flag = 0;
		}
		if (flag == 0 && n > 0) {
			int strLength = str.length(); // 计算出来的是索引。
			str.delete(strLength - 5, strLength);
		}
		if (n == 0 && io == 3) {
			int strLength = str.length(); // 计算出来的是索引。
			str.delete(strLength - 5, strLength);
		}
	}
}
```

# 3. 理解

对于这个题目，首先就是将这个数分为三层：亿、万、个位。

然后将每层分别处理：也就是处理四层。

处理到这儿就会出现一个问题，怎么控制每层的 ling 的出现。这儿有两种处理方法：

1. 设置一个标志性全局变量，通过它来控制首位 ling 的出现。
2. 然后就是最后一位出现的 ling ，这个 ling 的处理就是考虑从末尾删除。
3. 还有如果第三层全部出现的是 0 ，为了顾及第二层，所以第三层必须重新定义 0 的处理方法。我的处理方法是传递一个参数进去，来确定是那一层传递进来的。

这儿遇到几个知识点：

1. 首先就是`StringBuilder`怎么删除字符段：`str.delete(start,end)`。
2. 字符串的长度包括空格。