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

    public SavedObj(String name){
        Name = name;
    }

    public String toString(){
        return Name;
    }

}
