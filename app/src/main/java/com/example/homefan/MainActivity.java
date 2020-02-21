package com.example.homefan;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    ToggleButton toggeButton;
    ImageView imageView;
    ObjectAnimator rotateAnimator;
    Switch switchButton;
    SeekBar seekBar;
    final int SPEED[] = {0, 5000, 3000, 1000};
    GradientDrawable gd = new GradientDrawable();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            imageView = (ImageView) findViewById(R.id.fan);
            toggeButton = (ToggleButton) findViewById(R.id.off);
            switchButton = (Switch) findViewById(R.id.Light);
            seekBar = (SeekBar) findViewById(R.id.speed);

            rotateAnimator=ObjectAnimator. ofFloat (imageView, "rotation", 0,360);
            rotateAnimator.setDuration(1000);
            rotateAnimator.setRepeatCount(ValueAnimator. INFINITE );
            rotateAnimator.setInterpolator(new LinearInterpolator());

        toggeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(toggeButton.isChecked()){
                rotateAnimator.setDuration(SPEED[0]);
                rotateAnimator.start();
            }
            else {
                rotateAnimator.end();
            }
            }

        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int parameter, boolean fromUser) {
                if(fromUser && toggeButton.isChecked()){
                    rotateAnimator.setDuration(SPEED[parameter]);
                    rotateAnimator.start();
                }
                else {
                    rotateAnimator.end();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        gd.setShape(GradientDrawable. RECTANGLE );
        gd.setGradientType(GradientDrawable. RADIAL_GRADIENT );
        gd.setGradientRadius(330);

        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switchButton.isChecked()==true){
                    gd.setColors(new int[]{  Color. YELLOW, Color. TRANSPARENT
                    });
                    imageView.setBackground(gd);
                }
                if(switchButton.isChecked()==false){
                    imageView.setBackgroundColor(Color. TRANSPARENT );
                }
            }
        });

    }
}
