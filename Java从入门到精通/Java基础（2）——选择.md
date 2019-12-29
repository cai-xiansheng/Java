# Java选择

### 布尔类型

在选择结构中所有的判断条件都是按照布尔值来判断的。

### if语句

```python
if (布尔表达式){
	语句（组）;
}
else if(布尔表达式){
	语句（组）;
}
else{
    语句（组）;
}
```

基本的使用语法跟C语言中的完全一致。

### 逻辑运算符

与运算符（&&）

或运算符（||）

非运算符（!）

异或运算符（^）

### switch语句

```java
switch(witch条件){
    case 值1:语句（组）1;
        break;
    case 值2:语句（组）2;
        break;  
    default:默认请款下执行的语句（组）;
}
```

### 条件表达式

三元条件表达式：

> y = (x > 0) ? 1 : -1;

### 格式化输出

> System.out.printf(format,item1,item2);

java中格式化输出类似于C语言中的printf()一样。

### GUI对话框

使用showConfirmDialog()来显示一个输入的对话选择框。