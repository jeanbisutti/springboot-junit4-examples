package football;

import org.quickperf.spring.sql.QuickPerfProxyBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuickPerfPerBeanConfig {

    @Bean
    public QuickPerfProxyBeanPostProcessor dataSourceBeanPostProcessor() {
        return new QuickPerfProxyBeanPostProcessor();
    }

}
