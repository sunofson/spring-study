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
			//�� ���� JobDetail ʵ���� ָ��SimpleJob������Ϊ��jgroup1�� ����Ϊ:job1_1 
			JobDetail jobDetail = new JobDetail("job1_1", "jgroup1", SimpleJob.class);
			
			SimpleTrigger simpleTrigger = new SimpleTrigger("trigger1_1","tgroup1");
			//��-1 ͨ��SimpleTrigger ������ȹ���: ����������ÿ2������һ�Σ�����100��
//			simpleTrigger.setStartTime(new Date());
//			simpleTrigger.setRepeatInterval(2000);
//			simpleTrigger.setRepeatCount(100);
			
			//��-2 ���ȹ���: 6���ʼ����
			
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(System.currentTimeMillis() + 1000 * 7L); 
			simpleTrigger.setStartTime(new Date( System.currentTimeMillis() + 6000L));
			simpleTrigger.setEndTime(c.getTime());
			simpleTrigger.setRepeatInterval(2000);
			simpleTrigger.setRepeatCount(100);
			
			//�� ͨ�� SchedulerFactory ��ȡһ��������ʵ��
			SchedulerFactory schedulerFactory = new StdSchedulerFactory();
			Scheduler scheduler = schedulerFactory.getScheduler();
			//�� ע�Ტ���е���
			scheduler.scheduleJob(jobDetail, simpleTrigger);
			scheduler.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
