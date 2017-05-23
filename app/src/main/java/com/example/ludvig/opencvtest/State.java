package com.example.ludvig.opencvtest;

import java.io.Serializable;

/**
 * Created by Erik on 2017-05-15.
 */

public class State implements Serializable{     //TODO byt till Parcelable om det går segt med Serializable

    int high_hValue = 0;
    int high_sValue = 0;
    int high_vValue = 0;
    int low_hValue = 0;
    int low_sValue = 0;
    int low_vValue = 0;
    boolean isMenu = true;
    boolean isRBG = true;


    public State(){

    }


}
