package com.viroge.notes.presentation.content.generic;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * This is a view holder that must be used from now on.
 *
 * Please do not put any view logic here.
 */
public final class GenericViewHolder extends RecyclerView.ViewHolder {

    /**
     * Instead of creating multiple ViewHolders for each type, not just
     * reuse this class and pass different integers that represent different
     * types.
     */
    private final int holderType;

    /**
     * Pass here your custom view.
     *
     * @param itemView a custom view that represents an item in the Adapter
     */
    public GenericViewHolder(final View itemView,
                             final int holderType) {
        super(itemView);
        itemView.setTag(this);
        this.holderType = holderType;
    }

    public int getHolderType() {
        return holderType;
    }
}
