package com.viroge.utils.generic.drag_n_drop.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Parcelable;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.viroge.utils.R;
import com.viroge.utils.generic.drag_n_drop.DragNDropListener;
import com.viroge.utils.notesapp.reorder.ReorderUtil;

public class DragNDropView extends LinearLayout {

    protected TextView title;
    protected ImageView detailsButton;
    protected ImageView reorderButton;

    public DragNDropView(Context context) {
        super(context);
    }

    public DragNDropView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DragNDropView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DragNDropView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        title = findViewById(R.id.dnd_title);
        detailsButton = findViewById(R.id.dnd_details_button);
        reorderButton = findViewById(R.id.dnd_reorder_button);
    }

    protected void bind(final Parcelable model,
                        final String titleText,
                        final RecyclerView.ViewHolder holder,
                        final DragNDropListener startDragListener,
                        final int currentModeState) {
        // Set the data
        title.setText(titleText);

        // Open the details screen whenever the details button it touched
        detailsButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startDragListener.onOpenDetails(model);
            }
        });

        // Start a drag whenever the reorder button it touched
        reorderButton.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    startDragListener.onStartDrag(holder);
                }
                return false;
            }
        });

        // Prepare the button to show according to the current mode
        if (currentModeState == ReorderUtil.MENU_STATE_NORMAL) {
            detailsButton.setVisibility(VISIBLE);
            reorderButton.setVisibility(GONE);

        } else {
            detailsButton.setVisibility(GONE);
            reorderButton.setVisibility(VISIBLE);
        }
    }
}
