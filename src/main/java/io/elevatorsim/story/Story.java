package io.elevatorsim.story;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public abstract class Story {
    protected Action action;
    protected int floorNb;

    public void execute() { execute((ActionEvent event) -> {}); }
    public abstract void execute(EventHandler<ActionEvent> callback);

    public Action getAction() { return action; }
    public int getFloorNb() { return floorNb; }
}
