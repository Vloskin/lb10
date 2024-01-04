package com.example.lb10;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.util.Random;

public class GameActivity2 extends Activity {

    private static final int MARGIN = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CustomView(this));



    }

    class CustomView extends View {
        private int targetX;
        private int targetY;
        private Paint paint;

        Random random;

        public CustomView(Context context) {
            super(context);
            paint = new Paint();
            random = new Random();


            // Получаем размеры экрана
            Display display = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int screenWidth = size.x-MARGIN;
            int screenHeight = size.y-MARGIN;

            // Устанавливаем начальное положение цели в центр экрана
            targetX = (screenWidth / 2)-MARGIN;
            targetY = (screenHeight / 2)-MARGIN;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawColor(Color.BLACK);

            paint.setARGB(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));

            // Рисуем случайную фигуру
            int shape = random.nextInt(3); // Генерируем случайное число от 0 до 3
            switch (shape) {
                case 0: // Круг
                    canvas.drawCircle(targetX, targetY, 150, paint);
                    break;
                case 1: // Квадрат
                    canvas.drawRect(targetX-100, targetY-100, targetX + 100, targetY + 100, paint);
                    break;
                case 2: // Прямоугольник

                    canvas.drawRect(targetX-100, targetY-50, targetX + 100, targetY + 50, paint);
                    break;
            }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            // Получаем координаты касания
            int touchX = (int) event.getX();
            int touchY = (int) event.getY();

            // Если касание произошло внутри цели, перемещаем цель в случайное место на экране
            if (Math.abs(touchX - targetX) < 100 && Math.abs(touchY - targetY) < 100) {
                // Получаем размеры экрана
                Display display = ((WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                int screenWidth = size.x;
                int screenHeight = size.y;

                // Генерируем случайные координаты для нового положения цели
                targetX = (int) (random.nextInt(screenWidth));
                targetY = (int) (random.nextInt(screenHeight));

                // Перерисовываем холст
                invalidate();
            }

            return super.onTouchEvent(event);
        }
    }
}