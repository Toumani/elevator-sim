package io.elevatorsim.story;

import io.elevatorsim.main.FloorView;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static io.elevatorsim.ElevatorSimApplication.mainController;

public class StoryBoardView extends VBox implements Initializable {
    @FXML Label user_LBL;
    @FXML TextField nbUser_TXTF, floorNb_TXTF;
    @FXML ComboBox<Action> action_CMB;
    @FXML Button execute_BTN;

    private final IntegerProperty nbUser = new SimpleIntegerProperty(1);
    private final IntegerProperty floorNb = new SimpleIntegerProperty(0);

    public StoryBoardView() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/story/story-board-view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try { fxmlLoader.load(); }
        catch (IOException exception) { throw new RuntimeException(exception); }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        action_CMB.getItems().addAll(Action.ARRIVE, Action.ENTER, Action.EXIT);
        action_CMB.getSelectionModel().selectFirst();
        action_CMB.setConverter(new StringConverter<Action>() {
            @Override
            public String toString(Action action) {
                switch (action) {
                    case ARRIVE: return "arrive";
                    case ENTER: return "enter";
                    case EXIT: return "exit";
                    default: throw new RuntimeException("Unknown action. Action: " + action);
                }
            }

            @Override
            public Action fromString(String string) { return Action.valueOf(string); }
        });

        bindTextFieldToIntegerProperty(nbUser_TXTF, nbUser);
        bindTextFieldToIntegerProperty(floorNb_TXTF, floorNb);

        execute_BTN.setOnAction((ActionEvent e) -> {
            System.out.println(nbUser.get() + " user(s) " + action_CMB.getSelectionModel().getSelectedItem() + " at floor " + floorNb.get());
            FloorView floorView = mainController.getFloor(floorNb.get());
            FloorView elevatorFloorView = mainController.getElevatorFloor();
            switch (action_CMB.getSelectionModel().getSelectedItem()) {
                case ARRIVE:
                    floorView.pushNRandomUser(nbUser.get());
                    break;
                case ENTER:
                    elevatorFloorView.popNUser(nbUser.get());
                    break;
                case EXIT:
                    mainController.openElevator();
                    elevatorFloorView.pullNRandomUser(nbUser.get(), (ActionEvent event) -> mainController.closeElevator());
                    break;
            }
        });

    }

    private void bindTextFieldToIntegerProperty(TextField textField, IntegerProperty integerProperty) {
        textField.focusedProperty().addListener(((observable, oldValue, newValue) -> {
            if (!newValue) // on focus lost
                try {
                    integerProperty.set(Integer.parseInt(textField.getText()));
                    textField.setText(textField.getText());
                } catch (NumberFormatException ex) {
                    textField.setText("0");
                }
        }));
    }
}

enum Action {
    ARRIVE, ENTER, EXIT
}
