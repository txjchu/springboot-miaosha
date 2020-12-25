[慕课网：springboot项目练习-电商秒杀](https://www.imooc.com/learn/1079)

1. 课程介绍
2. 应用SpringBoot完成基础项目搭建
    2.1 使用IDEA创建MAVEN项目
    2.2 引入SpringBoot依赖包实现简单的Web
        1.
              <parent>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-parent</artifactId>
                <version>2.3.2.RELEASE</version>
                <relativePath/> <!-- lookup parent from repository -->
              </parent>
               <dependency>
                 <groupId>org.springframework.boot</groupId>
                 <artifactId>spring-boot-starter-web</artifactId>
                 <version>2.2.1.RELEASE</version>
               </dependency>
        2. @EnableAutoConfiguration    // 将该启动类，开启整个工程基于 SpringBoot 自动化注解配置
                SpringApplication.run(App.class, args);
        3. @RequestMapping("/")

    2.3 Mybatis接入SpringBoot项目
        1. # SpringBoot 默认在 resources 目录下寻找 application.properties 配置文件，加载其中的配置，就可以改变 SpringBoot 中默认配置。
           server.port=8090
        2. 集成 mysql 组件。 修改 pom.xmlpom.xm
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
        3. 配置 maven Command line : mybatis-generator:generate -e -X
            注意 ： generate 前是 :
        4. 生成后，需要给 mapper 接口添加注解
        5. application.properties 中添加数据源配置

            # 接入 mybatis 对应的数据源
            spring.datasource.name=miaosha
            spring.datasource.url=jdbc:mysql://127.0.0.1:3306/miaosha?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8&useSSL=false
            spring.datasource.username=root
            spring.datasource.password=1234

            # 使用 druid 数据源
            spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
            spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

3. 用户模块开发
    3.1 使用SpringMVC方式开发用户信息
    3.2 定义通用的返回对象-返回正确信息
        1. 通用的返回对象。方便前端解析数据。
            首先，定义返回对象类型 class， 包含 String status, Object date 2个属性。
            然后，定义 二重奏 create(Object result)-（该方法中如果没给 status 参数则给出默认值"success"） 调用 create(Object result, String status)，
    3.3 定义通用的返回对象-返回错误信息
    3.4 定义通用的返回对象-异常处理01
    3.5 定义通用的返回对象-异常处理02
    3.6 用户模型管理-otp验证码获取
    3.7 用户模型管理--Metronic模板简介(06:18)
    3.8 用户模型管理--getotp页面实现(16:00)
    3.9 用户模型管理--getotp页面美化(05:05)
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