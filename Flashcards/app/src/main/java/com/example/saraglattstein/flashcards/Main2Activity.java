package com.example.saraglattstein.flashcards;

/*
 * Done by RichardHty
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {
    private EditText userName;
    private EditText passwordInput; //do not have to use password input since this is not a real login
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        userName = (EditText) findViewById(R.id.userName);
        passwordInput = (EditText) findViewById(R.id.passwordInput);
        btnLogin = (Button) findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra("USER_NAME", userName.getText().toString());
                startActivity(intent);
            }
        });


    }
}