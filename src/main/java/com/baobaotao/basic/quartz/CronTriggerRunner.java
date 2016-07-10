package com.baobaotao.basic.quartz;

import java.text.ParseException;

import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

public class CronTriggerRunner {
	public static void main(String[] args) {
		try {
			JobDetail jDetail = new JobDetail("job1_2", "jgroup1", SimpleJob.class);
			CronTrigger cronTrigger = new CronTrigger("trigger1_2", "tgroup1");
			CronExpression cronExpression = new CronExpression("0/5 * * * * ?");
			cronTrigger.setCronExpression(cronExpression);
			
			SchedulerFactory schedulerFactory = new StdSchedulerFactory();
			Scheduler scheduler = schedulerFactory.getScheduler();
			scheduler.scheduleJob(jDetail, cronTrigger);
			scheduler.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
