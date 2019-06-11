package com.example.olivia_fabian.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.olivia_fabian.R;
import com.example.olivia_fabian.api.FlatsService;
import com.example.olivia_fabian.api.PostAPIUtils;
import com.example.olivia_fabian.api.PostLogin;
import com.example.olivia_fabian.json.ReadJson;
import com.example.olivia_fabian.pojo.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    Button enter;
    EditText password;
    EditText email;
    TextView register;
    List<User> users = null;
    private User checkUser;
    private boolean userFound;
    private FlatsService fs;

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

        fs = PostAPIUtils.getAPIService();

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.getText().toString().equals("test")) {
                    checkUser = new User("tester", "fabian.sitges@gmail.com", password.getText().toString());
                } else {
                    checkUser = new User("tester", email.getText().toString(), password.getText().toString());
                }
                if(!checkEmail()) {
                    dialogEmail();
                } else if(!passOk()) {
                    dialogPassword();
                } else {
                    ReadJson rj = new ReadJson();
                    users = rj.loadData(getFilesDir().toString());
                    Log.d("TAG", "MESSAGE HAS BEEN SENT LOOK HERE!");
                    userFound = false;
                    for(User u : users) {
                        if(u.equals(checkUser)) {
                            intentCreate(true);
                            userFound = true;
                        }
                    }
                    sendPost(checkUser.getEmail(),checkUser.getPassword());
                    if(!userFound) {

                        //dialogFaultyInput();
                    }
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

    public void sendPost(String email, String password) {

        fs.findUser(email, password).enqueue(new Callback<PostLogin>() {
            @Override
            public void onResponse(Call<PostLogin> call, Response<PostLogin> response) {

                Log.i("TAG", "CHECK!!!!");
                if(response.isSuccessful()) {
                    Log.i("TAG", "FIND USER post submitted to API." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<PostLogin> call, Throwable t) {
                Log.e("TAG", "Unable to submit post to API.");
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
        } else {
            Intent intent = new Intent(this, Register.class);
            startActivity(intent);
        }
    }

    public boolean passOk() {
        return checkUser.getPassword().length() > 0;
    }

    public boolean checkEmail() {
        String s = checkUser.getEmail();
        if(s.equals("test")) s = "fabian.sitges@gmail.com";
        return (!TextUtils.isEmpty(s) && Patterns.EMAIL_ADDRESS.matcher(s).matches());
    }

    public void dialogPassword() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Login.this);
        alertDialogBuilder.setTitle("Password Format");
        alertDialogBuilder.setMessage("Error password field cannot be empty").setCancelable(true);
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
        alertDialogBuilder.setTitle("Email Format");
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

    public void dialogFaultyInput() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Login.this);
        alertDialogBuilder.setTitle("Incorrect password or email");
        alertDialogBuilder.setMessage("Error! Either the password or the email that you " +
                "have inputted do not correspond to any user!").setCancelable(true);
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

}
