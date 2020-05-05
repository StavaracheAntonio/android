package com.example.lab2_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


public class Sensors extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);

        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(new CustomSensorAdapter(this, R.layout.activity_sensors, sensors));
    }

    public class CustomSensorAdapter extends ArrayAdapter<Sensor> {

        private int textViewResourceId;
        private TextView textView;

        public CustomSensorAdapter(Context context, int textViewResourceId, List<Sensor> items) {
            super(context, textViewResourceId, items);
            this.textViewResourceId = textViewResourceId;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(this.getContext()).inflate(textViewResourceId, parent, false);
            textView = (TextView) convertView.findViewById(R.id.textView3);

            Sensor item = getItem(position);
            if (item != null) {
                textView.setText("Name : " + item.getName() + "\n Vendor : " + item.getVendor() + "\n Version : " + item.getVersion() + "\n Resolution : " + item.getResolution() + "\n Maximum Range : " + item.getMaximumRange() + "\n Power : " + item.getPower());
            }
            return convertView;
        }
    }

}

