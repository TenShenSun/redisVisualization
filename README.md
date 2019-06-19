# redisVisualization
一个redis可视化工具

## 一、SpringBoot的配置和相关信息
### 1.1 springboot集成web
pom.xml加入
```xml
<!-- web -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```
### 1.2 配置欢迎页 index.html
config.MVCConfiguration
### 1.3 springboot集成thymeleaf
pom.xml加入
```xml
<!--thymeleaf-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```


### 二、前端

### 三、redis

### 四、项目说明
4.1 项目文件 application.properties
本项目是一个redis的可视化的web程序
前台技术栈：
后台技术栈：
4.1 启动 localhost:8080



### 五、学习资源总结

5.1 注解

[Appendix B. Configuration Metadata](https://docs.spring.io/spring-boot/docs/2.1.5.RELEASE/reference/html/configuration-metadata.html#configuration-metadata-annotation-processor)


### 六、代码规范

- 6.2 命名（采用驼峰命名法，变量和函数名简单易懂，见名知意）
- 6.1 注释（写好文档注释，方法注释）
- 6.2 模块化（按功能分模块，一个方法代码不要太长，太长就封装成不同的函数）
- 6.3 增加新功能时现在自己的新分支上进行更改，然后确定没有问题以后，merge到开发分支