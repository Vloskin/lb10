package com.example.lb10;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    private TextView touchInfoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        touchInfoTextView = findViewById(R.id.touch_info_text_view);
        touchInfoTextView.setOnTouchListener(this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getActionMasked();
        int pointerCount = event.getPointerCount();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                touchInfoTextView.setText("Нажато одиночное касание\n");
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                touchInfoTextView.append("Нажато множественное касание\n");
                break;
            case MotionEvent.ACTION_UP:
                touchInfoTextView.setText("Отпущено одиночное касание\n");
                break;
            case MotionEvent.ACTION_POINTER_UP:
                touchInfoTextView.append("Отпущено множественное касание\n");
                break;
            case MotionEvent.ACTION_MOVE:
                touchInfoTextView.setText("Перемещается\n");
                break;
        }

        for (int i = 0; i < pointerCount; i++) {
            int pointerId = event.getPointerId(i);
            float x = event.getX(i);
            float y = event.getY(i);
            touchInfoTextView.append("Касание #" + pointerId + " - X: " + x + ", Y: " + y + "\n");
        }

        return true;
    }
}
