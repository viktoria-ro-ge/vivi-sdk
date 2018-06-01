package com.viroge.utils.generic.drag_n_drop.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.viroge.utils.R;
import com.viroge.utils.generic.drag_n_drop.DragNDropListener;
import com.viroge.utils.generic.drag_n_drop.model.DragNDropItemModel;

public class DragNDropItemView extends DragNDropView {

    private View selectedDivider;
    private TextView subtitle;
    private TextView date;

    public DragNDropItemView(Context context) {
        super(context);
    }

    public DragNDropItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DragNDropItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        selectedDivider = findViewById(R.id.dnd_account_selected_divider);
        subtitle = findViewById(R.id.dnd_account_subtitle);
        date = findViewById(R.id.dnd_account_amount);
    }

    public void bind(final DragNDropItemModel itemModel,
                     final RecyclerView.ViewHolder holder,
                     final DragNDropListener startDragListener,
                     final int currentModeState) {
        super.bind(itemModel, itemModel.title, holder, startDragListener, currentModeState);

        // Set the item specific data
        subtitle.setText(itemModel.subtitle);
        date.setText(itemModel.date);
        setHidden(itemModel.isHidden);
    }

    public void setHidden(final boolean isHidden) {
        if (isHidden) {
            // Set colors to gray and hide selected divider
            selectedDivider.setVisibility(GONE);
            title.setTextColor(Color.GRAY);
            subtitle.setTextColor(Color.GRAY);
            date.setTextColor(Color.GRAY);

        } else {
            // The opposite
            selectedDivider.setVisibility(VISIBLE);
            title.setTextColor(Color.BLACK);
            subtitle.setTextColor(Color.BLACK);
            date.setTextColor(Color.BLACK);
        }
    }
}
