package io.elevatorsim.main;

import io.elevatorsim.ElevatorSimApplication;
import io.elevatorsim.board.ElevatorBoardView;
import io.elevatorsim.board.ElevatorButtonView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private AnchorPane root_ANC;
    @FXML
    private ImageView elevator_IMG;

    private int floorNb = 0;

    private final static double BASE_HEIGHT = 110.;
    private final static double FLOOR_HEIGHT = 80;

    public MainController() {
        ElevatorSimApplication.mainController = this;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ElevatorBoardView board = new ElevatorBoardView(5);
        board.add(new ElevatorButtonView(board, "L", 0, true));
        board.add(new ElevatorButtonView(board, "1", 1));
        board.add(new ElevatorButtonView(board, "2", 2));
        board.add(new ElevatorButtonView(board, "3", 3));
        board.add(new ElevatorButtonView(board, "4", 4));
        board.add(new ElevatorButtonView(board, "5", 5));
        board.add(new ElevatorButtonView(board, "6", 6));
        board.add(new ElevatorButtonView(board, "7", 7));
        board.add(new ElevatorButtonView(board, "8", 8));
        board.add(new ElevatorButtonView(board, "9", 9));

        AnchorPane.setBottomAnchor(elevator_IMG, BASE_HEIGHT + floorNb*FLOOR_HEIGHT);

        root_ANC.getChildren().add(board);
        AnchorPane.setRightAnchor(board, 45.);
        AnchorPane.setBottomAnchor(board, 450.);
    }

    public void setFloorNb(int floorNb) {
        this.floorNb = floorNb;
        AnchorPane.setBottomAnchor(elevator_IMG, BASE_HEIGHT + floorNb*FLOOR_HEIGHT);
    }
}
