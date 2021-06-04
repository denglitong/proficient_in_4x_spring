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

## Spring 快速入门

1. 领域对象 domain 从严格意义上将属于业务层，但由于领域对象可能同时被持久层和  
   展现层共享，所以一般将其单独划分到一个包中
2. 随着项目规模的增大，分层思想规划下的包结构会有不足，需要在业务模块包下进一步  
   按分层划分子包；对由若干独立的子系统组成的大型应用，在业务分层包的前面一般  
   还需要加上子系统的前缀。包的规划对于大型应用非常重要，它直接关系到应用部署和  
   分发的便利性。

## Spring Boot 快速入门

Spring 配置的复杂性多年来一直为人所诟病，需要编写复杂的 XML 配置文件。  
Spring Boot 的简易配置大大提升了开发体验，其背后是借助强大的 Groovy 动态语言，  
实现可插拔的 AST 转换器及内置的依赖解决方案引擎等。在其核心的编译模型中，Spring Boot  
使用 Groovy 来构建工程文件，所以它可以轻松地利用导入模板及方法模板对类所生成的字节码  
进行改造，从而让开发者仅用很简洁的代码就可以完成很复杂的操作。

### Spring Boot 特点

1. 内置 Tomcat 和 Jetty 容器，不需要部署 WAR 文件到 Web 容器即可独立运行应用
2. 提供许多基于 Maven 的 pom 配置模板来简化工场配置
3. 提供实现自动化配置的基础设施
4. 提供可直接应用于生产环境的功能，如性能指标、应用信息和应用健康检查
5. 开箱即用，没有代码生成，也无需配置文件，支持修改默认值来满足定制化需求

### Spring Boot 启动器

Spring Boot 是由一系列启动器组成的，这些启动器构成了一个强大的、灵活的开发助手。  
Spring Boot 实际上是一些类库的集合，它能够被任意项目的构建系统所引用，也可手工  
在项目类库中引入 spring-boot-x*.jar 类库来使用 Spring Boot，但是强烈建议  
使用构建系统 Maven/Gradle 来使用 Spring Boot。

## IoC 容器

Inverse of Control，控制反转。  
某一接口具体实现类的选择控制权从调用类中移除，转交给第三方决定，即由 Spring 容器  
借助由 Bean 配置来进行控制。  
Ioc 不够开门见山，说 DI（Dependency Injection，依赖注入）就很直观明了。  
让调用类对某一接口实现类的依赖关系由第三方（容器或协作类）注入，以移除调用类  
对某一接口实现类的依赖。

### IoC 的类型

1. 构造函数注入  
   通过调用类的构造函数，将接口实现类通过构造函数变量传入：

        public class MoAttack {   
            private GeLi geli; 
            
            // 注入革离的具体饰演者
            public MoAttack(Geli geli) { 
                this.geli = geli; 
            } 
        }

2. 属性注入  
   属性注入可以有选择地通过 setter 方法完成调用类所需依赖的注入，更加灵活方便：

        public class MoAttack {
            private Geli geli;
            
            // 属性注入方法
            public void setGeli(Geli geli) {
                this.geli = geli;
            }
        }

3. 接口注入  
   将调用类所有依赖注入的方法抽取到一个接口中，调用类通过实现该接口提供相应的注入方法。  
   接口注入需额外声明一个接口，增加了类的数目，而且效果和属性注入并无本质区别，不推荐。

### 通过容器完成依赖关系的注入

Spring 通过配置文件或注解描述类和类之间的依赖关系，自动完成类的初始化和依赖注入。  
Spring 的自动依赖注入建立在 Java 的反射基础之上。

Java 允许用户借助与 Class 相关的元信息对象间接调用 Class 对象的功能。

#### 类装载器 ClassLoader

1. 类转载器器的工作机制

   ClassLoader 就是寻找类的字节码文件并构造出类在 JVM 内部表示对象的组件，有以下步骤：  
   1）装载：查找和导入 Class 文件  
   2）链接：执行校验、准备和解析步骤，其中解析步骤是可选的

    - 2.1）校验：检查载入 Class 文件数据的正确性
    - 2.2）准备：给类的静态变量分配存储空间
    - 2.3）解析：将符号引用转换成直接引用

   3）初始化：对类的静态变量、静态代码块执行初始化工作

   类装载由 ClassLoader 及其子类负责，JVM 在运行时会产生 3 个 ClassLoader:
    - 根装载器  
      负责装载 JRE 的核心类库，如 JRE 目标下的 rt.jar、charsets.jar 等  
      根装载器不是 ClassLoader 的子类，使用 C++ 编写，在 Java 中看不到它
    - ExtClassLoader  
      扩展类装载器，负责装载 JRE 扩展目录 ext 中的 JAR 包
    - AppClassLoader  
      应用类装载器，负责装载 Classpath 路径下的类包

   装载器直接的父子层级关系：根转载器 > ExtClassLoader > AppClassLoader。  
   默认情况下，使用 AppClassLoader 装载应用程序的类。

2. JVM 转载类的"全盘负责委托机制"

- 全盘负责  
  是指当一个 ClassLoader 装载一个类时，除非显示使用另一个 ClassLoader，  
  该类所依赖及引用的类也由这个 ClassLoader 载入
- 委托机制  
  先委托装载器的父装载器寻找目标类，只有在找不到的情况下才从自己的类路径中查找并装载，  
  这主要是为了安全考虑。

3. java.lang.Class 对象

每个类在 JVM 中都拥有一个对应的 java.lang.Class 对象，它提供了类结构信息的描述。  
数组、枚举、注解及基本 Java 类型（如 int, double, void 等），都拥有对应的 Class 对象。  
Class 没有 public 的构造方法，Class 对象是由 JVM 通过调用类装载器中的 defineClass()  
方法自动构造的。

4. Java 的反射机制

Class 对象描述类语义结构，可从 Class 对象中获取构造函数、成员变量、方法类等元素的反射对象，  
并以编程的方式通过这些反射对象对目标类进行操作。

#### 资源加载

1. 资源抽象接口  
   为更好满足对底层各种资源的访问能力，Spring 设计了一个 Resource 接口，该接口拥有  
   对应不同资源类型的实现类：
    - WritableResource：可写资源接口，有 2 个实现类 FileSystemResource 和 PathResource
    - ByteArrayResource：二进制数组表示的资源，可在内存中通过程序构造出来
    - ClassPathResource：类路径下的资源，资源以相对于类路径（resources 目录）的方式表示
    - FileSystemResource：文件系统资源，资源以文件系统路径的方式表示
    - InputStreamResource：以输入流返回表示的资源
    - ServletContextResource：以相对于 Web 应用根目录的路径加载资源，为访问 Web 容器上下文的资源而设计
    - UrlResource：访问任何可通过 URL 表示的资源，如文件系统的资源、HTTP 资源、FTP 资源等
    - PathResource：访问任何可通过 URL、Path、系统文件路径表示的资源，如文件系统的资源、HTTP 资源、FTP 资源等

   Spring 的 Resource 接口及其实现比 JDK 访问资源的 API 更好用、更强大，可在脱离 Spring 框架的情况下使用。

2. 资源加载  
   访问不同类型的资源必须使用相应的 Resource 实现类，为了简化，Spring 提供了一个强大的加载资源的机制，  
   在不显式使用 Resource 实现类的情况下，仅通过资源地址的特殊表示就可访问相应的资源。
    - 资源地址表达式  
      classpath:/com/denglitong/beanfactory/beans-*.xml 从类路径加载资源，在第一个匹配的类路径下查找  
      classpath*:/com/denglitong/beanfactory/beans-*.xml 从类路径加载资源，在多个匹配的类路径下查找  
      file:/user/local/**/conf/nginx.conf 使用 UrlResource 从文件系统目录中装载资源  
      http://www.denglitong.com/resource/beans.xml 使用 UrlResource 从 Web 服务器中装载资源  
      ftp://www.denglitong.com/resource/beans.xml 使用 UrlResource 从 FTP 服务器中装载资源  
      没有前缀 com/denglitong/beanfactory/beans.xml 根据 ApplicationContext 的具体实现类采用对应类型的 Resource
    - Ant 风格的资源资质匹配符  
      \? 匹配文件名的一个字符  
      \* 匹配文件名中的任意字符  
      \*\* 匹配多次路径

3. 资源加载器  
   Spring 定义了一套资源加载的接口，并提供了实现类：
    - ResourceLoader  
      该接口仅有一个 getResource(String location): Resource 方法，且 location 参数仅支持  
      带类型前缀的表达式，不支持 Ant 风格的资源路径表达式。
    - ResourcePatternResolver  
      该接口扩展了 ResourceLoader 接口，方法 getResources(String locationPattern): Resource[]  
      支持带资源前缀及 Ant 风格的资源路径表达式。
    - PathMatchingResourcePatternResolver 是 Spring 提供的标准实现类

#### BeanFactory 和 ApplicationContext

Spring 通过一个配置文件描述 Bean 与 Bean 之间的依赖关系，利用 Java 的反射功能实例化 Bean 并  
建立 Bean 之间的依赖关系。Spring 的 IoC 容器在完成这些底层工作的基础上，还提供了 Bean 实例缓存、  
生命周期管理、Bean 实例代理、事件发布、资源装载等高级功能。

##### BeanFactory

BeanFactory 是 Spring 框架最核心的接口，它提供了高级 IoC 的配置机制。BeanFactory 使管理不同类型  
的 Java 对象成为可能，应用上下文 ApplicationContext 建立在 BeanFactory 的基础之上，提供了更多  
面向应用的功能，如框架事件体系和国际化支持。

BeanFactory 是 Spring 框架的基础设施，面向 Spring 本身；ApplicationContext 面向使用 Spring  
框架的开发者，几乎所有的应用场合都可以直接使用 ApplicationContext 而非底层的 BeanFactory。

    程序开发思想的不断进步使得软件抽象层面越来越高。Spring 框架是生成类对象的工厂。

##### ApplicationContext

ApplicationContext 的主要实现类是 ClassPathXmlApplicationContext 和 FileSystemApplicationContext，  
前者默认从类路径加载配置文件，后者默认从文件系统装在配置文件。

1. ApplicationContext 类体系结构

ApplicationContext 继承了 HierarchicalBeanFactory 和 ListableBeanFactory 接口，还通过其他接口  
扩展了 BeanFactory 的功能：

- ApplicationEventListener: 让容器拥有发布应用上下文事件的功能，包括容器启动事件、关闭事件等。  
  实现了 ApplicationListener 事件监听接口的 Bean 可以接收到容器事件作出响应处理。  
  在 ApplicationContext 抽象实现类 AbstractApplicationContext 中存在一个 ApplicationEventMulticaster，  
  它负责保存所有的监听器，以便在容器产生上下文事件时通知这些事件监听者。
- ResourcePatternResolver: 实现了类似 PathMatchingResourcePatternResolver 的功能
- LifeCycle: 该接口提供 start/stop 2 个方法，主要用于控制异步处理过程。具体使用是该接口同时被  
  ApplicationContext 实现及具体 Bean 实现，ApplicationContext 会将 start/stop 消息传递给  
  容器中所有实现了该接口的 Bean，已达到管理和控制JMX、任务调度等目的。

##### 父子容器

子容器可访问父容器中的 Bean，但父容器不能访问子容器中的 Bean。  
在容器内，Bean 的 id 必须是唯一的，但子容器可拥有一个和父容器 id 相同的 Bean。

##### Bean 的生命周期

Bean 的完整生命周期从 Spring 容器实例化 Bean 开始，直到最终销毁 Bean。其中涉及的方法调用大致有 4 类：

- Bean 自身的方法：如调用 Bean 构造函数实例化 Bean、调用 Setter 设置 Bean属性及通过<bean>的  
  init-method 和 destroy-method 所指定的方法。
- Bean 级生命周期接口方法：如 BeanNameAware、BeanFactoryAware、InitializingBean 和  
  DisposableBean，这些接口方法由 Bean 类直接实现。
- 容器级生命周期接口方法：如 InstantiationAwareBeanPostProcessor 和 BeanPostProcessor 这 2 个接口，  
  一般称为"后处理器"，实现类以容器附加装置的形式注册到 Spring 容器中，并通过接口反射为 Spring 容器所扫描识别。  
  当 Spring 容器创建任何 Bean 的时候，这些后处理器都会 invoke。
- 工厂后处理器接口方法：包括 AspectJWeavingEnabler、CustomAutowireConfigurer、  
  ConfigurationClassPostProcessor 等方法，工厂方法也是容器级的，仅在应用上下文初始化调用一次，  
  其目的是完成一些配置文件的加工处理工作。

        Bean 级生命周期接口和容器生命周期接口是个性和共性辩证统一思想的体现。

ApplicationContext 和 BeanFactory 另一个最大的不同之处在于：前者会利用 Java 反射机制自动识别出  
配置文件中定义的 BeanPostProcessor、InstantiationAwareBeanPostProcessor 和 BeanFactoryPostProcessor，  
并自动将它们注册到应用上下文中；而后者需要在代码中通过手工调用 addBeanPostProcessor()方法进行注册。  
这就是为什么在应用开发时普遍使用 ApplicationContext 而很少使用 BeanFactory 的原因之一。

Spring 为 Bean 提供了细致周全的生命周期过程，通过实现特定的接口或通过 <bean> (beans.xml 文件)属性设置，  
都可以对 Bean 的生命周期过程施加影响。为了让 Bean 绑定 Spring 框架，推荐使用配置方式（<bean>）而非接口方式。

## 在 IoC容器中装配 Bean

要使应用程序中的 Spring 容器成功启动，需同时具备以下三方面的条件：

- Spring 框架的类包都已经放到应用程序的类路径下
- Bean 的类都已经放到应用程序的类路径下
- 应用程序为 Spring 提供了完备的 Bean 配置信息

Bean 配置信息是 Bean 的元数据信息，由 4 个方面组成：

- Bean 的实现类
- Bean 的属性信息
- Bean 的依赖关系，Spring 根据依赖关系配置完成 Bean 之间的装配
- Bean 的行为配置，如生命周期范围及生命周期各过程的回调函数等

Bean 元数据信息在 Spring 容器中的内部对应实体是一个个 BeanDefinition（形成 Bean 注册表）,  
Spring 实现了 Bean 元数据信息内部表示和外部定义的解耦。

Spring 支持多种形式的 Bean 配置方式：

- Spring 1.0 仅支持基于 XML 的配置（<bean>）
- Spring 2.0 新增基于注解配置（@Autowire）的支持
- Spring 3.0 新增基于 Java 类配置（@Configuration）的支持
- Spring 4.0 新增基于 Groovy动态语言配置（groovy beans）的支持

不同的 Bean 配置形式本质上是基本相同的，基于 XML 的配置方式是最基础、最传统的。

### 循环依赖问题

将<bean>中的构造函数注入改为属性注入。

### Bean 作用域（scope）

- singleton  
  在 Spring IoC 容器中仅存在一个 Bean 实例，Bean 以单实例的方式存在。  
  无状态或状态不可变的类适合使用单例模式。  
  Spring 的 DAO 类都采用了单例模式，这是因为 Spring 利用 AOP 和 LocalThread 功能，  
  对非线程安全的变量/状态进行了特殊处理，使得这些非线程安全的类编程了线程安全的类。  
  也因为 AOP+LocalThread，Spring 中的大部分 Bean 都能以单实例的方式运行，  
  所以 scope 的默认值为 singleton.  
  默认情况下，Spring 的 AppilcationContext 容器在启动时，  
  自动实例化所有 singleton 的 Bean并缓存在容器中。
- prototype  
  每次从容器中调用 Bean 时，都返回一个新的实例，即相当于每次 getBean() 都执行 new XxBean()。  
  Spring 容器将 prototype 的 Bean 交给调用者后，就不再管理它的生命周期。
- request  
  每次 HTTP 请求都会创建一个新的 Bean，该作用域仅适用于 WebApplicationContext 环境
- session  
  同一个 HTTP Session 共享一个 Bean，不同的 HTTP Session 使用不同的 Bean, 该作用域仅适用于 WebApplicationContext 环境
- globalSession  
  顾名思义，全局 Session 共享一个 Bean，该作用域仅适用于 WebApplicationContext 环境，一般用于 Portlet 应用

### 自动装配 Bean（使用@Autowire自动注入）

Spring 可通过 @Autowire 注解自动实现 Bean 的依赖注入。

- @Autowire 可使用在属性、方法、方法参数上，默认按 byType 匹配，可结合 @Qualifier 指定 beanName
- 对集合类进行 @Autowire 标注，那么 Spring 会将容器中匹配的所有 Bean 都自动注入进来
- @Lazy 延迟依赖注入，延迟到调用次属性的时候才会注入属性值，需要在属性及目标 Bean 上同时标注
- @Resource 注解要求提供一个 Bean 名称的属性，缺省值为标注处的变量名或方法名；即 @Autowired 默认 byType 注入，  
  @Resource 按 byName 注入。不管是 @Resource 还是 @Inject，功能都没有 @Autowired 丰富。

一般采用 XML 配置 DataSource、SessionFactory 等资源 Bean.  
在 XML 中利用 aop, context 命名空间进行相关主题的配置。  
其他在项目中开发的 Bean 都通过基于注解配置的方式进行配置；很少采用基于 Java 类的配置方式。