package com.example.saraglattstein.flashcards;

/*
 * Done by saraglattstein
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tvOne;
    private TextView tvTwo;
    private TextView tvSign;
    private TextView tvGen;
    private TextView tvAnswer;

    private EditText input;

    private Button btn;

    private int no1;
    private int no2;
    private int answer;

    private int correct = 0;
    private int current = 0;

    // Added from RichardHty
    private String userName;
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Added from RichardHty
        userName = getIntent().getStringExtra("USER_NAME");
        Toast toast=Toast.makeText(getApplicationContext(),"Welcome "+ userName, Toast.LENGTH_LONG);
        toast.show();
        //

        tvOne = findViewById(R.id.tvOne);
        tvTwo = findViewById(R.id.tvTwo);
        tvSign = findViewById(R.id.tvSign);
        tvGen = findViewById(R.id.tvGen);
        tvAnswer = findViewById(R.id.tvAnswer);

        input = findViewById(R.id.input);
        btn = findViewById(R.id.btn);

        generate();

        Log.e("Testing","Question " + current);
        Log.e("Testing","New question: " + no1 + " / " + no2 + " = " + answer);


        View.OnClickListener onClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.e("Testing","Question " + current);
                Log.e("Testing","New question: " + no1 + " / " + no2 + " = " + answer);
                //check if correct, update correct, toast if needed or generate new
                String response = input.getText().toString();
                input.setText("");
                if(current == 10) {
                    //Toast to your accomplishments
                    Log.e("Testing","Finished");
                    if( response.equals(Integer.toString(answer))) {
                        correct++;
                        current++;
                    }
                    Toast.makeText(MainActivity.this, correct + " out of 10", Toast.LENGTH_SHORT).show();
                }
                else if(current > 10) {
                    Toast.makeText(MainActivity.this, correct + " out of 10", Toast.LENGTH_SHORT).show();
                }
                else {

                    if( response.equals(Integer.toString(answer))) {
                        Log.e("Testing","Correct!");
                        correct++;
                        generate();
                    }
                    else {
                        Log.e("Testing","Incorrect!");
                        generate();
                    }
                }
            }
        };
        btn.setOnClickListener(onClickListener);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("no1", tvOne.getText().toString());
        outState.putString("no2", tvTwo.getText().toString());

        outState.putInt("one", no1);
        outState.putInt("two", no2);
        outState.putInt("answer", answer);
        outState.putInt("current", current);
        outState.putInt("correct", correct);

        outState.putString("response", input.getText().toString());

        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
//        if (savedInstanceState != null) {
        // tvTotal.setText(savedInstanceState.getString("total"));  //or in one karate-chop...

        tvOne.setText(savedInstanceState.getString("no1"));
        tvTwo.setText(savedInstanceState.getString("no2"));
        input.setText(savedInstanceState.getString("response"));

        no1 = savedInstanceState.getInt("one");
        no2 = savedInstanceState.getInt("two");
        answer = savedInstanceState.getInt("answer");

        current = savedInstanceState.getInt("current");
        correct = savedInstanceState.getInt("correct");

    }

    public void generate() {
        current++;
        no2 = (int) (Math.random()*10)+1;
        answer = (int) (Math.random()*10)+1;
        no1 = no2 * answer;
        tvOne.setText(Integer.toString(no1));
        tvTwo.setText(Integer.toString(no2));

    }
}
