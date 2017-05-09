package com.example.ludvig.opencvtest;

import android.content.pm.ActivityInfo;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;

import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class MainActivity extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2 {

    private static final String TAG = "mainActivity";
    private JavaCameraView mCameraView;
    private Mat blurredImage;
    private Mat imgMat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWindowMode();
        OpenCVInit();
        //setContentView(R.layout.activity_main);


        mCameraView = new JavaCameraView(this, CameraBridgeViewBase.CAMERA_ID_ANY);
        mCameraView.enableFpsMeter();
        mCameraView.enableView();

        setContentView(mCameraView);
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
        blurredImage = new Mat();
        imgMat = new Mat();
    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame frame) {
        //Do calculations on frame...
        imgMat = frame.rgba();

        Imgproc.blur(imgMat,blurredImage,new Size(7,7));
        //Imgproc.cvtColor();   //convert colormode
        //...
        //Imgproc.findContours();

        return blurredImage;
    }

    @Override
    public void onCameraViewStopped() {

    }
}
