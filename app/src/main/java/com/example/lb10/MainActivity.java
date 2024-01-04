package com.example.lb10;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button touchButton;
    Button bubbleButton;
    Button imageButton;
    Button gameButton;
    Button gameButton2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        touchButton = (Button) findViewById(R.id.touchButton);
        bubbleButton = (Button) findViewById(R.id.bubbleButton);
        imageButton = (Button) findViewById(R.id.imageButton);
        gameButton = (Button) findViewById(R.id.gameButton);
        gameButton2 = (Button) findViewById(R.id.gameButton2);


        touchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,TouchActivity.class);
                startActivity(intent);
            }
        });

        bubbleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,BubbleActivity.class);
                startActivity(intent);
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,ImageActivity.class);
                startActivity(intent);
            }
        });
        gameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,GameActivity.class);
                startActivity(intent);
            }
        });
        gameButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, GameActivity2.class);
                startActivity(intent);
            }
        });
    }



}
