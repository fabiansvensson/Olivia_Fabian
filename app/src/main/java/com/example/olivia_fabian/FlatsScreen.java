package com.example.olivia_fabian;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.olivia_fabian.api.RetroFlats;
import com.example.olivia_fabian.api.RetrofitClientInstance;


import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import com.squareup.picasso.Picasso;

public class FlatsScreen extends AppCompatActivity {
    ListView lv;
    Object item;
    ImageView activityMain;
    private static List<RetroFlats> flats;
    private static List <Flat> flats_n;
    private FlatsAdapter adapter;
    private String time;
    private String date;
    private boolean like;
    private int id;
    private Button e, c, f;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        flats_n = new LinkedList<Flat>();
        setContentView(R.layout.activity_flats);
        activityMain = findViewById(R.id.flatpicture);
        makeCallToApi(this);

        adapter = new FlatsAdapter(getApplicationContext(), flats_n, this);

        lv = findViewById(R.id.listview);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                item = lv.getItemAtPosition(position);
                createIntent((Flat)item);
            }
        });

    }

    public void createIntent(Flat flat) {
        Intent intent = new Intent(this, Details.class);
        intent.putExtra("PRICE", flat.getPrice());
        intent.putExtra("DESCRIPTION", flat.getDescription());
        intent.putExtra("IMAGE", flat.getImg());
        intent.putExtra("LIKE", flat.getLike());
        intent.putExtra("SIZE", flat.getSize());
        intent.putExtra("IMAGE_API", flat.getImg_api());
        intent.putExtra("FLAT_ID", flat.getId());

        intent.putExtra("DATE", flat.getDate());
        intent.putExtra("TIME", flat.getTime());

        startActivityForResult(intent,1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                time = data.getStringExtra("TIME");
                date = data.getStringExtra("DATE");
                like = data.getBooleanExtra("LIKE", false);
                id = data.getIntExtra("ID", 0);
                Log.d("STRING TIME", "CHECK HERE IS THE TIME IN THE FLATSCREEN: " + time);
                Log.d("DATE TIME", "CHECK HERE IS THE DATE IN THE FLATSCREEN: " + date);
                Log.d("FLAT LIKE", "CHECK HERE IS THE LIKE IN THE FLATSCREEN: " + like);
                Log.d("FLAT ID", "CHECK HERE IS THE ID IN THE FLATSCREEN: " + id);
                flats_n.get(id).setLike(like);
                adapter.notifyDataSetChanged();
            }
        }
    }

    public void makeCallToApi(final Context context) {
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
                int j = 0;
               for (RetroFlats rf: flats) {
                   Flat f = new Flat(rf.getPrice(), rf.getShortdescription(), 5, R.drawable.logo, false, rf.getImage(), j);
                    flats_n.add(f);
                    j++;
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<RetroFlats>> call, Throwable t) {
                Log.d("FAIL", "FAILURE HAS OCCURED!");
                Toast.makeText(getApplicationContext(), "FAIL!", Toast.LENGTH_LONG).show();
            }
        });

    }

}
