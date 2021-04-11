package com.luo.order80.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLB implements LoadBalancer {
    public  AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int gteAndIncrement(){
        int current;
        int next;
        do {
            current = atomicInteger.get();
            next = current >= 2147483647 ? 0 : current+1;
        }while (!this.atomicInteger.compareAndSet(current,next));
        System.err.println("第几次访问："+next);
        return next;
    }

    public ServiceInstance instances(List<ServiceInstance> ServiceInstance) {
        int index = this.gteAndIncrement() % ServiceInstance.size();
        return ServiceInstance.get(index);
    }
}
