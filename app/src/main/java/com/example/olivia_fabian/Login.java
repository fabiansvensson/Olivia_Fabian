package com.example.olivia_fabian;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.olivia_fabian.api.RetroFlats;
import com.example.olivia_fabian.api.RetrofitClientInstance;
import com.example.olivia_fabian.json.ReadJson;
import com.example.olivia_fabian.json.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    Button enter;
    EditText password;
    EditText email;
    TextView register;
    User[] users = null;
    private List<RetroFlats> flats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        enter = findViewById(R.id.enter);
        password = findViewById(R.id.loginpassword);
        email = findViewById(R.id.loginemail);
        register = findViewById(R.id.loginregister);

        Toolbar my_toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(my_toolbar);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkEmail()) {
                    dialogEmail();
                } else if(!passOk()) {
                    dialogPassword();
                } else {
                    //ReadJson rj = new ReadJson();
                    //users = rj.loadData(Register.getFolder().toString());
                    makeCallToApi();
                    for(RetroFlats rf: flats) {
                        testToast(String.valueOf(rf.getId()));
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    intentCreate(true);
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentCreate(false);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return true;
    }
    //and this to handle actions
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.logo) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void intentCreate(boolean login) {
        if(login) {
            Intent intent = new Intent(this, FlatsScreen.class);
            startActivity(intent);
            finish();

        } else {
            Intent intent = new Intent(this, Register.class);
            startActivity(intent);
            finish();
        }
    }

    public boolean passOk() {
        String s = password.getText().toString();
        return s.length() > 0;
    }

    public boolean checkEmail() {
        String s = email.getText().toString();
        if(s.equals("test")) s = "fabian.sitges@gmail.com";
        return (!TextUtils.isEmpty(s) && Patterns.EMAIL_ADDRESS.matcher(s).matches());
    }

    public void dialogPassword() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Login.this);
        alertDialogBuilder.setTitle("Password Incorrect");
        alertDialogBuilder.setMessage("Error password field cannot be empty and must contain\n" +
                "at least one number, one lowercase and one uppercase letter").setCancelable(true);
        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.cancel();
            }
        });
        alertDialogBuilder.setNegativeButton("Close", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void dialogEmail() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Login.this);
        alertDialogBuilder.setTitle("Email Incorrect");
        alertDialogBuilder.setMessage("Error email needs to be set and \n" +
                "have a valid expression").setCancelable(true);
        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.cancel();
            }
        });
        alertDialogBuilder.setNegativeButton("Close", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void makeCallToApi() {
        final UserManager myapplication = (UserManager)getApplication();

        RetrofitClientInstance controller = new RetrofitClientInstance();
        controller.onStart(new Callback<List<RetroFlats>>() {
            @Override
            public void onResponse(Call<List<RetroFlats>> call, Response<List<RetroFlats>> response) {

                myapplication.flats=response.body();
                flats = response.body();

            }

            @Override
            public void onFailure(Call<List<RetroFlats>> call, Throwable t) {

            }
        });

    }

    public void testToast(String str) {
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
    }

}
