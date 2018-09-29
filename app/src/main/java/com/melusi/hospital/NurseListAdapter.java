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
public class NurseListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> values;
    private ArrayList<String> hospitals;
    private ArrayList<Boolean> toggle_vals;

    public NurseListAdapter(Context context, ArrayList<String> vals, ArrayList<String> hosp, ArrayList<Boolean> t) {
        this.values = vals;
        this.hospitals = hosp;
        this.toggle_vals = t;
        this.context = context;
    }

    public void Update(ArrayList<String> vals, ArrayList<String> hosp, ArrayList<Boolean> t) {
        this.values = vals;
        this.toggle_vals = t;
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
        final ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder(); LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_adapter, null, true);


            holder.text = (TextView) convertView.findViewById(R.id.list_text);
            holder.subtext = (TextView) convertView.findViewById(R.id.list_sub);
            holder.btn = (ToggleButton) convertView.findViewById(R.id.list_toggle);
            holder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v = (ToggleButton) v;
                    LoginActivity.toggles.set(position, !LoginActivity.toggles.get(position));
                }
            });

            holder.btn.setChecked(toggle_vals.get(position));
            holder.text.setText(values.get(position));
            holder.text.setText(hospitals.get(position));
            holder.subtext.setText(values.get(position));

            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }

        return convertView;
    }

    private class ViewHolder {

        protected ToggleButton btn;
        private TextView text;
        private TextView subtext;

    }
}
