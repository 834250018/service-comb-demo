package cn.ve.provider.test;

import cn.ve.provider.api.test.ITestProvider;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * REST方式-服务提供者
 * @author ve
 * @date 2020/3/21 19:45
 */
@RestSchema(schemaId = "test")
@RequestMapping(path = "/")
public class TestProvider implements ITestProvider {

    @Override
    @GetMapping("/test")
    public String test(String msg) {
        System.out.println("收到:" + msg);
        return "copy that";
    }
}
