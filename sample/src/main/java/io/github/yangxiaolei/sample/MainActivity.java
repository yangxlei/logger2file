package io.github.yangxiaolei.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.github.yangxiaolei.demo.Logger;
import io.github.yangxiaolei.demo.LoggerFactory;

public class MainActivity extends AppCompatActivity {

    Logger tripLogger = LoggerFactory.getLogger("Trip");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tripLogger.error("hello Logger", new NullPointerException());

    }
}
