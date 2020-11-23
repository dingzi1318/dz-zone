package com.ribbon.client;

import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.LoadBalancerBuilder;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.reactive.LoadBalancerCommand;
import rx.Observable;

import java.util.Arrays;
import java.util.List;

/**
 * Ribbon原生API调用demo
 *
 * @Author dingzi
 * @Date 2020/11/20 13:56
 */
public class RibbonNativeClientDemo {


    public static void main(String[] args) {

        // 构建一个静态的服务列表
        List<Server> serverList = Arrays.asList(new Server("localhost", 8081), new Server("localhost", 8082));

        // 构建负载实例
        BaseLoadBalancer loadBalancer = LoadBalancerBuilder.newBuilder().buildFixedServerListLoadBalancer(serverList);

        // 设置负载均衡策略: 随机策略
        loadBalancer.setRule(new RandomRule());

        for (int i = 1; i <= 5; i++) {
            String result = LoadBalancerCommand.<String>builder().withLoadBalancer(loadBalancer).build()
                    .submit(operation -> {
                                try {
                                    String addr = "http://" + operation.getHost() + ":" + operation.getPort();
                                    System.out.println("调用地址：" + addr);
                                    return Observable.just("");
                                } catch (Exception e) {
                                    return Observable.error(e);
                                }
                            }
                    ).toBlocking().first();

            System.out.println("调用结果：" + result);
        }
    }


}
