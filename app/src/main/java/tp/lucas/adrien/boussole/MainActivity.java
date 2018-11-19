package tp.lucas.adrien.boussole;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static final float rotationValueButton = 30;
    private float currentRotation = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

}
