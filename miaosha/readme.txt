[慕课网：springboot项目练习-电商秒杀](https://www.imooc.com/learn/1079)

1. 课程介绍
2. 应用SpringBoot完成基础项目搭建
    2.1 使用IDEA创建MAVEN项目
        1. New -> Project, 选择 Maven, Create from archetype，选择: maven-archetype-quickstart.
        2. 创建项目名为 miaosha .
        3. 等待 IDEA 构建项目。
        4. 标注 java 源码目录，resources 资源文件目录，test 测试目录。
        5. 运行启动类，class App 中的 main 方法，查看控制台是否输出正确。

    2.2 引入SpringBoot依赖包实现简单的Web
        1. 从零开始集成 SpringBoot，在主 pom.xml 文件中，引入 <parent> 依赖，表示主 pom 的父级 pom ，完成 SpringBoot 框架集成。
              <parent>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-parent</artifactId>
                <version>2.3.2.RELEASE</version>
                <relativePath/> <!-- lookup parent from repository -->
              </parent>
                <!--从父pom文件中，将该依赖引入-->
               <dependency>
                 <groupId>org.springframework.boot</groupId>
                 <artifactId>spring-boot-starter-web</artifactId>
                 <version>2.2.1.RELEASE</version>
               </dependency>
        2. @EnableAutoConfiguration    // 将该启动类，开启整个工程基于 SpringBoot 自动化注解配置
                需要在 main 方法中使用 SpringApplication.run(App.class, args); 语句使项目按照 spring 框架模式运行启动。内嵌 tomcat 容器，不再需要其他外部容器。
        3. @RequestMapping("/") // 在主启动类上使用该注解，并配置路径
                另外在 main 方法上同样使用 @RequestMapping("...")后，可以实现 springMVC 框架模式的项目，保证访问正确。

    2.3 Mybatis接入SpringBoot项目
        0. SpringBoot 配置化优势
                配置化操作：详见1.
        1. # SpringBoot 默认在 resources 目录下寻找 application.properties 配置文件，加载其中的配置，就可以改变 SpringBoot 中默认配置。
           server.port=8090，使用该配置文件可以配置 MySQL 数据库相关参数。（& -> &amp;）
        2. 集成 mysql 组件。 修改 pom.xml ,添加支持 mysql 操作的依赖。
                <dependency>
                  <groupId>mysql</groupId>
                  <artifactId>mysql-connector-java</artifactId>
                  <version>5.1.41</version>
                </dependency>
                <!--连接池管理-->
                <dependency>
                  <groupId>com.alibaba</groupId>
                  <artifactId>druid</artifactId>
                  <version>1.1.3</version>
                </dependency>
                <!--springboot 对 mybatis 的支持-->
                <dependency>
                  <groupId>org.mybatis.spring.boot</groupId>
                  <artifactId>mybatis-spring-boot-starter</artifactId>
                  <version>1.3.1</version>
                </dependency>
        3. application.properties 中添加 mybatis 的配置，用来启动带 mybatis 访问数据库功能的 SpringBoot 工程。
            mybatis.mapper-locations=classpath:mapping/*.xml
            在 resources 下创建 mapping 目录
        4. 使用 mybatis 自动生成工具，用来生成 mybatis 数据库对应的映射。 引入 mybatis 生成插件。 修改 pom.xml
                    <plugin>
                      <groupId>org.mybatis.generator</groupId>
                      <artifactId>mybatis-generator-maven-plugin</artifactId>
                      <version>1.3.5</version>
                      <dependencies>
                        <!--如果提示找不到该jar,则将其放入dependencies 内 -->
                        <dependency>
                          <groupId>org.mybatis.generator</groupId>
                          <artifactId>mybatis-generator-core</artifactId>
                          <version>1.4.0</version>
                          <type>pom</type>
                        </dependency>
                        <!--解析为 mysql-->
                        <dependency>
                          <groupId>mysql</groupId>
                          <artifactId>mysql-connector-java</artifactId>
                          <version>5.1.41</version>
                        </dependency>
                      </dependencies>
                      <executions>
                        <execution>
                          <id>mybatis generator</id><!--随便自定义-->
                          <phase>package</phase><!--在 package阶段-->
                          <goals><goal>generate</goal></goals><!--目标-->
                        </execution>
                      </executions>
                      <configuration>
                        <!--实际工作中会反复生成-->
                        <!--允许移动生成的文件-->
                        <verbose>true</verbose><!--默认false-->
                        <!--允许自动覆盖文件--><!--一般不能设置true,会覆盖其他人的xml，以增量方式，首次生成，后续只修改-->
                        <overwrite>true</overwrite>
                        <!--重要： mybatis-generator 配置文件的路径-->
                        <configurationFile>src/main/resources/mybatis-generator.xml</configurationFile>
                      </configuration>
                    </plugin>

    2.4 Mybatis自动生成器的使用方式
        1. mybatis-core版本要和mybatis-maven-plugin版本要一致
        2. mybatis-generator.xml 中的配置
            1). 准备数据库，创建名称为 miaosha 的数据库，创建表：user_info、user_passowrd.(可以直接使用项目中 miaosha.sql 文件创建整体数据库)
                    企业级应用用户的密码保存是需要分开存储的，一般是另外一个应用管理密码保存。
                    密码保存必须用加密后的密文方式保存，严禁使用明文保存。
            2). 需要注意的是在创建数据库表时，每个字段类型的设置。是否 null、默认值、长度、注释、主外键。
            3). mybatis-generator.xml 中配置 <context> 中
                    <jdbcConnection >    // 数据库连接信息
                    <javaModelGenerator > // 设置数据库表创建的 DataObject 类存放位置等
                    <sqlMapGenerator >      // 生成映射文件存放位置
                    <javaClientGenerator >  // 生成 DAO 类存放位置
                    <table >            // 配置根据对应表生成类的名称，以及去除自动生成基于 Example 的复杂SQL相关。
        3. 配置 maven Command line : mybatis-generator:generate -e -X
            注意 ： generate 前是 : 而不是 分号 ;
        4. 生成后，需要给 mapper 接口添加注解
        5. application.properties 中添加数据源配置

            # 接入 mybatis 对应的数据源配置
            spring.datasource.name=miaosha
            spring.datasource.url=jdbc:mysql://127.0.0.1:3306/miaosha?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8&useSSL=false
            spring.datasource.username=root
            spring.datasource.password=root

            # 使用 druid 数据源
            spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
            spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver    // mysql 8.0 版本驱动
        6. 将主启动类的注解 @EnableAutoConfiguration 修改为 @SpringBootApplication
            作用是一样的。
            主启动类添加 @MapperScan("com.miaoshaproject.dao")    // 定义 mapper 扫描路径
        7. 编写访问方法，其中使用 UserDOMapper 去查询数据库，并将结果返回给页面显示。测试是否成功。

3. 用户模块开发
    3.1 使用SpringMVC方式开发用户信息
        1. 搭建 MVC 层次组件
            创建 controller 层目录，并编写 UserController,包含一个 getUser(Integer id) 方法，注意添加注解。
            创建 service 层目录，并编写 UserService 接口以及 UserSServiceImpl 实现类，包含 getUserById(Integer id) 方法，其中使用了 UserDOMapper.
        2. 除了 UserDO 表示与数据库对应的数据对象类外， service 层业务领域也应该有与 DO 对象不同的对象 model, 即业务领域对象，用来封装 DO 对象，避免直接将 DO 对象返回给前端。
            创建 service 层领域模型对象: com.miaoshaproject.service.model.UserModer, 其中封装从 DO 到 model 封装方法。
            为 userPasswordDOMapper 添加 selectByUserId(Integer id) 方法，实现通过用户 id 查找对应的用户加密密码信息。
        3. 编写 userController 中的 getUser() 方法，返回领域模型 userModel, 测试是否可以通过前端成功访问。
        4. 创建一个前端模型对象 UserVO, 用来封装可以返回给前端的对象数据信息，不包含前端非必要属性字段。其中包含 convertFromModel(model) 方法，用来将核心领域模型 model 对象封装成可供前端使用的 VO 对象。
        5. 需要注意的是 DO、VO、Model 对象中的属性类型必须一致，字段名同样要一致，才可以使用 BeanUtils.copyProperties(srcObj, tarObj) 方法，否则会丢失不一致属性中的数据。

    3.2 定义通用的返回对象-返回正确信息
        1. 通用的返回对象。方便前端解析数据。
            首先，定义返回对象类型 class com.miaoshaproject.response.CommonReturnType， 包含 String status, Object date 2个属性。
            然后，定义 二重奏创建对象方法， create(Object result)-（该方法中如果没给 status 参数则给出默认值"success"） 调用 create(Object result, String status)，
        2. 返回的数据为 Object 对象。
                若 status = success ,则返回前端需要的 JSON 数据。
                若 status = fail , 则返回通用的错误码格式。
        3. 修改 controller 层中的方法，将返回值修改为 CommonReturnType 类型的对象。
        4. 查看运行效果。

    3.3 定义通用的返回对象-返回错误信息
        1. 定义通用的异常返回对象类。其中包含错误码与描述。
        2. 声明 com.miaoshaproject.error.CommonError 接口，包含
                int getErrCode(); String getErrMsg(); CommonError setErrMsg(String errMsg); 方法.
        3. 定义枚举 Enum EmBusinessError 实现 CommonError 接口中的方法。
                包含2个成员变量属性。 int errCode, String errMsg.
                定义枚举列表，实现接口中的方法。
        4. 定义一个统一的 Exception 继承 Exception 类，且实现 CommonError 接口，com.miaoshaproject.error.BusinessException。
            强关联一个 CommonError 对象，即 EmCommonError 枚举。（即包含一个成员变量）
            实现接口方法。注意 super() 方法的调用。
            项目中所有的捕获异常后都抛出该异常，最终由 Controller 组件进行异常处理。
            该方式采用的是 包装器 的设计模式。

    3.4 定义通用的返回对象-异常处理01
        1. 通过 SpringBoot 自带的 SpringMVC 的 ExceptionHandler 去解决一个通用的异常处理方式。
        2. 定义 exceptionhandler 解决未被 controller 层吸收的异常。
                定义捕获处理异常机制方法，在该方法上使用注解：
                @ExcetpionHandler(Exception.class)  // 标明该方法捕获某种类型的异常处理方法
                @ResponseStatus(HttpStatus.OK)      // 返回 HttpStatus状态为 OK
                Object handlerException(HttpServletRequest request, Exception ex)
                使用该方法返回一个之前定义的返回通用对象 CommonReturnType 方便前端解析数据。其中将该对象的 status 属性设置为 fail, data 设置为 ex.
                该方法只能返回到一个页面路径，不能返回 CommonReturnType 对象。

    3.5 定义通用的返回对象-异常处理02
        1. 为 handlerException() 方法继续添加 @ResponseBody 注解即可将返回的 object 返回给前端页面。
                该方式会将异常的所有栈信息序列化后输出到前端页面，因此还需要继续处理，只将前端需要的异常信息返回给前端。
        2. 将 ex 强转为 BusinessException ，然后使用其 getErrCode、getErrMsg 方法获取前端需要的异常信息，将其封装为 Map 后封装到 CommonReturnType 对象中，然后再返回给前端。
        3. 优化：使用 CommonReturnType 的静态方法 create 构造对象并返回。
        4. 继续完善该方法。
                判断 exception 是否为 BusinessException 类型，如果不是则为 CommonReturnType 对象赋值 errCode 为 EmBusinessError 枚举中的 UNKNOWN_ERROR 的 code 和 msg。
        5. 继续优化异常处理。
                因为该处理方式是所有 controller 都需要的方式，因此将其抽象为 BaseController 中的方法，然后让其他 controller 组件去继承该 controller。
        6. 总结：a. 定义一个 CommonReturnType, 能够用对应的 status, object 的方式返回所有的被 JSON 序列化对象，供前端解析使用，摒弃掉了使用 httpstatuscode + 内线 tomcat 自带的 error 页面方式去处理响应数据以及异常。
                b. 并且定义了一个 BusinessException ，统一管理我们自定义的错误码。
                c. 然后，在 BaseController 中定义一个所有 Controller 的基类，使用其中注解了 @ExceptionHandler 的方法来处理所有被 Controller 层捕获的异常。
                    按照异常的种类由2种处理方式，一种是自定义 BusinessException, 其中有自定义的 error 的 code 和 msg，一种是未知的异常，采用统一处理方式。
                d. 异常处理方法上还可以添加日志相关组件，方便项目运行记录与错误排查。

    3.6 用户模型管理-otp验证码获取
        0. 基础能力建设
                springboot + MVC + mybatis 框架搭建，外加常态的错误异常定义、正确的返回值类型定义。
           模型能力管理
                领域模型管理（如 user 对象就是一个用户领域的一个模型），包括完整的生命周期。用户模型、商品模型、秒杀模型等。
                    用户信息管理：
                        otp 短信获取
                        otp 注册用户
                        用户手机号登录
        1. 用户获取 otp 短信验证码
            a. 需要按照一定的规则生产OTP 验证码
            b. 将 OTP 验证码通对应用户的手机号关联（一般使用Redis处理，此处采用 session 模仿实现）
                使用 spring 注入方式注入一个 HttpServletRequest 对象，该对象其实是通过 spring bean 包装的 request 对象本质是 proxy 模式（spring 在注入 HttpServletRequest 时，发现如果注入的是 一个 ObjectFactory 类型的对象时，就会将注入的 bean 替换成一个 JDK 动态代理对象，代理对象在执行 HttpServletRequest 对象里的方法时，就会通过 RequestObjectFactory.getObject() 获取一个 新的 request 对象来执行。），即多例模式?。
                Spring能实现在多线程环境下，将各个线程的request进行隔离，且准确无误的进行注入，奥秘就是ThreadLocal. 它的内部拥有 ThreadLocal 方式的 map，去让用户在每个线程中处理自己对应的 request 中的数据，并且有ThreadLocal清除的机制。
            c. 将 OTP 验证码通关短信通道发送给用户

    3.7 用户模型管理--Metronic模板简介(06:18)
        1. 引入静态文件。暂时放在 miaosha 目录下。
        2. 创建 getotp.html

    3.8 用户模型管理--getotp页面实现(16:00)
        1. 完成 getotp.html 页面样式的编写。
        2. 完成 button 提交业务的编写
                先实现页面交互，再实现页面美化。
                a. 引入 jquery.js
                b. 编写 js 代码，实现 button 的 click 事件，用于向后端发送获取短信验证码的请求。
                    // 绑定 otp 的 click 事件用于向后端发送获取短信验证码的请求
                    // 使用 ajax 方式发送异步请求完成业务。
                    // 为 Controller 中的 @RequestMapper 添加指定的 method,consumes(将其声明到控制层基类中)
                    // 注意判空处理
                    // 注意 button 自定义 click 方法后，该方法内应该返回 false，因为使用 ajax 发送请求处理业务，不需要常见的事件冒泡给 form 提交 POST 方法。
                    // 注意程序中已经进行了统一异常处理，因此会尽可能返回了 success HTTPstatus = 200
                c. 因为是从本地 html 中发送请求到 localhost 的 url 因此会出现跨域安全异常，虽然请求能够到达控制层，并成功被控制层方法处理业务并返回，但是 ajax 请求会认为该请求是不安全的，因此走不到 success 块中，并会给浏览器报错。
                    在 springboot 中处理 ajax 跨域请求的方式：只要让 response 时刻返回 'Access-Control-Allow-Origin' 为所有的域，即 * 即可。
                    SpringBoot 提供给我们一个简单注解方式 @CrossOrigin 实现所有 SpringBoot 中所有请求返回对象带上一个 Access-Control-Allow-Origin 标签，即可实现跨域处理。
                    @CrossOrigin 可使用在 controller 上，或方法级别上，也可以同时使用，Spring将合并两个注释属性以创建合并的CORS配置。
                    该注解可以有2个参数：
                       origins ：允许可访问的域列表
                       maxAge：准备响应前的缓存持续的最大时间（以秒为单位）。

        3.  Ajax跨域请求问题，设置一下属性就可以了，
            前端 Ajax 请求中添加 xhrFields: {withCredentials: true},
            后端 controller 层添加 @CrossOrigin(origins = "*", allowCredentials = "true") 就可以访问了

    3.9 用户模型管理--getotp页面美化(05:05)
        1. 引入 bootstrap 相关的 css 样式表
            <link href="static/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
            <link href="static/assets/global/css/components.css" rel="stylesheet" type="text/css"/>
            <link href="static/assets/admin/pages/css/login.css" rel="stylesheet" type="text/css"/>
        2. 为页面元素添加 class 样式，实现页面美化。


    3.10 用户模型管理--用户注册功能实现01(19:25)
    3.11 用户模型管理--用户注册功能实现02(21:41)
    3.12 用户模型管理--用户登陆功能实现(12:01)
    3.13 优化校验规则(15:17)
4. 商品模块开发
   4.1 商品模型模型--商品创建01(13:53)
   4.2 商品模型模型--商品创建02(20:39)
   4.3 商品模型模型--商品创建03(07:39)
   4.4 商品模型模型--商品列表(06:47)
   4.5 商品模型模型--商品列表页面(11:03)
   4.6 商品模型模型--商品详情页面(06:39)
5. 交易模块开发
    5.1 交易模型管理--交易模型创建(06:15)
    5.2 交易模型管理--交易下单01(20:50)
    5.3 交易模型管理--交易下单02(16:06)
    5.4 交易模型管理--交易下单03(12:20)
6. 秒杀模块开发
    6.1 秒杀模型管理--活动模型创建(05:52)
    6.2 秒杀模型管理--活动模型与商品模型结合01(18:18)
    6.3 秒杀模型管理--活动模型与商品模型结合02(12:32)
    6.4 秒杀模型管理--活动模型与商品模型结合03(10:36)
7. 项目总结&遗留问题
    7.1 课程总结(10:42)