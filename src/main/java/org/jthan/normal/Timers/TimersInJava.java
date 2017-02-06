package org.jthan.normal.Timers;

import java.text.ParseException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 3 kind of java timers
 */
public class TimersInJava {

    public void _Thread() {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    System.out.println("hello world, Thread");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t1.start();
    }

    public void _TimerTask() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("hello world! Timer Task");
            }
        };

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task, 3000, 2000);
    }

    public void _ScheduledExecutorService() {
        Runnable runnable = new Runnable() {
            public void run() {
                System.out.println("Hello world!! _ScheduledExecutorService");
            }
        };

        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleAtFixedRate(runnable, 1000, 2000, TimeUnit.MILLISECONDS);
    }

    public void _QuartZ() throws SchedulerException, ParseException {
        //SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        //Scheduler scheduler = schedulerFactory.getScheduler();

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        JobDetail jobDetail = new JobDetail("jobDetail", "jobDetailGroup", MyQuartZJob.class);

        CronTrigger cronTrigger = new CronTrigger("cronTrigger", "triggerGroup");
        cronTrigger.setCronExpression(new CronExpression("0/2 * * * * ?"));

        scheduler.scheduleJob(jobDetail, cronTrigger);

        scheduler.start();
    }

    public static void main(String[] args) {
        TimersInJava tij = new TimersInJava();
        tij._Thread();
        tij._TimerTask();
        tij._ScheduledExecutorService();
        try {
            tij._QuartZ();
        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
