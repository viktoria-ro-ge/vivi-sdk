package com.viroge.utils.generic;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.TextView;

import com.viroge.utils.examples.R;

/**
 * ToolbarUtil is just a simple helper which provides easy update and
 * configuration for different types of toolbars.
 */
public class ToolbarUtil {

    public static void setToolbarTitle(final Toolbar toolbar,
                                       final String newTitle) {
        // A text view is contained within the toolbar to allow easier customization
        final TextView titleTextView = (TextView) toolbar.findViewById(R.id.toolbar_title);
        if (!TextUtils.isEmpty(newTitle) && titleTextView != null) {
            titleTextView.setText(newTitle);
            toolbar.setTitle("");
        }
    }

    public static void setToolbarMenu(final Toolbar toolbar,
                                      final int menu) {
        toolbar.getMenu().clear();
        toolbar.inflateMenu(menu);
    }

    public static void removeToolbarMenu(final Toolbar toolbar) {
        toolbar.getMenu().clear();
    }

    public static void setToolbarNavigationIcon(final Toolbar toolbar,
                                                final int navIco) {
        if (navIco > 0) {
            toolbar.setNavigationIcon(navIco);
        }
    }
}
