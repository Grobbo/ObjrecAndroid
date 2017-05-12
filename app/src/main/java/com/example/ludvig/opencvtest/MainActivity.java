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
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2 {

    private static final String TAG = "mainActivity";
    private JavaCameraView mCameraView;
    private Mat hsvMat;
    private Mat imgMat;
    private Mat resultMat;
    Mat hierarcy;
    Mat bwMat;

    int high_hValue;
    int high_sValue;
    int high_vValue;
    int low_hValue;
    int low_sValue;
    int low_vValue;
    boolean isMenu = true;
    boolean isRBG = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWindowMode();
        OpenCVInit();

        setContentView(R.layout.activity_main);
        mCameraView = GUI_Factory.getCameraView(this);
        LinearLayout fl = (LinearLayout) findViewById(R.id.Cameraframe);
        fl.addView(mCameraView,new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));

        mCameraView.setCvCameraViewListener(this);
        GUI_Factory.menu_init(this);



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
        hierarcy = new Mat();
        bwMat = new Mat();


    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame frame) {
        //Do calculations on frame...
        imgMat = frame.rgba();

        //Imgproc.blur(imgMat,hsvMat,new Size(7,7));  //TODO Ha kvar?
        Imgproc.cvtColor(imgMat,hsvMat, Imgproc.COLOR_RGB2HSV);   //RGB -> HSV

        //...
        Core.inRange(hsvMat, new Scalar(low_hValue, low_sValue, low_vValue), new Scalar(high_hValue, high_sValue, high_vValue), resultMat);
        Imgproc.blur(resultMat,resultMat,new Size(7,7));
        //Imgproc.medianBlur(resultMat, resultMat, 3);
        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        Imgproc.findContours(resultMat,contours,hierarcy,Imgproc.RETR_LIST,Imgproc.CHAIN_APPROX_SIMPLE);
        Imgproc.drawContours(imgMat,contours,-1, new Scalar(0,255,0),3);

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
