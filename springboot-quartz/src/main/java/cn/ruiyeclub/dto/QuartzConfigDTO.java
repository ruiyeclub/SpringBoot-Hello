package cn.ruiyeclub.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class QuartzConfigDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 任务名称
     */
    private String jobName;

    /**
     * 任务所属组
     */
    private String groupName;

    /**
     * 任务执行类
     */
    private String jobClass;

    /**
     * 任务调度时间表达式
     */
    private String cronExpression;

    /**
     * 附加参数
     */
    private Map<String, Object> param;


    public String getJobName() {
        return jobName;
    }

    public QuartzConfigDTO setJobName(String jobName) {
        this.jobName = jobName;
        return this;
    }

    public String getGroupName() {
        return groupName;
    }

    public QuartzConfigDTO setGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public String getJobClass() {
        return jobClass;
    }

    public QuartzConfigDTO setJobClass(String jobClass) {
        this.jobClass = jobClass;
        return this;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public QuartzConfigDTO setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
        return this;
    }

    public Map<String, Object> getParam() {
        return param;
    }

    public QuartzConfigDTO setParam(Map<String, Object> param) {
        this.param = param;
        return this;
    }
}