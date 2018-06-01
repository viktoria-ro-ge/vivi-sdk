package com.viroge.utils.notesapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.viroge.utils.R;
import com.viroge.utils.notesapp.reorder.ReorderRootView;

public class DemoActivity extends AppCompatActivity {

    private ReorderRootView personalizationOfAccountsRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.na_activity_demo);

        personalizationOfAccountsRoot = findViewById(R.id.personalization_of_accounts_root);
    }

    @Override
    public void onBackPressed() {
        final boolean eventConsumed = personalizationOfAccountsRoot.openPreviousScreen();
        if (!eventConsumed) {
            super.onBackPressed();
        }
    }
}