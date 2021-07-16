# 配置组件
> 统一配置服务, 由于很多框架类的项目都需要用到配置服务。每个框架的配置选用的文件格式，配置内容都大不相同。所以为了解决这个问题写了一个配置组件。

这个组件使用Groovy编写, 用到了Groovy灵活动态的特性编写的DSL进行配置。如果你要使用这个组件你只需要很简单的定义一个接口：

```java
@Resource(name = "myconfig.lckit")
public interface MyConfig extends Setting {
  // .....
}
```
1. 定义自己的配置文件只能通过接口去实现, 接口必须继承**Setting**
2. **@Resource**注解的
