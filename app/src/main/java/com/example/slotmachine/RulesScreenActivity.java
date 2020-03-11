package com.example.slotmachine;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.slotmachine.R;

public class RulesScreenActivity extends AppCompatActivity {

    private TextView rules_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        rules_text = findViewById(R.id.rules_text);

    }

    @Override
    public void onBackPressed() {

        Intent i = new Intent();
        i.putExtra("RULES", rules_text.getText());
        setResult(RESULT_OK, i);
        finish();
    }

}