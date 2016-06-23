package com.example.spal.todoapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    final String[] values = new String[] { "Android", "iPhone", "Windows Phone",
            "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
            "Linux", "Mint" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Poll poll = new Poll(values);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        // OnClickListener for FloatingActionButton
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    fabOnClick(poll);
                }
            });
        }

        /*** Now start with ListView stuff ***/
        // Initialize ListViewAdapter
        final ArrayAdapter<PollItem> adapter = new CustomAdapter(this, poll.asArray());

        // Initialize ListView
        final ListView lv= (ListView) findViewById(R.id.listView);

        if (lv != null) {

            lv.setAdapter(adapter);

            lv.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // Get item name (something from values list)
                        PollItem item = (PollItem) parent.getItemAtPosition(position);

                        // Update poll with 1 additional vote for the selected item.
                        poll.addVote(item.getValue());

                        // Notify the adapter of the change to update the listview.
                        adapter.notifyDataSetChanged();
                    }
                }
            );
        }
    }

    public void fabOnClick(Poll poll) {

        // Get the winner, dynamically create a message, then display a toast with information.
        List<PollItem> winners = poll.findWinner();

        // Variable to hold the message for the toast.
        String message = "";

        if (winners.size() == poll.asArray().length) {
            if (winners.get(0).getNumVotes() == 0) {
                message = "You haven't clicked an item yet";
            } else {
                message = "All items tied with " + winners.get(0).getNumVotes();
            }
        } else {
            if (winners.size() == 1) {
                // deal with only 1 winner
                PollItem winner = winners.get(0);
                message = winner.getValue() + " was chosen " + winner.getNumVotes();
                message += (winner.getNumVotes() > 1 ? " times": " time");
            } else {
                // deal with multiple winners
                for (int i = 0; i < winners.size(); i++) {
                    if (i == winners.size() - 1) {
                        if (winners.size() == 2) {
                            message += " ";
                        }

                        message += "and " + winners.get(i) + " were chosen " +
                                winners.get(i).getNumVotes();
                        message += (winners.get(0).getNumVotes() > 1 ? " times": " time");
                    } else {
                        message += winners.get(i);
                        if (winners.size() != 2) {
                            message += ", ";
                        }
                    }
                }
            }
        }

        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
