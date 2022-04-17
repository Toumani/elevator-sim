package io.elevatorsim.board;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
    private final String text;

    public ElevatorButtonView() { this(""); }

    public ElevatorButtonView(String text) { this(text, false); }

    public ElevatorButtonView(String text, boolean selected) {
        this.text = text;
        this.selected.set(selected);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/board/elevator-button.fxml"));
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
    }
}