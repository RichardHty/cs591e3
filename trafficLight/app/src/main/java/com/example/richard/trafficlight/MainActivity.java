package com.example.richard.trafficlight;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.ProcessingInstruction;

public class MainActivity extends AppCompatActivity {

    private TextView tvLight;
    private Button btnStart;
    private String[] color = {"#4CAF50","#FFEB3B","#FF0000"};
    private int[] timeLimit = {4000,1500,4000};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLight = (TextView) findViewById(R.id.tv_light);
        btnStart = (Button) findViewById(R.id.btn_start);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Handler trafficLightHandler = new Handler();
                trafficLightHandler.post(new Runnable() {
                    int count = 0;
                    @Override
                    public void run()
                    {
                        tvLight.setBackgroundColor(Color.parseColor(color[count]));
                        trafficLightHandler.postDelayed(this, timeLimit[count]);
                        count =(count+1)%3;
                    }
                });
            }
        });
    }
}
