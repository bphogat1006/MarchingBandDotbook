package com.example.android.marchingbanddotbook;

public class SheetMusic {

    private String mSongName = NO_SONG_LABEL_PROVIDED;
    private int mPart;
    private int mImageResourceId;
    private int mAudioResourceId;
    private int[] mTimestamps;

    private static final String NO_SONG_LABEL_PROVIDED = "1";

    public SheetMusic(String songName, int part, int imageResourceId, int audioResourceId, int[] timestamps) {
        mSongName = songName;
        mPart = part;
        mImageResourceId = imageResourceId;
        mAudioResourceId = audioResourceId;
        mTimestamps = timestamps;
    }

    public SheetMusic(int part, int imageResourceId) {
        mPart = part;
        mImageResourceId = imageResourceId;
    }

    public String getSongName() {return mSongName;}

    public int getPart() {return mPart;}

    public int getImageResourceId() {return mImageResourceId;}

    public int getAudioResourceId() {return mAudioResourceId;}

    public int[] getTimestamps() {return mTimestamps;}

    public boolean hasSongName() {return !mSongName.equals(NO_SONG_LABEL_PROVIDED);}
}
