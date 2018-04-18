package com.example.richard.flingandshake.method;

import android.content.Context;
import android.content.Intent;

import com.example.richard.flingandshake.Constants;
import com.example.richard.flingandshake.EastActivity;
import com.example.richard.flingandshake.NorthActivity;
import com.example.richard.flingandshake.SouthActivity;
import com.example.richard.flingandshake.WestActivity;

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
