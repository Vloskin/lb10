package com.example.lb10;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class ImageActivity extends AppCompatActivity {

    private LinearLayout container;
    private View bubble;

    private Button backButton;
    private boolean isInsideBubble = false; // Флаг для отслеживания начала касания внутри bubble

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        container = findViewById(R.id.container);
        bubble = findViewById(R.id.bubble);
        backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ImageActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        container.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Проверяем началось ли касание внутри области bubble
                        isInsideBubble = isMotionInsideView(bubble, motionEvent.getX(), motionEvent.getY());
                        break;
                    case MotionEvent.ACTION_MOVE:
                        // Перемещаем bubble только если касание началось внутри его
                        if (isInsideBubble) {
                            float x = motionEvent.getX();
                            float y = motionEvent.getY();
                            bubble.setX(x - bubble.getWidth() / 2);
                            bubble.setY(y - bubble.getHeight() / 3);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        // Когда касание завершено или отменено, сбрасываем флаг
                        isInsideBubble = false;
                        break;
                }
                return true;
            }
        });
    }

    private boolean isMotionInsideView(View view, float x, float y) {
        if (view == null) return false;

        int[] location = new int[2];
        view.getLocationOnScreen(location);
        float viewX = location[0];
        float viewY = location[1];

        // Проверяем, что координаты касания находятся внутри границ View
        return x >= viewX && x <= viewX + view.getWidth() && y >= viewY && y <= viewY + view.getHeight();
    }
}