#  **回文数**   

![image-20191230224759319](C:\Users\张辉\AppData\Roaming\Typora\typora-user-images\image-20191230224759319.png)

## 我的程序

```java
public class Basic8 {
	public static void main(String[] args) {
		char[] a = new char[] {1,0,0,0};
		for (int i = 1; i < 10; i++) {
			a[0] = a[3] = (char)i;
			for (int j = 0; j < 10; j++) {
				a[1] = a[2] = (char)j;
				System.out.println(a[0]*1000+a[1]*100+a[2]*10+a[3]);
			}
		}
	}
}
```

## 我的理解

刚开始准备逐个判断，但是太耗时。所以直接找四位回文数的特点，利用规律输出即可。

需要注意的就是输出一个结果后，要换行（这点系统中没有说明）。