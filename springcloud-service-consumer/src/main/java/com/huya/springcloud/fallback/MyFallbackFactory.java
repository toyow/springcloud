package com.huya.springcloud.fallback;


import com.huya.springcloud.service.HelloService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class MyFallbackFactory implements FallbackFactory<HelloService> {

    @Override
    public HelloService create(Throwable throwable) {
        return new HelloService() {
            @Override
            public String Hello() {
                String error = throwable.getMessage();
                if (error != null) {
                    return "服务报错:" + error;
                } else {
                    return "延时过高，熔断";
                }
            }
        };
    }
}