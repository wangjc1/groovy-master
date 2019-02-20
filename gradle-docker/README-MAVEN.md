# 基于Maven的SpringBoot与Docker集成

## Docker环境准备
参见[Docker安装与常用命令](/docs/Docker安装与常用命令.md)

## 一、程序修改
> 在src/main下，创建Dockerfile
```
# 基础镜像
FROM frolvlad/alpine-oraclejdk8:slim

# 让容器可以访问宿主机上的目录。
# 在主机/var/lib/docker目录下创建了一个临时文件，并链接到容器的/tmp。
# /tmp目录用来持久化到Docker数据文件夹，因为 Spring Boot 使用的内嵌 Tomcat 容器默认使用/tmp作为工作目录
VOLUME /tmp

# 从源系统上复制文件到目标容器的文件系统。
# 将项目的jar文件复制到到容器中，作为 "app.jar"。
# 注意里面的ADD xxx.jar是你使用maven打包之后的jar包的名称。
ADD docker-by-maven-1.0-SNAPSHOT.jar app.jar

# RUN表示在新创建的镜像中执行一些命令，然后把执行的结果提交到当前镜像。
# 这里使用touch命令来改变文件的修改时间，Docker创建的所有容器文件默认状态都是“未修改”。
# 这对于简单应用来说不需要，不过对于一些静态内容（比如：index.html）的文件就需要一个“修改时间”。
RUN sh -c 'touch /app.jar'

# 容器运行后默认执行的命令：用于执行项目app.jar。
# 为了缩短Tomcat的启动时间，添加一个系统属性指向 "/dev/urandom" 作为EntropySource。
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
```

> 修改pom.xml文件

引入变量（docker镜像文件的前缀）
```
<docker.image.prefix>jianing0</docker.image.prefix>
```
引入docker maven插件
```
<!--docker maven 插件-->
<plugin>
    <groupId>com.spotify</groupId>
    <artifactId>docker-maven-plugin</artifactId>
    <version>1.0.0</version>
    <configuration>
        <imageName>${docker.image.prefix}/${project.artifactId}</imageName>
        <dockerDirectory>src/main/docker</dockerDirectory>
        <resources>
            <resource>
                <targetPath>/</targetPath>
                <directory>${project.build.directory}</directory>
                <include>${project.build.finalName}.jar</include>
            </resource>
        </resources>
    </configuration>
</plugin>
```

## 二、使用Docker部署服务(本地Docker服务器)

> 构建镜像

执行命令
```
mvn package docker:build
```
或 Maven工具栏 -> Pluagins -> docker:bulid

> 检查构建镜像的结果

```
docker images
```

![检查构建镜像的结果.png](/docs/imgs/检查构建镜像的结果.png)

显示的结果中，镜像的名称，是由pom文件中的imageName配置的。

> 启动服务

```
docker run -d -p 8080:8080 jianing0/docker-by-maven
```
命令解释:
- -d 代表后台运行
- -p 标识宿主机与docker服务的端口映射，注意先后关系：【**宿主端口：docker内服务端口**】
- jianing0/docker-by-maven 就是启动镜像的名称，当然了使用IMAGE ID 也是可以的

> 检查服务是否已启动成功

显示正在运行的Docker容器
```
docker ps
```

显示所有的Docker容器
```
docker ps -a
```

> 访问服务

http://localhost:8080

## 三、使用Docker部署服务(远程Docker服务器)

>为docker开启远程API端口

1、 修改docker配置文件
```
vi /usr/lib/systemd/system/docker.service
```
2、 在ExecStart这一行后面加上（这里就写4个0，别改成自己的ip）
```
-H tcp://0.0.0.0:2375 -H unix:///var/run/docker.sock
```

修改后：
```
ExecStart=/usr/bin/dockerd -H tcp://0.0.0.0:2375 -H unix:///var/run/docker.sock
```

3、 重新加载配置文件
```
systemctl daemon-reload
```

4、 启动docker
```
systemctl start docker
```

5、 验证

输入``curl 127.0.0.1:2375/info``  显示若干信息，证明端口已经对外开放了

>配置dockerHost

在pom文件的docker-maven-plugin中，增加dockerHost的属性。

（假设远程的Docker服务器IP为172.16.115.186）

```
<dockerHost> http://172.16.115.186:2375</dockerHost>
```

> 启动服务，验证

1、 构建镜像
```
mvn package docker:build
```

2、 连接远程的Docker服务器

3、 启动服务
```
docker run -d -p 8080:8080 jianing0/docker-by-maven
```

## 四、将镜像推送到DockerHub

推送镜像通俗的说就是类似把代码推送到github一样，这个是把一个完整的应用程序推送到DockerHub，供其他Docker pull与使用。

> 在[DockerHub](https://hub.docker.com)中创建仓库（与pom里的配置相对应）

![新建DockerHub仓库.png](/docs/imgs/新建DockerHub仓库.png)

创建完成后：

![DockerHub仓库创建完成.png](/docs/imgs/DockerHub仓库创建完成.png)

> 在终端中登录（按照提示输入用户名和密码）
```
docker login
```

> 推送（push）镜像至DockerHub
```
docker push jianing0/docker-by-maven:latest
```
latest是tag，相当于版本号 

## 五、其他问题
- 镜像的名称只支持英文小写？[refer](https://github.com/spotify/dockerfile-maven/issues/95)

## 六、Refer
[SpringBoot集成Docker的部署、发布与应用](https://blog.csdn.net/qqhjqs/article/details/79101846)
