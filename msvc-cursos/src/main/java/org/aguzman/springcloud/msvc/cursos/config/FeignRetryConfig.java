package org.aguzman.springcloud.msvc.cursos.config;

import feign.Retryer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignRetryConfig {

    @Value("${feign.retry.period:100}")
    private long period;

    @Value("${feign.retry.max-period:1000}")
    private long maxPeriod;

    @Value("${feign.retry.max-attempts:3}")
    private int maxAttempts;

    @Bean
    public Retryer retryer() {
        return new Retryer.Default(period, maxPeriod, maxAttempts);
    }
}
