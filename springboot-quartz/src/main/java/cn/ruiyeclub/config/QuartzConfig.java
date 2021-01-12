package cn.ruiyeclub.config;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;

@Configuration
public class QuartzConfig {

    @Autowired
    private QuartzJobFactory jobFactory;


    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        //获取配置属性
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        //在quartz.properties中的属性被读取并注入后再初始化对象
        propertiesFactoryBean.afterPropertiesSet();
        //创建SchedulerFactoryBean
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setQuartzProperties(propertiesFactoryBean.getObject());
        factory.setJobFactory(jobFactory);//支持在JOB实例中注入其他的业务对象
        factory.setApplicationContextSchedulerContextKey("applicationContextKey");
        factory.setWaitForJobsToCompleteOnShutdown(true);//这样当spring关闭时，会等待所有已经启动的quartz job结束后spring才能完全shutdown。
        factory.setOverwriteExistingJobs(false);//是否覆盖己存在的Job
        factory.setStartupDelay(10);//QuartzScheduler 延时启动，应用启动完后 QuartzScheduler 再启动

        return factory;
    }

    /**
     * 通过SchedulerFactoryBean获取Scheduler的实例
     * @return
     * @throws IOException
     * @throws SchedulerException
     */
    @Bean(name = "scheduler")
    public Scheduler scheduler() throws IOException, SchedulerException {
        Scheduler scheduler = schedulerFactoryBean().getScheduler();
        return scheduler;
    }
}