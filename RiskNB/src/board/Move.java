package board;

import java.awt.Point;
import java.util.ArrayList;

public class Move {

    private ArrayList<Point> deployments;
    // deployments.x = numTroops.
    // deployments.y = territory ID
    // Read like: Assign deployments.x troops to territory having deployments.y ID
    private ArrayList<Point> attackSequence;
    // attackSequence.x = defending Territory ID
    // attackSequence.y = attacking Territory ID
    // Read like: Attack territory having ID=attackSequence.x from Territory having ID=attackSequence.y 

    public Move() {
        this.deployments = new ArrayList<>();
        this.attackSequence = new ArrayList<>();
    }

    public Move(ArrayList<Point> deployments, ArrayList<Point> attackSequence) {
        this.deployments = deployments;
        this.attackSequence = attackSequence;
    }

    public void addDeployment(Point deployment) {
        this.deployments.add(deployment);
    }

    public void setDeployments(ArrayList<Point> deployments) {
        this.deployments = deployments;
    }

    public ArrayList<Point> getDeployments() {
        return this.deployments;
    }

    public void addAttack(Point attack) {
        this.attackSequence.add(attack);
    }

    public void setAttackSequence(ArrayList<Point> attackSequence) {
        this.attackSequence = attackSequence;
    }

    public ArrayList<Point> getAttackSequence() {
        return this.attackSequence;
    }

    @Override
    public String toString() {
        return "Move{" + "attackSequence=" + attackSequence.size() + '}';
    }

}
