package io.elevatorsim.main;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static io.elevatorsim.Constants.FADE_DURATION;
import static io.elevatorsim.Constants.TRANSLATE_DURATION;

public class FloorView extends AnchorPane implements Initializable {
    @FXML HBox queue_HBX;

    private static final int USER_WIDTH = 43;

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
        Node newUser = createRandomUser();
        queue_HBX.getChildren().add(newUser);
        FadeTransition fadeTransition = new FadeTransition(FADE_DURATION, newUser);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }

    public void pushNRandomUser(int n) {
        for (int i = 0; i < n; i++)
            pushRandomUser();
    }

    public void popUser() {
        if (queue_HBX.getChildren().isEmpty())
            return;

        Node user = queue_HBX.getChildren().get(0);
        FadeTransition fadeTransition = new FadeTransition(FADE_DURATION, user);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
        fadeTransition.setOnFinished((ActionEvent e) -> queue_HBX.getChildren().remove(0));
    }

    public void popNUser(int n) {
        try {
            if (n > queue_HBX.getChildren().size())
                return;

            ParallelTransition fadeTransitions = new ParallelTransition();
            ParallelTransition translateTransitions = new ParallelTransition();
            for (int i = 0; i < queue_HBX.getChildren().size(); i++) {
                Node user = queue_HBX.getChildren().get(i);
                if (i < n) {
                    FadeTransition fadeTransition = new FadeTransition(FADE_DURATION, user);
                    fadeTransition.setFromValue(1.0);
                    fadeTransition.setToValue(0.0);
                    fadeTransitions.getChildren().add(fadeTransition);
                }
                else {
                    TranslateTransition translateTransition = new TranslateTransition(TRANSLATE_DURATION.multiply(n), user);
                    translateTransition.setByX(-USER_WIDTH*n);
                    translateTransitions.getChildren().add(translateTransition);
                }
            }
            SequentialTransition sequentialTransition = new SequentialTransition(fadeTransitions, translateTransitions);
            sequentialTransition.setOnFinished((ActionEvent) -> {
                queue_HBX.getChildren().remove(0, n);
                queue_HBX.getChildren().forEach(it -> it.setTranslateX(0));
            });
            sequentialTransition.play();
        }
        catch (IndexOutOfBoundsException ignored) { }

    }

    public void pullNRandomUser(int n, EventHandler<ActionEvent> callback) {
        SequentialTransition sequentialTransition = new SequentialTransition();
        for (int i = 0; i < n; i++) {
            Node user = createRandomUser();
            this.getChildren().add(user);
            AnchorPane.setBottomAnchor(user, 10.);
            AnchorPane.setRightAnchor(user, 185.);

            TranslateTransition translateTransition = new TranslateTransition(TRANSLATE_DURATION, user);
            translateTransition.setByX(70);

            FadeTransition fadeTransition = new FadeTransition(FADE_DURATION, user);
            fadeTransition.setFromValue(1.);
            fadeTransition.setToValue(0.);
            fadeTransition.setOnFinished(event -> this.getChildren().remove(user));

            sequentialTransition.getChildren().add(translateTransition);
            sequentialTransition.getChildren().add(fadeTransition);
        }
        sequentialTransition.setOnFinished(callback);
        sequentialTransition.play();
    }

    private ImageView createRandomUser() {
        boolean female = Math.random() > .5;
        URL url = getClass().getResource("/main/" + (female ? "wo" : "") + "man_standing.png");
        if (url == null)
            throw new RuntimeException();
        ImageView imageView = new ImageView(new Image("file://" + url.getPath()));
        imageView.setFitWidth(USER_WIDTH);
        imageView.setPreserveRatio(true);
        imageView.setOpacity(0.0);
        return imageView;
    }
}
