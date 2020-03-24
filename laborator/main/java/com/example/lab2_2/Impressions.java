package com.example.lab2_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Impressions extends AppCompatActivity {

    EditText editText;
    String file = "impressions.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_impressions);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_impressions);
    }

    public void save(View v) {
        editText = findViewById( R.id.edit_text);

        String s= editText.getText().toString();
        try {
            FileOutputStream f = openFileOutput(file, MODE_PRIVATE);
            f.write(s.getBytes());
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }

    public void load(View v) {

        editText = findViewById( R.id.edit_text);
        try {
            FileInputStream f = openFileInput(file);
            InputStreamReader inputStream = new InputStreamReader(f);
            StringBuilder strBuilder = new StringBuilder();
            BufferedReader b = new BufferedReader(inputStream);

            String s;
            while ((s = b.readLine()) != null)
                strBuilder.append(s).append('\n');

            editText.setText(strBuilder.toString());
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }
}
