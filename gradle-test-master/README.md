# Gradle Sample
测试Gradle在项目中的使用，考虑在常规项目实际运用的场景下进行配置编辑

> Gradle version 4.10.2

## Gradle相关核心点

- gradle父子项目中的结构
- gradle在父子项目中配置继承特点
- gradle自带提供的tasks测试
- gradle如何自定义task编写
- gradle父子项目时自定义task
- gradle如何构建java project\lib project\web project
- gradle中子项目相互引用
- gradle和springBoot结合构建web项目
- gradle项目linux中如何脚本话package发布

## 模块介绍

> gradle-test

父级模块，更多的是全局的配置，如respositry。  
测试了关于gradle scan的模式，scan也是gradle提供的一种全新的jar管理方案。    
测试自定义task任务是如何开发。  

> gradle-test-dubbo-api

接口模块，通过maven的nexus方式发布管理公司内部jar。   
依赖java-library和maven两个插件，后一个主要用来发布jar到maven私募；前一个是用来构建lib jar使用。   
执行命令：gradlew :gradle-test-dubbo-api:jar 可以生成lib jar，在/build/lib/目录下。   
执行命令：gradlew :gradle-test-dubbo-api:uploadArchives包lib jar发布到nexus中一般只公司自己的私服。  

> gradle-test-java

传统的java项目方式，java run main class进行部署发布。   
执行命令：gradlew :gradle-test-java:run 用来本地开发测试使用，也可以直接java run main class。   
执行命令：gradlew :gradle-test-java:jar 构建lib jar，使用原始命令java lib/*.jar main class方式来启动服务。 

> gradle-test-webapp

本地通过tomcat|jetty方式运行，以war方式进行部署发布。  
以war和org.gretty两个插件为核心，war用来构建打包，gretty用来本地启动服务调试。  
执行命令：gradlew :gradle-test-webapp:appRun 运行本地服务进行开发测试。  
执行命令：gradlew :gradle-test-webapp:war 构建war包发布部署，包在build/lib/目录下。  

> gradle-test-jpa

编写以spring-boot-starter-data-jpa方式连接数据库，结合gradle-test-bootapp模块进行测试。  

> gradle-test-bootapp

SpringBoot方式运行项目，并发布可执行jar方式部署。  
以org.springframework.boot插件为核心。  
执行命令：gradlew :gradle-test-bootapp:bootJar可以生成可执行jar，包在build/lib/目录下。   
执行命令：gradlew :gradle-test-bootapp:bootRun或直接java run main class启动项目，本地测试。   

*亮点：*   
gradle scan 的jar包管理模式也是很不错的，值得推荐特别是开源类型jar。

## 项目构建样例

> 本地开发环境，window为例

构建父项目  
gradle clean

> 自动化部署环境，linux为例

单独构建子项目  
gradle :gradle-test-java:run

## release history

v1.0 第一版，比较稳定。