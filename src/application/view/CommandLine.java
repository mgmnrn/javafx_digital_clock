package application.view;

import application.Model.*;
import application.controller.Battery;

import java.util.Scanner;

public class CommandLine {
    private State readyState;
    private State runningState;
    private State deadState;
    private State powerOffState;
    private State state;

    public CommandLine() {
        readyState = new ReadyState(this);
        runningState = new RunningState(this);
        powerOffState = new PowerOffState(this);
        deadState = new DeadState();
        state = readyState;

        Battery myBattery = new Battery(300, this);
        Thread batStart = new Thread(myBattery);
        batStart.start();

        Scanner scanner = new Scanner(System.in);
        System.out.println("CHANGE, UP, POWER, DOWN, START");
        while (true) {
            String state = scanner.nextLine().toUpperCase();
            switch (state) {
                case "CHANGE":
                    this.state.CHANGE();
                    break;
                case "UP":
                    this.state.UP();
                    break;
                case "POWER":
                    this.state.POWER();
                    break;
                case "DOWN":
                    this.state.DOWN();
                    break;
                default:
                    this.state.START();
                    break;
            }
        }
    }

    public void setState (State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public State getReadyState() {
        return readyState;
    }

    public State getRunningState() {
        return runningState;
    }

    public State getDeadState() {
        return deadState;
    }

    public State getPowerOffState() {
        return powerOffState;
    }
}
