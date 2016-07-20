package com.baobaotao.quartz;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;

public class MyJob implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		String size = (String) jobDataMap.get("size");
		ApplicationContext ctx = (ApplicationContext) jobDataMap.get("applicationContext");
		System.out.println("size" + size);
		jobDataMap.put("size:", size+"0");
		String count = (String) jobDataMap.get("count");
		System.out.println("count:" + count);
	}
	

}
