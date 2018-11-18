package com.example.android.marchingbanddotbook;

public class Set {

    private String mSetName;
    private String mNotes;
    private int mImageResourceId;

    public Set(String setName, String notes, int imageResourceId) {
        mSetName = setName;
        mNotes = notes;
        mImageResourceId = imageResourceId;
    }

    public String getSetName() {return mSetName;}

    public String getNotes() {return mNotes;}

    public int getImageResourceId() {return mImageResourceId;}
}
