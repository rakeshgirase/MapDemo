package com.exuberant.maps.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.exuberant.maps.mapdemo.R;
import com.exuberant.maps.model.DashboardRow;

import java.util.List;

public class DashboardAdapter extends BaseAdapter {

    private Context context;
    private List<DashboardRow> dashboardRows;

    public DashboardAdapter(Context context, List<DashboardRow> dashboardRows) {
        this.context = context;
        this.dashboardRows = dashboardRows;
    }

    private class ViewHolder{
        ImageView pic;
        TextView number;
        TextView city;

    }

    @Override
    public int getCount() {
        return dashboardRows.size();
    }

    @Override
    public Object getItem(int position) {
        return dashboardRows.get(position);
    }

    @Override
    public long getItemId(int position) {
        return dashboardRows.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView==null){
            convertView = mInflater.inflate(R.layout.trackable, null);
            viewHolder = new ViewHolder();
            viewHolder.pic = (ImageView) convertView.findViewById(R.id.trackable_pic);
            viewHolder.number = (TextView) convertView.findViewById(R.id.trackable_number);
            viewHolder.city = (TextView) convertView.findViewById(R.id.trackable_city);

            DashboardRow row = dashboardRows.get(position);
            viewHolder.pic.setImageResource(row.getPicAsResource());
            viewHolder.city.setText(row.getCity());
            viewHolder.number.setText(row.getNumber());
            convertView.setTag(viewHolder);
        }
        return convertView;
    }
}
