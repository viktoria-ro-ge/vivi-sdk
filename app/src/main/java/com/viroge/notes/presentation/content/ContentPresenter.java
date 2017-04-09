package com.viroge.notes.presentation.content;

import android.content.Context;
import android.os.Parcelable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.AttributeSet;

import com.viroge.notes.examples.R;
import com.viroge.notes.presentation.Notifier;
import com.viroge.notes.presentation.Presenter;
import com.viroge.notes.presentation.content.drag_n_drop.DragNDropAdapter;
import com.viroge.notes.presentation.content.drag_n_drop.DragNDropListener;
import com.viroge.notes.presentation.content.drag_n_drop.DragNDropTouchHelperCallback;
import com.viroge.notes.presentation.content.drag_n_drop.model.DragNDropNoteModel;
import com.viroge.notes.presentation.reorder.ReorderHeader;
import com.viroge.notes.presentation.reorder.ReorderItemDetailsLayout;
import com.viroge.notes.state.States;
import com.viroge.notes.state.ScreenStateManager;
import com.viroge.notes.state.ToolbarStateManager;

import java.util.ArrayList;

public class ContentPresenter extends CoordinatorLayout implements Presenter {

    // Provide hard coded TITLES for now for the categories and the items
    private static final String NOTES[] = new String[]{
            "Food", "Clothes", "Expenses", "Household Related", "Fun", "Travel", "Kids", "Art", "Books", "Sport", "Technology"
    };

    private final ArrayList<Parcelable> categoryModels = new ArrayList<>();
    private final DragNDropAdapter adapter = new DragNDropAdapter();
    private ReorderItemDetailsLayout reorderItemDetailsLayout;
    private Notifier notifier;

    public ContentPresenter(Context context) {
        super(context);
    }

    public ContentPresenter(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ContentPresenter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        final ReorderHeader categoryHeader = (ReorderHeader) findViewById(R.id.personalization_of_accounts_portfolio_header);
        reorderItemDetailsLayout = (ReorderItemDetailsLayout) findViewById(R.id.personalization_of_accounts_account_details_layout);

        final RecyclerView portfoliosList = (RecyclerView) findViewById(R.id.personalization_of_accounts_root_recycler);
        portfoliosList.setLayoutManager(new GridLayoutManager(getContext(), 2));

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
                if (ToolbarStateManager.getInstance().getState() == States.TOOLBAR_NORMAL) {
                    // Open NOTE edit
                    reorderItemDetailsLayout.bind((DragNDropNoteModel) item);
                    ScreenStateManager.getInstance().setState(States.SCREEN_NOTE_DETAILS);
                }
            }

            @Override
            public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
                if (ToolbarStateManager.getInstance().getState() == States.TOOLBAR_REORDER) {
                    touchHelper.startDrag(viewHolder);
                }
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
        Snackbar.make(this, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public boolean goBack() {
        if (reorderItemDetailsLayout.isShown()) {
            reorderItemDetailsLayout.bind(null);
            // TODO switch screen
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
        adapter.notifyDataSetChanged();
    }

    @Override
    public void updateOnScreenStateChanged() {
        adapter.notifyDataSetChanged();
    }
}
