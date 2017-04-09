package com.viroge.notes.presentation.toolbar;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;

import com.viroge.notes.examples.R;
import com.viroge.notes.presentation.Notifier;
import com.viroge.notes.presentation.Presenter;
import com.viroge.notes.state.States;
import com.viroge.notes.state.ScreenStateManager;
import com.viroge.notes.state.ToolbarStateManager;

public class ToolbarPresenter extends AppBarLayout implements Presenter {

    private Notifier notifier;
    private Toolbar toolbar;

    public ToolbarPresenter(Context context) {
        super(context);
    }

    public ToolbarPresenter(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifier.onToolbarBackPressed();
            }
        });
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
        if (ToolbarStateManager.getInstance().getState() != States.TOOLBAR_NORMAL) {
            ToolbarStateManager.getInstance().setState(States.TOOLBAR_NORMAL);
            return true;
        }
        return false;
    }

    @Override
    public void setNotifier(Notifier notifier) {
        this.notifier = notifier;
    }

    @Override
    public void updateOnToolbarStateChanged() {
        final int navigationIconRes;
        final int menuItemIconRes;
        switch (ToolbarStateManager.getInstance().getState()) {
            case States.TOOLBAR_REORDER:
                navigationIconRes = R.drawable.ic_action_clear;
                menuItemIconRes = R.drawable.ic_action_check;
                break;

            case States.TOOLBAR_NORMAL:
            default:
                navigationIconRes = R.drawable.ic_action_arrow_back;
                menuItemIconRes = R.drawable.ic_action_reorder;
                break;
        }
        ToolbarUtil.setToolbarNavigationIcon(toolbar, navigationIconRes);
        ToolbarUtil.setToolbarMenu(toolbar, R.menu.menu_reorder);
        // Prepare the mode switch menu item
        final MenuItem menuItem = toolbar.getMenu().findItem(R.id.action_switch_mode);
        menuItem.setIcon(menuItemIconRes);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (ToolbarStateManager.getInstance().getState() == States.TOOLBAR_NORMAL) {
                    ToolbarStateManager.getInstance().setState(States.TOOLBAR_REORDER);
                } else {
                    ToolbarStateManager.getInstance().setState(States.TOOLBAR_NORMAL);
                }
                return true;
            }
        });
    }

    @Override
    public void updateOnScreenStateChanged() {
        final String title;
        switch (ScreenStateManager.getInstance().getState()) {
            case States.SCREEN_HOME:
                title = ToolbarUtil.TOOLBAR_TITLE_CATEGORIES;
                break;

            case States.SCREEN_NOTE_DETAILS:
                title = ToolbarUtil.TOOLBAR_TITLE_ITEM_DETAILS;
                break;

            default:
                title = ToolbarUtil.TOOLBAR_TITLE_CATEGORIES;
                break;
        }
        ToolbarUtil.setToolbarTitle(toolbar, title);
    }
}
