## 单点登录服务
sso-cas-server 是一款基于 [cas](https://github.com/apereo/cas) 4.0.3的单点登录服务，forked from  [cas-overlay-template](https://github.com/apereo/cas-overlay-template)，代码实现参照[博客]( http://jadyer.cn/2015/07/16/sso-cas-server-demo/) 。

## 功能点
* 登录功能增加验证码校验
* 通过数据库验证用户名和密码
* 自定义返回用户信息

## 部署
通过下面命令打成 `war` 包并防止在 `tomcat/webapps`下
```bash
mvn clean package
```