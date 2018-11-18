package com.example.android.marchingbanddotbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        LinearLayout song1 = findViewById(R.id.song_1);
        song1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Song1Activity.class);
                startActivity(i);
            }
        });

        LinearLayout song2 = findViewById(R.id.song_2);
        song2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Song2Activity.class);
                startActivity(i);
            }
        });

        LinearLayout song3 = findViewById(R.id.song_3);
        song3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Song3Activity.class);
                startActivity(i);
            }
        });

        TextView sheetMusic = findViewById(R.id.sheet_music);
        sheetMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SheetMusicActivity.class);
                startActivity(i);
            }
        });
    }
}
