package io.elevatorsim.story;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import static io.elevatorsim.ElevatorSimApplication.mainController;

public class ElevatorStory extends Story {

    public ElevatorStory(int floorNb) {
        this.action = Action.MOVE;
        this.floorNb = floorNb;
    }

    public void execute(EventHandler<ActionEvent> callback) {
        mainController.moveElevatorToFloorNb(floorNb, callback);
    }
}
