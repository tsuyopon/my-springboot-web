package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.retry.annotation.EnableRetry;

// @Retryableを利用するためには@Configurationクラスに@EnableRetryをあわせて付与する必要があります。
@Configuration
@EnableRetry
@PropertySource(value = "classpath:retry.properties") // application.propertiesと同階層に配置する
public class RetryConfig {
}
