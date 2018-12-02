package player;

import board.Map;
import board.Move;
import board.State;

public abstract class AbstractAIPlayer extends Player{

	public AbstractAIPlayer(int id, Map map) {
		super(id, map);
		// TODO Auto-generated constructor stub
	}

	//// For AI players ////
	// Same deployment strategy: Minimize average BSR.
	// Attacking Strategy: 
	//   Maximize number of territories 
	//   Minimize average BSR (Do not overextend)
	//   Minimize number of bordering enemyStates
	
        @Override
	public Move takeTurn() {
		
		super.takeTurn(); // SETS MAPSTATE		
		
		return this.moves;
	}

	protected abstract void setNextMove();


	
}
