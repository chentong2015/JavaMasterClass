package concurrent_thread_pool.schedule_trigger_quartez;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class ScheduleTrigger {

    // TODO. quartz Scheduler 任务调度器
    // 1. 定义要调度的Job任务是什么
    // 2. 定义Job什么时候被触发Trigger以及触发的次数周期
    // 3. 使用Scheduler按照Trigger来调度Job任务
    public static void main(String[] args) throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                .withIdentity("job1", "group1")
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(3)
                        .withRepeatCount(5))
                .startNow()
                .build();

        // Tell quartz to schedule the job using our trigger
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
        // scheduler.shutdown();
    }
}
