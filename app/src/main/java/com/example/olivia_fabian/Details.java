package com.example.olivia_fabian;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class Details extends AppCompatActivity {

    private String price;
    private String description;
    private int image;
    private int like;
    private String size;
    private Button appointment;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        price = intent.getStringExtra("PRICE");
        description = intent.getStringExtra("DESCRIPTION");
        image = intent.getIntExtra("IMAGE", 0);
        like = intent.getIntExtra("LIKE", 0);
        size = intent.getStringExtra("SIZE");

        Log.d("PRICE", "this is the price: " + price);
        Log.d("DESCRIPTION", "this is the description: " + description);
        Log.d("IMAGE", "this is the image: " + image);
        Log.d("LIKE", "this is the like: " + like);
        Log.d("SIZE", "this is the size: " + size);

        TextView vshortdesc = findViewById(R.id.shortdescription);
        ImageView vflat_img = findViewById(R.id.logoflat2);
        TextView vsize = findViewById(R.id.sizenumberdetails);
        TextView vprice = findViewById(R.id.prizedetails2);
        TextView vlongdesc = findViewById(R.id.longdescription);

        vshortdesc.setText(description);
        vflat_img.setImageResource(image);
        vsize.setText(size);
        vprice.setText(price);
        vlongdesc.setText("We need to pass this still!");

        appointment = findViewById(R.id.askappointment);
        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createIntent();
            }

        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                String time = data.getStringExtra("TIME");
                String date = data.getStringExtra("DATE");
                TextView tv = findViewById(R.id.dateandtime);
                tv.setText("Tienes una cita el " + date + "a las " + time);
                tv.setVisibility(View.VISIBLE);
                appointment.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void createIntent() {
        Intent intent = new Intent(this, Appointment.class);
        startActivityForResult(intent, 1);
    }

}
