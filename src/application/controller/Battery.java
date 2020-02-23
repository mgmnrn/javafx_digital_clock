package application.controller;

import application.view.CommandLine;

public class Battery implements Runnable {
    private int capacity;
    private static int currentCapacity;
    private CommandLine commandLine;

    public Battery(int capacity, CommandLine commandLine) {
        this.commandLine = commandLine;
        this.capacity = capacity;
        currentCapacity = capacity;
    }

    public static synchronized boolean isAlive() {
        return currentCapacity > 0;
    }

    public synchronized int getCurrentCapacity() {
        return currentCapacity;
    }

    public synchronized String getBatteryPercent() {
        return Integer.toString((currentCapacity * 100) / capacity);
    }

    public synchronized String getBatteryIcon() {
        int percent = currentCapacity * 100 / capacity;
        if (percent > 80)
            return "BATTERY_4";
        else if (percent > 60)
            return "BATTERY_3";
        else if (percent > 40)
            return "BATTERY_2";
        else if (percent > 20)
            return "BATTERY_1";
        else return "BATTERY_0";
    }

    @Override
    public void run() {
        while (currentCapacity > 0) {
            try {
                Thread.sleep(1000);
                currentCapacity--;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (commandLine != null)
            commandLine.setState(commandLine.getDeadState());
        System.out.println("-> BATTERY IS LOW, POWER OFF");
    }
}
