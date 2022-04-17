package io.elevatorsim.main;

import io.elevatorsim.ElevatorSimApplication;
import io.elevatorsim.elevator.ElevatorBoardView;
import io.elevatorsim.elevator.ElevatorButtonView;
import io.elevatorsim.story.StoryBoardView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML private BorderPane root_BDP;
    @FXML private AnchorPane root_ANC;
    @FXML private VBox floorsContainer_VBX;
    @FXML private ImageView elevator_IMG;

    private int floorNb = 0;

    private final static double BASE_HEIGHT = 110.;
    private final static double FLOOR_HEIGHT = 80;
    private final static int NB_FLOORS = 10;

    public MainController() {
        ElevatorSimApplication.mainController = this;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Main
        floorsContainer_VBX.getChildren().clear();
        for (int i = 0; i < NB_FLOORS; i++)
            floorsContainer_VBX.getChildren().add(new FloorView());

        // Elevator board
        ElevatorBoardView elevatorBoardView = new ElevatorBoardView(5);
        elevatorBoardView.add(new ElevatorButtonView(elevatorBoardView, "L", 0, true));
        elevatorBoardView.add(new ElevatorButtonView(elevatorBoardView, "1", 1));
        elevatorBoardView.add(new ElevatorButtonView(elevatorBoardView, "2", 2));
        elevatorBoardView.add(new ElevatorButtonView(elevatorBoardView, "3", 3));
        elevatorBoardView.add(new ElevatorButtonView(elevatorBoardView, "4", 4));
        elevatorBoardView.add(new ElevatorButtonView(elevatorBoardView, "5", 5));
        elevatorBoardView.add(new ElevatorButtonView(elevatorBoardView, "6", 6));
        elevatorBoardView.add(new ElevatorButtonView(elevatorBoardView, "7", 7));
        elevatorBoardView.add(new ElevatorButtonView(elevatorBoardView, "8", 8));
        elevatorBoardView.add(new ElevatorButtonView(elevatorBoardView, "9", 9));

        AnchorPane.setBottomAnchor(elevator_IMG, BASE_HEIGHT + floorNb*FLOOR_HEIGHT);

        root_ANC.getChildren().add(elevatorBoardView);
        AnchorPane.setRightAnchor(elevatorBoardView, 45.);
        AnchorPane.setBottomAnchor(elevatorBoardView, 450.);

        // Story board
        StoryBoardView storyBoardView = new StoryBoardView();
        root_BDP.setLeft(storyBoardView);
    }

    public void setFloorNb(int floorNb) {
        this.floorNb = floorNb;
        AnchorPane.setBottomAnchor(elevator_IMG, BASE_HEIGHT + floorNb*FLOOR_HEIGHT);
    }

    public FloorView getFloor(int floorNb) {
        return (FloorView) floorsContainer_VBX.getChildren().get(NB_FLOORS - floorNb - 1);
    }
}
