package org.aguzman.springcloud.msvc.usuarios.config;

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
        if (maxAttempts <= 0) {
            throw new IllegalArgumentException("feign.retry.max-attempts must be greater than 0");
        }
        if (maxPeriod < period) {
            throw new IllegalArgumentException("feign.retry.max-period must be greater than or equal to feign.retry.period");
        }
        return new Retryer.Default(period, maxPeriod, maxAttempts);
    }
}
