package cn.ve.consumer.controller;

import cn.ve.consumer.service.impl.TestServiceImpl;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ve
 * @date 2020/3/21 19:51
 */
@RestSchema(schemaId = "ctl")
@RequestMapping("/test")
public class TestController {

//    @Autowired
//    private TestServiceImpl testService;

    @GetMapping("/test123")
    public String test() {
//        testService.invokeHello();
        return "ok";
    }
}
