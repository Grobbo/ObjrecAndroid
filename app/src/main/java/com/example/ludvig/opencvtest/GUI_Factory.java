package com.example.ludvig.opencvtest;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;

/**
 * Created by Erik on 2017-05-12.
 */

public class GUI_Factory {

    private GUI_Factory(){

    }

    static JavaCameraView getCameraView(Context c){
        JavaCameraView mCameraView = new JavaCameraView(c, CameraBridgeViewBase.CAMERA_ID_ANY);
        mCameraView.enableFpsMeter();
        mCameraView.enableView();
        mCameraView.setMaxFrameSize(800,640);
        return mCameraView;
    }

    static void menu_init(final MainActivity main){
        SeekBar hh_bar = (SeekBar) main.findViewById(R.id.hh_bar);
        hh_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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


        SeekBar hs_bar = (SeekBar) main.findViewById(R.id.hs_bar);
        hs_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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

        SeekBar hv_bar = (SeekBar) main.findViewById(R.id.hv_bar);
        hv_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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

        SeekBar lh_bar = (SeekBar) main.findViewById(R.id.lh_bar);
        lh_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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

        SeekBar ls_bar = (SeekBar) main.findViewById(R.id.ls_bar);
        ls_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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

        SeekBar lv_bar = (SeekBar) main.findViewById(R.id.lv_bar);
        lv_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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

        Button btn = (Button) main.findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout l = (LinearLayout) main.findViewById(R.id.menu);
                Button btn = (Button) main.findViewById(R.id.button);
                if(main.isMenu){
                    main.isMenu = false;
                    l.setVisibility(View.INVISIBLE);
                    l.setEnabled(false);
                    btn.setText("SHOW MENU");
                }else{
                    main.isMenu = true;
                    l.setVisibility(View.VISIBLE);
                    l.setEnabled(true);
                    btn.setText("HIDE MENU");
                }
            }
        });

        Button btn2 = (Button) main.findViewById(R.id.rbghsv_button);
        btn2.setOnClickListener(new View.OnClickListener() {
            Button b = (Button) main.findViewById(R.id.rbghsv_button);
            @Override
            public void onClick(View v) {
                if(main.isRBG){
                    main.isRBG = false;
                    b.setText("RBG");
                }else{
                    main.isRBG = true;
                    b.setText("HSV");
                }
            }
        });



    }
}
