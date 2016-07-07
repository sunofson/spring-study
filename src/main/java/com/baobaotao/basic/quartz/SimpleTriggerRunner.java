package com.baobaotao.basic.quartz;

import java.util.Calendar;
import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

public class SimpleTriggerRunner {
	public static void main(String[] args) {
		try {
			//① 创建 JobDetail 实例， 指定SimpleJob，组名为：jgroup1， 名称为:job1_1 
			JobDetail jobDetail = new JobDetail("job1_1", "jgroup1", SimpleJob.class);
			
			SimpleTrigger simpleTrigger = new SimpleTrigger("trigger1_1","tgroup1");
			//②-1 通过SimpleTrigger 定义调度规则: 马上启动，每2秒运行一次，运行100次
//			simpleTrigger.setStartTime(new Date());
//			simpleTrigger.setRepeatInterval(2000);
//			simpleTrigger.setRepeatCount(100);
			
			//②-2 调度规则: 6秒后开始运行
			
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(System.currentTimeMillis() + 1000 * 7L); 
			simpleTrigger.setStartTime(new Date( System.currentTimeMillis() + 6000L));
			simpleTrigger.setEndTime(c.getTime());
			simpleTrigger.setRepeatInterval(2000);
			simpleTrigger.setRepeatCount(100);
			
			//③ 通过 SchedulerFactory 获取一个调度器实例
			SchedulerFactory schedulerFactory = new StdSchedulerFactory();
			Scheduler scheduler = schedulerFactory.getScheduler();
			//④ 注册并进行调度
			scheduler.scheduleJob(jobDetail, simpleTrigger);
			scheduler.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
