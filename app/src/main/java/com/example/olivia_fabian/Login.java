package com.example.olivia_fabian;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    Button enter;
    EditText password;
    EditText email;
    TextView register;

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

        /*
        android.support.v7.app.ActionBar actionbar = getSupportActionBar();
        actionbar.setLogo(R.drawable.logo);
        actionbar.setDisplayUseLogoEnabled(true);
        actionbar.setDisplayUseLogoEnabled(true);
        actionbar.setTitle("Log In");
        */

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkEmail()) {
                    dialogEmail();
                } else if(passOk()) {
                    dialogPassword();
                } else {
                    intentCreate();
                }
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

    public void intentCreate() {
        /*Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
        finish();
        */
    }

    public boolean passOk() {
        String s = password.toString();
        if(s.length() >=  6) return true;
        return false;
    }

    public boolean checkEmail() {
        String s = email.toString();
        if(s.length() > 0) return true;
        return false;
    }

    public void dialogPassword() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Login.this);
        alertDialogBuilder.setTitle("Password Incorrect");
        alertDialogBuilder.setMessage("Error password min 6 chars and\n" +
                "Error email needs to be set").setCancelable(false);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void dialogEmail() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Login.this);
        alertDialogBuilder.setTitle("Email Incorrect");
        alertDialogBuilder.setMessage("Error email needs to be set").setCancelable(false);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


}
