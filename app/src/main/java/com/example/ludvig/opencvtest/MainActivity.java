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


import android.widget.LinearLayout;

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
        //mCameraView.setMinimumHeight();

        LinearLayout ll = new LinearLayout(this);
        ll.addView(mCameraView, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT));

        setContentView(ll);
        mCameraView.setCvCameraViewListener(this);




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
        Core.inRange(hsvMat, new Scalar(12, 180, 80), new Scalar(55, 255, 255), resultMat);
        //Imgproc.blur(resultMat,resultMat,new Size(10,10));
        //Imgproc.medianBlur(resultMat, resultMat, 3);


        //Imgproc.findContours(...);
        //Imgproc.drawContours(...);

        return resultMat;
    }

    @Override
    public void onCameraViewStopped() {

    }
}
