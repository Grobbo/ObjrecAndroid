package com.example.ludvig.opencvtest;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
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

import java.io.Serializable;

import static android.R.attr.id;
import static android.R.attr.state_empty;

/**
 * Created by Erik on 2017-05-13.
 */

public class ComputerVisionLayout {
    private State state;
    private SeekBar hueHigh_bar;
    private SeekBar hueLow_bar;
    private SeekBar satHigh_bar;
    private SeekBar satLow_bar;
    private SeekBar valHigh_bar;
    private SeekBar valLow_bar;


    public ComputerVisionLayout(State state) {
        this.state = state;
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

        hueHigh_bar = (SeekBar) main.findViewById(R.id.hh_bar);
        hueLow_bar = (SeekBar) main.findViewById(R.id.lh_bar);
        satHigh_bar = (SeekBar) main.findViewById(R.id.hs_bar);
        satLow_bar = (SeekBar) main.findViewById(R.id.ls_bar);
        valHigh_bar  = (SeekBar) main.findViewById(R.id.hv_bar);
        valLow_bar  = (SeekBar) main.findViewById(R.id.lv_bar);

        final EditText val_hue_high  = (EditText) main.findViewById(R.id.hh_value);
        final EditText val_hue_low  = (EditText) main.findViewById(R.id.lh_value);
        final EditText val_sat_high  = (EditText) main.findViewById(R.id.hs_value);
        final EditText val_sat_low  = (EditText) main.findViewById(R.id.ls_value);
        final EditText val_val_high = (EditText) main.findViewById(R.id.hv_value);
        final EditText val_val_low  = (EditText) main.findViewById(R.id.lv_value);

        final Button menuButton = (Button) main.findViewById(R.id.menu_button);
        final Button rbghsvButton = (Button) main.findViewById(R.id.rbghsv_button);
        final Button saveImportButton = (Button) main.findViewById(R.id.saveImport_button);

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
                state.high_hValue = seekBar.getProgress();
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
                state.high_sValue = seekBar.getProgress();
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
                state.high_vValue = seekBar.getProgress();
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
                state.low_hValue = seekBar.getProgress();
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
                state.low_sValue = seekBar.getProgress();
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
                state.low_vValue = seekBar.getProgress();
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
                if(state.isMenu){
                    state.isMenu = false;
                    hide_menu.setVisibility(View.INVISIBLE);
                    hide_menu.setEnabled(false);
                    menuButton.setText("SHOW MENU");
                }else{
                    state.isMenu = true;
                    hide_menu.setVisibility(View.VISIBLE);
                    hide_menu.setEnabled(true);
                    menuButton.setText("HIDE MENU");
                }
            }
        });

        rbghsvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(state.isRBG){
                    state.isRBG = false;
                    rbghsvButton.setText(" CHANGE TO RBG ");
                }else{
                    state.isRBG = true;
                    rbghsvButton.setText(" CHANGE TO HSV ");
                }
            }
        });

        saveImportButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(main,SettingsActivity.class);
                i.putExtra("state", main.state);
                main.startActivityForResult(i,0);
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
    public void setNewState(State state){
        this.state = state;
        hueHigh_bar.setProgress(state.high_hValue);
        satHigh_bar.setProgress(state.high_sValue);
        valHigh_bar.setProgress(state.high_vValue);
        hueLow_bar.setProgress(state.low_hValue);
        satLow_bar.setProgress(state.low_sValue);
        valLow_bar.setProgress(state.low_vValue);

    }
}
