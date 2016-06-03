package com.example.spal.todoapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

class CustomAdapter extends ArrayAdapter<String> {

    CustomAdapter(Context context, String[] devices) {
        super(context, R.layout.custom_row, devices);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.custom_row, parent, false);

        String header = getItem(position);

        // Get TextViews for Header and Subtitle
        TextView headTextItem = (TextView) view.findViewById(R.id.textViewHeader);
        TextView subTextItem = (TextView) view.findViewById(R.id.textViewSubTitle);

        // Populate both TextViews
        headTextItem.setText(header);
        subTextItem.setText("Current Counter: 0");

        return view;
    }


}
