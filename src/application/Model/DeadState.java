package application.Model;

public class DeadState implements State {
    @Override
    public void CHANGE() {
        System.out.println("BATTERY DUUSAN BAINA");
    }

    @Override
    public void UP() {
        System.out.println("BATTERY DUUSAN BAINA");
    }

    @Override
    public void POWER() {
        System.out.println("BATTERY DUUSAN BAINA");
    }

    @Override
    public void DOWN() {
        System.out.println("BATTERY DUUSAN BAINA");
    }

    @Override
    public void START() {
        System.out.println("BATTERY DUUSAN BAINA");
    }
}
