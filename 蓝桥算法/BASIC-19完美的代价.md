# 1.题目

![image-20200211105221711](C:\Users\张辉\AppData\Roaming\Typora\typora-user-images\image-20200211105221711.png)

# 2. 我的代码

```java
package BasicLQ;

import java.util.Scanner;
/**
完美的代价
	贪心算法

存在的问题：比如
		9
		ffdejjell
	存在一个问题：d不在后边。就得处理这儿。

* @author 张辉
*
*/
public class Basic19x {	
	public static int num = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		// n表示字符串的长度
		String str = sc.next();
		// str 表示要判断的回文字符串
		sc.close();
		char[] ch = str.toCharArray();
		// 将输入进来的字符串转变成 字符数组
		if (!isPalindrome(ch)) {
			System.out.println("Impossible");
			return;
		}
		for (int i = 0; i < n/2; i++) {
			hg:for (int j = n - 1 - i; j > i; j--) {
				if (ch[i] == ch[j]) {
					break hg;
				}else {
                    // 循环寻找互相匹配的字符。如果找不到，说明这个字符是唯一的那个。所以将这个字符串反转。
                    for (int k = j-1; k > i; k--) {
						if (ch[i] == ch[k]) {
							swp( ch, j, k);
							num += j-k;
							break hg;
						}
					}
					char tmp;
                    // 将字符串反转的方法
					for (int k = 0; k < n/2; k++) {
						tmp = ch[k];
						ch[k] = ch[n-1-k];
						ch[n-1-k] = tmp;
					}
					i --;
					j ++;
					break;
				}
			}
		}
		System.out.println(num);
	}
	public static char[] swp(char[] ch,int j,int k) {
        // 进行字符交换
		char tmp;
		for (int i = k; i < j; i++) {
			tmp = ch[i];
			ch[i] = ch[i+1];
			ch[i+1] = tmp;
		}
		return ch;
	}
	public static boolean isPalindrome(char[] ch) {
		// 判断字符串是不是可以构成回文串。
		// 若是：return true;
		// 否则：return false;
		int[] a = new int[26];
		for (int i = 0; i < ch.length; i++) {
			a[ch[i] - 'a']++;
		}
		int flag = 0;
		for (int i = 0; i < 26; i++) {
			if (a[i] % 2 == 1) {
				flag ++;
			}
		}
		if (flag > 1) {
			return false;
		}
		return true;
	}	
}
```

# 3.我的理解

刚开始对这个题目没有理解，所以不能做出争取的判断，对这个题理解了之后就好处理多了。

处理到最后就要解决奇数个字符的问题，防止单个的字符出现在字符串的前半部分或者交换到前半部分。如果出现这种情况，直接将字符串反转。