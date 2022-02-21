package com.example.atividade2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor light;
    private Sensor temp;
    TextView lightValue;
    TextView tempValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button getGPSBtn = (Button) findViewById(R.id.button);
        ActivityCompat.requestPermissions(MainActivity.this, new
                String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        getGPSBtn.setOnClickListener(v -> {
            GPSTracker g = new GPSTracker(getApplicationContext());
            Location l = g.getLocation();
            if(l!=null)
            {
                double lat = l.getLatitude();
                double longi = l.getLongitude();
                Toast.makeText(getApplicationContext(), "Lat: "+lat + " Long: " +
                        longi, Toast.LENGTH_LONG).show();
            }
        });

        lightValue = (TextView)findViewById(R.id.light);
        tempValue = (TextView)findViewById(R.id.temp);
        sensorManager = (SensorManager)
                getSystemService(Context.SENSOR_SERVICE);
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        temp=sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if(light != null)
        {
            sensorManager.registerListener(MainActivity.this, light,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }else
        {
            lightValue.setText(R.string.not_light);
        }
        if(temp != null)
        {
            sensorManager.registerListener(MainActivity.this, temp,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }else
        {
            tempValue.setText(R.string.not_temp);
        }

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        if(sensor.getType() == Sensor.TYPE_LIGHT)
        {
            lightValue.setText("Light Intensity: " + event.values[0]);
        }
        if(sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE)
        {
            tempValue.setText("Temperature in °C: " + event.values[0]);
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}