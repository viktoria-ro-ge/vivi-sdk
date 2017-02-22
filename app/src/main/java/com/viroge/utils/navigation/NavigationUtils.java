package com.viroge.utils.navigation;

import java.util.ArrayList;
import java.util.List;

class NavigationUtils {

    static List<NavigationItem> getMenuItems() {
        final List<NavigationItem> result = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            result.add(new NavigationItem("Title " + (i + 1)));
        }
        return result;
    }
}
