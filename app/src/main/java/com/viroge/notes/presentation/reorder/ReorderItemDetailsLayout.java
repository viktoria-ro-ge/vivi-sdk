package com.viroge.notes.presentation.reorder;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.viroge.notes.presentation.content.drag_n_drop.model.DragNDropNoteModel;
import com.viroge.notes.examples.R;

public class ReorderItemDetailsLayout extends RelativeLayout {

    private TextView itemTitle;
    private TextView itemSubtitle;
    private Button saveButton;

    private DragNDropNoteModel itemModel;

    public ReorderItemDetailsLayout(Context context) {
        super(context);
    }

    public ReorderItemDetailsLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ReorderItemDetailsLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ReorderItemDetailsLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        itemTitle = (TextView) findViewById(R.id.personalization_of_accounts_account_details_title);
        itemSubtitle = (TextView) findViewById(R.id.personalization_of_accounts_account_details_subtitle);
        saveButton = (Button) findViewById(R.id.personalization_of_accounts_account_details_save_button);
        saveButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO
            }
        });

        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true; // temporarily get all touch events when visible
            }
        });
    }

    public void bind(final DragNDropNoteModel itemModel) {
        this.itemModel = itemModel;

        if (itemModel != null) {
            setVisibility(VISIBLE);
            itemTitle.setText(itemModel.title);
            itemSubtitle.setText(itemModel.freeText);

        } else {
            setVisibility(GONE);
        }
    }

    public interface OnSaveListener {
        void onSave(DragNDropNoteModel itemModel);
    }
}
