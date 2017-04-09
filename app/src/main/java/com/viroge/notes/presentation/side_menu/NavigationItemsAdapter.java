package com.viroge.notes.presentation.side_menu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.viroge.notes.examples.R;

import java.util.List;

public class NavigationItemsAdapter extends ArrayAdapter<NavigationItem> {

    public NavigationItemsAdapter(final Context context,
                                  final List<NavigationItem> items) {
        super(context, 0, items);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        final NavigationItem navigationItem = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.navigation_item, parent, false);
        }
        final TextView title = (TextView) convertView.findViewById(R.id.navigation_item_title);
        title.setText(navigationItem.getTitle());

        return convertView;
    }
}