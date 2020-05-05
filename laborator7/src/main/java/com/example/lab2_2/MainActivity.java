package com.example.lab2_2;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;


import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;


public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "";
    public String[] values = new String[]{"Dell", "Lenovo", "Asus"};
    public String[] descriptions = new String[]{"cod produs: 7564\nDisponibilitate: Stoc epuizat \n Pret: 1566.00 Lei",
            "cod produs: 8844\nDisponibilitate: In stoc \n Pret: 3260.00 Lei",
            "cod produs: 1456\nDisponibilitate: In stoc \n Pret: 1999.99 Lei"};

    public StableArrayAdapter adapter;

    public TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.list_view);
        this.textView = findViewById(R.id.textView);

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }

        this.adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, list);

        listView.setAdapter(adapter);

        //final TextView t = (TextView) findViewById(R.id.textView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //t.setText( descriptions[position]);
                //Log.d("", values[0]);

                //Toast.makeText(getApplicationContext(), descriptions[position], Toast.LENGTH_LONG).show();
                textView.setText(descriptions[position]);

            }
        });

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        boolean setBackground = prefs.getBoolean("text color", true);
        String color = prefs.getString("textView", "#000000");

        this.textView.setTextColor( Color.parseColor(color));
        String nickName = prefs.getString("nickname", " ");
        Log.d("", nickName);


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("", "resume");


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean setBackground = prefs.getBoolean("text color", true);
        String color = prefs.getString("textView", "#000000");

        this.textView.setTextColor( Color.parseColor(color));
        String nickName = prefs.getString("nickname", " ");

        Log.d("", nickName);

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("", "stop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("", "pause");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {


        Log.d("", "save instance");

        outState.putStringArray("products", this.values);
        outState.putStringArray("descriptions", this.descriptions);
        outState.putCharSequence("text view", this.textView.getText());

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        //super.onRestoreInstanceState(savedInstanceState);

        Log.d("", "restore instance");

        String[] aux = savedInstanceState.getStringArray("products");
        CharSequence t = savedInstanceState.getCharSequence("text view");
        this.values = aux;
        this.descriptions = savedInstanceState.getStringArray("descriptions");
        this.textView.setText(t);


        this.adapter.notifyDataSetChanged();

        super.onRestoreInstanceState(savedInstanceState);
    }

    public class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId, List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = new Intent(this, Activity2.class);

        TextView editText = (TextView) findViewById(R.id.textView);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);

        switch (item.getItemId()) {
            case R.id.preferences:
                startActivity( new Intent(this, Settings.class));
                return true;
            case R.id.item1:
                startActivity(intent);
                return true;
            case R.id.item2:

                this.showDialog();
                //startActivity(intent);
                return true;
            case R.id.impressions:
                startActivity( new Intent(this, Impressions.class));
                return true;
            case R.id.sensors:
                startActivity( new Intent(this, Sensors.class));
                return true;
            case R.id.gps_location:
                startActivity( new Intent(this, GPSLocation.class));
                return true;
            case R.id.camera:
                startActivity( new Intent(this, Camera.class));
                return true;

                default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder( this);//getActivity());
        builder.setMessage("dialog?")//R.string.dialog_fire_missiles)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it

        builder.create().show();
    }
}