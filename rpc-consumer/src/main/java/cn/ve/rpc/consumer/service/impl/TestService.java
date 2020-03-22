package cn.ve.rpc.consumer.service.impl;

import cn.ve.rpc.provider.api.test.ITestProvider;
import org.apache.servicecomb.provider.pojo.RpcReference;
import org.springframework.stereotype.Service;

/**
 * RPC-服务消费者
 * @author ve
 * @date 2020/3/21 23:46
 */
@Service
public class TestService implements ITestProvider {

    // 从注册中心找到哪个应用程序下面的微服务
    // microserviceName=应用编号:应用名称
    // schemaId="",来自于服务提供者中的@RpcSchema中的值
    @RpcReference(microserviceName = "cn.ve:rpcProvider", schemaId = "testSchema")
    private ITestProvider testProvider;

    @Override
    public String test(String aabb) {
        return testProvider.test(aabb);
    }
}
