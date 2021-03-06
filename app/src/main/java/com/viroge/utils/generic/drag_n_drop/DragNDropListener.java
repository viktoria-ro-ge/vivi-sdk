package com.viroge.utils.generic.drag_n_drop;

import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;

public interface DragNDropListener {

    void onStartDrag(RecyclerView.ViewHolder viewHolder);

    void onOpenDetails(Parcelable item);
}
