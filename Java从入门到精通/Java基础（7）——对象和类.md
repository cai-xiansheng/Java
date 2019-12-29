# Java对象和类
### Java类
```java
class Circle {
    double radius;
    // 数据域，创建对象的时候传递进来的数据全部在这儿。
    Circle(){
    }
    // 构造一个默认的对象（默认构造方法）
    Circle(double newRadius){
        radius = newRadius;
    }
    double getArea(){
        return radius * radius * Math.PI;
    }
    // 方法。
}
```

上面就是将一个简单的Java类。

### 通过引用遍量访问对象

##### 声明类

> ClassName objectRefVar ;

##### 创建一个对象

```java 
Circle myCircle;
myCircle = new Circle();
//这就是声明了一个对象，然后再创建了一个对象。
Circle myCircle = new Circle();
//跟数组一样，也就可以直接声明、创建。（所有对象都是通过new来的，所以说数组也是对象。）
```

##### 访问对象的数据和方法

> objectRefVar.dataField;引用对象的数据域。

> objectRefVar.method(参数);调用对象的方法。

> 不仅可以用以上方法调用，还可以直接使用：System.out.println(new Cicle(5).getArea());

局部便量在使用时必须初始化，不然编译器就会报错。

### 基本类型和引用类型变量的区别

##### 基本类型变量

基本类型变量就是我们之前所用到的，基本类型如果你把一个的值赋给另外一个就是将值赋给了变量。

##### 引用类型变量

引用行就是将变量的引用赋给，所以他们两个指向同一个对象。

### Java库中的类

#### Data类

提供了很多处理时间的方法。

#### Random类

产生随机数或者随机字母，随机布尔值等等……

### 静态变量、常量、方法

静态变量就是被同一个类的所有实例共享的。

静态方法就是可以通过引用他们的方法名来调用。很多Math类中的方法和JOptionPane类中的方法都是静态方法。

### 类访问的权限

> public 

public权限，在哪儿都可以访问，不管是在包外还是包内都可以使用。

> private 

private 修饰的方法和数据域就只能在它自己的类中被访问。（也就是私有）

### 数据域的封装

封装就是为了防止有人篡改数据。

封装就是通过给数据域private就可以实现。（封装的也就是私有的，不能被访问。）

