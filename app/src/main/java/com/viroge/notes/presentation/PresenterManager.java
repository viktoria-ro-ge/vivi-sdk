package com.viroge.notes.presentation;

public class PresenterManager implements Presenter {

    private static final PresenterManager self = new PresenterManager();

    private Notifier notifier;
    private Presenter toolbarPresenter;
    private Presenter sideMenuPresenter;
    private Presenter contentPresenter;

    private PresenterManager() {
        // keep private
    }

    public static PresenterManager getInstance() {
        return self;
    }

    @Override
    public void setNotifier(Notifier notifier) {
        this.notifier = notifier;
        toolbarPresenter.setNotifier(notifier);
        sideMenuPresenter.setNotifier(notifier);
        contentPresenter.setNotifier(notifier);
    }

    public void setToolbarPresenter(Presenter toolbarPresenter) {
        this.toolbarPresenter = toolbarPresenter;
    }

    public void setSideMenuPresenter(Presenter sideMenuPresenter) {
        this.sideMenuPresenter = sideMenuPresenter;
    }

    public void setContentPresenter(Presenter contentPresenter) {
        this.contentPresenter = contentPresenter;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public boolean goBack() {
        boolean eventConsumed = sideMenuPresenter.goBack();
        if (!eventConsumed) {
            eventConsumed = toolbarPresenter.goBack();
        }
        if (!eventConsumed) {
            eventConsumed = contentPresenter.goBack();
        }
        if (!eventConsumed) {
            // TODO temp
            contentPresenter.onError("Some error");
        }
        return eventConsumed;
    }

    @Override
    public void updateOnToolbarStateChanged() {
        toolbarPresenter.updateOnToolbarStateChanged();
        sideMenuPresenter.updateOnToolbarStateChanged();
        contentPresenter.updateOnToolbarStateChanged();
    }

    @Override
    public void updateOnScreenStateChanged() {
        toolbarPresenter.updateOnScreenStateChanged();
        sideMenuPresenter.updateOnScreenStateChanged();
        contentPresenter.updateOnScreenStateChanged();
    }
}
