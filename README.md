# service-comb-dermo

### 资料

1. [ServiceComb官网](https://servicecomb.apache.org/cn/)
2. [ServiceComb脚手架](http://start.servicecomb.io/) 能拿到基础的配置文件及上手demo

### 介绍

本项目是基于ServiceComb微服务解决方案的一个入门教程

### 起步

1. 启动ServiceComb注册中心 Service Center [下载地址](https://servicecomb.apache.org/cn/release/)
3. 启动五个服务
   1. rest-provider
   2. rest--consumer
   3. rpc-provider
   4. rpc-consumer
   5. gateway-web
4. 验证
   1. rest:访问127.0.0.1:9081/test123 //浏览器--(rest)-->rest-consumer--(rest)-->rest-provider
   2. rpc:访问127.0.0.1:8088/rpc //浏览器--(rest)->rpc-consumer--(rpc)-->rpc-provider
   3. 负载均衡:额外启动一台rest-consumer(需先改端口),多次访问127.0.0.1:9081/test123,可以注意到请求分发到两台rest-consumer
   4. 服务限流:
      1. 修改rest-consumer的配置servicecomb.flowcontrol.Provider.qps.limit.gateway的值为1
      2. 重启服务rest-consumer
      3. 使用jmeter并发调用127.0.0.1:9081/test123,可以看到只有一个请求成功,其他请求返回429
   5. 服务熔断与降级:
      1. 使用jmeter循环请求127.0.0.1:9081/test123
      2. 关闭rest-provider服务,并观察请求结果
      3. 重启rest-provider服务,并观察请求结果
      4. 可以观察到在rest-provider关闭期间,虽然返回了一个错误,但是请求服务还是正常的,且这个异常是网关内抛出,也就是我们可以捕获这个异常然后二次加工

### 其他

#### 官方基础配置

```
APPLICATION_ID: start.servicecomb.io # 应用程序id
service_description:
  name: HelloServiceComb # 服务名称
  version: 0.0.1
servicecomb: # servicecomb相关配置
  circuitBreaker: # 服务熔断
    Provider: # 指定下游服务,哪个服务调不通就熔断
      HelloServiceComb: # 需要熔断的服务名称
        requestVolumeThreshold: 8 # 当在配置时间窗口内达到此数量的失败后，进行短路
  fallbackpolicy: # 服务降级,配合熔断同时使用
    provider:
      policy: returnnull # 降级策略,返回null
  flowcontrol: # 服务限流
    Provider:
      qps:
        limit:
          gateway: 1000 # 最大流量
  handler:
    chain:
      Provider: 服务治理调用链 当前为限流,熔断,日志追踪
        default: qps-flowcontrol-provider,bizkeeper-provider,tracing-provider
  highway:
    address: 0.0.0.0:9090
  rest:
    address: 0.0.0.0:9080
  service:
    registry: # 注册中心
      address: http://127.0.0.1:30100
      autodiscovery: false # 自动发现
```

#### 架构调整

   `网关->rest服务->rpc服务`
   
   结构为

   * 项目父框架 // 提供基础依赖
     * gateway 整个应用程序入口
     * rest-web // 提供rest服务给gateway使用
     * rest-web2 // 可选,比如web1是给前台调用的,web2可以给后台调用
     * module1-provider // 提供rpc服务给rest-web使用
     * module1-provider-api // 提供rpc服务的接口给rest-web使用
     * module*-provider // 可选,同上
     * module*-provider-api // 可选,同上
     * common // 存放公共jar包

#### 错误
* 犯了一个很致命的错误,父功能中,我为了好看,把parent节点往下移动到跟dependencyManagement一起,然后各模块的依赖一直有些许问题
   

