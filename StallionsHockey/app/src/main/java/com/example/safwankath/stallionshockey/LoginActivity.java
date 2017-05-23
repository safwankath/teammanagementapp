package com.example.safwankath.stallionshockey;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Safwan Kath on 2017-05-12.
 */
public class LoginActivity extends AppCompatActivity {
    public Toolbar toolbar;
    EditText usernameEt, passwordEt;
    public static String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        usernameEt = (EditText)findViewById(R.id.login_username);
        passwordEt = (EditText)findViewById(R.id.login_password);

    }

    // function called from btnlogin in login_layout
    public void onLogin(View view){
        username = usernameEt.getText().toString();
        String password = passwordEt.getText().toString();

        String type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(new BackgroundWorker.AsyncResponse() {
            @Override
            public void processFinish(String output) {
                if (output.equals("success")) {
                    // launch navigation activity
                    Intent intent = new Intent(LoginActivity.this, NavActivity.class);
                    startActivity(intent);
                }
            }
        },this);
        backgroundWorker.execute(type, username, password);



    }

}
