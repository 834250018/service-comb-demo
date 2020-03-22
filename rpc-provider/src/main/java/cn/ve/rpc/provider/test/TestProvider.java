package cn.ve.rpc.provider.test;

import cn.ve.rpc.provider.api.test.ITestProvider;
import org.apache.servicecomb.provider.pojo.RpcSchema;

/**
 * RPC方式-服务提供者
 * @author ve
 * @date 2020/3/21 23:33
 */
@RpcSchema(schemaId = "testSchema")
public class TestProvider implements ITestProvider {

    @Override
    public String test(String aabb) {
        // 负载均衡中,每次启动一个新服务,提供的服务总是以最新的服务代码执行
        System.out.println("hello");
        return "hello " + aabb;
    }

}
