package player;

import java.util.ArrayList;

import board.Map;
import board.Move;
import board.State;
import java.awt.Point;

public class GreedyAIPlayer extends AbstractAIPlayer {

    public GreedyAIPlayer(int id, Map map) {
        super(id, map);
        // TODO Auto-generated constructor stub
    }

    public Move takeTurn() {

        super.takeTurn(); // Sets mapState, freeTroops

        this.setNextMove();

        return this.moves;

    }

    @Override
    protected void setNextMove() {
        // TODO Auto-generated method stub
        ArrayList<State> children = this.mapState.generateChildren();
        System.out.println("branching Factor:" + children.size());

        State bestChildState = children.get(0);
        for (int i = 1; i < children.size(); i++) {
            ArrayList<Point> attackSequence = children.get(i).getTransitionMove().getAttackSequence();
            /*
            for (Point attack : attackSequence) {
                System.out.print(attack + " ");
            }
            System.out.println("  heuristic " + i + ">> " + children.get(i).getHeuristic());
             */
            if (children.get(i).getHeuristic() <= bestChildState.getHeuristic()) {
                bestChildState = children.get(i);
            }
        }

        this.moves = bestChildState.getTransitionMove();
    }

}
