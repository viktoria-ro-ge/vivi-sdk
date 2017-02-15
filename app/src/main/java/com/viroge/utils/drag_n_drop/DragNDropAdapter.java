package com.viroge.utils.drag_n_drop;

import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viroge.utils.drag_n_drop.model.DragNDropItemModel;
import com.viroge.utils.reorder.ReorderUtil;
import com.viroge.utils.drag_n_drop.model.DragNDropCategoryModel;
import com.viroge.utils.drag_n_drop.view.DragNDropItemView;
import com.viroge.utils.drag_n_drop.view.DragNDropCategoryView;
import com.viroge.utils.generic.GenericViewHolder;
import com.viroge.utils.examples.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DragNDropAdapter extends RecyclerView.Adapter<GenericViewHolder> {

    private static final int TYPE_CATEGORY = 0;
    private static final int TYPE_ITEM = 1;

    private final ArrayList<Parcelable> itemsList = new ArrayList<>();
    private DragNDropListener itemListener;

    private int currentState = ReorderUtil.MENU_STATE_NORMAL;

    public void setItems(final List<Parcelable> items) {
        itemsList.clear();
        itemsList.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (itemsList.get(position) instanceof DragNDropCategoryModel) {
            return TYPE_CATEGORY;
        }
        return TYPE_ITEM;
    }

    @Override
    public GenericViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view;
        switch (viewType) {
            case TYPE_CATEGORY:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drag_n_drop_category, parent, false);
                break;

            case TYPE_ITEM:
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drag_n_drop_item, parent, false);
                break;
        }
        return new GenericViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(GenericViewHolder holder, final int position) {
        // Bind the new data to the item
        switch (holder.getHolderType()) {
            case TYPE_CATEGORY:
                final DragNDropCategoryView portfolioItem = (DragNDropCategoryView) holder.itemView;
                portfolioItem.bind((DragNDropCategoryModel) itemsList.get(position), holder, itemListener, currentState);
                break;

            case TYPE_ITEM:
                final DragNDropItemView accountItem = (DragNDropItemView) holder.itemView;
                accountItem.bind((DragNDropItemModel) itemsList.get(position), holder, itemListener, currentState);
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public List<Parcelable> getItems() {
        return itemsList;
    }

    public boolean onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(itemsList, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(itemsList, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    public void setOnStartDragListener(final DragNDropListener startDragListener) {
        this.itemListener = startDragListener;
    }

    public void setMenuState(final int currentState) {
        this.currentState = currentState;
        notifyDataSetChanged();
    }
}
