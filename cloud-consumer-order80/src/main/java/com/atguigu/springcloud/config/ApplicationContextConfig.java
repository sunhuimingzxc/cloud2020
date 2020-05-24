package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced //使restTemplate具备负载均衡调用provider的功能
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    /**
     * @Configuration : 使用注解取代spring配置文件
     * @Bean：创建一个bean交给spring容器管理，未指定bean 的名称，默认采用的是 "方法名" + "首字母小写"的配置方式
     */
}
