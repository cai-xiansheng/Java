# Java字符串（String）和文本I/O

### String

String是字符串类型。字符串类型也是一个对象（new出来的）。

##### 构造

> String newString = new String(字符串的内容);

> String message = new String("Welcome to Java");

上面分别是构造方法和构造的实例。

##### 不可变字符串与限定字符串

不可变：就是当字符串被创建之后不能再被改变，因为String对象是不可变的，他的内容也是不能被改变的。如果你通过强制处理（也就是直接给赋值，那么就只会把当前指向原来字符串的变量指向新赋的值），那么就不能再访问它了。

限定字符串：限定就是可以从多个变量访问同一个String对象（也就给相同字符串序列的字符串使用同一个实例）。

> String s1 = "Welcome to Java";

> String s2 = "Welcome to Java";

这样就会指向同一个实例。

### 字符串处理

##### 字符串的比较

比较字符串就是使用String类提供的多种会字符串进行比较的方法：

> s1.equals(s2);	比较字符串是不是完全相同：Boolean

>s1.equalsIgnoreCase(s2);	不区分字符串大小写：Boolean

>s1.compare(s2);	比较字符串大小：int（前边大于后边就是正数，后边大于前边是负数，等于就是0）

>s1.compareToIgnoreCase(s2);	不区分大小写比较字符串（其他于compare相同）

>s1.startsWith(string);	判断字符串是不是以我们给的字符串开头的

>s1.endsWith(string);	判断字符串是不是以指定后缀结束的

##### 字符串长度

> s1,length();	

##### 获取单个字符

> s1.charAt(num);	返回指定下标处的元素

##### 组合字符串

> s1.concat(s2);	连接这两个字符串，赋给新的变量

##### 获取字符串

> s1.substring(beginIndex,endIndex);	可以获取字符串段，可以省略endIndex。

##### 字符串的的转换

>s1.toLowerCase();

>s1.toUpperCase();

##### 字符串的替换

> s1.replace(oldchar,newchar);	替换所有字

> s1.replaceFirst(oldstring,newstring);	只替换第一个字符串

> s1.replaceAll(oldstring,newstring);	替换所有字符串

##### 删除字符串空白

> s1.trim();

##### 分割

> s1.split("[.,:;?]")		在这儿就会把s1分割了，如果出现[]中的字符机会分割

##### 字符串与数组之间的转换

> char[] chars = "java".toCharArray();		将字符串Java变成了数组

> s1.getChars(int scrBegin,int ScrEnd,char[] charname,int charnameBegin);将前边的字符串从scrBegin到ScrEnd-1全部复制到数组的charnameBegin位置。

##### 格式化字符串

> String s = String.format(格式,数据);	这儿的格式就类似于C语言中的："%5.2f"

### 字符类

字符类中包含了处理字符的多种方法。

### StringBuilder/StringBuffer类

这里面包含了很多处理字符串的方法，追加、删除、插入、颠倒、替换等等。

### 文件的输入输出

##### 使用PrintWriter写数据

> java.io.PrintWriter output = new java.io.PrintWriter(filename);	创建一个新对象

> output.print("neirong ");	写入数据

##### 使用Scanner读数据

使用方法跟前边的输入相同。

