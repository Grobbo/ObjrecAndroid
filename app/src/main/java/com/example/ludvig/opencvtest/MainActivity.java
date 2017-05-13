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
import android.widget.TextView;

import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;

import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2 {

    private static final String TAG = "mainActivity";
    private JavaCameraView mCameraView;
    private Mat hsvMat;
    private Mat imgMat;
    private Mat resultMat;
    Mat hierarchy;
    Mat circleMat;

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
        ComputerVisionLayout CVL = new ComputerVisionLayout();
        CVL.GUI_init(this);


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
        hierarchy = new Mat();
        circleMat = new Mat();


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
        //List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        //Imgproc.findContours(resultMat,contours,hierarchy,Imgproc.RETR_LIST,Imgproc.CHAIN_APPROX_SIMPLE);
        //Imgproc.drawContours(imgMat,contours,-1, new Scalar(0,255,0),3);
        if(isRBG) {         //TODO rita ut alltid istället?
            Imgproc.HoughCircles(resultMat,circleMat,Imgproc.CV_HOUGH_GRADIENT,2,resultMat.rows()/4);  //4:e arg = resolution. 1 ger samma res, 2 ger halva res osv..
            for(int i = 0; i < circleMat.cols();i++) {
                double[] circle = circleMat.get(0, i);       //[0,1,2] = [x,y,r]

                Point center = new Point(circle[0], circle[1]);

                Imgproc.circle(imgMat, center, (int) circle[2], new Scalar(0, 255, 0), 3);
            }
        }



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
