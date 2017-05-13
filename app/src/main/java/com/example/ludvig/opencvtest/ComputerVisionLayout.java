package com.example.ludvig.opencvtest;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;

import static android.R.attr.id;

/**
 * Created by Erik on 2017-05-13.
 */

public class ComputerVisionLayout {

    public ComputerVisionLayout() {
    }

    void GUI_init(final MainActivity main){

        final LinearLayout cammeraFrame = (LinearLayout) main.findViewById(R.id.Cameraframe);
        final LinearLayout menuLayout = (LinearLayout) main.findViewById(R.id.MenuLayout);
        final LinearLayout hide_menu = (LinearLayout) main.findViewById(R.id.hide_menu);
        final LinearLayout hueHigh_ll = (LinearLayout) main.findViewById(R.id.hh_layout);
        final LinearLayout hueLow_ll = (LinearLayout) main.findViewById(R.id.lh_layout);
        final LinearLayout satHigh_ll = (LinearLayout) main.findViewById(R.id.hs_layout);
        final LinearLayout satLow_ll = (LinearLayout) main.findViewById(R.id.ls_layout);
        final LinearLayout valHigh_ll = (LinearLayout) main.findViewById(R.id.hv_layout);
        final LinearLayout valLow_ll = (LinearLayout) main.findViewById(R.id.lv_layout);

        final SeekBar hueHigh_bar = (SeekBar) main.findViewById(R.id.hh_bar);
        final SeekBar hueLow_bar = (SeekBar) main.findViewById(R.id.lh_bar);
        final SeekBar satHigh_bar = (SeekBar) main.findViewById(R.id.hs_bar);
        final SeekBar satLow_bar = (SeekBar) main.findViewById(R.id.ls_bar);
        final SeekBar valHigh_bar  = (SeekBar) main.findViewById(R.id.hv_bar);
        final SeekBar valLow_bar  = (SeekBar) main.findViewById(R.id.lv_bar);

        final TextView val_hue_high  = (TextView) main.findViewById(R.id.hue_upper_text);
        final TextView val_hue_low  = (TextView) main.findViewById(R.id.hue_lower_text);
        final TextView val_sat_high  = (TextView) main.findViewById(R.id.sat_upper_text);
        final TextView val_sat_low  = (TextView) main.findViewById(R.id.sat_lower_text);
        final TextView val_val_high = (TextView) main.findViewById(R.id.val_upper_text);
        final TextView val_val_low  = (TextView) main.findViewById(R.id.val_lower_text);

        final Button menuButton = (Button) main.findViewById(R.id.menu_button);
        final Button rbghsvButton = (Button) main.findViewById(R.id.rbghsv_button);

        JavaCameraView mCameraView = new JavaCameraView(main, CameraBridgeViewBase.CAMERA_ID_ANY);
        mCameraView.enableFpsMeter();
        mCameraView.enableView();
        mCameraView.setMaxFrameSize(800,640);
        LinearLayout fl = (LinearLayout) main.findViewById(R.id.Cameraframe);
        fl.addView(mCameraView,new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));

        mCameraView.setCvCameraViewListener(main);


        //lISTENERS.....
        hueHigh_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                main.high_hValue = seekBar.getProgress();
                TextView t = (TextView) main.findViewById(R.id.hh_value);
                String str = ""+progress;
                t.setText(str);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        satHigh_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                main.high_sValue = seekBar.getProgress();
                TextView t = (TextView) main.findViewById(R.id.hs_value);
                String str = ""+progress;
                t.setText(str);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        valHigh_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                main.high_vValue = seekBar.getProgress();
                TextView t = (TextView) main.findViewById(R.id.hv_value);
                String str = ""+progress;
                t.setText(str);

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        valLow_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                main.low_hValue = seekBar.getProgress();
                TextView t = (TextView) main.findViewById(R.id.lh_value);
                String str = ""+progress;
                t.setText(str);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


        satLow_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                main.low_sValue = seekBar.getProgress();
                TextView t = (TextView) main.findViewById(R.id.ls_value);
                String str = ""+progress;
                t.setText(str);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        valLow_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                main.low_vValue = seekBar.getProgress();TextView t = (TextView) main.findViewById(R.id.lv_value);
                String str = ""+progress;
                t.setText(str);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(main.isMenu){
                    main.isMenu = false;
                    hide_menu.setVisibility(View.INVISIBLE);
                    hide_menu.setEnabled(false);
                    menuButton.setText("SHOW MENU");
                }else{
                    main.isMenu = true;
                    hide_menu.setVisibility(View.VISIBLE);
                    hide_menu.setEnabled(true);
                    menuButton.setText("HIDE MENU");
                }
            }
        });

        rbghsvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(main.isRBG){
                    main.isRBG = false;
                    rbghsvButton.setText("CHANGE TO RBG");
                }else{
                    main.isRBG = true;
                    rbghsvButton.setText("CHANGE TO HSV");
                }
            }
        });



    }
}