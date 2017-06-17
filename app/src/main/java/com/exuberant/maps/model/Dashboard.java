package com.exuberant.maps.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dashboard {

    private Map<Status, Integer> runningStatus = new HashMap<>();

    private List<DashboardRow> items = new ArrayList<>();

    public void addItem(DashboardRow dashboardRow) {
        items.add(dashboardRow);
    }

    public void addAllItems(List<DashboardRow> items) {
        items.addAll(items);
    }

    public List<DashboardRow> getItems() {
        return items;
    }

    public int size(){
        return items.size();
    }

    public DashboardRow get(int position) {
        return items.get(position);
    }

    public long indexOf(DashboardRow item) {
        return items.indexOf(item);
    }

    public long getItemId(int position) {
        return items.get(position).getId();
    }
}
