package cn.ve.consumer.service.impl;

import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author ve
 * @date 2020/3/21 19:51
 */
@Service
public class TestServiceImpl {

    public static final String CSE_PROTOCOL = "cse://";

    public static final String serviceName = "restProvider";

    // RestTemplate做用是可以快速请求微服务,由springMvc提供
    // RestTemplateBuilder 构建出serviceComb的restTemplate,注意引入的包路径
    private static final RestTemplate restTemplate = RestTemplateBuilder.create();

    public void invokeHello() {
        String sendMsg = "Hi!";
        //service url is : cse://serviceName/operation
        System.out.println("get something: " + restTemplate.getForObject(CSE_PROTOCOL + serviceName + "/test?msg=" + sendMsg, String.class));
    }

}
