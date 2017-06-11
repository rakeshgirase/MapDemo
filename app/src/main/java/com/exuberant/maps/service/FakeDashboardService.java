package com.exuberant.maps.service;

import com.exuberant.maps.model.Dashboard;

public class FakeDashboardService implements DashboardService {

    @Override
    public Dashboard fetchQuickDashboard(){
        Dashboard dashboard = new Dashboard();

        return dashboard;
    }

}
