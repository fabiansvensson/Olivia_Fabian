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


        deviceCollection.add(new Flat(100, "Samsung", 1, R.drawable.ic_launcher_foreground, R.drawable.like));
        deviceCollection.add(new Flat(150, "Hyundai", 2, R.drawable.ic_launcher_foreground, R.drawable.like));
        deviceCollection.add(new Flat(751, "iPhone", 3, R.drawable.ic_launcher_foreground, R.drawable.like));
        deviceCollection.add(new Flat(200, "Erikson", 4, R.drawable.ic_launcher_foreground, R.drawable.like));

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
