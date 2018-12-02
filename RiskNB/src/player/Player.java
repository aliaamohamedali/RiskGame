package player;

import board.Map;
import board.Move;
import board.State;
import java.util.ArrayList;
import java.util.Random;

public abstract class Player {

    protected int id;
    protected State mapState;
    protected Map map;

    protected Move moves;

    public Player(int id, Map map) {
        this.id = id;
        this.map = map;
    }

    public Integer chooseTerritory(ArrayList<Integer> availableTerritories) {
        if (!availableTerritories.isEmpty()) {
            Random random = new Random();
            int choice = random.nextInt(availableTerritories.size());
            return availableTerritories.get(choice);
        } else {
            ArrayList<Integer> myTerritories = new ArrayList<>();
            for (int i = 0; i < map.getTerritories().length; i++) {
                if (this.equals(map.getTerritory(i).getPlayer())) {
                    myTerritories.add(map.getTerritory(i).getID());
                }
            }

            Random random = new Random();
            int choice = random.nextInt(myTerritories.size());
            return myTerritories.get(choice);
        }
    }

    public Move takeTurn() {

        this.mapState = new State(null, this, map.clone(), null, 0);
        return this.moves;
    }

    public int getID() {
        return this.id;
    }
   
}
