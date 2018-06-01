package com.viroge.utils.notesapp.reorder;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.viroge.utils.R;
import com.viroge.utils.generic.drag_n_drop.model.DragNDropCategoryModel;

public class ReorderHeader extends LinearLayout {

    private TextView title;

    public ReorderHeader(Context context) {
        super(context);
    }

    public ReorderHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ReorderHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        title = findViewById(R.id.personalization_of_accounts_portfolio_title);
    }

    public void bind(final DragNDropCategoryModel categoryModel) {
        if (categoryModel == null) {
            this.setVisibility(GONE);

        } else {
            // Set the data
            title.setText(categoryModel.title);
            this.setVisibility(VISIBLE);
        }
    }
}
