/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: AsyncConfig
 * Author:   zhangfan
 * Date:     2019-03-10 16:28
 * Description: task的配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package cn.ruiyeclub.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 异步线程池配置
 * @author Ray。
 */
@Configuration
@EnableAsync
public class AsyncConfig {

    @Value("${scheduled.corePoolSize}")
    private int corePoolSize;
    @Value("${scheduled.maxPoolSize}")
    private int maxPoolSize;
    @Value("${scheduled.queueCapacity}")
    private int queueCapacity;

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.initialize();
        return executor;
    }
}