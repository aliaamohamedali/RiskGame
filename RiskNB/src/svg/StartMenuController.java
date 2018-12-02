/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package svg;

import board.Board;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import player.*;

/**
 * FXML Controller class
 *
 * @author carnival
 */
public class StartMenuController implements Initializable {

    @FXML
    private RadioButton simMode;
    @FXML
    private RadioButton gameMode;

    @FXML
    private RadioButton USATerrain;
    @FXML
    private RadioButton EgyTerrain;

    @FXML
    private TextField player1;
    @FXML
    private TextField player2;
    @FXML
    private TextField player3;
    @FXML
    private TextField player4;
    @FXML
    private TextField player5;
    @FXML
    private TextField player6;
    @FXML
    private TextField playerCountTF;

    //Local Variables
    private TextField[] playerTypeTF;
    String playerRegex = "[1-8]"; // default is gameMode

    /**
     * Initializes the controller class.
     */
    EventHandler<MouseEvent> startPlaying = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            int playerCount = Integer.valueOf(playerCountTF.getText());
            for (int i = 0; i < playerCount; i++) {
                if (playerTypeTF[i].getText().equals("")) {
                    return;
                }
            }
        }
    };

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        playerTypeTF = new TextField[6];
        playerTypeTF[0] = player1;
        playerTypeTF[1] = player2;
        playerTypeTF[2] = player3;
        playerTypeTF[3] = player4;
        playerTypeTF[4] = player5;
        playerTypeTF[5] = player6;

        playerCountTF.setText("2");
        gameMode.setSelected(true);
        USATerrain.setSelected(true);

        for (int i = 2; i < 6; i++) {
            playerTypeTF[i].setDisable(true);
        }

        player1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.length() < 1 || !newValue.substring(newValue.length() - 1, newValue.length()).matches(playerRegex)) {
                    player1.setText(oldValue);
                } else {
                    player1.setText(newValue.substring(newValue.length() - 1, newValue.length()));
                }
            }
        });
        player2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.length() < 1 || !newValue.substring(newValue.length() - 1, newValue.length()).matches(playerRegex)) {
                    player2.setText(oldValue);
                } else {
                    player2.setText(newValue.substring(newValue.length() - 1, newValue.length()));
                }
            }
        });
        player3.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.length() < 1 || !newValue.substring(newValue.length() - 1, newValue.length()).matches(playerRegex)) {
                    player3.setText(oldValue);
                } else {
                    player3.setText(newValue.substring(newValue.length() - 1, newValue.length()));
                }
            }
        });
        player4.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.length() < 1 || !newValue.substring(newValue.length() - 1, newValue.length()).matches(playerRegex)) {
                    player4.setText(oldValue);
                } else {
                    player4.setText(newValue.substring(newValue.length() - 1, newValue.length()));
                }
            }
        });
        player5.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.length() < 1 || !newValue.substring(newValue.length() - 1, newValue.length()).matches(playerRegex)) {
                    player5.setText(oldValue);
                } else {
                    player5.setText(newValue.substring(newValue.length() - 1, newValue.length()));
                }
            }
        });
        player6.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.length() < 1 || !newValue.substring(newValue.length() - 1, newValue.length()).matches(playerRegex)) {
                    player6.setText(oldValue);
                } else {
                    player6.setText(newValue.substring(newValue.length() - 1, newValue.length()));
                }
            }
        });

        playerCountTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.length() < 1 || !newValue.substring(newValue.length() - 1, newValue.length()).matches("[2-6]{1}")) {
                    // If the newly input character isn't between 1-6
                    // leave the old value as is
                    playerCountTF.setText(oldValue);
                    return;
                } else {
                    // Else update the text to the last input Value
                    playerCountTF.setText(newValue.substring(newValue.length() - 1, newValue.length()));
                }
                Integer disable = Integer.valueOf(playerCountTF.getText());
                for (int i = 0; i < disable; i++) {
                    playerTypeTF[i].setDisable(false);
                }
                for (int i = disable; i < 6; i++) {
                    playerTypeTF[i].setText("");
                    playerTypeTF[i].setDisable(true);
                }
            }
        });

        simMode.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected) {
                    gameMode.setSelected(false);
                    playerRegex = "[2-8]";
                } else {
                    gameMode.setSelected(true);
                    playerRegex = "[1-8]";
                }
                // Reset all textfields because why the fuck not
                for (int i = 0; i < 6; i++) {
                    if (playerTypeTF[i].getText().equals("1")) {
                        playerTypeTF[i].setText("2");
                    }
                }
            }
        });

        gameMode.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected) {
                    simMode.setSelected(false);
                    playerRegex = "[1-8]";
                } else {
                    simMode.setSelected(true);
                    playerRegex = "[2-8]";
                }
            }
        });
        USATerrain.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected) {
                    EgyTerrain.setSelected(false);
                } else {
                    EgyTerrain.setSelected(true);
                }
            }
        });

        EgyTerrain.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected) {
                    USATerrain.setSelected(false);
                } else {
                    USATerrain.setSelected(true);
                }
            }
        });
    }

    public void startGame(MouseEvent event) throws IOException {

        TextField[] playerClass = new TextField[6];
        playerClass[0] = player1;
        playerClass[1] = player2;
        playerClass[2] = player3;
        playerClass[3] = player4;
        playerClass[4] = player5;
        playerClass[5] = player6;

        int playerCount = Integer.valueOf(playerCountTF.getText());
        for (int i = 0; i < playerCount; i++) {
            if (playerTypeTF[i].getText().equals("")) {
                return;
            }
        }

        // The default
        boolean terrain = Board.USA;

        // Set Terrain
        if (EgyTerrain.isSelected()) {
            terrain = Board.EGY;
        }
        // Set Players
        ArrayList<Player> players = new ArrayList<>();

        // Set Board
        Board board = Board.getInstance();
        board.configure(terrain, players);

        loop:
        for (int i = 0; i < Integer.valueOf(playerCountTF.getText()); i++) {
            switch (Integer.valueOf(playerClass[i].getText())) {
                case (1):
                    players.add(new HumanPlayer(i, board.getMap()));
                    break;
                case (2):
                    players.add(new PassivePlayer(i, board.getMap()));
                    break;
                case (3):
                    players.add(new AggressivePlayer(i, board.getMap()));
                    break;
                case (4):
                    players.add(new SemiPassivePlayer(i, board.getMap()));
                    break;
                case (5):
                    players.add(new GreedyAIPlayer(i, board.getMap()));
                    break;
                case (6):
                    players.add(new AStarPlayer(i, board.getMap()));
                    break;
                case (7):
                    players.add(new RealTimeAStarPlayer(i, board.getMap()));
                    break;
                case (8):
                    players.add(new MinimaxPlayer(i, board.getMap()));
                    break;
                default:
                    break loop; //
            }
        }

        Object source = event.getSource();
        if (source instanceof Button) {
            Stage stage = (Stage) ((Button) source).getScene().getWindow();
            stage.close();
        }

        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

}
