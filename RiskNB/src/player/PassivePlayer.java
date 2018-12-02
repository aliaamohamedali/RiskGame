package player;

import board.Map;
import java.util.ArrayList;

import board.Move;
import board.Territory;
import java.awt.Point;

public class PassivePlayer extends Player {

    public PassivePlayer(int id, Map map) {
        super(id, map);
    }

    @Override
    public Move takeTurn() {

        super.takeTurn();

        this.deployTroops(); // Decide next move

        return this.moves;
    }

    protected void deployTroops() {

        int min = Integer.MAX_VALUE;
        Territory minT = null;
        Territory[] territories = this.map.getTerritories();
        for (int i = 1; i < territories.length; i++) {
            if (territories[i].getPlayer().equals(this) && territories[i].getTroops() < min) {
                min = territories[i].getTroops();
                minT = territories[i];
            }
        }
        
        Point deployment = new Point();
        deployment.x = this.mapState.getNewTroops();
        deployment.y = minT.getID();
        ArrayList<Point> deployments = new ArrayList<>();
        deployments.add(deployment);
        this.moves = new Move(deployments, new ArrayList<Point>());

    }

}
