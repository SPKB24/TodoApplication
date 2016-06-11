package com.example.spal.todoapplication;

import java.util.ArrayList;
import java.util.List;

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

    public List<PollItem> findWinner() {

        // Create list to hold winner (or winners in the case of a tie)
        List<PollItem> winners = new ArrayList<>();

        for (PollItem item : mItems) {
            if (winners.isEmpty() || (winners.get(0).getNumVotes() != 0 &&
                    winners.get(0).getNumVotes() == item.getNumVotes())) {
                winners.add(item);
            } else if (winners.get(0).getNumVotes() < item.getNumVotes()) {
                winners.clear();
                winners.add(item);
            }
        }

        // If the highest is 0, just return 0;
        if (winners.get(0).getNumVotes() == 0) {
            return mItems;
        }

        return winners;
    }
}
