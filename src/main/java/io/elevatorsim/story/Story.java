package io.elevatorsim.story;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public abstract class Story {
    protected Action action;
    protected int floorNb;
    protected Story nextStory;

    public void execute() { execute((ActionEvent event) -> {}); }
    public abstract void execute(EventHandler<ActionEvent> callback);

    public boolean executeNext() {
        if (nextStory == null)
            return false;
        else
            nextStory.execute((ActionEvent) -> nextStory.executeNext());
        return true;
    }

    public Action getAction() { return action; }
    public int getFloorNb() { return floorNb; }

    public void setNextStory(Story nextStory) { this.nextStory = nextStory; }
}
