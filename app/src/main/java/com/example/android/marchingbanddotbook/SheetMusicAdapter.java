package com.example.android.marchingbanddotbook;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;



public class SheetMusicAdapter extends ArrayAdapter<SheetMusic> {

    private int dp(int px) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int)(px * density);
    }


    public SheetMusicAdapter(Activity context, ArrayList<SheetMusic> musicSheets) {
        super(context, 0, musicSheets);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }


        // Get the {@link Set} object located at this position in the list
        final SheetMusic currentMusicSheet = getItem(position);

        TextView songName = listItemView.findViewById(R.id.heading);
        TextView songPart = listItemView.findViewById(R.id.subheading);
        ImageView audioIcon = listItemView.findViewById(R.id.audio_icon);





        int part = currentMusicSheet.getPart();
        songPart.setText("Part " + String.valueOf(part));

        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) songPart.getLayoutParams();
        params.setMargins(dp(16), 0, 0, dp(12));
        songPart.setLayoutParams(params);


        if(currentMusicSheet.hasSongName()) {
            songName.setText(currentMusicSheet.getSongName());
            songName.setVisibility(View.VISIBLE);

            params = (ViewGroup.MarginLayoutParams) songName.getLayoutParams();
            params.setMargins(dp(4), dp(4), 0, 0);
            songName.setLayoutParams(params);

            params = (ViewGroup.MarginLayoutParams) audioIcon.getLayoutParams();
            params.setMargins(0, dp(12), dp(8), 0);
            audioIcon.setLayoutParams(params);
        } else {
            songName.setVisibility(View.GONE);
            audioIcon.setVisibility(View.GONE);
        }


        listItemView.findViewById(R.id.image_view).setVisibility(View.GONE);
        listItemView.setPadding(dp(20), 0, 0, 0);


        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SaveImage musicImage = new SaveImage(getContext(), currentMusicSheet.getImageResourceId());
                musicImage.write();
                musicImage.open();
            }
        });

        audioIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), PlayAudioActivity.class);
                i.putExtra("Audio_Source", currentMusicSheet.getAudioResourceId());
                i.putExtra("Song_Title", currentMusicSheet.getSongName());
                i.putExtra("Measure_Timestamps", currentMusicSheet.getTimestamps());
                getContext().startActivity(i);
            }
        });


        return listItemView;
    }
}
