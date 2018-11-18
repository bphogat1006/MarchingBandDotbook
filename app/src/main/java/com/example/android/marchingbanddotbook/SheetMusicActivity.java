package com.example.android.marchingbanddotbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SheetMusicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_list);

        LinearLayout jumpView = findViewById(R.id.select_set);
        jumpView.setVisibility(View.GONE);


        Map<String, int[]> timestamps = new HashMap<String, int[]>();
        timestamps.put("Song 1", new int[] {6175, 16822, 25893, 34990, 44250, 54725, 65400, 73340,
                84055, 89310, 95405, 107533, 116410, 125730, 133348, 144026});
        timestamps.put("Song 2", new int[] {});
        timestamps.put("Song 3", new int[] {});


        final ArrayList<SheetMusic> MusicSheets = new ArrayList<>();

        MusicSheets.add(new SheetMusic("Song 1", 1, R.drawable.song1_part1,
                R.raw.reflections_part_1, timestamps.get("Song 1")));
        MusicSheets.add(new SheetMusic(2, R.drawable.song1_part2));
        MusicSheets.add(new SheetMusic(3, R.drawable.song1_part3));

        MusicSheets.add(new SheetMusic("Song 2", 1, R.drawable.song2_part1,
                R.raw.reflections_part_2, timestamps.get("Song 2")));
        MusicSheets.add(new SheetMusic(2, R.drawable.song2_part2));
        MusicSheets.add(new SheetMusic(3, R.drawable.song2_part3));

        MusicSheets.add(new SheetMusic("Song 3", 1, R.drawable.song3_part1,
                R.raw.reflections_part_3, timestamps.get("Song 3")));
        MusicSheets.add(new SheetMusic(2, R.drawable.song3_part2));
        MusicSheets.add(new SheetMusic(3, R.drawable.song3_part3));


        SheetMusicAdapter sheetMusicAdapter = new SheetMusicAdapter(this, MusicSheets);

        final ListView listView = findViewById(R.id.list);
        listView.setAdapter(sheetMusicAdapter);
        listView.setDivider(null);
    }
}
