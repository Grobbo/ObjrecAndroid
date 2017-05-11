package com.example.ludvig.opencvtest;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;


import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;

import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class MainActivity extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2 {

    private static final String TAG = "mainActivity";
    private JavaCameraView mCameraView;
    private Mat hsvMat;
    private Mat imgMat;
    private Mat resultMat;
    private int high_hValue;
    private int high_sValue;
    private int high_vValue;
    private int low_hValue;
    private int low_sValue;
    private int low_vValue;
    boolean isMenu = true;
    boolean isRBG = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWindowMode();
        OpenCVInit();
        //setContentView(R.layout.activity_main);

        mCameraView = new JavaCameraView(this, CameraBridgeViewBase.CAMERA_ID_ANY);
        mCameraView.enableFpsMeter();
        mCameraView.enableView();
        mCameraView.setMaxFrameSize(800,640);

        setContentView(R.layout.activity_main);

        LinearLayout fl = (LinearLayout) findViewById(R.id.Cameraframe);
        fl.addView(mCameraView,new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));

        mCameraView.setCvCameraViewListener(this);


        SeekBar hh_bar = (SeekBar) findViewById(R.id.hh_bar);
        hh_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    high_hValue = seekBar.getProgress();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


        SeekBar hs_bar = (SeekBar) findViewById(R.id.hs_bar);
        hs_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    high_sValue = seekBar.getProgress();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        SeekBar hv_bar = (SeekBar) findViewById(R.id.hv_bar);
        hv_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    high_vValue = seekBar.getProgress();

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        SeekBar lh_bar = (SeekBar) findViewById(R.id.lh_bar);
        lh_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                low_hValue = seekBar.getProgress();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        SeekBar ls_bar = (SeekBar) findViewById(R.id.ls_bar);
        ls_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                low_sValue = seekBar.getProgress();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        SeekBar lv_bar = (SeekBar) findViewById(R.id.lv_bar);
        lv_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                low_vValue = seekBar.getProgress();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout l = (LinearLayout) findViewById(R.id.menu);
                Button btn = (Button) findViewById(R.id.button);
                if(isMenu){
                    isMenu = false;
                    l.setVisibility(View.INVISIBLE);
                    l.setEnabled(false);
                    btn.setText("SHOW MENU");
                }else{
                    isMenu = true;
                    l.setVisibility(View.VISIBLE);
                    l.setEnabled(true);
                    btn.setText("HIDE MENU");
                }
            }
        });

        Button btn2 = (Button) findViewById(R.id.rbghsv_button);
        btn2.setOnClickListener(new View.OnClickListener() {
            Button b = (Button) findViewById(R.id.rbghsv_button);
            @Override
            public void onClick(View v) {
                if(isRBG){
                    isRBG = false;
                    b.setText("RBG");
                }else{
                    isRBG = true;
                    b.setText("HSV");
                }
            }
        });


    }

    private void OpenCVInit(){
        if (!OpenCVLoader.initDebug()) {
            Log.e(TAG, "  OpenCVLoader.initDebug(), not working.");
        } else {
            Log.d(TAG, "  OpenCVLoader.initDebug(), working.");
        }
    }

    private void setWindowMode(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

    }


    @Override
    public void onCameraViewStarted(int width, int height) {
        hsvMat = new Mat();
        imgMat = new Mat();
        resultMat = new Mat();
    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame frame) {
        //Do calculations on frame...
        imgMat = frame.rgba();

        //Imgproc.blur(imgMat,hsvMat,new Size(7,7));  //TODO Ha kvar?
        Imgproc.cvtColor(imgMat,hsvMat, Imgproc.COLOR_RGB2HSV);   //RGB -> HSV

        //...
        Core.inRange(hsvMat, new Scalar(low_hValue, low_sValue, low_vValue), new Scalar(high_hValue, high_sValue, high_vValue), resultMat);
        //Imgproc.blur(resultMat,resultMat,new Size(10,10));
        //Imgproc.medianBlur(resultMat, resultMat, 3);


        //Imgproc.findContours(...);
        //Imgproc.drawContours(...);
        if(isRBG){
            return imgMat;
        }else{
            return resultMat;
        }

    }

    @Override
    public void onCameraViewStopped() {

    }
}
