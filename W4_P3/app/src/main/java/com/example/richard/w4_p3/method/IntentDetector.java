package com.example.richard.w4_p3.method;

import android.content.Context;
import android.content.Intent;

import com.example.richard.w4_p3.Constants;
import com.example.richard.w4_p3.EastActivity;
import com.example.richard.w4_p3.NorthActivity;
import com.example.richard.w4_p3.SouthActivity;
import com.example.richard.w4_p3.WestActivity;

/**
 * Created by richard on 2/24/18.
 */

public class IntentDetector {

    private Intent targetIntent;

    public void process(Context currentContext, int flingType){
        if (flingType == Constants.FLING_RIGHT){
            targetIntent = new Intent(currentContext, EastActivity.class);
        }else if (flingType == Constants.FLING_LEFT){
            targetIntent = new Intent(currentContext, WestActivity.class);
        }else if (flingType ==  Constants.FLING_UPWARDS){
            targetIntent = new Intent(currentContext, NorthActivity.class);
        }else  if (flingType == Constants.FLING_DOWNWARDS){
            targetIntent = new Intent(currentContext, SouthActivity.class);
        }
    }
    public Intent getTargetIntent(){
        return targetIntent;
    }
}
