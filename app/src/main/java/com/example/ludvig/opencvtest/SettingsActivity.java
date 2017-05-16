package com.example.ludvig.opencvtest;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Ludvig on 2017-05-15.
 */

public class SettingsActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWindowMode();

        setContentView(R.layout.activity_settings);

        ListView list = (ListView) findViewById(R.id.objlist);
        ArrayList<SavedObj> arraylist = new ArrayList<SavedObj>();

        SavedObj test = new SavedObj("GARGAMEL");
        SavedObj test2 = new SavedObj("GAMMELSMUFEN");

        arraylist.add(test);
        arraylist.add(test2);

        ArrayAdapter<SavedObj> adapter = new ArrayAdapter<SavedObj>(this,android.R.layout.simple_list_item_1,arraylist);

        list.setAdapter(adapter);


    }



    private void setWindowMode(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

    }
}
