package guoxuebing.bawei.com.circletouchdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import guoxuebing.bawei.com.circletouchdemo.R;
import guoxuebing.bawei.com.circletouchdemo.view.CircleTouchView;

public class MainActivity extends AppCompatActivity {

    private CircleTouchView circle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        circle = (CircleTouchView) findViewById(R.id.circle);

        circle.setRadius(80);
    }
}
