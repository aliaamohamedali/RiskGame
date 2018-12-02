package player;

import board.Map;
import board.Move;
import board.State;
import java.util.ArrayList;

public class MinimaxPlayer extends Player {

    public MinimaxPlayer(int id, Map map) {
        super(id, map);

    }

    @Override
    public Move takeTurn() {

        super.takeTurn(); // Sets mapState, freeTroops

        this.setNextMove();

        return this.moves;

    }

    private void setNextMove() {

        StateBlock wantedState = Maxmize(this.mapState, Integer.MIN_VALUE, Integer.MAX_VALUE);
        this.moves = wantedState.getState().getTransitionMove();
    }

    private StateBlock Maxmize(State s, double alpha, double beta) {
        if (s.goalTest()) {
            StateBlock state = new StateBlock(null, s.getHeuristic());
            return state;
        }
        StateBlock maxState = new StateBlock(null, Integer.MIN_VALUE);
        ArrayList<State> nextMoves = s.generateChildren();
        for (State st : nextMoves) {
            StateBlock newState = Minimize(st, alpha, beta);
            if (newState.getUtility() > maxState.getUtility()) {
                maxState = newState;
            }
            if (maxState.getUtility() >= beta) {
                break;
            }
            if (maxState.getUtility() >= alpha) {
                alpha = maxState.getUtility();
            }
        }

        return maxState;
    }

    private StateBlock Minimize(State s, double alpha, double beta) {
        if (s.goalTest()) {
            StateBlock state = new StateBlock(null, s.getHeuristic());
            return state;
        }
        StateBlock minState = new StateBlock(null, Integer.MAX_VALUE);
        ArrayList<State> nextMoves = s.generateChildren();
        for (State st : nextMoves) {
            StateBlock newState = Maxmize(st, alpha, beta);
            if (newState.getUtility() < minState.getUtility()) {
                minState = newState;
            }
            if (minState.getUtility() <= alpha) {
                break;
            }
            if (minState.getUtility() < beta) {
                beta = minState.getUtility();
            }
        }

        return minState;
    }

}
