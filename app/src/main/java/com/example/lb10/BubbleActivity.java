package com.example.lb10;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class BubbleActivity extends AppCompatActivity {

    Button backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubble);

        BubbleView bubbleView = findViewById(R.id.bubbleView);
        backButton = (Button) findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BubbleActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

}

class BubbleView extends View {
    private static final int NUM_BUBBLES = 1000;
    private Bubble[] bubbles;
    private Paint paint;
    private Random random;

    public BubbleView(Context context, AttributeSet attrs) {
        super(context, attrs);

        bubbles = new Bubble[NUM_BUBBLES];
        paint = new Paint();
        random = new Random();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (Bubble bubble : bubbles) {
            if (bubble != null) {
                paint.setColor(bubble.getColor());
                canvas.drawPath(bubble.getPath(), paint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            float y = event.getY();

            for (int i = 0; i < NUM_BUBBLES; i++) {
                if (bubbles[i] == null) {
                    createBubble(i, x, y);
                    break;
                }
            }

            invalidate();
        }

        return true;
    }

    private void createBubble(int index, float x, float y) {
        float radius = random.nextInt(100) + 50;
        int color = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        Path path = new Path();
        path.moveTo((float) (x + radius * Math.cos(0)), (float) (y + radius * Math.sin(0)));
        for (int i = 1; i <= 11; i++) {
            float angle = (float) (i * 2 * Math.PI / 11);
            float px = (float) (x + radius * Math.cos(angle));
            float py = (float) (y + radius * Math.sin(angle));
            path.lineTo(px, py);
        }

        path.close();

        bubbles[index] = new Bubble(path, color);
    }

    private static class Bubble {
        private Path path;
        private int color;

        public Bubble(Path path, int color) {
            this.path = path;
            this.color = color;
        }

        public Path getPath() {
            return path;
        }

        public int getColor() {
            return color;
        }
    }
}