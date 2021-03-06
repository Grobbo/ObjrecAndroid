package com.example.ludvig.opencvtest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Path;
import android.os.Vibrator;
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
import android.widget.RadioButton;
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
    Mat kernel;
    State state;
    long timestamp;
    Scalar colorLine;
    Scalar textColor;

    ComputerVisionLayout CVL;

    RadioButton contoursBtn;
    RadioButton circleBtn;
    RadioButton noneBtn;
    RadioButton onBtn;

    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWindowMode();
        OpenCVInit();

        colorLine = new Scalar(0,255,0);
        textColor = new Scalar(255,0,0);

        state = new State();
        setContentView(R.layout.activity_main);
        CVL = new ComputerVisionLayout(state);
        CVL.GUI_init(this);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        contoursBtn = (RadioButton) findViewById(R.id.RadioContours);
        circleBtn = (RadioButton) findViewById(R.id.radiocircle);
        noneBtn = (RadioButton) findViewById(R.id.radionone);
        onBtn = (RadioButton) findViewById(R.id.VibOn);
        kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(6,6));
    }

    @Override
    protected void onPause() {
        super.onPause();
        CVL.disableView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        CVL.enableView();
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

        //Imgproc.blur(imgMat,hsvMat,new Size(7,7));
        Imgproc.cvtColor(imgMat,hsvMat, Imgproc.COLOR_RGB2HSV);   //RGB -> HSV


        //...
        Imgproc.medianBlur(hsvMat,hsvMat,5);
        Core.inRange(hsvMat, new Scalar(state.low_hValue, state.low_sValue, state.low_vValue), new Scalar(state.high_hValue, state.high_sValue, state.high_vValue), resultMat);
        //Core.inRange(hsvMat, new Scalar(39, 37, 44), new Scalar(180, 224, 208), resultMat);
        //Imgproc.blur(resultMat,resultMat,new Size(7,7));
        Imgproc.medianBlur(resultMat,resultMat,5);






        if(contoursBtn.isChecked()){
            List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
            Imgproc.findContours(resultMat,contours,hierarchy,Imgproc.RETR_LIST,Imgproc.CHAIN_APPROX_SIMPLE);
            Imgproc.drawContours(imgMat,contours,-1, new Scalar(0,255,0),3);
        }

        if(circleBtn.isChecked()){
            if(state.isRBG) { 

                //Imgproc.morphologyEx(resultMat,resultMat,Imgproc.MORPH_CLOSE,kernel);  //Cant handle computation...

                Imgproc.HoughCircles(resultMat,circleMat,Imgproc.CV_HOUGH_GRADIENT,1.9,resultMat.rows()/2,180,55,30,150);  //4:e arg = resolution. 1 ger samma res, 2 ger halva res osv.. 5:e distance between circles

                if(!circleMat.empty()) {
                    if(onBtn.isChecked()){
                        if((System.currentTimeMillis() - timestamp) > 400){
                            timestamp = System.currentTimeMillis();
                            vibrator.vibrate(200);
                        }

                    }
                    for (int i = 0; i < circleMat.cols(); i++) {
                        double[] circle = circleMat.get(0, i);       //[0,1,2] = [x,y,r]
                        Point center = new Point(circle[0], circle[1]);
                        Imgproc.circle(imgMat, center, (int) circle[2], colorLine, 3);
                        //Imgproc.putText(imgMat,circleMat.size().toString(),center,Core.FONT_HERSHEY_COMPLEX,1,textColor);
                    }
                }
            }
        }

        if(state.isRBG){
            return imgMat;
        }else{
            return resultMat;
        }

    }

    @Override
    public void onCameraViewStopped() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0 && resultCode == Activity.RESULT_OK){
            this.state = (State)data.getSerializableExtra("loadedState");
            CVL.setNewState(state);
        }
    }
}
