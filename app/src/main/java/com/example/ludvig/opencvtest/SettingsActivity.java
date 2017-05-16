package com.example.ludvig.opencvtest;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Created by Ludvig on 2017-05-15.
 */

public class SettingsActivity extends AppCompatActivity {

    Button saveButton;
    EditText nameText;
    File savedSettings;
    String fileName = "Settings";
    FileOutputStream fos;
    OutputStreamWriter osw;
    FileInputStream fis;
    BufferedReader br;
    State state;
    ArrayList<SavedObj> arraylist;
    ArrayAdapter<SavedObj> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWindowMode();

        Intent i = getIntent();
        state = (State) i.getSerializableExtra("state");

        setContentView(R.layout.activity_settings);
        ListView list = (ListView) findViewById(R.id.objlist);
        saveButton = (Button) findViewById(R.id.saveButton);
        nameText = (EditText) findViewById(R.id.nameEditText);


        arraylist = new ArrayList<SavedObj>();
        SavedObj test = new SavedObj("GARGAMEL", 0, 0, 0, 0, 0, 0);
        SavedObj test2 = new SavedObj("GAMMELSMUFEN", 0, 0, 0, 0, 0, 0);
        arraylist.add(test);
        arraylist.add(test2);

        adapter = new ArrayAdapter<SavedObj>(this, android.R.layout.simple_list_item_1, arraylist);
        list.setAdapter(adapter);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSettings();
            }
        });


        readDataFromFile();

    }


    private void setWindowMode() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

    }

    private void saveSettings() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            savedSettings = new File(Environment.getExternalStorageDirectory(), fileName);
        }
        try {
            if (!savedSettings.exists()) {
                savedSettings.createNewFile();
            }
            fos = new FileOutputStream(savedSettings, true);
            osw = new OutputStreamWriter(fos);
            osw.append(getStateDataString(nameText.getText().toString()));
            osw.close();
            fos.flush();
            fos.close();
            Toast.makeText(this, "saved to:" + savedSettings.getAbsolutePath(), Toast.LENGTH_LONG).show();

            //update list with new entry
            arraylist.add(new SavedObj(nameText.getText().toString(), state.high_hValue, state.high_sValue, state.high_vValue,
                    state.low_hValue, state.low_sValue, state.low_vValue));
            adapter.notifyDataSetChanged();

        } catch (IOException e) {
            Log.e("Exception", "Save failed: " + e.toString());
        }
    }

    private String getStateDataString(String name) {
        String dataString = name + "." + state.high_hValue + "." + state.high_sValue + "." + state.high_vValue
                + "." + state.low_hValue + "." + state.low_sValue + "." + state.low_vValue + "\n";
        return dataString;
    }

    private void readDataFromFile() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            savedSettings = new File(Environment.getExternalStorageDirectory(), fileName);
        }
        try {
            if (!savedSettings.exists()) {
                savedSettings.createNewFile();
            }
            fis = new FileInputStream(savedSettings.getAbsolutePath());
            br = new BufferedReader(new InputStreamReader(fis));
            String line = br.readLine();
            while(line!=null){
                String data[] = line.split("\\.");
                arraylist.add(new SavedObj(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]), Integer.parseInt(data[3]),
                        Integer.parseInt(data[4]), Integer.parseInt(data[5]), Integer.parseInt(data[6])));
                adapter.notifyDataSetChanged();
                line=br.readLine();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
