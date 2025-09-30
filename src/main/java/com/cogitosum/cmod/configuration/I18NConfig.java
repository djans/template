package com.cogitosum.cmod.configuration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class I18NConfig {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages/messages");  // Path to the message files
        messageSource.setDefaultEncoding("UTF-8");  // Encoding
        messageSource.setCacheSeconds(3600);  // Reload messages every hour
        return messageSource;

    }
}
