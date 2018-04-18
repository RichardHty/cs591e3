package com.example.richard.flingandshake.method;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.example.richard.flingandshake.Constants;

/**
 * Created by richard on 2/24/18.
 */

public class OnFlingListener implements View.OnTouchListener {

    private GestureDetector gdt;
    private int flingType ;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (gdt == null) gdt = new GestureDetector(v.getContext(), new GestureListener());
        return gdt.onTouchEvent(event);
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {


        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1 != null && e2 != null) {
                if (e1.getX() - e2.getX() > Constants.SWIPE_MIN_DISTANCE && Math.abs(velocityX) > Constants.SWIPE_THRESHOLD_VELOCITY) {
                    onRightToLeft();
                    return true;
                } else if (e2.getX() - e1.getX() > Constants.SWIPE_MIN_DISTANCE && Math.abs(velocityX) > Constants.SWIPE_THRESHOLD_VELOCITY) {
                    onLeftToRight();
                    return true;
                }

                if (e1.getY() - e2.getY() > Constants.SWIPE_MIN_DISTANCE && Math.abs(velocityY) > Constants.SWIPE_THRESHOLD_VELOCITY) {
                    onBottomToTop();
                    return true;
                } else if (e2.getY() - e1.getY() > Constants.SWIPE_MIN_DISTANCE && Math.abs(velocityY) > Constants.SWIPE_THRESHOLD_VELOCITY) {
                    onTopToBottom();
                    return true;
                }
            }
            return false;
        }
    }

    private void onRightToLeft() {
        flingType = Constants.FLING_LEFT;
    }

    private void onLeftToRight() {
        flingType = Constants.FLING_RIGHT;
    }

    private void onBottomToTop() {
        flingType = Constants.FLING_UPWARDS;
    }

    private void onTopToBottom() {
        flingType = Constants.FLING_DOWNWARDS;
    }

    public int getFlingType(){
        return flingType;
    }

}
