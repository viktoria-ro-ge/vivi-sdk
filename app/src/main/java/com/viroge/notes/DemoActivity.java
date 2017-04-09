package com.viroge.notes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.viroge.notes.examples.R;
import com.viroge.notes.presentation.Notifier;
import com.viroge.notes.presentation.Presenter;
import com.viroge.notes.presentation.PresenterManager;
import com.viroge.notes.state.States;
import com.viroge.notes.state.ScreenStateManager;
import com.viroge.notes.state.ToolbarStateManager;

public class DemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        PresenterManager.getInstance().setToolbarPresenter((Presenter) findViewById(R.id.app_bar_layout));
        PresenterManager.getInstance().setSideMenuPresenter((Presenter) findViewById(R.id.navigation_drawer));
        PresenterManager.getInstance().setContentPresenter((Presenter) findViewById(R.id.personalization_of_accounts_root));

        PresenterManager.getInstance().setNotifier(notifier);

        // Set initial states:
        ToolbarStateManager.getInstance().setState(States.TOOLBAR_NORMAL);
        ScreenStateManager.getInstance().setState(States.SCREEN_HOME);
    }

    private final Notifier notifier = new Notifier() {
        @Override
        public void onToolbarBackPressed() {
            boolean eventConsumed = PresenterManager.getInstance().goBack();
            if (!eventConsumed) {
                finish();
            }
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        PresenterManager.getInstance().pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        PresenterManager.getInstance().resume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        PresenterManager.getInstance().stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PresenterManager.getInstance().destroy();
    }

    @Override
    public void onBackPressed() {
        boolean eventConsumed = PresenterManager.getInstance().goBack();
        if (!eventConsumed) {
            super.onBackPressed();
        }
    }
}
