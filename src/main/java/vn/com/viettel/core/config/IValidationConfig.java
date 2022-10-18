package vn.com.viettel.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public interface IValidationConfig {

    @Bean
    default LocalValidatorFactoryBean localValidatorFactoryBean() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        ResourceBundleMessageSource messageBundle = new ResourceBundleMessageSource();
        messageBundle.setBasenames("validation");
        messageBundle.setDefaultEncoding("UTF-8");
        messageBundle.setUseCodeAsDefaultMessage(true);
        bean.setValidationMessageSource(messageBundle);
        return bean;
    }

}
