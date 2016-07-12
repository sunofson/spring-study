package com.baobaotao.basic.quartz;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.AnnualCalendar;

public class CalendarExample {
	public static void main(String[] args) throws Exception {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler scheduler = sf.getScheduler();
		AnnualCalendar holidays = new AnnualCalendar();
		//五一劳动节
		Calendar laborDay = new GregorianCalendar();
		laborDay.add(Calendar.MONTH, 5);
		laborDay.add(Calendar.DATE, 1);
		holidays.setDayExcluded(laborDay, true);
		//国庆节
		Calendar nationalDay = new GregorianCalendar();
		nationalDay.add(Calendar.MONDAY, 10);
		nationalDay.add(Calendar.DATE, 1);
		holidays.setDayExcluded(nationalDay, true);
		
		scheduler.addCalendar("holidays", holidays, false, false);
		
		//从5月1号10am开始
		Date runDate = TriggerUtils.getDateOf(0, 0, 10, 1, 5);
		JobDetail detail = new JobDetail("job1", "group1", SimpleJob.class);
		SimpleTrigger simpleTrigger = new SimpleTrigger("trigger1", "group1", runDate, null, SimpleTrigger.REPEAT_INDEFINITELY, 60L * 60L * 1000L);
		
		//让Trigger遵守节日的规则（排除节日）
		simpleTrigger.setCalendarName("holidays");
        scheduler.scheduleJob(job, trigger);
        scheduler.start();
		
	}
}
