package com.pettyfer.intellijPlugin.backgroundImage;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.wm.impl.IdeBackgroundUtil;
import com.pettyfer.intellijPlugin.backgroundImage.ui.Settings;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author Petty
 */
public class RandomBackgroundService {

    private static ScheduledThreadPoolExecutor executor = null;

    public static void start() {
        PropertiesComponent prop = PropertiesComponent.getInstance();
        int interval = prop.getInt(Settings.INTERVAL, Settings.INTERVAL_SPINNER_DEFAULT);
        int timeUnit = prop.getInt(Settings.TIME_UNIT, Settings.TIME_UNIT_DEFAULT);
        if (interval == 0) {
            return;
        }
        if (executor != null) {
            stop();
        }
        BackgroundTask task = new BackgroundTask();
        executor = new ScheduledThreadPoolExecutor(1, new MyThreadFactory("RandomBackgroundTask"));
        try {
            int delay = prop.isValueSet(IdeBackgroundUtil.EDITOR_PROP) ? interval : 0;
            TimeUnit timeUnitEnum;
            switch (timeUnit) {
                case 0:
                    timeUnitEnum = TimeUnit.SECONDS;
                    break;
                case 2:
                    timeUnitEnum = TimeUnit.HOURS;
                    break;
                case 3:
                    timeUnitEnum = TimeUnit.DAYS;
                    break;
                default:
                    timeUnitEnum = TimeUnit.MINUTES;
                    break;
            }
            executor.scheduleAtFixedRate(task, delay, interval, timeUnitEnum);
        } catch (RejectedExecutionException e) {
            stop();
        }
    }

    public static void stop() {
        if (executor != null && !executor.isTerminated()) {
            executor.shutdownNow();
        }
        executor = null;
    }

    public static void restart() {
        stop();
        start();
    }

    public static class MyThreadFactory implements ThreadFactory {

        private int counter;

        private final String name;

        MyThreadFactory(String name) {
            counter = 0;
            this.name = name;
        }

        @Override
        public Thread newThread(@NotNull Runnable run) {
            Thread t = new Thread(run, name + "-Thread-" + counter);
            counter++;
            return t;
        }

    }

}
