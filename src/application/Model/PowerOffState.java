package application.Model;

import application.view.CommandLine;

public class PowerOffState implements State {
    private CommandLine commandLine;
    public PowerOffState(CommandLine commandLine) {
        this.commandLine = commandLine;
    }

    @Override
    public void CHANGE() {
        System.out.println("TSAG UNTARSAN BN");
    }

    @Override
    public void UP() {
        System.out.println("TSAG UNTARSAN BN");
    }

    @Override
    public void POWER() {
        commandLine.setState(commandLine.getReadyState());
        System.out.println("TSAG ASLAA");
    }

    @Override
    public void DOWN() {
        System.out.println("TSAG UNTARSAN BN");
    }

    @Override
    public void START() {
        System.out.println("TSAG UNTARSAN BN");
    }
}
