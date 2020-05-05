package com.example.matchinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button5x4;
    Button button6x5;
    Button buttonMatch3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button5x4 = (Button) findViewById(R.id.button1);
        button5x4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openGame5x4Activity();
            }
        });

        button6x5 = (Button) findViewById(R.id.button2);
        button6x5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openGame6x5Activity();
            }
        });

        buttonMatch3 = (Button) findViewById(R.id.button3);
        buttonMatch3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openGameMatch3Activity();
            }
        });
    }

    public void openGame5x4Activity(){
        Intent intent5x4 = new Intent(this, Game5x4Activity.class);
        startActivity(intent5x4);
    }

    public void openGame6x5Activity(){
        Intent intent6x5 = new Intent(this, Game6x5Activity.class);
        startActivity(intent6x5);
    }

    public void openGameMatch3Activity(){
        Intent intentMatch3 = new Intent(this, GameMatch3Activity.class);
        startActivity(intentMatch3);
    }
}
