package cn.ve.rpc.consumer.controller;

import cn.ve.rpc.consumer.service.impl.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 对外发布的controller
 *
 * @author ve
 * @date 2020/3/21 23:46
 */
@RestController
public class TestController {

    @Autowired
    TestService testService;

    @GetMapping("/rpc")
    public String test() {
        String ab = testService.test("rpc");
        System.out.println(ab);
        return ab;
    }
}
