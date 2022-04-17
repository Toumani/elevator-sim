package io.elevatorsim.main;

import io.elevatorsim.board.ElevatorBoardView;
import io.elevatorsim.board.ElevatorButtonView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private AnchorPane root_ANC;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ElevatorBoardView board = new ElevatorBoardView(5);
        board.add(new ElevatorButtonView("L", true));
        board.add(new ElevatorButtonView("1"));
        board.add(new ElevatorButtonView("2"));
        board.add(new ElevatorButtonView("3"));
        board.add(new ElevatorButtonView("4"));
        board.add(new ElevatorButtonView("5"));
        board.add(new ElevatorButtonView("6"));
        board.add(new ElevatorButtonView("7"));
        board.add(new ElevatorButtonView("8"));
        board.add(new ElevatorButtonView("9"));

        root_ANC.getChildren().add(board);
        AnchorPane.setRightAnchor(board, 45.);
        AnchorPane.setBottomAnchor(board, 450.);
    }
}
