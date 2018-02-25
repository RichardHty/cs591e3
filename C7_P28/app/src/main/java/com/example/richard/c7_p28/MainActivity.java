package com.example.richard.c7_p28;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.Random;

public class MainActivity extends AppCompatActivity
                       implements View.OnTouchListener{


    private TextView tv_target;
    private int startX;
    private int startY;


    private RelativeLayout.LayoutParams params;
    private VelocityTracker vTracker = null;

    private int screenWidth;    //used to set random position
    private int screenHeight;

    private int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tv_target = new TextView(this);
        tv_target.setText("Hello World");
        tv_target.setBackgroundColor(0xFFFF0000);
        tv_target.setGravity(Gravity.CENTER);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenHeight = displayMetrics.heightPixels - Constants.TV_HEIGHT*2 >0?displayMetrics.heightPixels - Constants.TV_HEIGHT*2:0;
        screenWidth = displayMetrics.widthPixels - Constants.TV_WIDTH*2 >0?displayMetrics.widthPixels - Constants.TV_WIDTH*2:0;

        RelativeLayout r1 = new RelativeLayout(this);
        params = new RelativeLayout.LayoutParams(Constants.TV_WIDTH,Constants.TV_HEIGHT);
        //set the center of textview in the middle of screen
        params.leftMargin = (displayMetrics.widthPixels - Constants.TV_WIDTH)/2;
        params.topMargin = (displayMetrics.heightPixels - Constants.TV_HEIGHT)/2;

        r1.addView(tv_target,params);
        setContentView(r1);

        tv_target.setOnTouchListener(this);

    }

    public boolean onTouch(View v, MotionEvent event){
        int action = event.getAction();
        switch (action){

            case MotionEvent.ACTION_MOVE:
                params.leftMargin +=  (int) event.getX()- Constants.TV_WIDTH/2;
                params.topMargin +=  (int) event.getY() - Constants.TV_HEIGHT/2;
                tv_target.setLayoutParams(params);

                if(vTracker == null){
                    vTracker = VelocityTracker.obtain();
                }else{
                    vTracker.clear();
                }

                vTracker.addMovement(event);
                vTracker.computeCurrentVelocity(500);
                float speedX = Math.abs(vTracker.getXVelocity());
                float speedY = Math.abs(vTracker.getYVelocity());
                Log.w("Testing","Here is the speed x: "+speedX);
                Log.w("Testing","Here is the speed y: "+speedY);

                if (speedX > Constants.VELOCITY_LIMIT_X || speedY > Constants.VELOCITY_LIMIT_Y){
                    flag = 1;
                }

                break;
            case MotionEvent.ACTION_UP:
                if (flag == 1){
                    Log.w("Testing","flag: "+flag);

                    Random x = new Random();
                    Random y = new Random();

                    params.leftMargin = x.nextInt(screenWidth);
                    params.topMargin = y.nextInt(screenHeight);
                    tv_target.setLayoutParams(params);
                    flag = 0;

                }
        }
        return true;
  }

}
