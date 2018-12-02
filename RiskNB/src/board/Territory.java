package board;

import java.util.Objects;
import player.Player;

public class Territory implements Comparable<Object> {
	
	private final int id;
	private int troops;
	private Player player;
	private int BST;
	private double BSR;
	private double reBSR;
	private double NBSR;
	
	public Territory(int id) {
		this.id = id;
		this.troops = 0;
	}
	
	public void setTroops(int troops) {
		this.troops = troops;
	}
	
	public void addTroops(int troops) {
		this.troops += troops;
	}
	
	public int getTroops() {
		return this.troops;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public int getID() {
		return this.id;
	}
	
	public void setBSTBSRreBSR(int BST, int hostileNeighbours) {
		this.BST = BST;
		this.BSR = (double) BST / this.troops;
		if(this.BSR !=0) {
			this.reBSR = this.BSR / hostileNeighbours;
		}
		else {
			this.reBSR = 0;
		}
	}
	
	public int getBST() {
		return this.BST;
	}
	
	public double getBSR() {
		return this.BSR;
	}
	
	public Double getReBSR() {
		return this.reBSR;
	}
	
	public void setNBSR(double NBSR) {
		this.NBSR = NBSR;
	}
	
	public double getNBSR() {
		return this.NBSR;
	}
		
    @Override
    public boolean equals(Object t) {
        if (t.getClass() != Territory.class) {
            return false;
        }
        
        boolean sameID = this.id == ((Territory) t).id;
        boolean sameHolder = this.player == ((Territory) t).player;
        boolean sameTroops = this.troops ==  ((Territory) t).troops;
        
        return (sameID && sameHolder && sameTroops);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.id;
        hash = 11 * hash + this.troops;
        hash = 11 * hash + Objects.hashCode(this.player);
        return hash;
    }

    @Override
    public int compareTo(Object o) {
        if (this.troops == ((Territory) o).getTroops()) {
            return 0;
        } else if (this.troops > ((Territory) o).getTroops()) {
            return 1;
        } else {
            return -1;
        }
    }
    
    @Override
    public Territory clone() {
    	Territory newTerritory = new Territory(this.id);
    	newTerritory.BSR = this.BSR;
    	newTerritory.BST = this.BST;
    	newTerritory.NBSR = this.NBSR;
    	newTerritory.player = this.player;
    	newTerritory.reBSR = this.reBSR;
    	newTerritory.troops = this.troops;
    	
    	return newTerritory;
    	
    }

    @Override
    public String toString() {
        return "Territory{" + "id=" + id + ", troops=" + troops + ", player=" + player + '}';
    }

}
