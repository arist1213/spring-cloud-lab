# 云原生应用的配置中心测试

本项目主要演示`spring-cloud-config`使用git作为配置管理机制的实现demo

1. `config-server` 配置中心，分发配置
2. `config-client` 配置中心客户端，获取配置

## Getting Started

基础搭建[参考](https://cloud.spring.io/spring-cloud-static/spring-cloud-config/1.3.3.RELEASE/multi/multi__spring_cloud_config_server.html) ，本文着重实例应用的各个方面。

### Prerequisites

1.本地git配置库config-repo

``` shell
cd $HOME
mkdir config-repo
cd config-repo
git init .
echo foo.info=bar > application.properties
git add -A .
git commit -m "Add application.properties"
```

2.远程git库

`https://github.com/arist1213/config-repo.git`

### Installing

1. 编译项目 `mvn clean package`
2. 启动`config-server`, `java -Xmx200m -jar config-server/target/config-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=local`
3. 启动`config-client`, `java -Dspring.profiles.active=dev -Xmx200m -jar config-client/target/config-client-0.0.1-SNAPSHOT.jar`

```shell
/ {application} / {profile} [ / {label} ]
/ {application} - {profile} . yml
/ {label} / {application} - {profile} . yml
/ {application} - {profile} . properties
/ {label} / {application} - {profile} . properties
```

访问 `curl localhost:8085/config-client/dev` 获取配置

## Running the tests

1.访问 `curl localhost:8080/hello`, 返回 `your role is none`

新增配置文件到仓库 `client-config-dev.propeties`

```ini
user.role=guest
```

2.刷新配置 `curl -XPOST http://localhost:8080/actuator/refresh`
继续访问 `curl localhost:8080/hello`, 返回 `your role is guest`


*远程git仓库修改同理*


> 使用脚本启动 `./run start config-server 8085 local` (macos系统专用)