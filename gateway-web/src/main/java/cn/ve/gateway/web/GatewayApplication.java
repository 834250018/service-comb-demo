package cn.ve.gateway.web;

import org.apache.servicecomb.springboot.starter.provider.EnableServiceComb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author ve
 * @date 2020/3/21 19:43
 */
@SpringBootApplication
@EnableServiceComb
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // 允许跨域
        config.addAllowedOrigin("*"); // 允许向该服务器请求的URLI,*表示全部允许
        config.addAllowedHeader("*"); // 允许访问的偷信息,*表示全部
        config.setMaxAge(18000L); // 预检请求的缓存时间(秒),即在这个时间段里,对于相同的跨域请求不校验
        config.addAllowedMethod("OPTIONS"); // 允许提交请求的方法,*表示全部允许
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
