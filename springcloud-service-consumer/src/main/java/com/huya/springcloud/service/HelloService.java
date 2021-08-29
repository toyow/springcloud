package com.huya.springcloud.service;


import com.huya.springcloud.fallback.MyFallback;
import com.huya.springcloud.fallback.MyFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="springcloud-service-provider", /*fallback = MyFallback.class*/ fallbackFactory = MyFallbackFactory.class)
//@FeignClient(name="springcloud-service-provider", fallback = MyFallback.class)
public interface HelloService {

    /**
     * 声明一个方法，这个方法就是远程的服务提供者提供的那个方法
     *
     * @return
     */
    @RequestMapping("/service/hello")
    public String Hello();
}
