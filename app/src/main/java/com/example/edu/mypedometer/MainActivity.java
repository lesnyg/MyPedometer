package com.example.edu.mypedometer;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,SensorEventListener {
    float previousY,currentY,steps,threshold;
    float previousX,currentX;
    float previousZ,currentZ;
    float acceleration;
    SensorManager sensorManager;
    TextView textViewSteps,textViewGx,textViewGy,textViewGz;
   // SeekBar seekBarSensitive;
    Button buttonreset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        threshold = 3;//seekBarSensitive.getProgress();
        previousY = currentY =  0;
        previousX = currentX = 0;
        acceleration = 0.0f;
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(
                Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);

        buttonreset = (Button)findViewById(R.id.btnReset);
        buttonreset.setOnClickListener(this);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        textViewSteps=findViewById(R.id.textView);
        textViewGx = findViewById(R.id.textView3);
        textViewGy = findViewById(R.id.textView4);
        textViewGz = findViewById(R.id.textView5);
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        currentX = x;
        currentY = y;
        currentZ = z;
        if(Math.abs(currentY - previousY) > threshold || (currentX - previousX) > threshold){
            steps++;
            textViewSteps.setText(String.valueOf(steps));
        }
        textViewGx.setText(String.valueOf(x));
        textViewGy.setText(String.valueOf(y));
        textViewGz.setText(String.valueOf(z));
        previousX = x;
        previousY = y;
        previousZ = z;


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onClick(View v) {
        steps=0;
        textViewSteps.setText(String.valueOf(steps));
    }

//    @Override
//    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//
//    }
//
//    @Override
//    public void onStartTrackingTouch(SeekBar seekBar) {
//
//    }
//
//    @Override
//    public void onStopTrackingTouch(SeekBar seekBar) {
//
//    }


}
