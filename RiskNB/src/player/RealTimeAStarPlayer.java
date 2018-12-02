package player;

import board.Map;
import board.Move;
import board.State;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class RealTimeAStarPlayer extends AbstractAIPlayer {

    public static int MAX_DEPTH = 5;
    
    public RealTimeAStarPlayer(int id, Map map) {
        super(id, map);
    }

    
    
    @Override
    public Move takeTurn() {

        super.takeTurn(); // Sets mapState, freeTroops

        this.setNextMove();
        
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
        State bestStateYet = this.mapState;
        int search_Depth = 0;
        frontier.add(this.mapState);
        int x = 0;
        while (!frontier.isEmpty()) {
            //if ((x++ % 100) == 0) {
            //    System.out.println("Frontier Size: " + frontier.size());
            //}
            s = frontier.poll();
            explored.add(s);
            
            // System.out.println("MyTerritories: " + s.getMyTerritories().size());
            // System.out.println("Turn #: " + s.getDepth());
            
            search_Depth = Integer.max(s.getDepth(),search_Depth);
            
            if (s.goalTest()) {
                // System.out.println("Gaol State Depth: " + s.getDepth());
                // System.out.println("Next Move: ");
                this.moves = this.getTransitionMove(s);
                return;
            }
            
            if(s.getCost() < bestStateYet.getCost()){
                bestStateYet = s;
            }
            
            if (search_Depth > MAX_DEPTH){
                // System.out.println("Best State Found: " + bestStateYet.toString());
                this.moves = this.getTransitionMove(bestStateYet);
                break;
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
        // System.out.println("# of Expansions: " + explored.size());
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
