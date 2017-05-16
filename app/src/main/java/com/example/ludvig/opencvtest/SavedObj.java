package com.example.ludvig.opencvtest;

/**
 * Created by Erik on 2017-05-16.
 */

public class SavedObj {
    int high_hue;
    int high_sat;
    int high_val;
    int low_hue;
    int low_sat;
    int low_val;
    String Name;

    public SavedObj(String name,int high_hue,int high_sat,int high_val,int low_hue,int low_sat,int low_val){
        Name = name;
        this.high_hue = high_hue;
        this.high_sat = high_sat;
        this.high_val = high_val;
        this.low_hue = low_hue;
        this.low_sat = low_sat;
        this.low_val = low_val;
    }

    public String toString(){
        return Name;
    }

}
