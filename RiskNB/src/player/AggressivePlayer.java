package player;

import board.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

import board.Move;
import board.Territory;
import java.awt.Point;

public class AggressivePlayer extends Player {

    public AggressivePlayer(int id, Map map) {
        super(id, map);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Move takeTurn() {

        super.takeTurn();

        this.deployTroops(); // Decide deployment in moves pair

        this.attack();		// Decide attack in moves pair

        return this.moves;
    }

    protected void deployTroops() {

        int max = Integer.MIN_VALUE;
        Territory maxT = null;
        Territory[] territories = this.map.getTerritories();
        for (int i = 1; i < territories.length; i++) {
            if (territories[i].getPlayer().equals(this) && territories[i].getTroops() > max) {
                max = territories[i].getTroops();
                maxT = territories[i];
            }
        }
        /*
		maxT.setTroops(maxT.getTroops() + this.freeTroops);
		return;
         */
        Point deployment = new Point();
        deployment.x = this.mapState.getNewTroops();
        deployment.y = maxT.getID();
        ArrayList<Point> deployments = new ArrayList<>();
        deployments.add(deployment);
        this.moves = new Move(deployments, new ArrayList<Point>());

    }

    protected void attack() {

        // Sorting Enemy Territories Descendingly
        PriorityQueue<Territory> enemyTerritories = new PriorityQueue<>(Collections.reverseOrder()); // Max heap
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
