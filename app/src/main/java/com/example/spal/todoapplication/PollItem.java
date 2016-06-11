package com.example.spal.todoapplication;

public class PollItem {

    private String mValue;
    private int mNumVotes;

    public PollItem(String value) {
        setValue(value);
        setNumVotes(0);
    }

    public PollItem(String value, int numVotes) {
        setValue(value);
        setNumVotes(numVotes);
    }

    public void addVote() {
        mNumVotes++;
    }

    public void setValue(String value) {
        mValue = value;
    }

    public void setNumVotes(int numVotes) {
        mNumVotes = numVotes;
    }

    public String getValue() {
        return mValue;
    }

    public int getNumVotes() {
        return mNumVotes;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
