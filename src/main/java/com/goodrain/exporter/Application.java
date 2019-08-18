package com.goodrain.exporter;

import com.goodrain.exporter.collector.SimpleCollector;
import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnablePrometheusEndpoint
public class Application {
    static SimpleCollector customExporter = new SimpleCollector();

    public static void main(String[] args) {
        customExporter.register();
        SpringApplication.run(Application.class, args);
    }
}
