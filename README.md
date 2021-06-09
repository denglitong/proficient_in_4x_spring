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

## Spring 容器高级主题

Spring 容器就像一台构造精妙的机器，我们通过配置文件向机器传达控制信息，机器就能按照设定的模式工作。

### SpringContext 的内部工作机制

    // 1.初始化 BeanFactory 
    //   Spring 将配置文件装载（ResourceLoader）入容器的 Bean 定义注册表（BeanDefinitionRegistry），
    //   此时 Bean 还未初始化  
    ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();

    // 2.调用工厂后处理器
    //   根据反射机制从 BeanDefinitionRegistry 中找出所有实现了 BeanFactoryPostProcessor 接口的 Bean  
    //   并调用其 postProcessBeanFactory() 接口方法
    invokeBeanFactoryPostProcessors();
    
    // 3.注册 Bean 后处理器
    //   根据反射机制从 BeanDifinitionRegistry 中找出所有实现了 BeanPostProcessor 接口的 Bean，  
    //   并将它们注册到容器 Bean 后处理器的注册表中
    // registerBeanPostProcessors();

    // 4.初始化消息源（初始化容器的国际化消息资源）
    initMessageSource();

    // 5.初始化应用上下文事件广播器
    initApplicationEventMulticaster();

    // 6.初始化其他特殊的 Bean  
    //   这是一个钩子方法，由具体子类实现执行一些特殊的操作
    onRefresh();

    // 7.注册事件监听器
    registerListerners();

    // 8.初始化所有单实例的 Bean（并放入缓存池中），使用懒加载模式的 Bean 除外
    finishBeanFactoryInitiliaztion(beanFactory);

    // 9.发布上下文刷新事件
    //   事件广播器负责将这些事件广播到每个注册的事件监听器中
    finishRefresh();

#### Spring 容器的设计优点之处：

1. 接口层描述了容器的重要组件及组件间的协作关系
2. 继承体系逐步实现组件的各项功能

接口层勾勒出了 Spring框架的高层功能、框架，同时使用接口层保证了框架的扩展性，在实现上  
使用继承体系，逐步扩展、分步骤实现框架功能，保证了代码不会堆积在某些类上，完美地分解了  
框架的复杂度。

#### Spring 组件按其所承担的橘色可划分为 2 类

1. 物料组件  
   Resource、BeanDefinition、PropertyEditor 及最终的 Bean 等，它们是加工流程中  
   被加工、被消费的组件。
2. 设备组件  
   ResourceLoader、BeanDefinitionReader、BeanFactoryPostProcessor、  
   InstantiationStrategy 及 BeanWrapper 等。它们就像流水线上不同设备的加工设备，  
   对物料组件进行加工处理。

### 使用外部属性文件

在进行数据源或邮件服务器等资源的配置时，用户可直接在 Spring 配置文件中配置用户名/密码、链接地址等信息。  
但一种更好的做法是将这些配置信息独立到一个外部属性文件中，并在 Spring 配置文件中通过形如 ${user}、  
${password} 的占位符引用属性文件中的属性项。这种配置方式有 2 个明显的好处：

- 减少维护的工作量  
  在资源的配置信息被多个应用共享时就体现出来了
- 使部署更简单  
  Spring 配置文件主要描述应用工程中的 Bean，这些配置在开发完成后就基本确定下来了。  
  但数据源、邮件服务器等资源配置信息却需要在部署时根据现场确定，如果通过一个独立的属性文件  
  放这些配置信息，则部署人员只需调整这个属性文件即可，根本不需要关注大量的 Spring 配置文件。  
  这不仅给部署和维护带来了方便，也降低了出错的概率。

Spring 提供了一个 PropertyPlaceholderConfigurer，它能使 Bean 在配置时引用外部属性文件，  
是一个 Bean 工厂后处理器（BeanFactoryPostProcessor）。

### 使用解密的属性文件

对于一些安全要求特别高的应用系统来说，要求对应用程序配置文件的某些属性进行加密，让 Spring 容器  
在读取属性文件后，在内存中对属性进行解密，然后再将解密后的属性值赋给目标对象。

PropertyPlaceholderConfigurer 继承自 PropertyResourceConfigurer 类，其中的 protected  
方法 convertProperties/convertProperty/convertPropertyValue 用于在属性使用之前对属性列表  
中的属性值进行转换，可扩展 PropertyPlaceholderConfigurer 覆写这几个方法，实现配置文件属性加密。

### 引用 Bean 的属性值

将应用系统的配置信息放在配置文件中并非总是最适合的，如果应用系统以集群方式部署、或希望在运行期动态调整  
应用系统中的某些配置，此时更适合的方式是放到数据库中，可有效增强应用系统的可维护性。

自 Spring 3.0 起，可通过 #{beanName.beanProperty} 方式引用另一个 Bean 的属性值。  
在基于注解和基于 Java 类配置的 Bean 中，可通过 @Value("#{beanName.propertyName})   
的注解形式引用 Bean 的属性值。

### 国际化信息

有国际化要求的应用系统，需要为每种语言提供一套相应的资源文件，并以规范化的方式保存在特定的目录中，  
由系统自动根据客户端语言选择适合的资源文件。国际化资源文件的规范命名为：

    <资源名>_<语言代码>_<国家/地区代码>.properties  
    其中 <语言代码>_<国家/地区代码> 是可选的，如果没有就应用默认的 <资源名>.properties 文件  

"国际化信息"也称"本地化信息"，一般需要 2 个信息才能确定，分别是"语言类型"和"国家/地区类型"。  
Java 通过 java.util.Locale 类表示一个本地化对象，允许通过语言参数和国家/地区参数创建一个  
确定的本地化对象。

- 语言参数  
  使用 ISO 标准语言代码表示，每种语言由 2 位小写字母表示。  
  参见：http://www.loc.gov/standards/iso639-2/php/English_list.php
- 国家/地区参数  
  由标准的 ISO 国家/地区代码表示，每个国家/地区由 2 个大写字母表示。    
  参见：https://www.iso.org/obp/ui/#search

ResourceBundle、MessageSource 用于方便地访问资源文件。  
`ResourceBundleMessageSource`、`ReloadableResourceBundleMessageSource`

ApplicationContext 也实现了 MessageSource 接口，支持容器级的国际化信息支持。  
在 <bean> 中配置 messageResource bean 信息声明 property.beannames 指定  
国际化信息资源文件，即可使用 ApplicationContext.getMessage(key, locale);  
获取对应的国际化信息。

### 容器事件

Java 通过 java.util.EventObject 类和 java.util.EventListener 接口描述事件和监听器，  
某个组件或框架如需事件发布和监听机制，都需要通过扩展它们进行定义。Spring 的 ApplicationContext  
能够发布事件并且运行注册相应的事件监听器，因此它拥有一套完善的事件发布和监听机制。

- 事件：EventObject
- 监听器：EventListener
- 事件源：事件的产生者，每一个 EventObject 都必须拥有一个事件源
- 事件监听器注册表：EventListenerTable，组件或框架必须提供一个地方保存事件监听器，这就是  
  事件监听器注册表
- 事件广播器：它是事件和事件监听器沟通的桥梁，负责把事件通知给事件监听器

事件体系是观察者模式的一种具体实现，它并没有任何神秘之处。

#### Spring 事件类结构

1. 事件类  
   ApplicationEvent, ApplicationContextEvent, RequestHandledEvent.
2. 事件监听器接口  
   EventListener, ApplicationListener, SmartApplicationListener, GenericApplicationListener.

        Spring4.0提供了一个可解析类型 ResolvableType 支持类，提供更加简单易用的泛型操作。

3. 事件广播器    
   ApplicationEventMulticaster, AbstractApplicationEventMulticaster（完成了事件体系的搭建）,  
   SimpleApplicationEventMulticaster. Spring 启动过程中会通过反射机制将任何实现了 ApplicationEventMulticaster  
   的类注册成容器的事件广播器，如果没找到外部配置可用的类，则默认使用 SimpleApplicationEventMulticaster 作为事件广播器。

## Spring AOP基础

### AOP 概述（Aspect Oriented Programming，面向切面编程）

Spring AOP 构建于 IoC 之上，和 IoC "浑然天成"，统一于 Spring 容器之中。Spring AOP 是 AOP 技术在 Spring 中的  
具体体现。

编程语言的终极目标就是能以更自然、更灵活的方式模拟世界。AOP 是软件开发思想发展到一定阶段的产物，是 OOP 的有益补充。  
AOP 是有特定的应用场景的，它只适合那些具有横切逻辑的应用场景，如性能检测、访问控制、事务管理及日志记录。

OOP `纵向继承体系`，AOP `横向抽取机制`。

#### AOP 术语

- 连接点（Joinpoint）  
  `特定点` 是程序执行的某个特定位置，如类开始初始化前/类初始化后、某个方法调用前/调用后/方法抛出异常后等，  
  一个类或一段程序代码拥有一些具有边界性质的`特定点`，这些代码中的`特定点`就被称为 `连接点`。Spring 仅支持  
  方法的连接点，即仅能在方法调用前/调用后/方法抛出异常时及方法调用前后这些程序执行点织入增强。  
  连接点由 2 个信息确定：一是用方法表示的程序执行点；而是用相对位置表示的方位（前/后）。  
  Spring 使用`切点`对执行点进行定位，而方位则在增强类型中定义。

- 切点（Pointcut）  
  连接点是程序中客观存在的实体，如一个拥有 2 个方法的类，这 2 个方法都是连接点。在众多的连接点中，AOP 通过  
  `切点`来定位感兴趣的连接点。Spring 通过 org.springframework.aop.Pointcut 接口描述切点，它使用类和  
  方法作为执行点的查询条件，Spring AOP的规则引擎解析切点所设定的查询条件，找到执行点。

- 增强（Advice）  
  增强是织入目标类连接点上的一段程序代码，还拥有执行点的方位信息。结合切点信息和方位信息，就可以找到特定的连接。  
  Spring 提供的增强接口都是带方位名的，如 BeforeAdvice, AfterReturnningAdvice, ThrowsAdvice 等。

- 目标对象（Target）  
  增强逻辑的织入目标类

- 引介（Introduction）  
  引介是一种特殊的增强，它为类增加一些属性和方法。这样即使一个业务类原本没有实现某个接口，通过 AOP 的引介功能，  
  也可以动态地为该业务类添加接口的实现逻辑。

- 织入（Weaving）  
  织入是将增强添加到目标类的具体连接点上的过程。AOP 就像一台织布机，将目标类、增强或引介天衣无缝地编织到一起。  
  AOP有 3 种织入方式：
    1. 编译器织入：要求使用特殊的 Java 编译器
    2. 类装载期织入：要求使用特殊的类装载器
    3. 动态代理织入：在运行期为目标类添加增强生成子类的方式

  Spring 采用动态代理织入，而 AspectJ 采用编译器织入和类装载期织入。

- 代理（Proxy）  
  一个类被 AOP 织入增强后就产生了一个结果类，它是融合了原类和增强逻辑的代理类。根据不同的代理方式，代理类既可能  
  是和原类有相同接口的类，也可能就是原类的子类，所以可采用与调用原类相同的方式调用代理类。

- 切面（Aspect）  
  切面由切点和增强（/引介）组成，它既包含横切逻辑的定义，也包括连接点的定义。Spring AOP 就是负责实施切面的框架，  
  它将切面所定义的横切逻辑织入切面所指定的连接点中。

  AOP 的工作重心在于如何将增强应用于目标对象的连接点上，这包括：
    1. 如何通过切点和增强定位到连接点上
    2. 如何在增强中编写切面的代码

#### AOP 的实现者

AOP 工具的设计目标是把横切的问题（如性能监视、事务管理）模块化。使用类似 OOP 的方式进行切面的编程工作。  
位于 AOP 工具核心的是连接点模型，它提供了一种机制，可以定位到需要在哪里发生横切。

1. AspectJ AspectJ 是语言级的 AOP 实现，它扩展了 Java 语言，定义了 AOP 语法，能在编译期提供横切代码的织入，有一个专门的  
   编译期来生成遵守 Java 字节码的 Class 文件。

2. AspectWerkz  
   它支持运行期或类装载期的横切织入，拥有一个特殊的类装载期。现在 AspectJ 和 AspectWerkz 已经合并。

3. JBoss AOP

4. Spring AOP  
   Spring AOP 使用纯 Java 实现，它不需要专门的编译过程和特殊的类装载期，它在运行期通过代理方式向目标类织入增强代码。  
   Spring AOP 侧重于提供一种和 Spring IoC 容器整合的 AOP 实现，可以无缝地将 Spring AOP、IoC 和 AspectJ 整合。

### AOP 基础知识

Spring AOP 使用了两种代理机制：一种是基于 JDK 的动态代理，另一种是基于 CGLib 的动态代理。之所以需要两种代理机制，  
很大程度上是因为 JDK 本身只提供接口的代理，而不支持类的代理。

#### JDK 动态代理

自 Java1.3以后，Java 提供了动态代理技术，允许开发者在运行期创建接口的代理实例。  
JDK 动态代理主要涉及 java.lang.reflect 包中的两个类：Proxy 和 InvocationHandler。

使用 JDK 创建代理有一个限制：即它只能为接口创建代理实例。CGLib 采用底层的字节码技术，  
可以为一个类创建子类，在子类中采用方法拦截的技术拦截所有父类方法的调用并顺势织入横切逻辑。  
CGLib 主要涉及 org.springframework.cglib.proxy 包中的 MethodInterceptor 接口、  
MethodProxy、Enhancer 类。

    由于 CGLib 采用动态创建子类的方式生成代理对象，所有不能对目标中的 final或 private 方法进行处理。  

#### Spring AOP

Spring AOP 通过 Pointcut（切点）指定在哪些类的哪些方法上织入横切逻辑，通过 Advice（增强）描述  
横切逻辑和方法的具体织入点（方法前、方法后、方法的两端等）。此外，Spring 通过 Advisor（切面）将  
Pointcut 和 Advice 组装起来，这样有了 Advisor 的信息后 Spring 就可以利用 JDK 或 CGLib 动态  
代理技术采用统一的方式为目标 Bean 创建织入切面的代理对象了。

    JDK 动态代理所创建的代理对象运行性能比 CGLib 差（10 倍），但 CGLib 的代理对象所花费的时间比  
    JDK 动态代理多（8 倍），所以，对于 Singleton 的代理对象或具有实例池的代理适合采用 CGLib   
    动态代理技术，反之则适合采用 JDK 动态代理技术。  

`ProxyFactory`是 Spring 提供的为目标类生成代理的工厂类，主要方法有`setTarget()`设置代理目标、  
`addAdvice()`为代理目标添加增强、`getProxy()`生成代理实例。

#### 前置增强

`MethodBeforeAdvice`

#### 后置增强

`AfterReturningAdvice`

#### 环绕增强

`MethodInterceptor`

#### 异常抛出增强

`ThrowsAdvice`

    标签接口是没有任何方法和属性的接口，它不对实现类有任何语义上的要求，仅仅表明它的实现类属于一个特定的类型。  
    它非常类似于 Web2.0中 TAG 的概念，Java使用它表示某一类对象，主要有 2 个用途：  
    第一，通过标签接口标识同一类型的类，这些类本身可能并不具有相同的方法；  
    第二，通过标签接口使程序或 JVM 采取一些特殊处理，如 java.io.Serializable 接口，它告诉 JVM 对象可被序列化。  

#### 引介增强

`IntroductionInterceptor`（该接口没有任何方法，Spring 为其提供了 DelegatingIntroductionInterceptor 实现类）

引介增强比较特殊，它不是在目标方法周围织入增强，而是为目标类创建新的方法和属性，所以引介增强的连接点是类  
级别的。通过引介增强，可为目标类添加一个接口的实现（即创建某接口的代理）。

    引介增强富有吸引力，因为它能够在横向上定义接口的实现方法，思考问题的角度发生了很大的变化。  

在 Spring4.0 中，CGLib 的类代理不再要求目标类必须有无参构造函数，这是因为 Spring 内联了 objenesis  
类库，其可以在不调用构造函数的情况下实例化类。

### 创建切面

描述连接点是 AOP 编程最主要的工作。增强提供了连接点的方位信息，而切点进一步描述了织入哪些类的哪些方法上。  
Spring 通过 org.springframework.aop.Pointcut 接口描述切点，Pointcut 由 ClassFilter 和  
MethodFilter 构成。Spring 支持注解切点和表达式切点。

#### 静态普通方法名匹配切面

`StaticMethodMatcherPointcutAdvisor`代表一个静态方法匹配切面，通过类过滤和方法名来匹配所定义的切点。

#### 静态正则表达式匹配切面

`RegexpMethodPointcutAdvisor`

#### 动态切面

    动态切面是指必须在运行期根据方法入参的值来判断增强是否需要织入目标类的连接点上。

`DefaultPointcutAdvisor` + `DynamicMethodMatcherPointcut`

DynamicMethodMatcherPointcut 是一个抽象类，它将 isRuntime() 标识为 final 且返回 true，这样其  
子类就一定是一个动态切点。该抽象类默认匹配所有的类和方法，因此需要扩展该类编写符合要求的动态切点。

由于动态切点检查对执行性能影响较大，Spring 在创建代理织入切面时，对目标类中的所有方法先进行静态切点检查，  
后续每次调用时只对静态检查通过的方法再进行动态切点检查，缩小了动态检查的范围，尽量减少对不匹配方法的执行效率。

#### 流程切面

`DefaultPointcutAdvisor` + `ControlFlowPointcut`

流程切点代表由某个方法直接或间接发起调用的其他方法。

#### 复合切点

有时候一个切点难以描述目标连接点的信息，比如需要一个切点来描述流程切点，还需要一个切点来描述目标方法切点。  
此时就需要使用 Spring 提供的 `ComposablePointcut` 把两个切点组合起来。

#### 引介切面

`IntroductionAdvisor`，`DefaultIntroductionAdvisor`, `DeclaredParentsAdvisor`

引介切面是引介增强的封装器，通过引介切面，可以更容易地为现有对象添加任何接口的实现。

### 自动创建代理

在前面所有的例子中，都需要通过 ProxyFactoryBean 创建织入切面的代理，每个需要被代理的 Bean 都需要使用  
一个 ProxyFactoryBean 进行配置，这会很麻烦。Spring 基于 BeanPostProcessor 提供了自动代理机制，  
让容器在实例化 Bean 的时候为匹配的 Bean 自动生成代理。这些代理创建器可分为 3 类：

1. 基于 Bean 配置名规则的自动代理创建器：`BeanNameAutoProxyCreator`
2. 基于 Advisor 匹配机制的自动代理创建器：`DefaultAdvisorAutoProxyCreator`，对容器中所有 Advisor  
   进行扫描，对 Advisor 所匹配的 Bean 在其实例化的时候自动应用上这些切面
3. 基于 Bean 中 AspectJ 注解标签的自动代理创建起：`AnnotationAwareAspectJAutoProxyCreator`，  
   为包含 AspectJ 注解的 Bean 自动创建代理实例。

#### AOP 无法增强疑难问题剖析

AOP 底层实现有 2 种方法：一种基于 JDK 动态代理，另一种基于 CGLib 动态代理。  
前者通过接口实现方法拦截，所以必须确保要拦截的目标方法在接口中有定义；后者通过  
生成代理子类来实现方法拦截，所以必须确保要拦截的目标方法可悲子类访问。

### 小结

AOP 是 OOP 的延伸，它为程序开发提供了一个崭新的思考角度，可以将重复性的横切逻辑抽取到统一的模块中。  
通过 OOP 的纵向抽象和 AOP 的横向抽取，程序可以更好地解决重复性代码问题。

Spring 只能在方法级别上织入增强，Spring 提供了 4 中类型的方法增强，分别是：前置增强、后置增强、环绕增强  
和异常抛出增强。此外还有一种特殊的引介增强，它为目标类织入新的接口实现，所以是类级别的。广义上来说，增强其实  
就是一种最简单的切面，包括横切代码和方法相对位置信息，需要和切点联合去表示一个实用的切面。

在 Spring 中，普通的切点通过目标类名和方法名描述连接点的信息。流程切点比较特殊，通过方法调用栈的运行信息来  
决定连接点。可使用符合切点来对多个切点进行交叉确定一个最终的切点。

切面是增强和切点的联合体，可通过 Spring 提供的 ProxyFactoryBean 方便地将切面织入不同的目标类中。  
Spring 还提供了一些自动创建代理/织入切面的自动代理创建器，省去了为每个目标类配置切面的烦琐操作。  
如 `DefaultAdvisorAutoProxyCreator` 就是功能强大的自动代理创建器，它将容器中所有的 Advisor 自动  
织入目标 Bean 中。
