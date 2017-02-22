package com.viroge.utils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.viroge.utils.examples.R;
import com.viroge.utils.navigation.NavigationDrawer;
import com.viroge.utils.reorder.ReorderRootView;

public class DemoActivity extends AppCompatActivity {

    private ReorderRootView personalizationOfAccountsRoot;

    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            parent.getAdapter();
            view.isFocused();
            Log.d("", "all: " + position + " - " + id);

            // todo see what to hook here
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        this.personalizationOfAccountsRoot = (ReorderRootView) findViewById(R.id.personalization_of_accounts_root);

        final NavigationDrawer navigationDrawer = (NavigationDrawer) findViewById(R.id.navigation_drawer);
        navigationDrawer.bind(personalizationOfAccountsRoot, itemClickListener);
    }

    @Override
    public void onBackPressed() {
        final boolean eventConsumed = personalizationOfAccountsRoot.openPreviousScreen();
        if (!eventConsumed) {
            super.onBackPressed();
        }
    }
}
