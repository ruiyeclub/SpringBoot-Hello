package cn.ruiyeclub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * 配置国际化语言
 * @author Ray。
 * @date 2020/8/4 17:15
 */
@Configuration
public class LocaleConfig {

    /**
     * 默认解析器 其中locale表示默认语言
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        //默认中文
        localeResolver.setDefaultLocale(Locale.CHINA);
        return localeResolver;
    }
}
