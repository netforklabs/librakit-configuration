# 配置组件
> 统一配置服务, 由于很多框架类的项目都需要用到配置服务。每个框架的配置选用的文件格式，配置内容都大不相同。所以为了解决这个问题写了一个配置组件。

这个组件使用Groovy编写, 用到了Groovy灵活动态的特性编写的DSL进行配置。如果你要使用这个组件你只需要很简单的定义一个接口：

```java
@Resource(name = "myconfig.lckit")
public interface MyConfig extends Setting {
  // .....
}
```
- 定义自己的配置文件只能通过接口去实现, 接口必须继承**Setting**
- **@Resource**注解的**name**属性表示去解析哪个配置文件(名称和后缀自定义)

像上面那样一个简单的工作就完成了, 如何定义自己的配置呢？这里我们以一个springboot配置Mysql为样例展示如何自定义配置。

```yml
spring:
 datasource:
  driver-class-name: com.mysql.cj.jdbc.Driver
  url: jdbc:mysql://连接IP地址:端口/数据库名?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC
  username: 用户名
  password: 密码
  type: com.alibaba.druid.pool.DruidDataSource
  
mybatis:
 mapper-locations: classpath:com/../../mapper/*.xml
```
要实现上面的内容我们需要先编写一个实体类(注意: 实体必须实现GET/SET函数, 下面我就不写了):
```java
class Spring {

  @NewInstance
  DataSource datasource;
  
  @NewInstance
  Mybatis    mybatis;
}

// 定义数据源实体
class DataSource {
  private String driverClassName;
  private String url;
  private String username;
  private String password;
  private String type;
}

class Mybatis {
  private String mapperLocations;
}

```

创建实体类之后我们在接口中添加两个函数:
```java
@Resource(name = "myconfig.lckit")
public interface MyConfig extends Setting {

  Integer port();
  
  @Closure
  Spring spring();

}
```

配置文件编写: 
```
port 8080

spring {
  datasource.driverClassName        = "com.mysql.cj.jdbc.Driver"
  datasource.url                    = "jdbc:mysql://连接IP地址:端口/数据库名?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC"
  datasource.username               = "用户名"
  datasource.password               = "用户密码"
  datasource.type                   = "com.alibaba.druid.pool.DruidDataSource"
  
  mybatis.mapperLocations           = "classpath:com/../../mapper/*.xml"
}
```

这样就实现了一个简单的配置了。在接口中的 **@Closure** 注解是为了让spring这个函数支持闭包。**@NewInstance**注解是为了能够让DataSource和Mybatis这两个实体对象初始化, 一般默认是不会初始化内部的对象的。
