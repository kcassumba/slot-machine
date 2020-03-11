package com.example.slotmachine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private ImageView[] imageViews;
    private Drawable cherry;
    private Drawable grape;
    private Drawable pear;
    private Drawable strawberry;
    private TextView pointsLabel;
    private Random rand;
    private int fruitLocation;
 //   public updateSlot updateSlot;
    private Handler handler;
    public boolean on;
    private SeekBar seekbar;
    private int points;
    private int i;
    private String rules_text;

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
        fruitLocation = rand.nextInt(3);
        handler = new Handler();
        on = false;
        seekbar = findViewById(R.id.seekBar);
        i=0;




    }

    public void rulesPressed(View v){
        Intent i  = new Intent(this, RulesScreenActivity.class);
        i.putExtra("RULES", rules_text);
        startActivityForResult(i,1);

    }

    public void onActivityResult(Intent data, String rules_text){
        rules_text = data.getStringExtra(rules_text);
    }

/*
    public void startGame(View v){
        if(on){
            on = false;
            handler.removeCallbacks(updateSlot);

        }else {
            on = true;

            handler.postDelayed(updateSlot, 1000);
        }
    }

    private class updateSlot implements Runnable{

        public void run(){

        for(int i=0; i<3; i++){ // or create three image views and set indiviual slots to different images

            imageViews[fruitLocation].setImageDrawable(null);
            fruitLocation = rand.nextInt(3);
            imageViews[fruitLocation].setImageDrawable(   );
            handler.postDelayed(updateSlot, 1000);
            }
        }
    }
*/
}
