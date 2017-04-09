package com.viroge.notes.state;

import com.viroge.notes.presentation.PresenterManager;

public class ToolbarStateManager extends StateManager {
    // This is a singleton class
    private static final ToolbarStateManager self = new ToolbarStateManager();

    private int currentState = States.SCREEN_HOME;

    private ToolbarStateManager() {
        // keep private
    }

    public static ToolbarStateManager getInstance() {
        return self;
    }

    @Override
    public boolean setState(final int newState) {
        // TODO make necessary checks that this new state can be set
        if (false) {
            return false;
        }
        this.currentState = newState;
        PresenterManager.getInstance().updateOnToolbarStateChanged();
        return true;
    }

    @Override
    public int getState() {
        return currentState;
    }
}
