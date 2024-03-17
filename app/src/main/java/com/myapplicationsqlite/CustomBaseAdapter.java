package com.myapplicationsqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomBaseAdapter extends BaseAdapter {
    Context context;
    String names[];
    String emails[];

    LayoutInflater inflater;

    public CustomBaseAdapter(Context ctx, String[] names, String[] emails) {
        this.context = ctx;
        this.names = names;
        this.emails = emails;
        inflater = LayoutInflater.from(ctx);

    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.activity_custom_list_view, null);
        TextView tvName = (TextView) convertView.findViewById(R.id.itemName);
        TextView tvEmail = (TextView) convertView.findViewById(R.id.itemEmail);
        tvName.setText(names[position]);
        tvEmail.setText(emails[position]);
        return convertView;
    }
}
