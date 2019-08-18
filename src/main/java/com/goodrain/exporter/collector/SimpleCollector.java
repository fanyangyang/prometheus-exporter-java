package com.goodrain.exporter.collector;

import io.prometheus.client.Collector;
import io.prometheus.client.Gauge;
import io.prometheus.client.GaugeMetricFamily;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/*
使用非标准指标类型进行数据采集，业务倾入性小，耦合性低

自己定义收集器，继承Collector抽象类，实现collect方法，定义收集指标

虽说自己定义收集器，但是同样是使用了默认的注册器
 */
@Component
public class SimpleCollector extends Collector {
    @Override
    public List<MetricFamilySamples> collect() {
        List<MetricFamilySamples> mfs = new ArrayList<>();

        //创建metrics指标
        GaugeMetricFamily labeledGauge = new GaugeMetricFamily("me_goodrain_custom_metrics", "goodrain.me custom metrics", Collections.singletonList("labelname"));

        //设置指标的label以及value
        labeledGauge.addMetric(Collections.singletonList("labelvalue"), new Random().nextInt());// 获取当前时间的数据，供Prometheus拉取

        //
        labeledGauge.addMetric(Collections.singletonList("labelkey"), new Random().nextInt());

        mfs.add(labeledGauge);

        return mfs;
    }
}
