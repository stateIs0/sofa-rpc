package com.alipay.sofa.rpc.quickstart;

import java.util.concurrent.TimeUnit;

import com.alipay.sofa.rpc.config.ConsumerConfig;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 *
 * @author stateis0
 * @date 2018/10/14-下午4:26
 */
@State(Scope.Thread)
@BenchmarkMode({Mode.Throughput, Mode.AverageTime, Mode.SampleTime})
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 2, time = 3, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
@Fork(0)
public class JmhTest {

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



    @Benchmark
    public  void jdk() {
        helloService.jdkTest99("jdkTest99");
    }

    @Benchmark
    public  void javassist() {
        helloService2.javassist("javassist");
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder().include(JmhTest.class.getSimpleName()).build();

        new Runner(opt).run();
    }

}

