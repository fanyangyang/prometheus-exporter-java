package com.goodrain.exporter;

import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
 @EnablePrometheusEndpoint
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
