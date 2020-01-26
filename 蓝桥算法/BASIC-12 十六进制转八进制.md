# BASIC-12 十六进制转八进制


# 1. 问题
![image-20200126211005863](C:\Users\张辉\AppData\Roaming\Typora\typora-user-images\image-20200126211005863.png)

# 2. 代码

```java
import java.util.Scanner;

/**
 * 十六进制转八进制
 * 	十六进制15	转换成二进制1111 1+2+4+8
 * 	16:F 10:15 8:17 2'8': 0001:1011	17	1111
 * 	16:9 10:9 8:12 2'8': 0001:0010	12	1001
 * 	16:6 10:6 8:06 2'8': 0000:0110	6	0110
 * 		111 110 010 110
 * @author 蔡先生
 * hexadecimal
 * octal
 * binary
 * hexTobin
 * binTooct
 */
public class Basic12x {
	private static final String[] strs={"0000","0001","0010","0011","0100","0101","0110","0111","1000","1001","1010",
			"1011","1100","1101","1110","1111"};
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		String[] strs = new String[n];
		for (int i = 0; i < n; i++) {
			strs[i] = in.next();
		}
		in.close();
		for (int i = 0; i < n; i++) {
			StringBuilder result = new StringBuilder();		
			result = hexTobin(strs[i]);
			binTooct(result);	
		}
	}
	public static void binTooct(StringBuilder str) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < str.length()-2; i+=3) {
			result.append(switchsO(str.substring(i, i+3)));
		}
		if ("0".equals(result.substring(0,1))) {
			System.out.println(result.substring(1));
			return;
		}
		System.out.println(result);
	}
	public static StringBuilder hexTobin(String str) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			result.append(switchH(str.charAt(i)));
		}
		int length = result.length();
		if (length % 3 != 0 ) {
			int n = length%3;
			switch (n) {
			case 1:
				result.insert(0,"00");
				break;
			case 2:
				result.insert(0,"0");
				break;
			}
		}
		return result;
	}
	public static String switchH(char c) {
		switch (c) {
		case '0':
			return strs[0];
		case '1':
			return strs[1];
		case '2':
			return strs[2];
		case '3':
			return strs[3];
		case '4':
			return strs[4];
		case '5':
			return strs[5];
		case '6':
			return strs[6];
		case '7':
			return strs[7];
		case '8':
			return strs[8];
		case '9':
			return strs[9];
		case 'A':
			return strs[10];
		case 'B':
			return strs[11];
		case 'C':
			return strs[12];
		case 'D':
			return strs[13];
		case 'E':
			return strs[14];
		case 'F':
			return strs[15];
		}
		return "";
	}
	public static String switchsO(String s) {
		switch (s) {
		case "000":
			return "0";
		case "001":
			return "1";
		case "010":
			return "2";
		case "011":
			return "3";
		case "100":
			return "4";
		case "101":
			return "5";
		case "110":
			return "6";
		case "111":
			return "7";
		}
		return "";
	}
}
```


# 3. 理解
![image.png](https://cdn.nlark.com/yuque/0/2020/png/657552/1580044455950-7e6283b8-0d96-46ae-85c1-8cd6bac58279.png#align=left&display=inline&height=154&name=image.png&originHeight=201&originWidth=976&size=12119&status=done&style=none&width=746)<br />这个提示很重要！<br />之前写的时候写成了通过十进制转化的，但是如题目给的数据范围远远超过了long，所以就只能被out。这还是很难的！所以直接利用二进制还是非常好的！<br />关键是转化过程中的细节！

要注意equals和==的使用，两个是完全不同的东西。<br />详细关注这个链接：[链接](https://www.cnblogs.com/weibanggang/p/9457757.html)