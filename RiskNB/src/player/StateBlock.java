package player;

import board.*;

public class StateBlock {

    private State s;
    private double utility;

    public StateBlock(State s, double utility) {
        this.s = s;
        this.utility = utility;
    }

    public void setState(State s) {
        this.s = s;
    }

    public void setUtility(int utility) {
        this.utility = utility;
    }

    public State getState() {
        return this.s;
    }

    public double getUtility() {
        return this.utility;
    }
}
