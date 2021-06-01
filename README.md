# 精通 Spring 4.X

## Spring 概述

Spring 是分层的 Java/SE/EE应用一站式的轻量级开源框架，以 IoC（Inverse of Control，控制反转） 和 AOP（Aspect Oriented Programming，切面编程）为内核，提供了展现层
Spring MVC、持久层 Spring JDBC 及业务层事务管理等一站式的企业级应用技术。

Rod Johnson 《Expert One-toOne J2EE Design and Development》 -> interface21 框架 Spring 1.0 以 interface21 框架为基础，于 2004 年发布。
《Expert Ont-toOne J2EE Development without EJB》

Rod Johnson 成立了 SpringSource 公司，开始商业化运作，收购 Spring 周边项目背后的母公司以提供支持， 并找到了商业盈利的模式（SpringSource 企业版服务器）。

应用开发框架、应用服务器、应用服务监控

2009 年，VMware斥资 4.2 亿美元收购 SpringSource。 2012 年，Rod Johnson 离开 SpringSource 和 VMware，"去从事其他一些感兴趣的事情"。 2013 年 12 月，Spring
Framework 4.0 正式版本发布。

EJB（Enterprise Java Beans）的缺点：对所有企业应用采用统一标准，认为它们都需要分布式对象、远程事务， 这早就了 EJB 框架的复杂度、难以测试。  
EJB 的优点：声明事务、透明持久化。  
Spring的优点：方便解耦，简化开发；AOP 编程的支持；声明式事务的支持；方便程序的测试； 方便集成各种优秀框架；降低 Java EE API的使用难度；高质量的 Java 源码；

### Spring 体系结构

整个框架按其所属功能可划分为 5 个主要模块：`IoC`, `AOP`, `数据访问和集成`，`Web 及远程操作`，`测试框架`。

1. IoC（Bean, Context, 表达式语言）

- `IoC` 将类与类之间的依赖从代码中脱离出来，用配置的方式进行依赖关系描述，由 IoC 容器负责依赖来之间的创建、 拼接、管理、获取等工作。BeanFactory 是 IoC 模块的核心接口，Spring 核心模块实现了
  IoC的功能。

- `Context` 模块扩展了 BeanFactory 的功能，添加了 Bean 生命周期管理、框架事件体系、资源加载透明化等 多项功能，还提供企业级服务的支持如邮件服务、任务调度、、远程访问、EJB 集成、JNDI获取等。
  ApplicationContext 是 Context 模块的核心接口，Context 模块构建于 Spring 核心模块之上。

- `表达式语言`用于查询和管理运行期的对象，支持设置/获取对象属性、调用对象方法、操作数组/集合等，此外该模块 还提供了逻辑表达式运算、变量定义等功能，可方便地和 Spring IoC 容器进行交互。表达式语言是统一表达式语言
  （Unified EL）的一个扩展。

2. AOP（Spring AOP, Aspects, Instrument）

AOP 是继 OOP 之后，对编程设计思想影响极大的技术之一。AOP 是进行横切逻辑编程的思想，它开拓了 考虑问题的思虑。Spring 提供了满足 AOP Alliance 规范的实现，还整合了 AspectJ 这种语言级的框架。 Java
5.0引入 java.lang.instrument，允许在 JVM 启动时启用一个代理类，通过该代理类在运行期修改类的 字节码，改变一个类的功能，从而实现 AOP 的功能。

3. 数据访问和集成（JDBC，ORM，OXM(Object XML Mapping)，JMS(Java Message Service)，事务管理）

任何应用程序的核心问题是对数据的访问和操作。数据有多种形式如数据表、XML、消息等，每种数据形式又有不同的 数据访问技术。

- 首先，Spring 站在 DAO 的抽象层面，建立了一套面向 DAO 层的统一的异常体系，还将各种访问数据的检查型异常 转换为非检查型异常，为整合各种持久化框架提供基础。
- 其次，Spring 通过模板话对各种数据访问技术进行了薄层封装，将模式化的代码隐藏起来，使得数据访问得到大幅简化。
- 这样，Spring 就建立起了和数据形式及访问技术无关的统一的 DAO 层，借助 Spring 的 AOP 技术， Spring 提供了声明式事务的能力。

4. Web 及远程操作（MVC，Portlet，Web Service，WebSocket）

该模块建立在 com.denglitong.Application Context 模块之上，提供了 Web 应用的各种工具类，如通过 Listener 或 Servlet 初始化 Spring 容器，将 Spring 容器注册到 Web
容器中。该模块还提供了多项面向 Web的功能，如透明化文件上传， Velocity/FreeMarker 模板引起等此外，Spring 可以整合 Structs/WebWork 等 MVC 框架。

5. Web 及远程访问

Spring 自己提供了一个完整的类似于 Structs 的 MVC 框架，称为 Spring MVC。实现 MVC 其实是一项简单的 工作，当然，Spring 在简化 Portlet 的开发商业做了很多工作，开发者可从中受益。

6. WebSocket

WebSocket 提供了一个在 Web 应用汇总高校、双向的通信，需要考虑到客户端和服务器之间的高频和低时延消息交换。 一般的应用场景有在线交易、游戏、写作、数据可视化等。

### Spring 4.0 新特性

1. 全面支持 Java 8.0

- Java 8.0 的 Lambda 表达式
- Java 8.0 的时间和日期 API Java 开发者对 java.util.Data 笨拙的设计忍耐依旧，Java 8.0引入了一系列有用的新类解决了那些久被诟病 的问题，如 java.time 下面的 LocalDate,
  LocalTime, LocalDateTime 等。

      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate)

- 重复注解支持
- 空指针终结者：Optional<T>

      In RequestMapping params: (..., Optional<String> email)

- 核心容器的增强  
  支持泛型依赖注入，

      public abstract class BaseService<M extends Seriable> {
        @Autowired
        protected BaseDao<M> dao;
      }
      Map 依赖注入：
      @Autowired
      private Map<String, BaseService> map;
      其中，key 是 Bean 的名字，value 是所有实现了 BaseService 的 Bean

  提供 @Conditional 注解来实现 Bean 的条件过滤，   
  提供 @Lazy 注解解决 Bean 延时依赖注入，  
  支持 Bean 被注入 List 或者 Array 时可通过 @Order 注解或基于 Ordered 接口进行排序，

      @Autowired
      private List<BaseService> list;
      这样会注入所有实现了 BaseService 的 Bean，但顺序是不确定的，
      可通过 @Order 注解或 Ordered 接口来实现排序，
      @Order(value = 1)
      @Service
      public MyService implements BaseService {}

- 支持用 Groovy 定义 Bean
- Web 的增强  
  Spring MVC 基于 Servlet 3.0开发，  
  引入 @RestController,  
  同时添加了一个 AsyncRestTemplate，支持 REST客户端的异步无阻塞请求

- 支持 WebSocket
- 测试的增强  
  提供 @Sql 注解  
