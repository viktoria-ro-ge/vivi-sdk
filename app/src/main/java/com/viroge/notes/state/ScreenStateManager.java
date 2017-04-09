package com.viroge.notes.state;

public class ScreenStateManager extends StateManager {
    // This is a singleton class
    private static final ScreenStateManager self = new ScreenStateManager();

    private int currentState = States.SCREEN_HOME;

    private ScreenStateManager() {
        // keep private
    }

    public static ScreenStateManager getInstance() {
        return self;
    }

    @Override
    public boolean setState(final int newState) {
        // TODO make necessary checks that this new state can be set
        if (false) {
            return false;
        }
        this.currentState = newState;
        return true;
    }

    @Override
    public int getState() {
        return currentState;
    }
}
