package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    //取自yml配置文件中的server.port
    @Value("${server.port}")
    private int port;

    @Resource
    private DiscoveryClient discoveryClient;

    /**
     * 传给前端的是CommonResult
     * @RequestBody:将请求过来的json绑定到对应bean上；必须为post请求时使用
     * @return
     */
    @PostMapping(value="/payment/create")
    public CommonResult<Payment> create(@RequestBody  Payment payment){
        int result = paymentService.create(payment);
        log.info("插入结果：" + result);
        if(result > 0){
            return new CommonResult(200, "插入数据成功,port="+port, result);
        }
        return new CommonResult(444,"插入数据失败", null);
    }

    @GetMapping(value="/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果：" + payment);
        if(payment != null){
            return new CommonResult(200, "查询成功,port="+port, payment);
        }
        return new CommonResult(444,"没有对应记录，查询ID：" + id, "没有对应记录");
    }

    /**
     * 服务发现
     * @return
     */
    @GetMapping("/payment/discover")
    public Object discover(){
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("*****element: "+element);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }
}
