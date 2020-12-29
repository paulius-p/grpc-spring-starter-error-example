package com.example.config;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.config.MeterFilter;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MonitoringConfiguration {

  /*
   * Some customized metrics. That's why `spring-boot-actuator-autoconfigure` is needed.
   */
  @Bean
  public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
    return registry -> registry.config()
        .meterFilter(MeterFilter.acceptNameStartsWith("grpc"))
        .meterFilter(MeterFilter.acceptNameStartsWith("method"))
        .meterFilter(MeterFilter.deny());
  }
}