package board;

import java.awt.Point;
import java.util.ArrayList;

public class Move {
	
	private ArrayList<Point> deployments;
	// x = numTroops.
	// deployments[1] = territory ID
	// Read like: Assign deployments[0] troops to territory having deployments[1] ID
	private ArrayList<Point> attackSequence;
	// attackSequence[0] = defending Territory ID
	// attackSequence[1] = attacking Territory ID
	// Read like: Attack territory having ID=attackSequence[0] from Territory having ID=attackSequence[1] 
	
	
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
	
	public ArrayList<Point> getAttackSequence(){
		return this.attackSequence;
	}

    @Override
    public String toString() {
        return "Move{" + "attackSequence=" + attackSequence.size() + '}';
    }
	
	
		
}
