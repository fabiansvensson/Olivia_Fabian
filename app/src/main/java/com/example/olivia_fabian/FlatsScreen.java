package com.example.olivia_fabian;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FlatsScreen extends AppCompatActivity {
    ListView lv;
    Object item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flats);

        List<Flat> deviceCollection = new ArrayList<>();


        FlatsAdapter adapter = new FlatsAdapter(getApplicationContext(), deviceCollection, this);

        lv = (ListView) findViewById(R.id.listview);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                item = lv.getItemAtPosition(position);
            }
        });

    }

}
