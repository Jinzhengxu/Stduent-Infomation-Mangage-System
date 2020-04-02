

* 体系框架
* 实现思路
* 数据库设计
* 界面设计
* 设计模式
* 源代码文件

### 体系框架

<img src="https://s2.ax1x.com/2019/02/19/kcb3uD.gif" alt="tea" width="60%" />
<img src="https://s2.ax1x.com/2019/02/19/kcbYEd.gif" alt="stu" width="60%" />

登录系统实现学生和教师的同一客户端登录，通过访问数据库中不同的数据表区分，实现增查改删学籍信息，增查改删成绩信息，修改个人登录密码等功能。

### 实现思路

<img src="https://s2.ax1x.com/2019/02/19/kcbaCt.gif" alt="score" width="60%" />
<img src="https://s2.ax1x.com/2019/02/19/kcbtUA.gif" alt="score" width="60%" />
<img src="https://s2.ax1x.com/2019/02/19/kcbN4I.gif" alt="score" width="60%" />
通过`JDBCHelper`实现所有通过数据库的功能，这样将数据库连接与界面设计隔离开。有利于代码的复用。

### 数据库设计

<img src="https://s2.ax1x.com/2019/02/19/kcbGHH.gif" alt="数据库设计" width="60%" />

数据库内有两张表，分别为教师表和学生表，学生表中储存学籍、成绩等个人信息，学生初始密码为姓名，可以再个人中心中实现自我更改

### 界面设计

通过`new`新类来弹出其他窗口。

### 设计模式

ＤＡＯ设计模式