package io.elevatorsim.main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FloorView extends AnchorPane implements Initializable {
    @FXML HBox queue_HBX;

    public FloorView() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/floor-view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try { fxmlLoader.load(); }
        catch (IOException exception) { throw new RuntimeException(exception); }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void pushRandomUser() {
        queue_HBX.getChildren().add(createRandomUser());
    }

    public void pushNRandomUser(int n) {
        for (int i = 0; i < n; i++)
            queue_HBX.getChildren().add(createRandomUser());
    }

    public void popUser() {
        try { queue_HBX.getChildren().remove(0); }
        catch (IndexOutOfBoundsException ignored) { }
    }

    public void popNUser(int n) {
        try { queue_HBX.getChildren().remove(0, n); }
        catch (IndexOutOfBoundsException ignored) { }

    }

    public void pullRandomUser() {
        System.out.println("Not implemented yet"); // TODO corresponds to user leaving the elevator and accessing the floor
    }

    public void pullNRandomUser(int n) {
        System.out.println("Not implemented yet"); // TODO corresponds to user leaving the elevator and accessing the floor
    }

    private ImageView createRandomUser() {
        boolean female = Math.random() > .5;
        URL url = getClass().getResource("/main/" + (female ? "wo" : "") + "man_standing.png");
        if (url == null)
            throw new RuntimeException();
        ImageView imageView = new ImageView(new Image("file://" + url.getPath()));
        imageView.setFitHeight(43);
        imageView.setPreserveRatio(true);
        return imageView;
    }
}
