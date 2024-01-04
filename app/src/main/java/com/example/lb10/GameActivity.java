package com.example.lb10;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity  {

    private LinearLayout container;
    private View[] bubbles; // массив объектов, которые можно двигать
    private View movingBubble = null; // ссылка на объект, который мы сейчас двигаем

    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        container = findViewById(R.id.container);
        // Инициализируем массив bubbles с нужным количеством элементов и получаем их из layout
        bubbles = new View[]{findViewById(R.id.bubble1), findViewById(R.id.bubble2),findViewById(R.id.bubble3),findViewById(R.id.bubble4),findViewById(R.id.bubble5),findViewById(R.id.bubble6),findViewById(R.id.bubble7) };
        backButton = (Button) findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        container.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Проверяем, какой объект был коснут, и помечаем как movingBubble
                        for (View bubble : bubbles) {
                            if (isMotionInsideView(bubble, motionEvent.getX(), motionEvent.getY())) {
                                movingBubble = bubble; // Помечаем этот bubble как движущийся
                                break;
                            }
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        // Перемещаем объект только если было касание внутри какого-либо bubble
                        if (movingBubble != null) {
                            float x = motionEvent.getX();
                            float y = motionEvent.getY();
                            movingBubble.setX(x - movingBubble.getWidth() / 2);
                            movingBubble.setY(y - movingBubble.getHeight() / 2);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        // Когда касание завершено или отменено, сбрасываем ссылку на движущийся bubble
                        movingBubble = null;
                        break;
                }
                // Возвращаем true, чтобы указать, что событие было обработано
                return true;
            }
        });
    }

    // Вспомогательный метод для определения, находится ли точка касания внутри видимой области View
    private boolean isMotionInsideView(View view, float x, float y) {
        if (view == null) return false;

        int[] location = new int[2];
        view.getLocationOnScreen(location);
        float viewX = location[0];
        float viewY = location[1];

        // Проверяем, что координаты касания находятся внутри границ View
        return x >= viewX && x < viewX + view.getWidth() && y >= viewY && y < viewY + view.getHeight();
    }
}