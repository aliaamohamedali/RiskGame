package test;

import java.util.ArrayList;
import java.util.Scanner;
import board.*;
import board.Territory;
import player.*;

public class TestPassive {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    
    public static String[] colors = new String[7];

    public static void main(String args[]) {
/*
        Scanner s = new Scanner(System.in);
        
        colors[0] = ANSI_RESET;
        colors[1] = ANSI_GREEN;
        colors[2] = ANSI_RED;
        colors[3] = ANSI_BLUE;
        colors[4] = ANSI_YELLOW;
        colors[5] = ANSI_CYAN;
        colors[6] = ANSI_PURPLE;
        

        int[] playerModes = new int[3];
        System.out.print("Enter Player1 Type: ");
        playerModes[0] = s.nextInt();
        System.out.print("Enter Player2 Type: ");
        playerModes[1] = s.nextInt();
        System.out.print("Enter Player3 Type: ");
        playerModes[2] = s.nextInt();

        ArrayList<Player> players = new ArrayList<>();
        players.add(new PassivePlayer(0));
        players.add(new AggressivePlayer(1));
        players.add(new SemiPassivePlayer(2));
        
        Board game = Board.getInstance();
        game.configure(Board.USA, players);

        randomAssignment(game.getMap().getTerritories(), game.getPlayers());

        print(game.getMap().getTerritories(), game.getPlayers());

        for (int i = 0; i < 10; i++) {

            Move move = game.getPlayers().get(i % playerModes.length).takeTurn(new State(null, game.getPlayers().get(i % 2), game.getMap().clone(), null));
            System.out.printf("%s", (colors[i % playerModes.length+1] + "Deployed " + move.getDeployments().get(0)[0] + " troops to territory #" + move.getDeployments().get(0)[1] + colors[0] + "\n"));
            if (!move.getAttackSequence().isEmpty()) {
                System.out.println("Attacked Territory #" + move.getAttackSequence().get(0)[0] + " from Territory #" + move.getAttackSequence().get(0)[1]);
            }
            game.executeMoves(move);
            print(game.getMap().getTerritories(), game.getPlayers());
        }*/

    }

    public static void print(Territory[] territories, ArrayList<Player> p) {
        for (Territory t : territories) {
            // (t.getPlayer().getID() == 0 ? "\u001B[31m" : "\u001B[34m") + + "\u001B[0m"
            System.out.printf("%-30s",(colors[t.getPlayer().getID()+1] + "#" + t.getID() + ": " + t.getTroops() + " Player: " + t.getPlayer().getID() + colors[0]));
        }
        System.out.println();
        /*for(AbstractPlayer P : p) {
			System.out.println("#" + P.getID()+ ": " + P.getClass());
		}*/

    }

    public static Territory[] randomAssignment(Territory[] T, ArrayList<Player> players) {
        for (int i = 0; i < T.length; i++) {
            T[i].setPlayer(players.get((int) (Math.random() * 3 + 1) - 1));
            T[i].setTroops((int) (Math.random() * 10 + 1));
        }
        return T;

    }

}
