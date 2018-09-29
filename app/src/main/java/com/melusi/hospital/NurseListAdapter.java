package com.melusi.hospital;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Random;

/**
 * TODO: document your custom view class.
 */
public class NurseListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> values;
    private ArrayList<String> hospitals;
    private ArrayList<Boolean> toggle_vals;
    private ArrayList<Boolean> reserved;

    public NurseListAdapter(Context context, ArrayList<String> vals, ArrayList<String> hosp,
                            ArrayList<Boolean> t, ArrayList<Boolean> res) {
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
        final ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder(); LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_adapter, null, true);


            holder.text = (TextView) convertView.findViewById(R.id.list_text);
            holder.subtext = (TextView) convertView.findViewById(R.id.list_sub);
            holder.btn = (ToggleButton) convertView.findViewById(R.id.list_toggle);
            holder.btn_res = (ToggleButton) convertView.findViewById(R.id.list_reserved);
            holder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v = (ToggleButton) v;

                    Boolean newVal = !LoginActivity.toggles.get(position);

                    // Hospital bed opened
                    if(!newVal) {
                        Vibrator vb = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                        vb.vibrate(100);

                        try {
                            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                            Ringtone r = RingtoneManager.getRingtone(context.getApplicationContext(), notification);
                            r.play();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        SendNotification();
                    }

                    LoginActivity.toggles.set(position, newVal);
                }
            });

            holder.btn_res.setClickable(false);
            holder.btn_res.setChecked(reserved.get(position));

            if(!reserved.get(position)) {
                holder.btn_res.setVisibility(View.INVISIBLE);
                holder.btn.setVisibility(View.VISIBLE);
            }
            else {
                holder.btn.setVisibility(View.INVISIBLE);
                holder.btn_res.setVisibility(View.VISIBLE);
            }

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

    private void SendNotification() {
        int notificationId = new Random().nextInt(60000);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(context, NurseView.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, "yes123")
                .setSmallIcon(R.drawable.ic_launcher_foreground)  //a resource for your custom small icon
                .setContentTitle("Vacancy") //the "title" value you sent in your notification
                .setContentText("A hospital bed has been labeled as vacant!") //ditto
//                .setAutoCancel(true)  //dismisses the notification on click
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("A hospital bed has been labeled as vacant!"))
                .setContentIntent(pendingIntent)
                .setSound(defaultSoundUri);

        notificationManager.notify(notificationId /* ID of notification */, notificationBuilder.build());
    }

    public static class ViewHolder {

        public ToggleButton btn, btn_res;
        public TextView text;
        public TextView subtext;

    }
}
