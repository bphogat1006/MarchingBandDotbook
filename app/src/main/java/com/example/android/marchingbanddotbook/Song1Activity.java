package com.example.android.marchingbanddotbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class Song1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_list);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        final ArrayList<Set> Sets = new ArrayList<>();

        int[] timestamps = new int[] {};

        Sets.add(new Set("1", "1 step outside 40B\n3 steps in front of FH\nMove 16", R.drawable.test_drill_page_1));
        Sets.add(new Set("2", "2 steps outside 45B\nOn FH\nMove 8", R.drawable.test_drill_page_2));
        Sets.add(new Set("3", "1 step outside 40B\n3 steps in front of FH\nMove 16", R.drawable.test_drill_page_1));
        Sets.add(new Set("4", "2 steps outside 45B\nOn FH\nMove 8", R.drawable.test_drill_page_2));
        Sets.add(new Set("5", "1 step outside 40B\n3 steps in front of FH\nMove 16", R.drawable.test_drill_page_1));
        Sets.add(new Set("6", "2 steps outside 45B\nOn FH\nMove 8", R.drawable.test_drill_page_2));
        Sets.add(new Set("6B", "1 step outside 40B\n3 steps in front of FH\nMove 16", R.drawable.test_drill_page_1));
        Sets.add(new Set("7", "2 steps outside 45B\nOn FH\nMove 8", R.drawable.test_drill_page_2));
        Sets.add(new Set("8", "1 step outside 40B\n3 steps in front of FH\nMove 16", R.drawable.test_drill_page_1));
        Sets.add(new Set("9", "2 steps outside 45B\nOn FH\nMove 8", R.drawable.test_drill_page_2));
        Sets.add(new Set("10", "1 step outside 40B\n3 steps in front of FH\nMove 16", R.drawable.test_drill_page_1));
        Sets.add(new Set("11", "2 steps outside 45B\nOn FH\nMove 8", R.drawable.test_drill_page_2));

        SetAdapter setAdapter = new SetAdapter(this, Sets);

        final ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(setAdapter);



        SearchView selectSet = findViewById(R.id.select_set);
        selectSet.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                callSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                callSearch(newText);
                return true;
            }

            public void callSearch(String query) {
                Set set;
                int selectedSet = 0;
                for(int i = 0; i < Sets.size(); i++) {
                    set = Sets.get(i);

                    if(set.getSetName().equalsIgnoreCase(query)) {
                        selectedSet = i;
                    }
                }
                listView.setSelection(selectedSet);
            }
        });
    }
}
