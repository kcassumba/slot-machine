package com.example.slotmachine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private ImageView imageViews1;
    private ImageView imageViews2;
    private ImageView imageViews3;
    private Drawable cherry;
    private Drawable grape;
    private Drawable pear;
    private Drawable strawberry;
    private TextView pointsLabel;
    public updateSlot1 updateSlot1;
    public updateSlot2 updateSlot2;
    public updateSlot3 updateSlot3;
    private Handler handler;
    public boolean on;
    private SeekBar seekbar;
    private int points;
    private String rules_text;
    private int mili;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.linearLayout);
        cherry = getDrawable(R.drawable.cherry);
        grape = getDrawable(R.drawable.grape);
        pear = getDrawable(R.drawable.pear);
        strawberry = getDrawable(R.drawable.strawberry);
        pointsLabel = findViewById(R.id.pointsLabel);
        handler = new Handler();

        seekbar = findViewById(R.id.seekBar);
        imageViews1 = findViewById(R.id.imageView4);
        imageViews2 = findViewById(R.id.imageView3);
        imageViews3 = findViewById(R.id.imageView2);
        updateSlot1 = new updateSlot1();
        updateSlot2 = new updateSlot2();
        updateSlot3 = new updateSlot3();


        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mili = seekBar.getProgress();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        //Makes sure that the points stay the same after the screen is switched to landscape
        if (savedInstanceState == null) {
            points = 0;
            on = false;
            imageViews1.setImageDrawable(pear);
            imageViews2.setImageDrawable(cherry);
            imageViews3.setImageDrawable(grape);
        } else {
            points = savedInstanceState.getInt("POINTS");
            pointsLabel.setText("" + points);
            on = savedInstanceState.getBoolean("ON");

            //int g = (savedInstanceState.getInt("GRAPE"));
            if(savedInstanceState.getInt("STRAWBERRY1")== drawableToInt(strawberry)) {
                imageViews1.setImageDrawable(intToDrawable(savedInstanceState.getInt("STRAWBERRY1")));
            } else if(savedInstanceState.getInt("GRAPE1")== drawableToInt(grape))
                imageViews1.setImageDrawable(intToDrawable(savedInstanceState.getInt("GRAPE1")));
            else if(savedInstanceState.getInt("PEAR1")== drawableToInt(pear)){
                imageViews1.setImageDrawable(intToDrawable(savedInstanceState.getInt("PEAR1")));
            }else {
                imageViews1.setImageDrawable(intToDrawable(savedInstanceState.getInt("CHERRY1")));
            }

            if(savedInstanceState.getInt("STRAWBERRY2")== drawableToInt(strawberry)) {
                imageViews2.setImageDrawable(intToDrawable(savedInstanceState.getInt("STRAWBERRY2")));
            } else if(savedInstanceState.getInt("GRAPE2")== drawableToInt(grape))
                imageViews2.setImageDrawable(intToDrawable(savedInstanceState.getInt("GRAPE2")));
            else if(savedInstanceState.getInt("PEAR2")== drawableToInt(pear)){
                imageViews2.setImageDrawable(intToDrawable(savedInstanceState.getInt("PEAR2")));
            }else {
                imageViews2.setImageDrawable(intToDrawable(savedInstanceState.getInt("CHERRY2")));
            }

            if(savedInstanceState.getInt("STRAWBERRY3")== drawableToInt(strawberry)) {
                imageViews3.setImageDrawable(intToDrawable(savedInstanceState.getInt("STRAWBERRY3")));
            } else if(savedInstanceState.getInt("GRAPE1")== drawableToInt(grape))
                imageViews3.setImageDrawable(intToDrawable(savedInstanceState.getInt("GRAPE3")));
            else if(savedInstanceState.getInt("PEAR1")== drawableToInt(pear)){
                imageViews3.setImageDrawable(intToDrawable(savedInstanceState.getInt("PEAR3")));
            }else {
                imageViews3.setImageDrawable(intToDrawable(savedInstanceState.getInt("CHERRY3")));
            }











        }


    }
        //When the app is paused the events stop updating
        public void onPause () {
            super.onPause();
            handler.removeCallbacks(updateSlot1);
            handler.removeCallbacks(updateSlot2);
            handler.removeCallbacks(updateSlot3);
        }
        //When the app is resumed then it continues to update and change images
        public void onResume () {
            super.onResume();
            if (on) {
                handler.postDelayed(updateSlot1, 1000 + mili);
                handler.postDelayed(updateSlot2, 500 + mili);
                handler.postDelayed(updateSlot3, 2000 + mili);
            }
        }
        public int drawableToInt (Drawable d){
            if (d == cherry)
                return R.drawable.cherry;
            else if (d == grape)
                return R.drawable.grape;
            else if (d == pear)
                return R.drawable.pear;
            else
                return R.drawable.strawberry;
        }

        public Drawable intToDrawable ( int i){
            switch (i) {
                case R.drawable.strawberry:
                    return strawberry;
                case R.drawable.pear:
                    return pear;
                case R.drawable.cherry:
                    return cherry;
                default:
                    return grape;
            }
        }
        //Tracks the points, puts it in a bundle so that it stays the same after the screen is switched to landscape
        public void onSaveInstanceState(Bundle bundle){
            super.onSaveInstanceState(bundle);
            bundle.putInt("POINTS", points);
            bundle.putBoolean("ON", on);
            if(imageViews1.getDrawable()==strawberry){
                bundle.putInt("STRAWBERRY1", drawableToInt(strawberry));
            }else if(imageViews1.getDrawable()==grape){
                bundle.putInt("GRAPE1", drawableToInt(grape));
            }else if(imageViews1.getDrawable()==pear){
                bundle.putInt("PEAR1", drawableToInt(pear));
            }else{
                bundle.putInt("CHERRY1", drawableToInt(cherry));
            }

            if(imageViews2.getDrawable()==strawberry){
                bundle.putInt("STRAWBERRY2", drawableToInt(strawberry));
            }else if(imageViews2.getDrawable()==grape){
                bundle.putInt("GRAPE2", drawableToInt(grape));
            }else if(imageViews2.getDrawable()==pear){
                bundle.putInt("PEAR2", drawableToInt(pear));
            }else{
                bundle.putInt("CHERRY2", drawableToInt(cherry));
            }

            if(imageViews3.getDrawable()==strawberry){
                bundle.putInt("STRAWBERRY3", drawableToInt(strawberry));
            }else if(imageViews3.getDrawable()==grape){
                bundle.putInt("GRAPE3", drawableToInt(grape));
            }else if(imageViews3.getDrawable()==pear){
                bundle.putInt("PEAR3", drawableToInt(pear));
            }else{
                bundle.putInt("CHERRY3", drawableToInt(cherry));
            }


        }

        //When the "rules" button is pressed, it starts a new activity and displays a screen with text
        public void rulesPressed (View v){
            Intent i = new Intent(this, RulesScreenActivity.class);
            i.putExtra("RULES", rules_text);
            startActivityForResult(i, 1);

        }

        public void onActivityResult (Intent data, String rules_text){
            rules_text = data.getStringExtra(rules_text);
        }

        public void pointsGame (View v){
            //points = 0;
            if (imageViews1.getDrawable() == imageViews2.getDrawable() && imageViews2.getDrawable() == imageViews3.getDrawable()) {
                points += 100;
                pointsLabel.setText(points + "");
            } else if (imageViews1.getDrawable() == cherry || imageViews2.getDrawable() == cherry || imageViews3.getDrawable() == cherry) {
                points += 10;
                pointsLabel.setText(points + "");
            } else {
                pointsLabel.setText(points + "");
            }

        }
        public void startGame (View v){
            if (on) {
                on = false;
                handler.removeCallbacks(updateSlot1);
                handler.removeCallbacks(updateSlot2);
                handler.removeCallbacks(updateSlot3);
                pointsGame(v);
                //have 3 remove callbacks at different times

            } else {
                on = true;
                handler.postDelayed(updateSlot1, 1000 + mili);
                handler.postDelayed(updateSlot2, 500 + mili);
                handler.postDelayed(updateSlot3, 2000 + mili);
                //have 3 handlers post delayed to move at seprate times
            }
        }

        //three different updateSlots
        private class updateSlot1 implements Runnable {

            public void run() {

                if (imageViews1.getDrawable() == cherry) {
                    imageViews1.setImageDrawable(grape);
                } else if (imageViews1.getDrawable() == grape) {
                    imageViews1.setImageDrawable(pear);
                } else if (imageViews1.getDrawable() == pear) {
                    imageViews1.setImageDrawable(strawberry);
                } else if (imageViews1.getDrawable() == strawberry) {
                    imageViews1.setImageDrawable(cherry);
                } else {
                    imageViews1.setImageDrawable(cherry);
                }

                handler.postDelayed(updateSlot1, 1000 + mili);
            }
        }

        private class updateSlot2 implements Runnable {

            public void run() {
                if (imageViews2.getDrawable() == grape) {
                    imageViews2.setImageDrawable(cherry);
                } else if (imageViews2.getDrawable() == cherry) {
                    imageViews2.setImageDrawable(pear);
                } else if (imageViews2.getDrawable() == pear) {
                    imageViews2.setImageDrawable(strawberry);
                } else if (imageViews2.getDrawable() == strawberry) {
                    imageViews2.setImageDrawable(grape);
                } else {
                    imageViews2.setImageDrawable(grape);
                }

                handler.postDelayed(updateSlot2, 500 + mili);
            }
        }

        private class updateSlot3 implements Runnable {

            public void run() {
                if (imageViews3.getDrawable() == pear) {
                    imageViews3.setImageDrawable(cherry);
                } else if (imageViews3.getDrawable() == cherry) {
                    imageViews3.setImageDrawable(grape);
                } else if (imageViews3.getDrawable() == grape) {
                    imageViews3.setImageDrawable(strawberry);
                } else if (imageViews3.getDrawable() == strawberry) {
                    imageViews3.setImageDrawable(pear);
                } else {
                    imageViews3.setImageDrawable(pear);
                }

                handler.postDelayed(updateSlot3, 2000 + mili);

            }
        }

    }
