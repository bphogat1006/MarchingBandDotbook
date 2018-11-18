package com.example.android.marchingbanddotbook;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SetAdapter extends ArrayAdapter<Set> {

    public SetAdapter(Activity context, ArrayList<Set> sets) {
        super(context, 0, sets);
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
        final Set currentSet = getItem(position);


        TextView drillSetName = listItemView.findViewById(R.id.heading);
        drillSetName.setText("Set " + currentSet.getSetName());
        if(currentSet.getSetName().equals("1")) {
            float density = getContext().getResources().getDisplayMetrics().density;
            int paddingDp = (int)(20 * density);
            listItemView.setPadding(paddingDp, paddingDp/2, paddingDp, paddingDp);
        }

        ImageView drillImage = listItemView.findViewById(R.id.image_view);
        drillImage.setImageResource(currentSet.getImageResourceId());

        TextView drillNotes = listItemView.findViewById(R.id.subheading);
        drillNotes.setText(currentSet.getNotes());

        ImageView audioIcon = listItemView.findViewById(R.id.audio_icon);
        audioIcon.setVisibility(View.GONE);




        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SaveImage drillSetImage = new SaveImage(getContext(), currentSet.getImageResourceId());
                drillSetImage.write();
                drillSetImage.open();
            }
        });



        return listItemView;
    }
}
