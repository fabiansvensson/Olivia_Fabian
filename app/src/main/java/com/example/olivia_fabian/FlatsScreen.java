package com.example.olivia_fabian;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.olivia_fabian.api.RetroFlats;
import com.example.olivia_fabian.api.RetrofitClientInstance;


//import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlatsScreen extends AppCompatActivity {
    ListView lv;
    Object item;
    ImageView activityMain;
    private static List<RetroFlats> flats;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flats);
        Context context = getApplicationContext();
        ImageView imageView , aux;
        int id = 1;

        makeCallToApi();

        List<Flat> deviceCollection = new ArrayList<>();
        activityMain = findViewById(R.id.flatpicture);


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

    public void makeCallToApi() {
        final UserManager myapplication = (UserManager)getApplication();

        RetrofitClientInstance controller = new RetrofitClientInstance();
        controller.onStart(new Callback<List<RetroFlats>>() {
            @Override
            public void onResponse(Call<List<RetroFlats>> call, Response<List<RetroFlats>> response) {

                myapplication.flats=response.body();
                flats = response.body();

                for(RetroFlats rf : flats) {
                    Log.d("TAG", rf.getShortdescription());
                }
            }

            @Override
            public void onFailure(Call<List<RetroFlats>> call, Throwable t) {

            }
        });

        //flats.size();

    }

}
