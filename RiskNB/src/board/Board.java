package board;

import java.util.ArrayList;

import player.*;

public class Board {

    public static final boolean USA = true;
    public static final boolean EGY = true;
    public static final int USA_SIZE = 49;   
    public static final int EGY_SIZE = 28;

    private static Board INSTANCE;
    // private static final boolean EGYPT = false;

    private Map map;
    private ArrayList<Player> players;

    private Board() {}

    public static Board getInstance() {
        if (Board.INSTANCE == null) {
            Board.INSTANCE = new Board();
            // Configure Board
        }
        return Board.INSTANCE;
    }

    public void configure(boolean terrain, ArrayList<Player> players) {
        // Sets the board data

        this.setMap(terrain);

        this.map.configureDistances();

        this.players = players;

    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }
    
    public Map getMap() {
        return this.map;
    }

    private void setMap(boolean terrain) {
        if (terrain == Board.USA) {
            this.buildStates();
        }
        this.buildEgypt();
    }

    private void buildStates() {

        this.map = new Map(this.USA_SIZE);

        map.addNeighbour(1, 2);
        map.addNeighbour(1, 5);

        map.addNeighbour(2, 1);
        map.addNeighbour(2, 3);
        map.addNeighbour(2, 4);
        map.addNeighbour(2, 5);

        map.addNeighbour(3, 2);
        map.addNeighbour(3, 4);
        map.addNeighbour(3, 9);

        map.addNeighbour(4, 2);
        map.addNeighbour(4, 3);
        map.addNeighbour(4, 5);
        map.addNeighbour(4, 8);
        map.addNeighbour(4, 9);

        map.addNeighbour(5, 1);
        map.addNeighbour(5, 2);
        map.addNeighbour(5, 4);
        map.addNeighbour(5, 6);
        map.addNeighbour(5, 7);
        map.addNeighbour(5, 8);

        map.addNeighbour(6, 5);
        map.addNeighbour(6, 7);
        map.addNeighbour(6, 16);
        map.addNeighbour(6, 17);

        map.addNeighbour(7, 5);
        map.addNeighbour(7, 6);
        map.addNeighbour(7, 8);
        map.addNeighbour(7, 10);
        map.addNeighbour(7, 15);
        map.addNeighbour(7, 16);

        map.addNeighbour(8, 4);
        map.addNeighbour(8, 5);
        map.addNeighbour(8, 7);
        map.addNeighbour(8, 9);
        map.addNeighbour(8, 10);

        map.addNeighbour(9, 3);
        map.addNeighbour(9, 4);
        map.addNeighbour(9, 8);
        map.addNeighbour(9, 11);

        map.addNeighbour(10, 7);
        map.addNeighbour(10, 8);
        map.addNeighbour(10, 11);
        map.addNeighbour(10, 13);
        map.addNeighbour(10, 14);
        map.addNeighbour(10, 15);

        map.addNeighbour(11, 9);
        map.addNeighbour(11, 10);
        map.addNeighbour(11, 12);
        map.addNeighbour(11, 13);

        map.addNeighbour(12, 11);
        map.addNeighbour(12, 13);
        map.addNeighbour(12, 21);
        map.addNeighbour(12, 22);

        map.addNeighbour(13, 10);
        map.addNeighbour(13, 11);
        map.addNeighbour(13, 12);
        map.addNeighbour(13, 14);
        map.addNeighbour(13, 20);
        map.addNeighbour(13, 21);

        map.addNeighbour(14, 10);
        map.addNeighbour(14, 13);
        map.addNeighbour(14, 15);
        map.addNeighbour(14, 20);

        map.addNeighbour(15, 7);
        map.addNeighbour(15, 10);
        map.addNeighbour(15, 14);
        map.addNeighbour(15, 16);
        map.addNeighbour(15, 19);
        map.addNeighbour(15, 20);

        map.addNeighbour(16, 6);
        map.addNeighbour(16, 7);
        map.addNeighbour(16, 15);
        map.addNeighbour(16, 17);
        map.addNeighbour(16, 18);
        map.addNeighbour(16, 19);

        map.addNeighbour(17, 6);
        map.addNeighbour(17, 16);
        map.addNeighbour(17, 18);

        map.addNeighbour(18, 16);
        map.addNeighbour(18, 17);
        map.addNeighbour(18, 19);
        map.addNeighbour(18, 34);

        map.addNeighbour(19, 15);
        map.addNeighbour(19, 16);
        map.addNeighbour(19, 18);
        map.addNeighbour(19, 20);
        map.addNeighbour(19, 33);
        map.addNeighbour(19, 34);

        map.addNeighbour(20, 13);
        map.addNeighbour(20, 14);
        map.addNeighbour(20, 15);
        map.addNeighbour(20, 19);
        map.addNeighbour(20, 21);
        map.addNeighbour(20, 31);
        map.addNeighbour(20, 32);
        map.addNeighbour(20, 33);

        map.addNeighbour(21, 12);
        map.addNeighbour(21, 13);
        map.addNeighbour(21, 20);
        map.addNeighbour(21, 22);
        map.addNeighbour(21, 23);
        map.addNeighbour(21, 31);

        map.addNeighbour(22, 12);
        map.addNeighbour(22, 21);
        map.addNeighbour(22, 23);

        map.addNeighbour(23, 21);
        map.addNeighbour(23, 22);
        map.addNeighbour(23, 24);
        map.addNeighbour(23, 31);

        map.addNeighbour(24, 23);
        map.addNeighbour(24, 25);
        map.addNeighbour(24, 26);
        map.addNeighbour(24, 31);

        map.addNeighbour(25, 24);
        map.addNeighbour(25, 26);

        map.addNeighbour(26, 24);
        map.addNeighbour(26, 25);
        map.addNeighbour(26, 27);
        map.addNeighbour(26, 28);
        map.addNeighbour(26, 31);

        map.addNeighbour(27, 26);
        map.addNeighbour(27, 28);

        map.addNeighbour(28, 26);
        map.addNeighbour(28, 27);
        map.addNeighbour(28, 29);
        map.addNeighbour(28, 31);

        map.addNeighbour(29, 28);
        map.addNeighbour(29, 30);
        map.addNeighbour(29, 31);
        map.addNeighbour(29, 32);
        map.addNeighbour(29, 48);

        map.addNeighbour(30, 29);
        map.addNeighbour(30, 32);
        map.addNeighbour(30, 37);
        map.addNeighbour(30, 38);
        map.addNeighbour(30, 48);

        map.addNeighbour(31, 20);
        map.addNeighbour(31, 21);
        map.addNeighbour(31, 23);
        map.addNeighbour(31, 24);
        map.addNeighbour(31, 26);
        map.addNeighbour(31, 28);
        map.addNeighbour(31, 29);
        map.addNeighbour(31, 32);

        map.addNeighbour(32, 20);
        map.addNeighbour(32, 29);
        map.addNeighbour(32, 30);
        map.addNeighbour(32, 31);
        map.addNeighbour(32, 33);
        map.addNeighbour(32, 35);
        map.addNeighbour(32, 37);

        map.addNeighbour(33, 19);
        map.addNeighbour(33, 20);
        map.addNeighbour(33, 32);
        map.addNeighbour(33, 34);
        map.addNeighbour(33, 35);

        map.addNeighbour(34, 18);
        map.addNeighbour(34, 19);
        map.addNeighbour(34, 33);
        map.addNeighbour(30, 36);

        map.addNeighbour(35, 32);
        map.addNeighbour(35, 33);
        map.addNeighbour(35, 36);
        map.addNeighbour(35, 37);

        map.addNeighbour(36, 34);
        map.addNeighbour(36, 35);
        map.addNeighbour(36, 37);

        map.addNeighbour(37, 30);
        map.addNeighbour(37, 32);
        map.addNeighbour(37, 35);
        map.addNeighbour(37, 36);
        map.addNeighbour(37, 38);

        map.addNeighbour(38, 30);
        map.addNeighbour(38, 37);
        map.addNeighbour(38, 39);
        map.addNeighbour(38, 46);
        map.addNeighbour(38, 47);
        map.addNeighbour(38, 48);

        map.addNeighbour(39, 38);
        map.addNeighbour(39, 40);
        map.addNeighbour(39, 43);
        map.addNeighbour(39, 45);
        map.addNeighbour(39, 46);

        map.addNeighbour(40, 39);
        map.addNeighbour(40, 41);
        map.addNeighbour(40, 43);

        map.addNeighbour(41, 40);
        map.addNeighbour(41, 42);
        map.addNeighbour(41, 43);

        map.addNeighbour(42, 41);

        map.addNeighbour(43, 39);
        map.addNeighbour(43, 40);
        map.addNeighbour(43, 41);
        map.addNeighbour(43, 44);
        map.addNeighbour(43, 45);

        map.addNeighbour(44, 43);
        map.addNeighbour(44, 45);

        map.addNeighbour(45, 39);
        map.addNeighbour(45, 43);
        map.addNeighbour(45, 44);

        map.addNeighbour(46, 38);
        map.addNeighbour(46, 39);
        map.addNeighbour(46, 47);

        map.addNeighbour(47, 38);
        map.addNeighbour(47, 46);
        map.addNeighbour(47, 48);

        map.addNeighbour(48, 29);
        map.addNeighbour(48, 30);
        map.addNeighbour(48, 38);
        map.addNeighbour(48, 47);

    }

    private void buildEgypt() {
        //this.map = new Map();
    }

}
