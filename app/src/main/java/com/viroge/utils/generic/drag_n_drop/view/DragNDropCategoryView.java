package com.viroge.utils.generic.drag_n_drop.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.viroge.utils.generic.drag_n_drop.DragNDropListener;
import com.viroge.utils.generic.drag_n_drop.model.DragNDropCategoryModel;

public class DragNDropCategoryView extends DragNDropView {

    public DragNDropCategoryView(Context context) {
        super(context);
    }

    public DragNDropCategoryView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DragNDropCategoryView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DragNDropCategoryView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void bind(final DragNDropCategoryModel categoryModel,
                     final RecyclerView.ViewHolder holder,
                     final DragNDropListener startDragListener,
                     final int currentModeState) {
        super.bind(categoryModel, categoryModel.title, holder, startDragListener, currentModeState);
    }
}
