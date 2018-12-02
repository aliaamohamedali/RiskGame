package player;

import board.Map;
import board.Move;
import board.State;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class AStarPlayer extends AbstractAIPlayer {

    public AStarPlayer(int id, Map map) {
        super(id, map);

    }

    @Override
    public Move takeTurn() {

        super.takeTurn(); // Sets mapState, freeTroops

        this.setNextMove();

        /*moves.getDeployments().forEach((deployment) -> {
            System.out.println(deployment);
        });
        moves.getAttackSequence().forEach((attack) -> {
            System.out.println(attack);
        });*/
        return this.moves;
    }

    @Override
    protected void setNextMove() {
        Set<State> explored = new HashSet<>();
        PriorityQueue<State> frontier = new PriorityQueue<>((Object o1, Object o2) -> {
            if (((State) o1).getCost() == ((State) o2).getCost()) {
                return ((State) o1).getHeuristic() - ((State) o2).getHeuristic(); // tie Breaker
            } else if (((State) o1).getCost() > ((State) o2).getCost()) {
                return 1;
            } else {
                return -1;
            }
        });
        
        
        
        State s;

        frontier.add(this.mapState);
        int x = 0;
        while (!frontier.isEmpty()) {
            // if ((x++ % 100) == 0)
            //    System.out.println("Frontier Size: " + frontier.size());
            s = frontier.poll();
            explored.add(s);
            
             System.out.println("MyTerritories: " + s.getMyTerritories().size());
             System.out.println("Turn #: "  + s.getDepth());

            if (s.goalTest()) {
                // System.out.println("Gaol State Depth: " + s.getDepth());
                // System.out.println("Next Move: ");
                this.moves = this.getTransitionMove(s);
                return;
            }
            //ArrayList<State> nextMoves = s.generateChildren();
            //System.out.println("Children Count: " + nextMoves.size());
            for (State child : s.generateChildren()) {
                if (!explored.contains(child) && !frontier.contains(child)) {
                    frontier.add(child);
                    //System.out.println("Added new unexplored Territory");
                } else if (frontier.contains(child)) {
                    frontier.remove(child);
                    frontier.add(child);
                    //System.out.println("Added improved Territory");
                }
            }
        }
        // System.out.println("searchEnded: " + frontier.size());
    }

    private Move getTransitionMove(State s) {
        State wantedState = s;
        while (wantedState.getParent().getParent() != null) {
            wantedState = wantedState.getParent();
        }
        return wantedState.getTransitionMove();
    }

}
