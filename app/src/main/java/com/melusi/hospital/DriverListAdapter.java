package com.melusi.hospital;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

/**
 * TODO: document your custom view class.
 */
public class DriverListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> values;
    private ArrayList<String> hospitals;
    private ArrayList<Boolean> toggle_vals;
    private ArrayList<Boolean> reserved;

    public DriverListAdapter(Context context, ArrayList<String> vals, ArrayList<String> hosp,
                             ArrayList<Boolean> t, ArrayList<Boolean> res)  {
        this.values = vals;
        this.hospitals = hosp;
        this.toggle_vals = t;
        this.reserved = res;
        this.context = context;
    }

    public void Update(ArrayList<String> vals, ArrayList<String> hosp,
                       ArrayList<Boolean> t, ArrayList<Boolean> res) {
        this.values = vals;
        this.toggle_vals = t;
        this.reserved = res;
        this.hospitals = hosp;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public Object getItem(int position) {
        return values.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final NurseListAdapter.ViewHolder holder;

        if (convertView == null) {
            holder = new NurseListAdapter.ViewHolder(); LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_adapter, null, true);


            holder.text = (TextView) convertView.findViewById(R.id.list_text);
            holder.subtext = (TextView) convertView.findViewById(R.id.list_sub);
            holder.btn = (ToggleButton) convertView.findViewById(R.id.list_toggle);
            holder.btn_res = (ToggleButton) convertView.findViewById(R.id.list_reserved);
            holder.btn_res.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LoginActivity.reserved.set(position, !LoginActivity.reserved.get(position));
                }
            });

            holder.btn_res.setChecked(reserved.get(position));
            holder.btn.setChecked(toggle_vals.get(position));
            holder.btn.setClickable(false);
            holder.text.setText(hospitals.get(position));
            holder.subtext.setText(values.get(position));

            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (NurseListAdapter.ViewHolder) convertView.getTag();
        }

        return convertView;
    }
}
