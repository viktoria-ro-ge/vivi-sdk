package com.viroge.notes.presentation.content.drag_n_drop.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.viroge.notes.presentation.content.drag_n_drop.DragNDropListener;
import com.viroge.notes.presentation.content.drag_n_drop.model.DragNDropNoteModel;
import com.viroge.notes.examples.R;
import com.viroge.notes.state.States;
import com.viroge.notes.state.ToolbarStateManager;

public class DragNDropNoteView extends LinearLayout {

    private TextView title;
    private TextView freeText;
    private ImageView reorderButton;

    private DragNDropNoteModel itemModel;
    private DragNDropListener startDragListener;

    private OnClickListener clickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (startDragListener != null) {
                startDragListener.onOpenDetails(itemModel);
            }
        }
    };

    public DragNDropNoteView(Context context) {
        super(context);
    }

    public DragNDropNoteView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DragNDropNoteView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DragNDropNoteView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        this.title = (TextView) findViewById(R.id.dnd_title);
        this.freeText = (TextView) findViewById(R.id.dnd_free_text);
        this.reorderButton = (ImageView) findViewById(R.id.dnd_reorder_button);
    }

    public void bind(final DragNDropNoteModel itemModel,
                     final RecyclerView.ViewHolder holder,
                     final DragNDropListener startDragListener) {
        this.itemModel = itemModel;
        this.startDragListener = startDragListener;

        // Set the data
        title.setText(itemModel.title);

        // TODO rework this to not create listener each time
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
        if (ToolbarStateManager.getInstance().getState() == States.TOOLBAR_NORMAL) {
            reorderButton.setVisibility(GONE);
            this.setOnClickListener(clickListener);

        } else {
            reorderButton.setVisibility(VISIBLE);
            this.setOnClickListener(null);
        }

        // Set the item specific data
        freeText.setText(itemModel.freeText);
    }
}
