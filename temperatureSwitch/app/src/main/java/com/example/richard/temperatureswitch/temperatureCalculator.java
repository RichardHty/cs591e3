package com.example.richard.temperatureswitch;

/**
 * Created by richard on 2/3/18.
 */

public class temperatureCalculator {
    private float celsius;
    private float fahrenheit;

    public temperatureCalculator(float newCel,float newFah){
        setCel(newCel);
        setFah(newFah);
    }
    public void setCel(float newCel){
        celsius = newCel;
    }
    public void  setFah(float newFah){
        fahrenheit = newFah;
    }
    public float celToFah(){
        return celsius*9/5+32 ;
    }
    public float fahToCel(){
        return (fahrenheit-32)*5/9;
    }
}
