/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package svg;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.SVGPath;

import board.Board;
import board.Move;
import board.Territory;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import player.Player;
import player.HumanPlayer;

/**
 *
 * @author carnival
 */
public class FXMLDocumentController implements Initializable {

    // Buttons
    @FXML
    private Button attackBtn;
    @FXML
    private Button nextBtn;
    @FXML
    private Button addBtn;
    @FXML
    private Button subtractBtn;
    @FXML
    private Button playTurnBtn;

    // Labels
    @FXML
    private Label freeTroopsLabel;
    @FXML
    private Label playerTurnLabel;
    @FXML
    private Label phaseLabel;
    @FXML
    private Label attackerDie1;
    @FXML
    private Label attackerDie2;
    @FXML
    private Label attackerDie3;
    @FXML
    private Label defenderDie1;
    @FXML
    private Label defenderDie2;

    @FXML
    private Label troops1;
    @FXML
    private Label troops10;
    @FXML
    private Label troops11;
    @FXML
    private Label troops12;
    @FXML
    private Label troops13;
    @FXML
    private Label troops14;
    @FXML
    private Label troops15;
    @FXML
    private Label troops16;
    @FXML
    private Label troops17;
    @FXML
    private Label troops18;
    @FXML
    private Label troops19;
    @FXML
    private Label troops2;
    @FXML
    private Label troops20;
    @FXML
    private Label troops21;
    @FXML
    private Label troops22;
    @FXML
    private Label troops23;
    @FXML
    private Label troops24;
    @FXML
    private Label troops25;
    @FXML
    private Label troops26;
    @FXML
    private Label troops27;
    @FXML
    private Label troops28;
    @FXML
    private Label troops29;
    @FXML
    private Label troops3;
    @FXML
    private Label troops30;
    @FXML
    private Label troops31;
    @FXML
    private Label troops32;
    @FXML
    private Label troops33;
    @FXML
    private Label troops34;
    @FXML
    private Label troops35;
    @FXML
    private Label troops36;
    @FXML
    private Label troops37;
    @FXML
    private Label troops38;
    @FXML
    private Label troops39;
    @FXML
    private Label troops4;
    @FXML
    private Label troops40;
    @FXML
    private Label troops41;
    @FXML
    private Label troops42;
    @FXML
    private Label troops43;
    @FXML
    private Label troops44;
    @FXML
    private Label troops45;
    @FXML
    private Label troops46;
    @FXML
    private Label troops47;
    @FXML
    private Label troops48;
    @FXML
    private Label troops5;
    @FXML
    private Label troops6;
    @FXML
    private Label troops7;
    @FXML
    private Label troops8;
    @FXML
    private Label troops9;

    // SVGPaths
    @FXML
    private SVGPath alabama;
    @FXML
    private SVGPath arizona;
    @FXML
    private SVGPath arkansas;
    @FXML
    private SVGPath california;
    @FXML
    private SVGPath colorado;
    @FXML
    private SVGPath connecticut;
    @FXML
    private SVGPath delaware;
    @FXML
    private SVGPath florida;
    @FXML
    private SVGPath georgia;
    @FXML
    private SVGPath idaho;
    @FXML
    private SVGPath illinois;
    @FXML
    private SVGPath indiana;
    @FXML
    private SVGPath kansas;
    @FXML
    private SVGPath kentucky;
    @FXML
    private SVGPath louisiana;
    @FXML
    private SVGPath iowa;
    @FXML
    private SVGPath maine;
    @FXML
    private SVGPath maryLand;
    @FXML
    private SVGPath massachussetts;
    @FXML
    private SVGPath michigan;
    @FXML
    private SVGPath minnesota;
    @FXML
    private SVGPath mississippi;
    @FXML
    private SVGPath missouri;
    @FXML
    private SVGPath montana;
    @FXML
    private SVGPath nebraska;
    @FXML
    private SVGPath nevada;
    @FXML
    private SVGPath newHampshire;
    @FXML
    private SVGPath newJersey;
    @FXML
    private SVGPath newMexico;
    @FXML
    private SVGPath newYork;
    @FXML
    private SVGPath northCarolina;
    @FXML
    private SVGPath northDakota;
    @FXML
    private SVGPath ohio;
    @FXML
    private SVGPath oklahoma;
    @FXML
    private SVGPath oregon;
    @FXML
    private SVGPath pennsylvania;
    @FXML
    private SVGPath rhodeIsland;
    @FXML
    private SVGPath southCarolina;
    @FXML
    private SVGPath southDakota;
    @FXML
    private SVGPath tennessee;
    @FXML
    private SVGPath texas;
    @FXML
    private SVGPath utah;
    @FXML
    private SVGPath vermont;
    @FXML
    private SVGPath virginia;
    @FXML
    private SVGPath washington;
    @FXML
    private SVGPath westVirginia;
    @FXML
    private SVGPath wisconsin;
    @FXML
    private SVGPath wyoming;

    // Image Views
    @FXML
    private ImageView imgView1;
    @FXML
    private ImageView imgView2;
    @FXML
    private ImageView imgView3;
    @FXML
    private ImageView imgView4;
    @FXML
    private ImageView imgView5;
    @FXML
    private ImageView imgView6;

    // Variables
    private static final int DEFAULT = 0;
    private static final int DEPLOY = 1;
    private static final int ATTACK = 2;
    private static final int FORTIFY = 3;
    private static final boolean MINE = true;
    private static final boolean ADVERSARY = false;

    private static final String[] COLORS = {"#ff0000", "#00ff00", "#0000ff", "#ffff00", "#00ffff", "#ff00ff"};
    private final Board board = Board.getInstance();

    private SVGPath[] svgPaths; // new SVGPath[49];
    private Label[] troopCountLabel; // new Label[49];
    private Label[] attackerDiceLabel;
    private Label[] defenderDiceLabel;

    private int selectedMine;
    private int selectedAdversary;
    private int mode;
    private int currentPlayer;

    private int[] deployments;

    private ArrayList<Integer> availableTerritories;
    private int totalRemainingTroops;

    EventHandler<MouseEvent> play = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            availableTerritories = new ArrayList<>();
            for (int i = 1; i < board.getMap().getTerritories().length; i++) {
                availableTerritories.add(i);
            }

            int numOfTerriotries = availableTerritories.size();
            int numOfPlayers = board.getPlayers().size();
            int startingTroops = 2 * numOfTerriotries / numOfPlayers;
            totalRemainingTroops = startingTroops * numOfPlayers;

            System.out.println(numOfTerriotries + " " + numOfPlayers + " " + startingTroops + " " + totalRemainingTroops);

            phaseLabel.setText("Initial Deployment Phase");

            nextBtn.setText(">>>");
            nextBtn.removeEventFilter(MouseEvent.MOUSE_CLICKED, play);
            mode = DEFAULT;

            distributeNext();
            if (totalRemainingTroops <= 0) {
                currentPlayer = 0;
                startGame();
            }
        }
    };
    EventHandler<MouseEvent> confirmDeployment = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if (selectedMine != -1) {
                Player player = board.getPlayers().get(currentPlayer);
                Territory territory = board.getMap().getTerritory(selectedMine);

                territory.setPlayer(player);
                territory.addTroops(1);

                svgPaths[selectedMine].setFill(Paint.valueOf(COLORS[currentPlayer]));
                troopCountLabel[selectedMine].setText(String.valueOf(Integer.valueOf(troopCountLabel[selectedMine].getText()) + 1));

                availableTerritories.remove(Integer.valueOf(selectedMine));
                deselect(selectedMine, DEPLOY, MINE);

                Territory[] territories = board.getMap().getTerritories();
                ArrayList<Player> players = board.getPlayers();

                for (int i = 1; i < territories.length; i++) {
                    if (territories[i].getPlayer() == null || players.get(currentPlayer).equals(territories[i].getPlayer())) {
                        svgPaths[i].removeEventFilter(MouseEvent.MOUSE_CLICKED, selectMine);
                        svgPaths[i].removeEventFilter(MouseEvent.MOUSE_ENTERED, highlighter);
                        svgPaths[i].removeEventFilter(MouseEvent.MOUSE_EXITED, dehighlighter);
                    }
                }
                totalRemainingTroops--;
                currentPlayer = (currentPlayer + 1) % board.getPlayers().size();

            }
            // Commit fortifications to board
            // CONTROLLER.MOVETONEXTPLAYER();

            nextBtn.removeEventFilter(MouseEvent.MOUSE_CLICKED, confirmDeployment);
            distributeNext();
            if (totalRemainingTroops <= 0) {
                currentPlayer = 0;
                startGame();
            }
        }

    };

    EventHandler<MouseEvent> startAttacks = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {

            if (Integer.valueOf(freeTroopsLabel.getText()) > 0) {
                return;
            }

            phaseLabel.setText("Attacking Phase");

            if (selectedMine != -1) {
                deselect(selectedMine, mode, MINE);
            }
            addBtn.removeEventFilter(MouseEvent.MOUSE_CLICKED, addTroops);
            subtractBtn.removeEventFilter(MouseEvent.MOUSE_CLICKED, removeTroops);
            attackBtn.addEventFilter(MouseEvent.MOUSE_CLICKED, attackTerritory);
            freeTroopsLabel.setText("");

            // Commit deployments to board'
            Territory[] territories = board.getMap().getTerritories();
            for (int i = 1; i < territories.length; i++) {
                territories[i].addTroops(deployments[i]);
            }

            for (int i = 1; i < territories.length; i++) {
                if (board.getPlayers().get(currentPlayer).equals(territories[i].getPlayer()) && territories[i].getTroops() <= 1) {
                    svgPaths[i].removeEventFilter(MouseEvent.MOUSE_CLICKED, selectMine);
                    svgPaths[i].removeEventFilter(MouseEvent.MOUSE_ENTERED, highlighter);
                    svgPaths[i].removeEventFilter(MouseEvent.MOUSE_EXITED, dehighlighter);
                }
            }

            nextBtn.removeEventFilter(MouseEvent.MOUSE_CLICKED, startAttacks);
            nextBtn.addEventFilter(MouseEvent.MOUSE_CLICKED, startFortify);

            mode = ATTACK;
            // Allow strategy to enable Territories ?
        }
    };
    EventHandler<MouseEvent> startFortify = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if (selectedMine != -1) {
                deselect(selectedMine, mode, MINE);
            }
            if (selectedAdversary != -1) {
                deselect(selectedAdversary, mode, ADVERSARY);
            }

            phaseLabel.setText("Fortifying Phase");

            attackBtn.removeEventFilter(MouseEvent.MOUSE_CLICKED, attackTerritory);
            nextBtn.removeEventFilter(MouseEvent.MOUSE_CLICKED, startFortify);
            nextBtn.addEventFilter(MouseEvent.MOUSE_CLICKED, endTurn);
            mode = FORTIFY;
        }
    };
    EventHandler<MouseEvent> endTurn = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if (selectedMine != -1) {
                deselect(selectedMine, mode, MINE);
            }
            if (selectedAdversary != -1) {
                deselect(selectedAdversary, mode, ADVERSARY);
            }

            phaseLabel.setText("Turn over");

            // Commit Fortifications to board
            nextBtn.removeEventFilter(MouseEvent.MOUSE_CLICKED, endTurn);
            currentPlayer = (++currentPlayer) % board.getPlayers().size();
            startGame();
        }

    };

    EventHandler<MouseEvent> highlighter = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            Object source = event.getSource();
            if (source instanceof SVGPath) {
                SVGPath territory = ((SVGPath) source);
                int id = Integer.valueOf(territory.getId());
                svgPaths[id].setOpacity(0.65);
            }
        }
    };
    EventHandler<MouseEvent> dehighlighter = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            Object source = event.getSource();
            if (source instanceof SVGPath) {
                SVGPath territory = ((SVGPath) source);
                int id = Integer.valueOf(territory.getId());
                svgPaths[id].setOpacity(1);
            }
        }
    };

    EventHandler<MouseEvent> selectMine = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            Object source = event.getSource();
            if (source instanceof SVGPath) {
                SVGPath territory = ((SVGPath) source);
                int id = Integer.valueOf(territory.getId());
                // Review scenarios for Deploy, Attack and Fortify
                //int previousSelectedT = currentSelectedT;
                if (selectedMine == -1) {
                    select(id, mode, MINE);
                } else if (selectedMine == id) { // mine -> mine (different)
                    deselect(selectedMine, mode, MINE);
                } else { // mine -> mine (same)
                    deselect(selectedMine, mode, MINE);
                    select(id, mode, MINE);
                }

            }
        }
    };
    EventHandler<MouseEvent> selectAdversary = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            Object source = event.getSource();
            if (source instanceof SVGPath) {
                SVGPath territory = ((SVGPath) source);
                int id = Integer.valueOf(territory.getId());
                // Review scenarios for Deploy, Attack and Fortify
                //int previousSelectedT = currentSelectedT;
                if (selectedAdversary == -1) { // his
                    select(id, DEFAULT, ADVERSARY);
                } else if (id == selectedAdversary) { // his -> his (different)
                    deselect(selectedAdversary, DEFAULT, ADVERSARY);
                } else { // his -> his (same)
                    deselect(selectedAdversary, DEFAULT, ADVERSARY);
                    select(id, DEFAULT, ADVERSARY);
                }

            }
        }
    };

    EventHandler<MouseEvent> addTroops = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            int freeTroopCount = Integer.valueOf(freeTroopsLabel.getText());
            if (freeTroopCount > 0 && selectedMine != -1) {
                int territoryTroopCount = Integer.valueOf(troopCountLabel[selectedMine].getText());
                troopCountLabel[selectedMine].setText(String.valueOf(territoryTroopCount + 1));
                freeTroopsLabel.setText(String.valueOf(freeTroopCount - 1));
                deployments[selectedMine]++;
            }
        }
    };
    EventHandler<MouseEvent> removeTroops = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            int freeTroopCount = Integer.valueOf(freeTroopsLabel.getText());
            if (selectedMine != -1 && deployments[selectedMine] > 0) {
                int territoryTroopCount = Integer.valueOf(troopCountLabel[selectedMine].getText());
                troopCountLabel[selectedMine].setText(String.valueOf(territoryTroopCount - 1));
                freeTroopsLabel.setText(String.valueOf(freeTroopCount + 1));
                deployments[selectedMine]--;
            }
        }
    };

    EventHandler<MouseEvent> attackTerritory = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if (selectedMine != -1 && selectedAdversary != -1) {
                carryOutAttack(selectedMine, selectedAdversary);
            }
        }
    };

    private void distributeNext() {
        if (totalRemainingTroops <= 0) {
            return;
        }
        if (board.getPlayers().get(currentPlayer) instanceof HumanPlayer) {
            Territory[] territories = board.getMap().getTerritories();
            ArrayList<Player> players = board.getPlayers();
            for (int i = 1; i < territories.length; i++) {
                if (territories[i].getPlayer() == null || players.get(currentPlayer).equals(territories[i].getPlayer())) {
                    svgPaths[i].addEventFilter(MouseEvent.MOUSE_CLICKED, selectMine);
                    svgPaths[i].addEventFilter(MouseEvent.MOUSE_ENTERED, highlighter);
                    svgPaths[i].addEventFilter(MouseEvent.MOUSE_EXITED, dehighlighter);
                }
            }
            nextBtn.addEventFilter(MouseEvent.MOUSE_CLICKED, confirmDeployment);
        } else {
            Player player = board.getPlayers().get(currentPlayer);
            int choice = player.chooseTerritory(availableTerritories);
            board.getMap().getTerritory(choice).setPlayer(player);
            board.getMap().getTerritory(choice).addTroops(1);
            availableTerritories.remove(Integer.valueOf(choice));

            svgPaths[choice].setFill(Paint.valueOf(COLORS[currentPlayer]));
            troopCountLabel[choice].setText(String.valueOf(Integer.valueOf(troopCountLabel[choice].getText()) + 1));

            totalRemainingTroops--;
            currentPlayer = (currentPlayer + 1) % board.getPlayers().size();
            distributeNext();
        }

    } // Incomplete

    public void startGame() {
        int x = 0;
        int limit = 1;
        while (limit-- > 0) {
            x++;
            Set<Integer> remainingPlayers = new HashSet<>();
            Territory[] territories = board.getMap().getTerritories();
            for (int i = 1; i < territories.length; i++) {
                remainingPlayers.add(territories[i].getPlayer().getID());
            }
            if (remainingPlayers.size() == 1) {
                phaseLabel.setText("Game Over.");
                break;
            }
            if (remainingPlayers.size() < board.getPlayers().size()) {
                // find & remove the deadPlayer
                for (int i = 0; i < board.getPlayers().size(); i++) {
                    if (!remainingPlayers.contains(board.getPlayers().get(i).getID())) {
                        board.getPlayers().remove(board.getPlayers().get(i));
                    }
                }
            }

            playerTurnLabel.setText("Player " + board.getPlayers().get(currentPlayer).getID());

            if (board.getPlayers().get(currentPlayer) instanceof HumanPlayer) {

                phaseLabel.setText("Deployment Phase");

                deployments = new int[49];
                int territoryCount = 0;
                for (int i = 1; i < territories.length; i++) {
                    if (territories[i].getPlayer().getID() == currentPlayer) {
                        territoryCount++;
                        svgPaths[i].addEventFilter(MouseEvent.MOUSE_CLICKED, selectMine);
                        svgPaths[i].addEventFilter(MouseEvent.MOUSE_ENTERED, highlighter);
                        svgPaths[i].addEventFilter(MouseEvent.MOUSE_EXITED, dehighlighter);
                    }
                }

                int freeTroops = Math.max(territoryCount / 3, 3);

                freeTroopsLabel.setText(String.valueOf(freeTroops));
                addBtn.addEventFilter(MouseEvent.MOUSE_CLICKED, addTroops);
                subtractBtn.addEventFilter(MouseEvent.MOUSE_CLICKED, removeTroops);
                nextBtn.addEventFilter(MouseEvent.MOUSE_CLICKED, startAttacks);

                mode = DEPLOY;
                break;
            } else { // Nonhuman Player
                // System.out.println("CurrentPlayer = " + currentPlayer);
                Move move = board.getPlayers().get(currentPlayer).takeTurn();
                
                //freeTroopsLabel.setText(String.valueOf(board.getPlayers().get(currentPlayer)));
                
                //deploy Troops
                for (Point deployment : move.getDeployments()) {
                    int newTroops = deployment.x;
                    // System.out.println("New Troops: " + newTroops);
                    Territory deploymentT = board.getMap().getTerritory(deployment.y);

                    deploymentT.addTroops(newTroops);

                    int oldTroops = Integer.valueOf(troopCountLabel[deploymentT.getID()].getText());
                    // System.out.println("Label should be: " + (oldTroops + newTroops));
                    troopCountLabel[deploymentT.getID()].setText(String.valueOf(oldTroops + newTroops));

                }
                // Execute attacks
                for (Point attack : move.getAttackSequence()) {
                    //System.out.println(board.getPlayers().get(currentPlayer).getClass() + " attacked Territory #" + attack.y + " from Territory #" + attack.x);

                    Territory attackerT = board.getMap().getTerritory(attack.x);
                    Territory defenderT = board.getMap().getTerritory(attack.y);
                    if (attackerT.getPlayer().getID() == currentPlayer) {
                        carryOutAttack(attackerT.getID(), defenderT.getID());
                    }
                }

                // Execute Fortification
                // Move on
                currentPlayer = (++currentPlayer) % board.getPlayers().size();
            }
        }
        // System.out.println(x);
        // StartGame is "Recursively" called in two parts:
        // At the end of a Human Turn - ie: at "endTurn" event handler
        // At the end of a non-Human Turn ... 5 lines above

    } // Incomplete

    public void select(int id, int mode, boolean territoryAllegiance) {
        //svgPaths[id].setStrokeWidth(5);
        svgPaths[id].setStroke(Paint.valueOf("#000000"));
        if (territoryAllegiance == MINE) {
            selectedMine = id;
        } else {
            selectedAdversary = id;
        }
        switch (mode) {
            case (DEPLOY):
                return;
            case (ATTACK):
                if (territoryAllegiance == MINE) {
                    for (int adj : board.getMap().getNeighbours(id)) {
                        if (board.getMap().getTerritory(adj).getPlayer().getID() != currentPlayer) {
                            svgPaths[adj].addEventFilter(MouseEvent.MOUSE_CLICKED, selectAdversary);
                            svgPaths[adj].addEventFilter(MouseEvent.MOUSE_ENTERED, highlighter);
                            svgPaths[adj].addEventFilter(MouseEvent.MOUSE_EXITED, dehighlighter);
                        }
                    }
                }
                break;
            case (FORTIFY):
                break;
            default:
                break;
        }
    }

    public void deselect(int id, int mode, boolean territoryAllegiance) {
        //svgPaths[id].setStrokeWidth(2);
        svgPaths[id].setStroke(Paint.valueOf("#FFFFFF"));
        if (territoryAllegiance == MINE) {
            selectedMine = -1;
        } else {
            selectedAdversary = -1;
        }
        switch (mode) {
            case (DEPLOY):
                return;
            case (ATTACK):
                if (territoryAllegiance == MINE) {
                    for (int adj : board.getMap().getNeighbours(id)) {
                        if (board.getMap().getTerritory(adj).getPlayer().getID() != currentPlayer) {
                            svgPaths[adj].removeEventFilter(MouseEvent.MOUSE_CLICKED, selectAdversary);
                            svgPaths[adj].removeEventFilter(MouseEvent.MOUSE_ENTERED, highlighter);
                            svgPaths[adj].removeEventFilter(MouseEvent.MOUSE_EXITED, dehighlighter);
                        }
                    }
                    if (selectedAdversary != -1) {
                        deselect(selectedAdversary, ATTACK, ADVERSARY);
                    }
                }
                break;
            case (FORTIFY):
                break;
            default:
                break;
        }
    }

    public void carryOutAttack(int attackerID, int defenderID) {

        Territory attacker = board.getMap().getTerritory(attackerID);
        Territory defender = board.getMap().getTerritory(defenderID);

        Integer[] log = simulateAttack(attacker.getTroops(), defender.getTroops());

        boolean conquered = (log[0] == 1);
        int attackerRemainingTroops = log[1];
        int defenderRemainingTroops = log[2];
        System.out.println("Result: " + conquered + " Remaining Troops " + log[1] + ", " + log[2]);
        // retrieve dice values dice
        // Commit to board and update view
        if (board.getPlayers().get(currentPlayer) instanceof HumanPlayer) {
            deselect(selectedMine, ATTACK, MINE);
            //deselect(selectedAdversary, ATTACK, ADVERSARY);
            svgPaths[attacker.getID()].removeEventFilter(MouseEvent.MOUSE_CLICKED, selectMine);
            svgPaths[attacker.getID()].removeEventFilter(MouseEvent.MOUSE_ENTERED, highlighter);
            svgPaths[attacker.getID()].removeEventFilter(MouseEvent.MOUSE_EXITED, dehighlighter);
            if (conquered && attackerRemainingTroops > 3) {
                svgPaths[defender.getID()].addEventFilter(MouseEvent.MOUSE_CLICKED, selectMine);
                svgPaths[defender.getID()].addEventFilter(MouseEvent.MOUSE_ENTERED, highlighter);
                svgPaths[defender.getID()].addEventFilter(MouseEvent.MOUSE_EXITED, dehighlighter);

            }
        }
        if (conquered) {

            defender.setPlayer(board.getPlayers().get(currentPlayer));
            defender.setTroops(attackerRemainingTroops - 1);
            attacker.setTroops(1);

            svgPaths[defender.getID()].setFill(Color.valueOf(COLORS[currentPlayer]));
            troopCountLabel[defender.getID()].setText(String.valueOf(attackerRemainingTroops - 1));
            troopCountLabel[attacker.getID()].setText(String.valueOf(1));
        } else {
            defender.setTroops(defenderRemainingTroops);
            attacker.setTroops(attackerRemainingTroops);

            troopCountLabel[defender.getID()].setText(String.valueOf(defenderRemainingTroops));
            troopCountLabel[attacker.getID()].setText(String.valueOf(attackerRemainingTroops));
        }
    }

    public Integer[] simulateAttack(int attackerTroops, int defenderTroops) {
        // Carry attack between a territory having # troops = attackerTroops
        // On a territory having # troops = defenderTroops;
        // Return attackLog : winner and losses of each territory troops
        // 		attackLog[0] = winner(1:attacker, 0:defender)
        // 		attackLog[1] = remaining attacker troops
        // 		attackLog[2] = remaining defender troops
        Integer[] attackLog = new Integer[3];

        // Attacking Logic (Die and stuff)
        // ArrayList<Integer[]> attackerDice = new ArrayList<>();
        // ArrayList<Integer[]> defenderDice = new ArrayList<>();
        int attackerDice, defenderDice;
        int[] losses;

        while (attackerTroops > 1 && defenderTroops > 0) {
            attackerDice = Math.min(attackerTroops - 1, 3); // Attack with all but one of attacker troops
            defenderDice = Math.min(defenderTroops, 2);
            losses = throwDice(attackerDice, defenderDice);

            // decrement mT by losses[0]
            attackerTroops -= losses[0];
            // decrement t by losses[1]
            defenderTroops -= losses[1];
        }

        if (defenderTroops == 0) { // Attack successful
            attackLog[0] = 1;
        } else {
            attackLog[0] = 0;
        }

        attackLog[1] = attackerTroops;
        attackLog[2] = defenderTroops;

        return attackLog;
    }

    protected int[] throwDice(int dice1, int dice2) {
        // Simulates random dice throws during an attack	
        // Returns losses[] where:
        // 		losses[0] = Attacker's losses
        // 		losse[1] = Defender's losses

        Integer[] values1 = new Integer[dice1]; // 1 <= dice1 <= 3
        Integer[] values2 = new Integer[dice2]; // 1 <= dice2 <= 2

        int[] losses = new int[2];

        for (int i = 0; i < dice1; i++) {
            values1[i] = (int) (Math.random() * 6 + 1);
        }
        for (int i = 0; i < dice2; i++) {
            values2[i] = (int) (Math.random() * 6 + 1);
        }

        Arrays.sort(values1, Collections.reverseOrder());
        Arrays.sort(values2, Collections.reverseOrder());

        for (int i = 0; i < Math.min(dice1, dice2); i++) { // We know that for all dice1,dice2, dice2<=dice1            
            if (values2[i] >= values1[i]) {
                losses[0] += 1;
            } else {
                losses[1] += 1;
            }
        }

        for (int i = 0; i < attackerDiceLabel.length; i++) {
            attackerDiceLabel[i].setText("");
        }
        for (int i = 0; i < defenderDiceLabel.length; i++) {
            defenderDiceLabel[i].setText("");
        }

        for (int i = 0; i < dice1; i++) {
            attackerDiceLabel[i].setText(String.valueOf(values1[i]));
        }
        for (int i = 0; i < dice2; i++) {
            defenderDiceLabel[i].setText(String.valueOf(values2[i]));
        }

        return losses;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        assert alabama != null : "fx:id=\"alabama\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert arizona != null : "fx:id=\"arizona\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert arkansas != null : "fx:id=\"arkansas\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert california != null : "fx:id=\"california\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert colorado != null : "fx:id=\"colorado\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert connecticut != null : "fx:id=\"connecticut\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert delaware != null : "fx:id=\"delaware\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert florida != null : "fx:id=\"florida\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert georgia != null : "fx:id=\"georgia\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert idaho != null : "fx:id=\"idaho\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert illinois != null : "fx:id=\"illinois\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert indiana != null : "fx:id=\"indiana\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert kansas != null : "fx:id=\"kansas\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert kentucky != null : "fx:id=\"kentucky\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert louisiana != null : "fx:id=\"louisiana\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert iowa != null : "fx:id=\"iowa\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert maine != null : "fx:id=\"maine\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert maryLand != null : "fx:id=\"maryLand\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert massachussetts != null : "fx:id=\"massachussetts\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert michigan != null : "fx:id=\"michigan\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert minnesota != null : "fx:id=\"minnesota\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert mississippi != null : "fx:id=\"mississippi\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert missouri != null : "fx:id=\"missouri\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert montana != null : "fx:id=\"montana\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert nebraska != null : "fx:id=\"nebraska\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert nevada != null : "fx:id=\"nevada\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert newHampshire != null : "fx:id=\"newHampshire\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert newJersey != null : "fx:id=\"newJersey\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert newMexico != null : "fx:id=\"newMexico\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert newYork != null : "fx:id=\"newYork\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert northCarolina != null : "fx:id=\"northCalorina\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert northDakota != null : "fx:id=\"northDakota\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert ohio != null : "fx:id=\"ohio\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert oklahoma != null : "fx:id=\"oklahoma\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert oregon != null : "fx:id=\"oregon\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert pennsylvania != null : "fx:id=\"pennsylvania\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert rhodeIsland != null : "fx:id=\"rhodeIsland\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert southCarolina != null : "fx:id=\"southCarolina\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert southDakota != null : "fx:id=\"southDakota\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert tennessee != null : "fx:id=\"tennessee\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert texas != null : "fx:id=\"texas\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert utah != null : "fx:id=\"utah\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert vermont != null : "fx:id=\"vermont\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert virginia != null : "fx:id=\"virginia\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert washington != null : "fx:id=\"washington\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert westVirginia != null : "fx:id=\"westVirginia\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert wisconsin != null : "fx:id=\"wisconsin\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert wyoming != null : "fx:id=\"wyoming\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops1 != null : "fx:id=\"troops1\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops10 != null : "fx:id=\"troops10\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops11 != null : "fx:id=\"troops11\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops12 != null : "fx:id=\"troops12\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops13 != null : "fx:id=\"troops13\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops14 != null : "fx:id=\"troops14\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops15 != null : "fx:id=\"troops15\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops16 != null : "fx:id=\"troops16\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops17 != null : "fx:id=\"troops17\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops18 != null : "fx:id=\"troops18\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops19 != null : "fx:id=\"troops19\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops2 != null : "fx:id=\"troops2\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops20 != null : "fx:id=\"troops20\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops21 != null : "fx:id=\"troops21\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops22 != null : "fx:id=\"troops22\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops23 != null : "fx:id=\"troops23\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops24 != null : "fx:id=\"troops24\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops25 != null : "fx:id=\"troops25\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops26 != null : "fx:id=\"troops26\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops27 != null : "fx:id=\"troops27\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops28 != null : "fx:id=\"troops28\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops29 != null : "fx:id=\"troops29\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops3 != null : "fx:id=\"troops3\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops30 != null : "fx:id=\"troops30\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops31 != null : "fx:id=\"troops31\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops32 != null : "fx:id=\"troops32\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops33 != null : "fx:id=\"troops33\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops34 != null : "fx:id=\"troops34\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops35 != null : "fx:id=\"troops35\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops36 != null : "fx:id=\"troops36\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops37 != null : "fx:id=\"troops37\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops38 != null : "fx:id=\"troops38\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops39 != null : "fx:id=\"troops39\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops4 != null : "fx:id=\"troops4\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops40 != null : "fx:id=\"troops40\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops41 != null : "fx:id=\"troops41\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops42 != null : "fx:id=\"troops42\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops43 != null : "fx:id=\"troops43\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops44 != null : "fx:id=\"troops44\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops45 != null : "fx:id=\"troops45\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops46 != null : "fx:id=\"troops46\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops47 != null : "fx:id=\"troops47\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops48 != null : "fx:id=\"troops48\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops5 != null : "fx:id=\"troops5\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops6 != null : "fx:id=\"troops6\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops7 != null : "fx:id=\"troops7\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops8 != null : "fx:id=\"troops8\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert troops9 != null : "fx:id=\"troops9\" was not injected: check your FXML file 'FXMLDocument.fxml'.";

        attackerDiceLabel = new Label[3];
        attackerDiceLabel[0] = attackerDie1;
        attackerDiceLabel[1] = attackerDie2;
        attackerDiceLabel[2] = attackerDie3;

        defenderDiceLabel = new Label[2];
        defenderDiceLabel[0] = defenderDie1;
        defenderDiceLabel[1] = defenderDie2;

        svgPaths = new SVGPath[49]; // 49 = model.getMap().getTerritories().length;
        svgPaths[1] = washington;
        svgPaths[2] = oregon;
        svgPaths[3] = california;
        svgPaths[4] = nevada;
        svgPaths[5] = idaho;
        svgPaths[6] = montana;
        svgPaths[7] = wyoming;
        svgPaths[8] = utah;
        svgPaths[9] = arizona;
        svgPaths[10] = colorado;
        svgPaths[11] = newMexico;
        svgPaths[12] = texas;
        svgPaths[13] = oklahoma;
        svgPaths[14] = kansas;
        svgPaths[15] = nebraska;
        svgPaths[16] = southDakota;
        svgPaths[17] = northDakota;
        svgPaths[18] = minnesota;
        svgPaths[19] = iowa;
        svgPaths[20] = missouri;
        svgPaths[21] = arkansas;
        svgPaths[22] = louisiana;
        svgPaths[23] = mississippi;
        svgPaths[24] = alabama;
        svgPaths[25] = florida;
        svgPaths[26] = georgia;
        svgPaths[27] = southCarolina;
        svgPaths[28] = northCarolina;
        svgPaths[29] = virginia;
        svgPaths[30] = westVirginia;
        svgPaths[31] = tennessee;
        svgPaths[32] = kentucky;
        svgPaths[33] = illinois;
        svgPaths[34] = wisconsin;
        svgPaths[35] = indiana;
        svgPaths[36] = michigan;
        svgPaths[37] = ohio;
        svgPaths[38] = pennsylvania;
        svgPaths[39] = newYork;
        svgPaths[40] = vermont;
        svgPaths[41] = newHampshire;
        svgPaths[42] = maine;
        svgPaths[43] = massachussetts;
        svgPaths[44] = rhodeIsland;
        svgPaths[45] = connecticut;
        svgPaths[46] = newJersey;
        svgPaths[47] = delaware;
        svgPaths[48] = maryLand;

        for (int i = 1; i < 49; i++) {
            svgPaths[i].setFill(Paint.valueOf("#CCCCCC"));
            svgPaths[i].setStroke(Paint.valueOf("#FFFFFF"));
            svgPaths[i].setStrokeWidth(2);
        }

        troopCountLabel = new Label[49];
        troopCountLabel[1] = troops1;
        troopCountLabel[2] = troops2;
        troopCountLabel[3] = troops3;
        troopCountLabel[4] = troops4;
        troopCountLabel[5] = troops5;
        troopCountLabel[6] = troops6;
        troopCountLabel[7] = troops7;
        troopCountLabel[8] = troops8;
        troopCountLabel[9] = troops9;
        troopCountLabel[10] = troops10;
        troopCountLabel[11] = troops11;
        troopCountLabel[12] = troops12;
        troopCountLabel[13] = troops13;
        troopCountLabel[14] = troops14;
        troopCountLabel[15] = troops15;
        troopCountLabel[16] = troops16;
        troopCountLabel[17] = troops17;
        troopCountLabel[18] = troops18;
        troopCountLabel[19] = troops19;
        troopCountLabel[20] = troops20;
        troopCountLabel[21] = troops21;
        troopCountLabel[22] = troops22;
        troopCountLabel[23] = troops23;
        troopCountLabel[24] = troops24;
        troopCountLabel[25] = troops25;
        troopCountLabel[26] = troops26;
        troopCountLabel[27] = troops27;
        troopCountLabel[28] = troops28;
        troopCountLabel[29] = troops29;
        troopCountLabel[30] = troops30;
        troopCountLabel[31] = troops31;
        troopCountLabel[32] = troops32;
        troopCountLabel[33] = troops33;
        troopCountLabel[34] = troops34;
        troopCountLabel[35] = troops35;
        troopCountLabel[36] = troops36;
        troopCountLabel[37] = troops37;
        troopCountLabel[38] = troops38;
        troopCountLabel[39] = troops39;
        troopCountLabel[40] = troops40;
        troopCountLabel[41] = troops41;
        troopCountLabel[42] = troops42;
        troopCountLabel[43] = troops43;
        troopCountLabel[44] = troops44;
        troopCountLabel[45] = troops45;
        troopCountLabel[46] = troops46;
        troopCountLabel[47] = troops47;
        troopCountLabel[48] = troops48;

        selectedMine = -1;
        selectedAdversary = -1;

        deployments = new int[49];

        nextBtn.addEventFilter(MouseEvent.MOUSE_CLICKED, play);
        nextBtn.setText("Start Game");

    }
}
