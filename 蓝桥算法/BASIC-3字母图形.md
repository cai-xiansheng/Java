 

# BASIC-3 字母图形


# 1. 题目
 ![image.png](https://cdn.nlark.com/yuque/0/2020/png/657552/1579621478915-d9115156-e9b4-406f-bb22-98f24818e74c.png)


# 2. 代码

```java
import java.util.Scanner;
/**
 * 基础练习 字母图形
 * @author 张辉
 *
 */
public class Basic3x {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		in.close();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (i > j) {
					System.out.print((char)(i-j+'A'));
				}else {
					System.out.print((char)(j-i+'A'));
				}
			}
			System.out.println();
		}
	}
}
```


# 3. 理解
这儿我最难理解的就是图形的逻辑关系，（怕的是他会重复几十次，但是没想到它就是小于它的长度）。这个比较难理解。<br />还有不要按照传统逻辑直接将一行一块输出，也可以输出之后再进行一次换行，这样也就可以了！<br />![image.png](https://cdn.nlark.com/yuque/0/2020/png/657552/1579621888005-21aacea8-3ec1-4762-aa76-891afeefe158.png#align=left&display=inline&height=153&name=image.png&originHeight=160&originWidth=780&size=9156&status=done&style=none&width=746)<br />突然发现这个锦囊，是非常有用的，多看看！<br />看锦囊+找规律！实用的套路！