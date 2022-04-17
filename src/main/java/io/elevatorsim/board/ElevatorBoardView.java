package io.elevatorsim.board;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static io.elevatorsim.ElevatorSimApplication.mainController;

public class ElevatorBoardView extends GridPane implements Initializable {
    @FXML GridPane root_GRD;

    private int nbButtons = 0;
    private final int nbRow;

    private ElevatorButtonView selectedButton;

    public ElevatorBoardView() { this(1); }

    public ElevatorBoardView(int nbRow) {
        this.nbRow = nbRow;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/board/elevator-board-view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try { fxmlLoader.load(); }
        catch (IOException exception) { throw new RuntimeException(exception); }
    }

    public void add(ElevatorButtonView child) {
        int columnIndex, rowIndex;
        if (nbButtons % 2 == 0) {
            columnIndex = 0;
            rowIndex = nbRow - nbButtons / 2;
        }
        else {
            columnIndex = 1;
            rowIndex = nbRow - (nbButtons - 1) / 2;
        }

        super.add(child, columnIndex, rowIndex);
        nbButtons++;

        if (child.isSelected()) {
            selectedButton = child;
            getChildren().forEach(it -> {
                if (it != selectedButton)
                    ((ElevatorButtonView) it).setSelected(false);
            });
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        root_GRD.getRowConstraints().clear();
        for (int i = 0; i < nbRow; i++) {
            root_GRD.getRowConstraints().add(new RowConstraints(50., 50., 50.));
        }
    }

    public boolean requestSelection(ElevatorButtonView button) {
        if (button == selectedButton)
            return false;

        selectedButton.setSelected(false);
        button.setSelected(true);
        selectedButton = button;

        mainController.setFloorNb(button.getFloorNb());

        return true;
    }
}
