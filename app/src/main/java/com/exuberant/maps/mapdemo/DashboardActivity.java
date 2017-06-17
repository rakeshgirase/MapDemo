package com.exuberant.maps.mapdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.exuberant.maps.adapter.DashboardAdapter;
import com.exuberant.maps.model.Dashboard;
import com.exuberant.maps.model.DashboardRow;
import com.exuberant.maps.service.DashboardService;
import com.exuberant.maps.service.ServiceFactory;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.value;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class DashboardActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private  ArrayAdapter<String> adapter;

    private ListView trackables;
    private Dashboard dashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        DashboardService dashboardService = ServiceFactory.dashboardService();
        dashboard = dashboardService.fetchQuickDashboard();
        DashboardAdapter dashboardAdapter = new DashboardAdapter(getApplicationContext(), dashboard);
        trackables = (ListView) findViewById(R.id.trackables);
        trackables.setAdapter(dashboardAdapter);
        trackables.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String city = dashboard.get(position).getCity();
        Toast.makeText(getApplicationContext(), city, Toast.LENGTH_SHORT).show();
        Intent myIntent = new Intent(DashboardActivity.this, FirstMapsActivity.class);
        //myIntent.putExtra("key", value); //Optional parameters
        DashboardActivity.this.startActivity(myIntent);
    }
}
