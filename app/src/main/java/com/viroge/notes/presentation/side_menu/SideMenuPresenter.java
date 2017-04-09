package com.viroge.notes.presentation.side_menu;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.viroge.notes.presentation.Notifier;
import com.viroge.notes.presentation.Presenter;
import com.viroge.notes.presentation.side_menu.NavigationItemsAdapter;
import com.viroge.notes.presentation.side_menu.NavigationUtils;
import com.viroge.notes.examples.R;

public class SideMenuPresenter extends DrawerLayout
        implements Presenter, NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener {

    private Notifier notifier;
    private CoordinatorLayout coordinatorLayout;
    private ListView drawerList;

    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            parent.getAdapter();
            view.isFocused();
            Log.d("", "all: " + position + " - " + id);

            // todo see what to hook here
        }
    };

    public SideMenuPresenter(Context context) {
        super(context);
    }

    public SideMenuPresenter(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SideMenuPresenter(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.addDrawerListener(this);

        this.drawerList = (ListView) findViewById(R.id.navigation_drawer_menu);
        final NavigationItemsAdapter adapter = new NavigationItemsAdapter(getContext(), NavigationUtils.getMenuItems());
        drawerList.setAdapter(adapter);
    }

    public void bind(final CoordinatorLayout coordinatorLayout,
                     final AdapterView.OnItemClickListener itemClickListener) {
        this.coordinatorLayout = coordinatorLayout;

        drawerList.setOnItemClickListener(itemClickListener);
    }

    @Override
    public void onDrawerSlide(View drawerView, float offset) {
        if (coordinatorLayout != null) {
            coordinatorLayout.setTranslationX(offset * drawerView.getWidth());
        }
    }

    @Override
    public void onDrawerOpened(View drawerView) {

    }

    @Override
    public void onDrawerClosed(View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
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
        return false;
    }

    @Override
    public void setNotifier(Notifier notifier) {
        this.notifier = notifier;
    }

    @Override
    public void updateOnToolbarStateChanged() {

    }

    @Override
    public void updateOnScreenStateChanged() {

    }
}
