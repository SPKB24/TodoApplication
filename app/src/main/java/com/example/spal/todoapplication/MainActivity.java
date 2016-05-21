package com.example.spal.todoapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    final String[] values = new String[] { "Android", "iPhone", "Windows Phone",
            "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
            "Linux", "Mint" };

    Map<String, Integer> poll = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Map.Entry<String, Integer> maxEntry = new AbstractMap.SimpleEntry<>("null", 0);

                    // Iterate Map and find value with most clicks
                    for (Map.Entry<String, Integer> item : poll.entrySet()) {
                        if (maxEntry.getValue() < item.getValue()) {
                            maxEntry = item;
                        }
                    }

                    String message = maxEntry.getKey() + " was chosen " + maxEntry.getValue() + " times";

                    if (maxEntry.getValue() == 0 && maxEntry.getKey().equals("null")) {
                        message = "There was a tie";
                    }

                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            });
        }

        final ListView lv= (ListView) findViewById(R.id.listView);

        // Fill the map with all the values set at 0 clicks
        for (String value : values) {
            poll.put(value, 0);
        }

        final ArrayList<String> list = new ArrayList<>();
        for (String value : this.values) { list.add(value); }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, this.values);
        if (lv != null) {

            lv.setAdapter(adapter);

            lv.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String value = String.valueOf(parent.getItemAtPosition(position));
                        int count = poll.get(value);

                        // Add 1 click to the value that was clicked on
                        poll.put(value, ++count);
                    }
                }
            );
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
