package com.alipay.sofa.rpc.quickstart;

import com.alipay.sofa.rpc.config.ConsumerConfig;

import org.openjdk.jmh.runner.RunnerException;

/**
 *
 *
 * @author 玄灭
 * @date 2018/10/14-下午6:42
 */
public class NormalTest {

    static ConsumerConfig<HelloService> consumerConfig = new ConsumerConfig<HelloService>()
        .setInterfaceId(HelloService.class.getName()) // 指定接口
        .setProtocol("bolt") // 指定协议
        .setDirectUrl("bolt://127.0.0.1:9696") // 指定直连地址
        .setConnectTimeout(10 * 1000);

    static HelloService helloService = consumerConfig.refer();

    static ConsumerConfig<HelloService2> consumerConfig2 = new ConsumerConfig<HelloService2>()
        .setInterfaceId(HelloService2.class.getName()) // 指定接口
        .setProtocol("bolt") // 指定协议
        .setDirectUrl("bolt://127.0.0.1:9697") // 指定直连地址
        .setConnectTimeout(10 * 1000);

    static HelloService2 helloService2 = consumerConfig2.refer();

    public static void main(String[] args) throws RunnerException {

        warp();

        long s = System.currentTimeMillis();

        for (int i = 0; i < 100000; i++) {
            helloService.jdkTest99("jdkTest99");//15321
        }
        long e = System.currentTimeMillis();
        System.out.println(e - s);



        s = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            helloService.jdkTest99("jdkTest99");//15321
        }
        e = System.currentTimeMillis();
        System.out.println(e - s);
    }

    private static void warp() {
        for (int i = 0; i < 100000; i++) {
            helloService.jdkTest99("jdkTest99");//15321
            helloService2.javassist("javassist");//14172
        }
    }
}
