package com.example.ludvig.opencvtest;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.KeyEvent;
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

        LinearLayout cammeraFrame = (LinearLayout) main.findViewById(R.id.Cameraframe);
        //LinearLayout menuLayout = (LinearLayout) main.findViewById(R.id.MenuLayout);
        final LinearLayout hide_menu = (LinearLayout) main.findViewById(R.id.hide_menu);
        //LinearLayout hueHigh_ll = (LinearLayout) main.findViewById(R.id.hh_layout);
        //LinearLayout hueLow_ll = (LinearLayout) main.findViewById(R.id.lh_layout);
        //LinearLayout satHigh_ll = (LinearLayout) main.findViewById(R.id.hs_layout);
        //LinearLayout satLow_ll = (LinearLayout) main.findViewById(R.id.ls_layout);
        //LinearLayout valHigh_ll = (LinearLayout) main.findViewById(R.id.hv_layout);
        //LinearLayout valLow_ll = (LinearLayout) main.findViewById(R.id.lv_layout);

        final SeekBar hueHigh_bar = (SeekBar) main.findViewById(R.id.hh_bar);
        final SeekBar hueLow_bar = (SeekBar) main.findViewById(R.id.lh_bar);
        final SeekBar satHigh_bar = (SeekBar) main.findViewById(R.id.hs_bar);
        final SeekBar satLow_bar = (SeekBar) main.findViewById(R.id.ls_bar);
        final SeekBar valHigh_bar  = (SeekBar) main.findViewById(R.id.hv_bar);
        final SeekBar valLow_bar  = (SeekBar) main.findViewById(R.id.lv_bar);

        final EditText val_hue_high  = (EditText) main.findViewById(R.id.hh_value);
        final EditText val_hue_low  = (EditText) main.findViewById(R.id.lh_value);
        final EditText val_sat_high  = (EditText) main.findViewById(R.id.hs_value);
        final EditText val_sat_low  = (EditText) main.findViewById(R.id.ls_value);
        final EditText val_val_high = (EditText) main.findViewById(R.id.hv_value);
        final EditText val_val_low  = (EditText) main.findViewById(R.id.lv_value);

        final Button menuButton = (Button) main.findViewById(R.id.menu_button);
        final Button rbghsvButton = (Button) main.findViewById(R.id.rbghsv_button);

        JavaCameraView mCameraView = new JavaCameraView(main, CameraBridgeViewBase.CAMERA_ID_ANY);
        mCameraView.enableFpsMeter();
        mCameraView.enableView();
        mCameraView.setMaxFrameSize(800,640);

        cammeraFrame.addView(mCameraView,new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));

        mCameraView.setCvCameraViewListener(main);


        //lISTENERS.....
        hueHigh_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                main.high_hValue = seekBar.getProgress();
                val_hue_high.setText(String.valueOf(progress));
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
                val_sat_high.setText(String.valueOf(progress));
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
                val_val_high.setText(String.valueOf(progress));

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        hueLow_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                main.low_hValue = seekBar.getProgress();
                val_hue_low.setText(String.valueOf(progress));
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
                val_sat_low.setText(String.valueOf(progress));
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
                main.low_vValue = seekBar.getProgress();
                val_val_low.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        menuButton.setBackgroundColor(0);
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
                    rbghsvButton.setText(" CHANGE TO RBG ");
                }else{
                    main.isRBG = true;
                    rbghsvButton.setText(" CHANGE TO HSV ");
                }
            }
        });

        val_hue_high.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                hueHigh_bar.setProgress(Integer.parseInt(v.getText().toString()));
                return true;
            }
        });

        val_hue_low.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                hueLow_bar.setProgress(Integer.parseInt(v.getText().toString()));
                return true;
            }
        });

        val_sat_high.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                satHigh_bar.setProgress(Integer.parseInt(v.getText().toString()));
                return true;
            }
        });

        val_sat_low.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                satLow_bar.setProgress(Integer.parseInt(v.getText().toString()));
                return true;
            }
        });

        val_val_high.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                valHigh_bar.setProgress(Integer.parseInt(v.getText().toString()));
                return true;
            }
        });

        val_val_low.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                valLow_bar.setProgress(Integer.parseInt(v.getText().toString()));
                return true;
            }
        });



    }
}
