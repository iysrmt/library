# 图书管理系统

[TOC]

## 项目简介

图书管理系统 毕业设计

## 项目使用效果图

### 登录页

![image-20230530193328910](https://raw.githubusercontent.com/iysrmt/library/main/assets/image-20230530193328910.png)

### 超级管理员主页

![image-20230530193348281](https://raw.githubusercontent.com/iysrmt/library/main/assets/image-20230530193348281.png)

### 管理员主页

![image-20230530193452641](https://raw.githubusercontent.com/iysrmt/library/main/assets/image-20230530193452641.png)

### 普通用户主页

![image-20230530193845440](https://raw.githubusercontent.com/iysrmt/library/main/assets/image-20230530193845440.png)

## 安装说明

### 环境

- MySQL 8
- JDK 21
- Maven

*需要配置好环境变量.*

### 使用

1. 登录 MySQL, 创建数据库, 导入 SQL 脚本:

   ```sh
   # 控制台中操作 登录
   mysql -uroot -p
   
   # MySQL 中操作, 注意: 会删除字为 library 的数据库
   source /path/library.sql;
   ```

2. 配置文件 `src/main/resources/application.yml`

   ```yaml
   spring:
     datasource:
       driver-class-name: com.mysql.cj.jdbc.Driver
       url: jdbc:mysql://localhost:3306/library
       username: 数据库用户
       password: 密码
   ```

3. 打包项目

   ```sh
   mvn clean package
   ```

4. 运行项目

   ```sh
   java -jar /path/target/library-0.0.1.jar
   ```
