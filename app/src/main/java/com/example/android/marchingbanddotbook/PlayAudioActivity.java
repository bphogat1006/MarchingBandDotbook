package com.example.android.marchingbanddotbook;

import android.media.MediaPlayer;
import android.media.PlaybackParams;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class PlayAudioActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private MediaPlayer mp;
    float speed = 1.00f;
    int[] measureTimestamps;

    TextView trackProgressTextView;
    TextView playbackSpeedTextView;
    SeekBar progressSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_audio);

        /*
         * Retrieve extras from getIntent()
         */
        int audioSrc = getIntent().getIntExtra("Audio_Source", 0);
        mp = MediaPlayer.create(getApplicationContext(), audioSrc);

        String songName = getIntent().getStringExtra("Song_Title");
        ((TextView)findViewById(R.id.song_title)).setText(songName);

        measureTimestamps = getIntent().getIntArrayExtra("Measure_Timestamps");

        /*
         * Set view variables
         */
        trackProgressTextView = findViewById(R.id.track_progress);
        playbackSpeedTextView = findViewById(R.id.playback_speed);
        progressSeekBar = findViewById(R.id.seek_bar);

        /*
         * Link the seek bar with the media player
         */
        progressSeekBar.setMax(mp.getDuration());
        progressSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mp.seekTo(progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        /*
         * Link the spinner with the media player
         */
        Spinner spinner = (Spinner) findViewById(R.id.seek_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.song_1_measures, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        int measureId = pos-2;
        if(measureId >= 0) {
            mp.seekTo(measureTimestamps[measureId]);
            progressSeekBar.setProgress(measureTimestamps[measureId]);
            Toast.makeText(getApplicationContext(), Integer.toString(measureTimestamps[measureId]), Toast.LENGTH_SHORT).show();
        } else if(measureId == -1) {
            mp.seekTo(0);
            progressSeekBar.setProgress(0);
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    @Override
    public void onPause() {
        super.onPause();
        mp.stop();
    }

    public void playButton(View v) {
        mp.start();
        Toast.makeText(getApplicationContext(), Integer.toString(mp.getCurrentPosition()), Toast.LENGTH_SHORT).show();
    }

    public void pauseButton(View v) {
        mp.pause();
    }

    public void slowDownButton(View v) {
        if(speed > 0.5) {
            speed -= 0.10;
            updateSpeed();
        }
    }

    public void speedUpButton(View v) {
        if(speed < 0.9) {
            speed += 0.10;
            updateSpeed();
        }
    }

    private void updateSpeed() {
        if(mp.isPlaying()) {
            mp.setPlaybackParams(new PlaybackParams().setSpeed(speed));
        } else {
            mp.setPlaybackParams(new PlaybackParams().setSpeed(speed));
            mp.pause();
        }
        float newSpeed = (float) Math.ceil(speed*10)/10; // round to tenths place
        String newText = "Speed: " + newSpeed + "0x";
        playbackSpeedTextView.setText(newText);
    }
}