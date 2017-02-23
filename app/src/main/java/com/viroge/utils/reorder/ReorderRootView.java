package com.viroge.utils.reorder;

import android.content.Context;
import android.os.Parcelable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;

import com.viroge.utils.examples.R;
import com.viroge.utils.drag_n_drop.DragNDropListener;
import com.viroge.utils.drag_n_drop.DragNDropAdapter;
import com.viroge.utils.drag_n_drop.DragNDropTouchHelperCallback;
import com.viroge.utils.drag_n_drop.model.DragNDropNoteModel;
import com.viroge.utils.generic.ToolbarUtil;

import java.util.ArrayList;

public class ReorderRootView extends CoordinatorLayout {

    // Provide hard coded TITLES for now for the categories and the items
    private static final String NOTES[] = new String[]{
            "Food", "Clothes", "Expenses", "Household Related", "Fun", "Travel", "Kids", "Art", "Books", "Sport", "Technology"
    };

    private final ReorderItemDetailsLayout.OnSaveListener onSaveListener =
            new ReorderItemDetailsLayout.OnSaveListener() {
                @Override
                public void onSave(DragNDropNoteModel note) {
                    // TODO later will actually save the data
                    openPreviousScreen();
                }
            };

    private final ArrayList<Parcelable> categoryModels = new ArrayList<>();

    private int menuState = ReorderUtil.MENU_STATE_NORMAL;
    private int toolbarMode = ReorderUtil.TOOLBAR_MODE_NOTES;

    private Toolbar toolbar;
    private ReorderHeader categoryHeader;
    private ReorderItemDetailsLayout reorderItemDetailsLayout;
    private DragNDropAdapter adapter;

    public ReorderRootView(Context context) {
        super(context);
    }

    public ReorderRootView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ReorderRootView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPreviousScreen();
            }
        });
        updateToolbar(ReorderUtil.TOOLBAR_MODE_NOTES);

        categoryHeader = (ReorderHeader) findViewById(R.id.personalization_of_accounts_portfolio_header);
        reorderItemDetailsLayout = (ReorderItemDetailsLayout) findViewById(R.id.personalization_of_accounts_account_details_layout);

        final RecyclerView portfoliosList = (RecyclerView) findViewById(R.id.personalization_of_accounts_root_recycler);
        portfoliosList.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new DragNDropAdapter();

        // Prepare the Notes
        for (String note : NOTES) {
            final DragNDropNoteModel mask = new DragNDropNoteModel(note, "Temp useless text");
            categoryModels.add(mask);
        }

        // Initially prepare the adapter with CATEGORIES
        adapter.setItems(categoryModels);
        categoryHeader.bind(null); // No portfolio to bound to the header - List all CATEGORIES
        portfoliosList.setAdapter(adapter);

        final ItemTouchHelper.Callback callback = new DragNDropTouchHelperCallback(adapter);
        final ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(portfoliosList);

        // Prepare the trigger
        adapter.setOnStartDragListener(new DragNDropListener() {
            @Override
            public void onOpenDetails(Parcelable item) {
                if (menuState == ReorderUtil.MENU_STATE_NORMAL) {
                    // Open NOTE edit
                    reorderItemDetailsLayout.bind((DragNDropNoteModel) item, onSaveListener);
                    updateToolbar(ReorderUtil.TOOLBAR_MODE_DETAILS);
                }
            }

            @Override
            public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
                if (menuState == ReorderUtil.MENU_STATE_REORDER) {
                    touchHelper.startDrag(viewHolder);
                }
            }
        });

        // Set initial state - normal
        setMenuState(ReorderUtil.MENU_STATE_NORMAL);
    }

    public void toggleModeState() {
        if (menuState == ReorderUtil.MENU_STATE_NORMAL) {
            menuState = ReorderUtil.MENU_STATE_REORDER;
        } else {
            menuState = ReorderUtil.MENU_STATE_NORMAL;
        }
        adapter.setMenuState(menuState);
    }

    public void setMenuState(final int menuState) {
        this.menuState = menuState;
        adapter.setMenuState(menuState);
    }

    public boolean openPreviousScreen() {
        if (menuState != ReorderUtil.MENU_STATE_NORMAL) {
            setMenuState(ReorderUtil.MENU_STATE_NORMAL);
            return true;

        } else if (reorderItemDetailsLayout.isShown()) {
            reorderItemDetailsLayout.bind(null, null);
            updateToolbar(ReorderUtil.TOOLBAR_MODE_NOTES);
            return true;
        }
        return false;
    }

    private void updateToolbar(final int toolbarMode) {
        this.toolbarMode = toolbarMode;

        final int navigationIconRes;
        final int menuItemIconRes;
        switch (menuState) {
            case ReorderUtil.MENU_STATE_REORDER:
                navigationIconRes = R.drawable.ic_action_clear;
                menuItemIconRes = R.drawable.ic_action_check;
                break;

            case ReorderUtil.MENU_STATE_NORMAL:
            default:
                navigationIconRes = R.drawable.ic_action_arrow_back;
                menuItemIconRes = R.drawable.ic_action_reorder;
                break;
        }
        switch (toolbarMode) {
            case ReorderUtil.TOOLBAR_MODE_DETAILS:
                updateToolbar(
                        navigationIconRes,
                        ReorderUtil.TOOLBAR_TITLE_ITEM_DETAILS,
                        true,
                        menuItemIconRes);
                break;

            case ReorderUtil.TOOLBAR_MODE_NOTES:
                updateToolbar(
                        navigationIconRes,
                        ReorderUtil.TOOLBAR_TITLE_CATEGORIES,
                        true,
                        menuItemIconRes);
                break;
        }
    }

    private void updateToolbar(final int navigationIconRes,
                               final String title,
                               final boolean showMenu,
                               final int menuItemIconRes) {
        ToolbarUtil.setToolbarTitle(toolbar, title);
        ToolbarUtil.setToolbarNavigationIcon(toolbar, navigationIconRes);
        if (showMenu) {
            ToolbarUtil.setToolbarMenu(toolbar, R.menu.menu_reorder);
            // Prepare the mode switch menu item
            final MenuItem menuItem = toolbar.getMenu().findItem(R.id.action_switch_mode);
            menuItem.setIcon(menuItemIconRes);
            menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    final boolean shouldSaveState;
                    if (menuState == ReorderUtil.MENU_STATE_NORMAL) {
                        menuState = ReorderUtil.MENU_STATE_REORDER;
                        shouldSaveState = false;

                    } else {
                        menuState = ReorderUtil.MENU_STATE_NORMAL;
                        shouldSaveState = true;
                    }
                    adapter.setMenuState(menuState);

                    // Will not change the state, only the icons
                    updateToolbar(toolbarMode);

//                    if (shouldSaveState) {
//                        // Check the screen state to know what to save exactly
//                        if (categoryHeader.isShown()) {
//                            // Meaning we have a specific portfolio and its accounts open currently
//                            eventListener.onSaveItemsState(adapter.getItems());
//
//                        } else {
//                            eventListener.onSaveCategoriesState(adapter.getItems());
//                        }
//                    }
                    return true;
                }
            });

        } else {
            ToolbarUtil.removeToolbarMenu(toolbar);
        }
    }
}
