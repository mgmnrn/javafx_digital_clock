package application.controller;

import java.util.TimerTask;

public class TimeTask extends TimerTask {
    private static boolean pause = false;
    private int totalSecs;
    private int hour;
    private int minute;
    private int second;

    public static void setPause(boolean ps) {
        pause = ps;
    }

    public TimeTask(int seconds) {
        this.totalSecs = seconds;
    }

    public synchronized int getHour() {
        return this.hour;
    }

    public synchronized int getMinute() {
        return this.minute;
    }

    public synchronized int getSecond() {
        return this.second;
    }

    @Override
    public void run() {
        if (!pause) {
            totalSecs++;
            hour = totalSecs / 3600;
            minute = (totalSecs % 3600) / 60;
            second = totalSecs % 60;
            System.out.println(hour + ":" + minute+ ":" +second);
        }
    }
}
