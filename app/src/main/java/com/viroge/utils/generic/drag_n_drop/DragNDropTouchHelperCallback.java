package com.viroge.utils.generic.drag_n_drop;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class DragNDropTouchHelperCallback extends ItemTouchHelper.Callback {

    private final DragNDropAdapter mAdapter;

    public DragNDropTouchHelperCallback(final DragNDropAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    @Override
    public int getMovementFlags(final RecyclerView recyclerView,
                                final RecyclerView.ViewHolder viewHolder) {
        final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        final int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(final RecyclerView recyclerView,
                          final RecyclerView.ViewHolder viewHolder,
                          final RecyclerView.ViewHolder target) {
        mAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(final RecyclerView.ViewHolder viewHolder,
                         final int direction) {
        // NOTE: Currently does nothing.
        // Could be used for swipe to delete in future.
    }
}