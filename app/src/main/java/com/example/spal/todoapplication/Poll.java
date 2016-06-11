package com.example.spal.todoapplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Poll {

    List<PollItem> mItems = new ArrayList<>();

    /**
     * Constructor for data class, needs data values
     * @param data String array of values (names)
     */
    public Poll(String[] data) {

        if (data.length == 0) {
            System.out.println("Creating Poll with 0 items");
        }

        for (String item : data) {
            mItems.add( new PollItem(item) );
        }
    }

    public void addVote(String valueToAddTo) {

        for (PollItem item : mItems) {
            if (item.getValue().equalsIgnoreCase(valueToAddTo)) {
                item.addVote();
                break;
            }
        }
    }

    public PollItem[] asArray() {
        PollItem[] toReturn = new PollItem[mItems.size()];

        for (int i = 0; i < mItems.size(); i++) {
            toReturn[i] = mItems.get(i);
        }

        return toReturn;
    }

    public PollItem findWinner() {

        Map<PollItem, Integer> counter = new HashMap<>();
        List<PollItem> winners = new ArrayList<>();

        for (PollItem item : mItems) {


        }


        return new PollItem("Null");
    }
}
