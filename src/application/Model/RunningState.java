package application.Model;

import application.controller.TimeTask;
import application.view.CommandLine;

import java.util.Timer;

public class RunningState implements  State{
    private CommandLine commandLine;
    private static Timer timer;

    public RunningState(CommandLine commandLine) {
        this.commandLine = commandLine;
    }

    @Override
    public void CHANGE() {
        System.out.println("!! TSAG AJILJ BAINA");
        commandLine.setState(commandLine.getReadyState());
    }

    @Override
    public void UP() {
        System.out.println("!! TSAG AJILJ BAINA");
    }

    @Override
    public void POWER() {
        System.out.println("TSAG UNTARLAA");
        commandLine.setState(commandLine.getPowerOffState());
    }

    @Override
    public void DOWN() {
        System.out.println("!! TSAG AJILJ BAINA");
    }

    @Override
    public void START() {
        System.out.println("TSAG ZOGLOO");
        commandLine.setState(commandLine.getReadyState());
        timer.cancel();
        TimeTask.setPause(true);
    }

    public static void setTimer(Timer time) {
        timer = time;
    }
}
