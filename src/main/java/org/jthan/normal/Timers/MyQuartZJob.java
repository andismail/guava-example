package org.jthan.normal.Timers;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 */
public class MyQuartZJob implements Job {

    //Logger log = LoggerFactory.getLogger(MyQuartZJob.class);

    public void execute(JobExecutionContext context) throws JobExecutionException {
        //log.info("Hello world!! QuartZ");
        System.out.println("Hello world!!QuartZ");
    }
}
