# Java-polymorphic
面向对象三大特征之三：多态


## 1. 多态的概述

## 1.1 什么是多态？

指对象可以有多种形态。
多态的常见形式

## 1.2 多态中成员访问特点

方法调用：编译看左边，运行看右边。
变量调用：编译看左边，运行也看左边。（注意）

## 1.3 多态的前提

有继承/实现关系；有父类引用指向子类对象；有方法重写（多态侧重行为多态）。

```java
package com.company.polymorphic;

public class Animal {
    public  void run(){
        System.out.println("跑");

    }}
    package com.company.polymorphic;

public class Dog extends  Animal {
    public String name = "🐕";
    @Override
    public void run() {
        System.out.println("🐕跑的快");
    }
}
package com.company.polymorphic;

public class Tortoise extends  Animal {
    public String name = "🐢";
    @Override
    public void run() {
        System.out.println("🐢跑的慢");
    }
}
```

```java
package com.company.polymorphic;

public class Test {
    public static void main(String[] args) {
        //多态形式
        //方法调用：编译看左边，运行看右边。
        //变量调用：编译看左边，运行也看左边
        Animal a = new Dog();
        a.run();
        System.out.println( ((Dog) a).name );


        Animal a2 = new Tortoise();
        a2.run();
        System.out.println( ((Tortoise) a2).name);

    }
}
```

## 1.4 多态的优势

在多态形式下，右边对象可以实现解耦合，便于扩展和维护。

定义方法的时候，使用父类型作为参数，该方法就可以接收这父类的一切子类对象，体现出多态的扩展性与便利。
多态下会产生的一个问题:
多态下不能使用子类的独有功能

```java
package com.company.polymorphic.advantage;

public class Test {
    public static void main(String[] args) {
        Animal d = new Dog();
        go(d);
        //多态下不能使用子类的独有功能
        //d.LookDog();
        Animal t = new Tortoise();
        go(t);
        
    }
    public  static  void go(Animal a){
        System.out.println("预备");
        a.run();
        System.out.println("结束");
    }
}
```

## 1.5 多态下: 类型转换问题

自动类型转换（从子到父)：Animal c = new Cat();
强制类型转换（从父到子)
从父到子（ 必须进行强制类型转换,否则报错）:  子类 对象变量 = (子类)父类类型的变量
 作用：可以解决多态下的劣势，可以实现调用子类独有的功能。
 注意： 有继承/实现关系的类就可以在编译阶段进行强制类型转换；但是，如果转型后的类型和对象真实对象的类型不是同一种类型，那么在运行代码时，就会出现ClassCastException

Java建议强转转换前使用instanceof判断当前对象的真实类型，再进行强制转换



```java
package convert;

public class Test {
    public static void main(String[] args) {
        //自动类型转化
        Animal a = new Dog();
        a.run();
       // a.lookDog();
        //强制类型转换 可以实现调用子类独有功能
        Dog d = (Dog) a;
        d.LookDog();
        //注意，多态强制类型转换可能类型转换异常
        //运行可能出错
        //Tortoise t = (Tortoise) a;
        //先判断 instanceof
        if(a instanceof  Tortoise) {
            Tortoise t = (Tortoise) a;
            t.LayEggs();
        } else if(a instanceof Dog){
            Dog d1 = (Dog) a;
            d1.LookDog();
        }
        
    }
}
```



## 1.6 引用数据类型的类型转换，有几种方式？

 自动类型转换、强制类型转换。

## 1.7 强制类型转换能解决什么问题？

 可以转换成真正的子类类型，从而调用子类独有功能。

## 1.8 强制类型转换需要注意什么？

 有继承关系/实现的2个类型就可以进行强制转换，编译无问题。
 运行时，如果发现强制转换后的类型不是对象真实类型则报错（ClassCastException）
强制类型转换前最好做什么事情，如何进行？
 使用instanceof判断当前对象的真实类型，再进行强制转换
 对象变量名 instanceof 真实类型

## 1.9 多态的综合案例

①定义一个USB的接口（申明USB设备的规范必须是：可以接入和拔出）。

②提供2个USB实现类代表鼠标和键盘，让其实现USB接口，并分别定义独有功能。

③创建电脑对象，创建2个USB实现类对象，分别安装到电脑中并触发功能的执行。

```JAVA
package test;

public class Computer {
    public  void installUSB(USB u){
        u.connect();
        if(u instanceof  Mouse){
            Mouse m =(Mouse) u;
            m.click();

        }else if (u instanceof  KeyBoard){
            KeyBoard k = (KeyBoard) u;
            k.keyDown();
        }
        u.unconnect();

    }

}
package test;

public class KeyBoard implements  USB {
    private  String name;

    public KeyBoard(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void connect() {
        System.out.println(name + "成功弹出");

    }

    @Override
    public void unconnect() {
        System.out.println(name + "成功弹出");

        }
    public  void  keyDown(){
        System.out.println(name + "666666666666666");
    }
}
package test;
//子类
public class Mouse implements  USB{
private  String name;

    public Mouse() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Mouse(String name) {
        this.name = name;
    }

    @Override
    public void connect() {
        System.out.println(name + "成功接入");

    }

    @Override
    public void unconnect() {
        System.out.println(name + "成功弹出");

    }
    public  void  click(){
        System.out.println(name + "双击点亮");
    }
}
package test;

public interface USB {
    void connect();
    void unconnect();
}

```



```JAVA
package test;

public class Test {
    public static void main(String[] args) {
        Computer c = new Computer();
        USB U = new Mouse("蜘蛛蟹");
        c.installUSB( U );
        USB K = new KeyBoard( "大闸蟹" );
        c.installUSB( K );
    }
}
```
