package player;

import board.Map;
import java.util.PriorityQueue;

import board.Move;
import board.Territory;
import java.awt.Point;

public class SemiPassivePlayer extends PassivePlayer {

    public SemiPassivePlayer(int id, Map map) {
        super(id, map);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Move takeTurn() {

        super.takeTurn();

        this.deployTroops();

        this.attack();

        return this.moves;
    }

    @Override
    protected void deployTroops() {
        // Deploys Troops Like a Passive Player
        super.deployTroops();
    }

    protected void attack() {

        // Sorting Enemy Territories in Ascending order
        PriorityQueue<Territory> enemyTerritories = new PriorityQueue<>(); // Min heap 
        Territory[] territories = this.map.getTerritories();
        for (int i = 1; i < territories.length; i++) {
            if (!territories[i].getPlayer().equals(this)) {
                enemyTerritories.add(territories[i]);
            }
        }

        // Attack first eligible territory
        Territory enemyT;
        outerLoop:
        while (!enemyTerritories.isEmpty()) {
            enemyT = enemyTerritories.poll();
            for (int tID : this.mapState.getMap().getNeighbours(enemyT.getID())) {
                Territory neighbour = this.mapState.getMap().getTerritories()[tID];
                if (neighbour.getPlayer().equals(this) && neighbour.getTroops() > 1) {
                    Point attackPair = new Point();
                    attackPair.x = enemyT.getID();
                    attackPair.y = tID;
                    this.moves.addAttack(attackPair);
                    break outerLoop;
                }
            }
        }
    }
}
