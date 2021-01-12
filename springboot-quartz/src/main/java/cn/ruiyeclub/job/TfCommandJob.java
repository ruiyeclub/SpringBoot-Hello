package cn.ruiyeclub.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.SchedulerException;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class TfCommandJob implements Job {

    @Override
    public void execute(JobExecutionContext context) {
        try {
            System.out.println(context.getScheduler().getSchedulerInstanceId() + "--" + new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date()));
        } catch (SchedulerException e) {
            log.error("任务执行失败",e);
        }
    }
}
