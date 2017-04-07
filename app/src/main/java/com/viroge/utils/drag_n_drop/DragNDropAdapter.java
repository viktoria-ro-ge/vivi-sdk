package com.viroge.utils.drag_n_drop;

import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viroge.utils.reorder.ReorderUtil;
import com.viroge.utils.drag_n_drop.model.DragNDropNoteModel;
import com.viroge.utils.drag_n_drop.view.DragNDropNoteView;
import com.viroge.utils.generic.GenericViewHolder;
import com.viroge.utils.examples.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DragNDropAdapter extends RecyclerView.Adapter<GenericViewHolder> {

    public static final int TYPE_ADD_NEW = 0;
    public static final int TYPE_NOTE = 1;

    private final ArrayList<Parcelable> itemsList = new ArrayList<>();
    private DragNDropListener itemListener;

    private int currentState = ReorderUtil.MENU_STATE_NORMAL;

    public void setItems(final List<Parcelable> items) {
        itemsList.clear();
        itemsList.addAll(items);
        itemsList.add(null); // for the button ADD NEW
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (itemsList.get(position) instanceof DragNDropNoteModel) {
            return TYPE_NOTE;
        }
        return TYPE_ADD_NEW;
    }

    @Override
    public GenericViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view;
        switch (viewType) {
            case TYPE_NOTE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drag_n_drop_note, parent, false);
                break;

            case TYPE_ADD_NEW:
            default:
                // TODO LATER change this one to an "add new" item
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drag_n_drop_add_new, parent, false);
                break;
        }
        return new GenericViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(GenericViewHolder holder, final int position) {
        // Bind the new data to the item
        switch (holder.getHolderType()) {
            case TYPE_NOTE:
                final DragNDropNoteView view = (DragNDropNoteView) holder.itemView;
                view.bind((DragNDropNoteModel) itemsList.get(position), holder, itemListener, currentState);
                break;

            case TYPE_ADD_NEW:
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
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
