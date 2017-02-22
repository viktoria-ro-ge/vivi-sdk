package com.viroge.utils.navigation;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.viroge.utils.examples.R;

public class NavigationDrawer extends DrawerLayout implements NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener {

    private CoordinatorLayout coordinatorLayout;
    private ListView drawerList;

    public NavigationDrawer(Context context) {
        super(context);
    }

    public NavigationDrawer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NavigationDrawer(Context context, AttributeSet attrs, int defStyle) {
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
}
