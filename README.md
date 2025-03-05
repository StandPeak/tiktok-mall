# 抖音商城
Docker 是一个开源的应用容器引擎，它允许开发者打包他们的应用以及依赖包到一个可移植的容器中，然后发布到任何流行的 Linux 机器上，也可以实现虚拟化。容器是完全使用沙箱机制，相互之间不会有任何接口。本项目使用到支持采用docker部署。
[图片]
技术选型
使用springboot、nacos、mysql、redis、rabbitmq、mybatis-plus、fegin、Jackson、SLF4J、SaToken、Hibernate Validator、elasticsearch等技术或功能。
1. 客户端层
- Web/H5/小程序：用户访问入口
- 通信协议：HTTP/HTTPS、WebSocket（实时通知）

---
2. API网关层
- Spring Cloud Gateway
  - 功能：
    - 路由转发（将请求分发到对应微服务）
    - 鉴权拦截（SaToken验证Token）
    - 参数校验（Hibernate Validator校验请求体）
    - 日志记录（SLF4J记录请求流水）
  - 依赖组件：
    - Nacos：动态获取路由配置
    - 

---
3. 微服务层
- 服务划分：
  - 用户服务（注册、登录、权限管理）
  - 商品服务（商品详情、库存管理）
  - 订单服务（下单、支付状态）
  - 支付服务（对接第三方支付）
  - 营销服务（优惠券、秒杀活动）
- 技术实现：
  - Spring Boot：服务基础框架
  - Feign：服务间调用（如订单服务调用商品服务扣减库存）
  - MyBatis-Plus：操作MySQL数据库（简化CRUD）
  - Redis：缓存热点数据（商品详情、购物车）
  - RabbitMQ：异步处理（订单超时取消、库存回滚）
  - Jackson：JSON序列化（API响应和消息队列数据）
  - Nacos：服务注册发现 + 动态配置（如数据库连接池参数）

---
4. 中间件层
- Nacos：
  - 服务注册中心（微服务注册与发现）
  - 配置中心（统一管理各环境配置文件）
- Redis：
  - 分布式锁（秒杀场景）
  - 会话存储（用户登录状态）
- RabbitMQ：
  - 消息队列（订单延迟队列、削峰填谷）
  - 交换机与队列绑定（如order.delay.queue）

---
5. 数据层
- MySQL：
  - 业务主库（用户表、商品表、订单表）
  - 读写分离（通过MyBatis-Plus动态数据源）
  - 分库分表（可选，大数据量场景）

---
6. 全局支撑
- SaToken：
  - 单点登录（SSO）
  - 权限拦截（角色/权限注解@SaCheckRole("admin")）
- SLF4J：
  - 日志分级（DEBUG/INFO/ERROR）
  - 日志采集（ELK或Graylog聚合日志）
- Hibernate Validator：
  - API参数校验（如@NotBlank校验用户名）
