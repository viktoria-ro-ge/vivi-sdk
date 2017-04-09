package com.viroge.notes.presentation.side_menu;

import java.util.ArrayList;
import java.util.List;

public class NavigationUtils {

    public static List<NavigationItem> getMenuItems() {
        final List<NavigationItem> result = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            result.add(new NavigationItem("Title " + (i + 1)));
        }
        return result;
    }
}
