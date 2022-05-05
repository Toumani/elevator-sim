package io.elevatorsim.story;

import io.elevatorsim.main.FloorView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import static io.elevatorsim.ElevatorSimApplication.mainController;

public class UserStory extends Story {
    private final int nbUser;

    public UserStory(int nbUser, Action action, int floorNb) {
        this.nbUser = nbUser;
        this.action = action;
        this.floorNb = floorNb;
    }

    @Override
    public void execute(EventHandler<ActionEvent> callback) {
        System.out.println(nbUser + " user(s) " + action + " at floor " + floorNb);
        FloorView floorView = mainController.getFloor(floorNb);
        FloorView elevatorFloorView = mainController.getElevatorFloor();
        switch (action) {
            case ARRIVE:
                floorView.pushNRandomUser(nbUser, callback);
                break;
            case ENTER:
                elevatorFloorView.popNUser(nbUser, callback);
                break;
            case EXIT:
                mainController.openElevator();
                elevatorFloorView.pullNRandomUser(nbUser, callback);
                break;
        }
    }

    public int getNbUser() { return nbUser; }
}
