package board;

import java.util.ArrayList;
import java.awt.Point;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import player.Player;
import player.AStarPlayer;
import player.MinimaxPlayer;

public class State implements Comparable<Object> {

    private final State parent;
    private final Player player;
    private final Map map;
    private final int heuristic; // We haven't yet decided the heuristic (WE HAVE !!.... or did we (0_0)
    private final int newTroops;
    private final ArrayList<Point> deployments;
    private final ArrayList<Territory> myTerritories;
    private final ArrayList<State> children;
    private final ArrayList<Move> moves;

    private final Move transitionMove; // Points to set of moves to reach this state, for root = null
    private final int depth;

    public ArrayList<Territory> getMyTerritories() {
        return myTerritories;
    }

    @Override
    public String toString() {
        return "State{" + "heuristic=" + heuristic + "depth=" + depth + '}';
    }

    // Constructor
    public State(State parentState, Player player, Map mapClone, Move transitionMove, int depth) {
        this.parent = parentState;
        this.player = player;
        this.map = mapClone;
        this.transitionMove = transitionMove;
        this.deployments = new ArrayList<>();
        this.myTerritories = new ArrayList<>();
        this.depth = depth;

        this.children = new ArrayList<>();
        this.moves = new ArrayList<>();

        Territory[] territories = this.map.getTerritories();
        for (int i = 1; i < territories.length; i++) {
            if (this.player.equals(territories[i].getPlayer())) {
                myTerritories.add(territories[i]);
            }
        }

        this.newTroops = Integer.max(myTerritories.size() / 3, 3);

        this.heuristic = this.calculateHeuristic4();

    }

    // Children
    public ArrayList<State> generateChildren() {

        this.calculateTerritoryFeautures(null);
        this.deployTroops2();

        // Features change after deployments so need to be recalculated
        // In an intermediate map
        Map intermediateMap = this.map.clone();
        // Commit deployments to intermediate map
        for (Point deployment : this.deployments) {
            intermediateMap.getTerritory(deployment.y).addTroops(deployment.x);
        }

        this.calculateTerritoryFeautures(intermediateMap);
        this.setAttackCombinations(intermediateMap);

        for (Move move : moves) {
            Map mapClone = intermediateMap.clone();
            for (Point attackPair : move.getAttackSequence()) {
                // I only take attacks with P(Success > 0.6) so I always expect to win   
                Territory attacker = mapClone.getTerritory(attackPair.x);
                Territory conquered = mapClone.getTerritory(attackPair.y);

                int myRemainingTroops = (int) Math.ceil(Expectations.getMyTroops(attacker.getTroops(), conquered.getTroops()));

                attacker.setTroops(1);
                conquered.setTroops(myRemainingTroops);
                conquered.setPlayer(this.player);
            }
            this.children.add(new State(this, this.player, mapClone, move, this.depth + 1));
        }

        return this.children;
    }

    // Move deciders Setters
    public boolean goalTest() {
        return myTerritories.size() == (this.map.getTerritories().length - 1);
    }

    private void deployTroops() {
        //// SAME DEPLOYMENT STRATEGY FOR ALL AI PLAYERS

        myTerritories.sort((Territory t1, Territory t2) -> {
            if (t1.getNBSR() == t2.getNBSR()) {
                return 0;
            } else if (t1.getNBSR() < t2.getNBSR()) {
                return 1;
            } else {
                return -1;
            }
        });

        int remainingTroops = this.newTroops;

        for (Territory t : myTerritories) {
            Point deployment = new Point();
            deployment.x = Math.min(remainingTroops, (int) Math.ceil(t.getNBSR() * this.newTroops));
            deployment.y = t.getID();

            // This should be added when simulating the moves
            t.addTroops(deployment.x);
            remainingTroops -= deployment.x;

            this.deployments.add(deployment);

            if (remainingTroops == 0) {
                break;
            }
        }
    }

    private void deployTroops2() {
        myTerritories.sort((Territory t1, Territory t2) -> {
            if (t1.getBSR() == t2.getBSR()) {
                return 0;
            } else if (t1.getBSR() > t2.getBSR()) {
                return 1;
            } else {
                return -1;
            }
        });

        ArrayList<Territory> filtered = new ArrayList<>();
        myTerritories.stream().filter((t) -> (t.getBSR() > 1)).forEachOrdered((t) -> {
            filtered.add(t);
        });
        ArrayList<Territory> chosen = new ArrayList<>();
        chosen.add(filtered.get(0));
        filtered.remove(0);

        boolean picked = false;
        for (int i = 0; i < filtered.size(); i++) {
            if (map.getDistance(filtered.get(i).getID(), chosen.get(0).getID()) > 2) {
                chosen.add(filtered.get(i));
                filtered.remove(i);
                picked = true;
                break;
            }
        }
        if (picked) {
            for (int i = 0; i < filtered.size(); i++) {
                if (map.getDistance(filtered.get(i).getID(), chosen.get(0).getID()) > 2 && map.getDistance(filtered.get(i).getID(), chosen.get(1).getID()) > 2) {
                    chosen.add(filtered.get(i));
                    break;
                }
            }
        }

        switch (chosen.size()) {
            case 1:
                Point deployment = new Point();
                deployment.x = this.newTroops;
                deployment.y = chosen.get(0).getID();
                this.deployments.add(deployment);
                //System.out.printf("Deployed %d in Territory #%d \n", this.newTroops, chosen.get(0).getID());
                break;
            case 2: {
                Point deployment1 = new Point();
                deployment1.x = this.newTroops / 2;
                deployment1.y = chosen.get(0).getID();
                this.deployments.add(deployment1);
                //System.out.printf("Deployed %d in Territory #%d \n", this.newTroops / 2, chosen.get(0).getID());
                Point deployment2 = new Point();
                deployment2.x = this.newTroops - this.newTroops / 2;
                deployment2.y = chosen.get(1).getID();
                this.deployments.add(deployment2);
                //System.out.printf("Deployed %d in Territory #%d \n", this.newTroops - this.newTroops / 2, chosen.get(1).getID());
                break;
            }
            default: {
                Point deployment1 = new Point();
                deployment1.x = this.newTroops / 2;
                deployment1.y = chosen.get(0).getID();
                this.deployments.add(deployment1);
                //System.out.printf("Deployed %d in Territory #%d \n", this.newTroops / 2, chosen.get(0).getID());
                Point deployment2 = new Point();
                deployment2.x = this.newTroops / 3;
                deployment2.y = chosen.get(1).getID();
                this.deployments.add(deployment2);
                //System.out.printf("Deployed %d in Territory #%d \n", this.newTroops / 3, chosen.get(1).getID());
                Point deployment3 = new Point();
                deployment3.x = this.newTroops - this.newTroops / 2 - this.newTroops / 3;
                deployment3.y = chosen.get(2).getID();
                this.deployments.add(deployment3);
                //System.out.printf("Deployed %d in Territory #%d \n", this.newTroops - this.newTroops / 2 - this.newTroops / 3, chosen.get(2).getID());
                break;
            }
        }

    }

    private void calculateTerritoryFeautures(Map map) {

        Map calculateIn = map == null ? this.map : map;

        Territory enemy;
        int sigmaBSR = 0;
        for (Territory t1 : calculateIn.getTerritories()) {
            // if (t1.getPlayer().equals(this.player)) {} // Calculate for all territories or just me ?
            int hostileNeighbours = 0;
            for (Integer t2 : calculateIn.getNeighbours(t1.getID())) {
                enemy = calculateIn.getTerritory(t2);
                if (!t1.getPlayer().equals(enemy.getPlayer())) {
                    hostileNeighbours++;
                    t1.setBSTBSRreBSR(t1.getBST() + enemy.getTroops(), hostileNeighbours);
                    sigmaBSR += enemy.getBSR();
                }
            }
        }
        for (Territory t1 : calculateIn.getTerritories()) {
            t1.setNBSR(t1.getBSR() / sigmaBSR);
        }
    }

    private double calculateHeuristic() {
        int max = 0;
        int[] minDistance = new int[49];
        for (int i = 1; i < 49; i++) {
            minDistance[i] = Integer.MAX_VALUE;
        }

        for (int i = 1; i < map.getTerritories().length; i++) {
            if (!map.getTerritories()[i].getPlayer().equals(this.player)) {
                for (int j = 1; j < map.getTerritories().length; j++) {
                    if (map.getTerritories()[j].getPlayer().equals(this.player)) {
                        minDistance[i] = Math.min(minDistance[i], map.getDistance(i, j));
                    }
                }
            }
        }
        double temp = 0;
        for (int i = 1; i < 49; i++) {
            if (minDistance[i] != Integer.MAX_VALUE) {
                temp = Math.max(this.heuristic, minDistance[i]);
            }
        }
        return temp;
    }

    private double calculateHeuristic2() {
        Territory[] territories = this.map.getTerritories();

        int enemyTroops = 0; // Feature 1 (neumerator)
        int totalTroops = 0; // Feature 1 (denominator)

        int hinterlandEnemyTerritories = 0; // Feature 2 (neumerator)
        int fortifiedEnemyTerritories = 0; // Feature 3 (neumerator)
        int totalEnemyTerritories = 0; // Features 2 (denominator), 3 (denominator), 4 (neumerator)

        int totalTerritories = 0; // Feature 4 (denominator)

        for (int i = 1; i < territories.length; i++) {
            totalTroops += territories[i].getTroops();
            totalTerritories += 1;
            if (!this.player.equals(territories[i].getPlayer())) {
                enemyTroops += territories[i].getTroops();
                totalEnemyTerritories += 1;
                if (territories[i].getTroops() > 1) {
                    fortifiedEnemyTerritories += 1;
                }
                if (territories[i].getBST() == 0) {
                    hinterlandEnemyTerritories += 1;
                }
            }
        }

        double armiesFeature = (double) enemyTroops / totalTroops;
        double hinterlandFeature = totalEnemyTerritories == 0 ? 0 : (double) hinterlandEnemyTerritories / totalEnemyTerritories;
        double multipleArmiesFeature = totalEnemyTerritories == 0 ? 0 : (double) fortifiedEnemyTerritories / totalEnemyTerritories;
        double unoccupiedTerritoryFeature = (double) totalEnemyTerritories / totalTerritories;

        return 2 * armiesFeature + 3 * hinterlandFeature + 5 * multipleArmiesFeature + 7 * unoccupiedTerritoryFeature;
    }

    private double calculateHeuristic3() {
        Territory[] territories = this.map.getTerritories();
        double troops = 0;
        for (int i = 1; i < territories.length; i++) {
            if (this.player.equals(territories[i].getPlayer())) {
                troops += territories[i].getTroops();
            }
        }
        return troops;
    }

    private int calculateHeuristic4() {
        return this.map.getTerritories().length - myTerritories.size() - 1;
    }

    private void setAttackCombinations(Map map) {

        ArrayList<Territory> reInforcedTerritories = new ArrayList<>();

        Territory[] territories = map.getTerritories();
        for (int i = 1; i < territories.length; i++) {
            if (this.player.equals(territories[i].getPlayer())) {
                reInforcedTerritories.add(territories[i]);
            }
        }

        ArrayList<Point> attackPairs = new ArrayList<>();
        for (Territory reInforcedTerritory : reInforcedTerritories) {
            if (reInforcedTerritory.getTroops() > 1 && reInforcedTerritory.getBSR() > 0) {
                ArrayList<Integer> neighbourIDs = map.getNeighbours(reInforcedTerritory.getID());
                for (Integer neighbourID : neighbourIDs) {
                    if (!this.player.equals(map.getTerritory(neighbourID).getPlayer())) {
                        double x = Expectations.getProbability(reInforcedTerritory.getTroops(), map.getTerritory(neighbourID).getTroops());
                        //System.out.print("probability:" + x + "   ");
                        Point a = new Point(reInforcedTerritory.getID(), neighbourID);
                        //System.out.println(a);
                        if (x > 0.6) {
                            attackPairs.add(a);
                            //System.out.println("Added");
                        }

                    }
                }
            }
        }
        int size = attackPairs.size();

        loop:
        for (int i = 0; i < (1 << size); i++) {
            Move move = new Move();
            move.setDeployments(deployments);

            Set<Integer> attackingTerritoryIDs = new HashSet<>();
            Set<Integer> defendingTerritoryIDs = new HashSet<>();

            for (int j = 0; j < size; j++) {
                if ((i & (1 << j)) > 0) {
                    if (attackingTerritoryIDs.contains(attackPairs.get(j).x) || defendingTerritoryIDs.contains(attackPairs.get(j).y)) {
                        continue loop;
                    }
                    attackingTerritoryIDs.add(attackPairs.get(j).x);
                    defendingTerritoryIDs.add(attackPairs.get(j).y);
                    move.addAttack(attackPairs.get(j));
                }
            }
            //boolean discarded = false;
            /*System.out.print("Sequence # " + (i + 1) + "  ");
            move.getAttackSequence().forEach((attack) -> {
                System.out.print(attack + "  ");
            });
            System.out.println();
           */
            moves.add(move);
        }
        if (this.player instanceof AStarPlayer || this.player instanceof MinimaxPlayer) {
            moves.sort((Object o1, Object o2) -> ((Move)o1).getAttackSequence().size() - ((Move)o2).getAttackSequence().size());
            int sizeLimit = (int)Math.ceil(moves.size()/3);
            while (moves.size() > sizeLimit){
                moves.remove(0);
            }
        }
        //System.out.println("Moves size inside the state: " + moves.size());
    }

    // GETTERS
    public int getHeuristic() {
        return this.heuristic;
    }

    public double getCost() {
        return this.depth + this.heuristic;
    }

    public Move getTransitionMove() {
        return this.transitionMove;
    }

    public int getNewTroops() {
        return this.newTroops;
    }

    public Map getMap() {
        return this.map;
    }

    public State getParent() {
        return this.parent;
    }

    public int getDepth() {
        return this.depth;
    }

    // Methods for State comparison
    @Override
    public boolean equals(Object otherState) {
        if (otherState.getClass() != State.class) {
            return false;
        }
        Territory[] territories1 = this.map.getTerritories();
        Territory[] territories2 = ((State) otherState).getMap().getTerritories();
        for (int i = 1; i < territories1.length; i++) {
            if (/* territories1[i].getTroops() != territories2[i].getTroops() || */territories1[i].getPlayer().getID() != territories2[i].getPlayer().getID()) {
                return false;
            }
        }
        return true;
    }

    // Open for review
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.map);
        return hash;
    }

    @Override
    public int compareTo(Object o) {
        if (this.heuristic == ((State) o).heuristic) {
            return 0;
        } else if (this.heuristic > ((State) o).heuristic) {
            return 1;
        } else {
            return -1;
        }
    }

}
