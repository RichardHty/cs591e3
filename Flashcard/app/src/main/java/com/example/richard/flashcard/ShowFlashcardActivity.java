package com.example.richard.flashcard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by richard on 2/11/18.
 */

public class ShowFlashcardActivity extends AppCompatActivity {

    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //TODO set context view
        setContentView(R.layout.activity_flashcard);
        //here is for the toast message
        userName = getIntent().getStringExtra("USER_NAME");
        Toast toast=Toast.makeText(getApplicationContext(),"Welcome "+userName,Toast.LENGTH_LONG);
        toast.show();
    }
}
