package com.goodrain.exporter.controller;

import java.util.Random;

import io.prometheus.client.Gauge;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.prometheus.client.Counter;

/*
使用标准指标类型进行数据的采集操作

标准指标类型都继承了io.prometheus.client.SimpleCollector，所以使用的是simpleCollector的register方法进行指标的注册，将指标注册到默认的注册器中

在Prometheus触发项目暴露的/metrics服务时采集数据
 */
@RestController
public class Ping {


    private static Random random = new Random();

    private static final Counter requestTotal = Counter.build().name("request_counter_total").labelNames("status").help("A simple Counter to illustrate custom Counters in Spring Boot and Prometheus").register();

    // 定义Prometheus监控指标，同时添加三类标签（service_id:服务ID；service_name:服务名称；data_type:数据类型）
    private static final Gauge serviceTotal = Gauge.build().labelNames("service_id", "service_name", "data_type").name("service_total").help("service prometheus metrics").register();

    @RequestMapping("/endpoint")
    public void endpoint() {
        if (random.nextInt(2) > 0) {

            requestTotal.labels("success").inc();
        } else {
            requestTotal.labels("error").inc();
        }
    }

    /*
    模拟输入服务数据
     */
    @RequestMapping("/service")
    public void plainTotalAdd() {
        String s = getRandomString(2);
        serviceTotal.labels(s, s, "inData0").inc(random.nextInt(2));//根据不同服务ID、服务名称以及数据类型【主动】向Prometheus推送数据
        serviceTotal.labels(s, s, "prcData0").inc(random.nextInt(2));
        s = getRandomString(2);
        serviceTotal.labels(s, s, "inData1").inc(random.nextInt(2));
        serviceTotal.labels(s, s, "prcData1").inc(random.nextInt(2));
        s = getRandomString(2);
        serviceTotal.labels(s, s, "inData2").inc(random.nextInt(2));
        serviceTotal.labels(s, s, "prcData2").inc(random.nextInt(2));
        s = getRandomString(2);
        serviceTotal.labels(s, s, "inData3").inc(random.nextInt(2));
        serviceTotal.labels(s, s, "prcData3").inc(random.nextInt(2));

    }

    @RequestMapping("/service/d")
    public void plainTotalDel() {
        String s = getRandomString(2);
        serviceTotal.labels(s, s, "inData0").dec(random.nextInt(2));
        serviceTotal.labels(s, s, "prcData0").dec(random.nextInt(2));
        s = getRandomString(2);
        serviceTotal.labels(s, s, "inData1").dec(random.nextInt(2));
        serviceTotal.labels(s, s, "prcData1").dec(random.nextInt(2));
        s = getRandomString(2);
        serviceTotal.labels(s, s, "inData2").dec(random.nextInt(2));
        serviceTotal.labels(s, s, "prcData2").dec(random.nextInt(2));
        s = getRandomString(2);
        serviceTotal.labels(s, s, "inData3").dec(random.nextInt(2));
        serviceTotal.labels(s, s, "prcData3").dec(random.nextInt(2));

    }
    public String[] ids = new String[]{"service_1","service_2","service_3","service_4"};

    public String getRandomString(int index) {
        return ids[index];
    }

}

