package io.elevatorsim.elevator;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ElevatorButtonView extends StackPane implements Initializable {
    @FXML Circle circle_CRC;
    @FXML Text text_TXT;

    private final BooleanProperty selected = new SimpleBooleanProperty();

    private final ElevatorBoardView parent;
    private final String text;
    private final int floorNb;

    public ElevatorButtonView() { this(null, "", 0); }

    public ElevatorButtonView(ElevatorBoardView parent, String text, int floorNb) { this(parent, text, floorNb, false); }

    public ElevatorButtonView(ElevatorBoardView parent, String text, int floorNb, boolean selected) {
        this.parent = parent;
        this.text = text;
        this.floorNb = floorNb;
        this.selected.set(selected);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/elevator/elevator-button.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try { fxmlLoader.load(); }
        catch (IOException exception) { throw new RuntimeException(exception); }
    }

    public boolean isSelected() { return selected.get(); }
    public void setSelected(boolean value) { selected.set(value); }
    public BooleanProperty selectedProperty() { return selected; }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        text_TXT.setText(text);
        text_TXT.fillProperty().bind(Bindings.when(selected).then(Color.RED).otherwise(Color.BLACK));
        circle_CRC.strokeProperty().bind(Bindings.when(selected).then(Color.RED).otherwise(Color.BLACK));
        circle_CRC.strokeWidthProperty().bind(Bindings.when(selected).then(4).otherwise(2));

        this.setOnMouseClicked((MouseEvent e) -> requestSelection());
    }

    public void requestSelection() {
        parent.requestSelection(this);
    }

    public int getFloorNb() { return floorNb; }
}
