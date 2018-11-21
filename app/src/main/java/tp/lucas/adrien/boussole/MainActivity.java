package tp.lucas.adrien.boussole;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private static final float rotationValueButton = 30;
    private float currentRotation = 0;


    SensorManager mSensorManager;
    Sensor mAccelerometer;
    Sensor mMagnetic;


    private TextView tvLat;
    private TextView tvLong;
    private TextView tvMagnetude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvLat = findViewById(R.id.tvLatitude);
        tvLong = findViewById(R.id.tvLongitude);
        tvMagnetude = findViewById(R.id.tvMagnetude);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mMagnetic = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    public void click_button_rotation(android.view.View view, float multiplier){
        currentRotation += 30 * multiplier;

        ImageView Compass = findViewById(R.id.imTrumpass);
        Compass.setRotation(currentRotation);
    }

    public void click_button_left(android.view.View view){
        click_button_rotation(view, -1);
    }

    public void click_button_right(android.view.View view){
        click_button_rotation(view, 1);
    }



    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mMagnetic, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {

        /*
        float x, y, z;
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            x = event.values[0];
            tvLong.setText(Float.toString(x));

            y = event.values[1];
            tvLat.setText(Float.toString(y));

            z = event.values[2];
        } */

        float xMagnetic, yMagnetic, zMagnetic;
        double magneticStrenght;
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            // Valeur du vecteur du champ magnétique (x,y,z)
            xMagnetic = event.values[0];
            yMagnetic = event.values[1];
            zMagnetic = event.values[2];
            // Valeur de la norme de ce vecteur
            magneticStrenght=Math.sqrt((double)
                    (xMagnetic*xMagnetic + yMagnetic*yMagnetic + zMagnetic*zMagnetic));

            tvMagnetude.setText(Double.toString(magneticStrenght));

            ImageView Compass = findViewById(R.id.imTrumpass);
            Compass.setRotation((float)magneticStrenght);
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //RAS
    }

}
