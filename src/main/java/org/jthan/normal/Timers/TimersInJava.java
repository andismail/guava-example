package org.jthan.normal.Timers;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.junit.Test;

/**
 * 3 kind of java timers
 */
public class TimersInJava {

    @Test
    public void _Thread() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("hello world");
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

    @Test
    public void _TimerTask() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("hello world!");
            }
        };

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task, 3000, 2000);
    }

    @Test
    public void _ScheduledExecutorService() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world!!");
            }
        };

        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleAtFixedRate(runnable, 1000, 2000, TimeUnit.MILLISECONDS);
    }

}
