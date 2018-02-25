package com.example.richard.w4_p3;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.richard.w4_p3.method.IntentDetector;
import com.example.richard.w4_p3.method.OnFlingListener;
import com.example.richard.w4_p3.method.ShakeListener;

/**
 * Created by richard on 2/24/18.
 */

public class NorthActivity extends AppCompatActivity
        implements View.OnTouchListener{

    private TextView tv_target;
    private RelativeLayout.LayoutParams params;
    private IntentDetector intentDetector;
    private OnFlingListener flingListener;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeListener mShakeDetector;

    private int flag = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tv_target = new TextView(this);
        tv_target.setText(Constants.TV_TEXT_NORTH);
        tv_target.setBackgroundColor(Color.parseColor(Constants.ACTIVITY_NORTH_COLOR));
        tv_target.setGravity(Gravity.CENTER);

        RelativeLayout r1 = new RelativeLayout(this);
        params = new RelativeLayout.LayoutParams(Constants.TV_WIDTH,Constants.TV_HEIGHT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);

        r1.addView(tv_target,params);
        setContentView(r1);

        intentDetector = new IntentDetector();
        flingListener = new OnFlingListener();

        r1.setOnTouchListener(this);
        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeListener();
        mShakeDetector.setOnShakeListener(new ShakeListener.OnShakeListener() {

            @Override
            public void onShake(int count) {
                handleShakeEvent(NorthActivity.this,count);

            }
        });


    }
    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }

    public void handleShakeEvent(Context mContext, int count){
        final Handler shakingHandler = new Handler();
        Log.w("Testing", "here shaked: " + count);
        if (count >= Constants.COUNT_SHAKING_FEVERISHLY){
            Log.w("Testing", "here flag: " + flag);
            final Animation shake = AnimationUtils.loadAnimation(mContext, R.anim.shake);
            final Runnable shakingRunnable = new Runnable() {
                @Override
                public void run() {
                    flag = 1;
                    tv_target.startAnimation(shake);
                    shakingHandler.postDelayed(this,100);
                }
            };
            shakingHandler.post(shakingRunnable);

            shakingHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    flag = 0;
                    Log.w("Testing", "here is flag: " + flag);
                    shakingHandler.removeCallbacks(shakingRunnable); //stop next runnable execution
                }
            }, Constants.SHAKING_DELAY);


        }

    }
    public boolean onTouch(View v, MotionEvent event){
        if (flag == 0 && flingListener.onTouch(v,event)){

            intentDetector.process(NorthActivity.this,flingListener.getFlingType());
            Intent nextIntent = intentDetector.getTargetIntent();
            startActivity(nextIntent);

            return false;
        }


        return true;
    }
}
