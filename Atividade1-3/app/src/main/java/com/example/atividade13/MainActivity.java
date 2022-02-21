package com.example.atividade13;

import static java.lang.Math.abs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private boolean f_int=true;
    private float X, Y, Z;
    EditText co_x;
    EditText co_y;
    EditText co_z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        co_x =  findViewById(R.id.editTextTextPersonName);
        co_y =  findViewById(R.id.editTextTextPersonName2);
        co_z =  findViewById(R.id.editTextTextPersonName3);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()== Sensor.TYPE_ACCELEROMETER) {
            float sensorX = event.values[0];
            float sensorY = event.values[1];
            float sensorZ = event.values[2];

            if (f_int==true){
                X = sensorX;
                Y = sensorY;
                Z = sensorZ;
                f_int = false;
            }

            if (abs(X - sensorX) > 2 || abs(Y - sensorY) > 2 || abs(Z - sensorZ) > 2 ){
                Intent inte = new Intent(this, MainActivity2.class);
                inte.putExtra("message", getString(R.string.calma));
                MainActivity.this.startActivity(inte);
            }

            X = sensorX;
            Y = sensorY;
            Z = sensorZ;
            co_x.setText(String.valueOf(sensorX));
            co_y.setText(String.valueOf(sensorY));
            co_z.setText(String.valueOf(sensorZ));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}