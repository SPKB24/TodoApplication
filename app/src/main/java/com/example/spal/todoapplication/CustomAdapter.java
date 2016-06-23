package com.example.spal.todoapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

class CustomAdapter extends ArrayAdapter< PollItem > {

    CustomAdapter(Context context, PollItem[] poll) {
        super(context, R.layout.custom_row, poll);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.from(getContext()).inflate(R.layout.custom_row, parent, false);

        PollItem item = getItem(position);

        // Get TextViews for Header and Subtitle
        TextView headTextItem = (TextView) view.findViewById(R.id.textViewHeader);
        TextView subTextItem = (TextView) view.findViewById(R.id.textViewSubTitle);

        // Populate both TextViews
        headTextItem.setText(item.getValue());
        //subTextItem.setText(5);

        return view;
    }
}
