package application.Model;

import application.controller.TimeTask;
import application.view.CommandLine;

import java.time.LocalTime;
import java.util.Timer;

public class ReadyState implements State {
    private CommandLine commandLine;
    private int hour;
    private int minute;
    private int second;
    private String string = "HOUR";
    private LocalTime localTime;

    public ReadyState(CommandLine commandLine) {
        this.commandLine = commandLine;
    }

    @Override
    public void START() {
        commandLine.setState(commandLine.getRunningState());
        localTime = LocalTime.of(hour, minute, second);
        TimeTask task = new TimeTask(localTime.toSecondOfDay());
        TimeTask.setPause(false);
        Timer timer = new Timer();
        RunningState.setTimer(timer);
        timer.schedule(task, 1000, 1000);
    }

    @Override
    public void UP() {
        if (string.equals("HOUR"))
            System.out.println(hour++);
        else if (string.equals("MINUTE"))
            System.out.println(minute++);
        else System.out.println(second++);
    }

    @Override
    public void POWER() {
        commandLine.setState(commandLine.getPowerOffState());
        System.out.println("TSAG UNTARLAA");
    }

    @Override
    public void DOWN() {
        if (string.equals("HOUR"))
            System.out.println(hour--);
        else if (string.equals("MINUTE"))
            System.out.println(minute--);
        else System.out.println(second--);
    }

    @Override
    public void CHANGE() {
        if (string.equals("HOUR")) {
            System.out.println(string + " IS BLINKING");
            string = "MINUTE";
        } else if (string.equals("MINUTE")) {
            System.out.println(string + " IS BLINKING");
            string = "SECOND";
        } else {
            System.out.println(string + " IS BLINKING");
            string = "HOUR";
        }
    }
}
