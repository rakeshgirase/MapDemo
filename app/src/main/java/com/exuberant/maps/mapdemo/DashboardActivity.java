package com.exuberant.maps.mapdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import static com.exuberant.maps.mapdemo.R.id.trackables;

public class DashboardActivity extends AppCompatActivity {

    private  ArrayAdapter<String> adapter;
    private int clickCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                trackables);
        addItems();
    }

    public void addItems() {
        adapter.add("Hello");
        adapter.add("Frien");
    }
}
