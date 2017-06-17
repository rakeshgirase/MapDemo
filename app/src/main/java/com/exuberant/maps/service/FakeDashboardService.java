package com.exuberant.maps.service;

import com.exuberant.maps.model.Dashboard;
import com.exuberant.maps.model.DashboardRow;

public class FakeDashboardService implements DashboardService {

    @Override
    public Dashboard fetchQuickDashboard(){
        Dashboard dashboard = new Dashboard();
        dashboard.addItem(new DashboardRow(1L, "PIC1", "MUMBAI", "MH0100"));
        dashboard.addItem(new DashboardRow(2L, "PIC2", "SURAT", "MH0200"));
        dashboard.addItem(new DashboardRow(3L, "PIC3", "GOA", "MH0300"));
        return dashboard;
    }

}
