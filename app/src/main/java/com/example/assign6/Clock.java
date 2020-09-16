package com.example.assign6;
//CSC 309
//Jonathon Gower
//Clock Program
//Date: 10/31/2019
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import java.util.Calendar;
import androidx.annotation.Nullable;
import com.example.assign6.R;

public class Clock extends View  { //drawing class
        View clock = findViewById(R.id.clock);
        Context c;
        private Paint p; //initalize paint
        // Constructors
        public Clock(Context context) {
            super(context);
            c = context;
            init();

        }

        // alternate constructor
        public Clock(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
            c = context;

            init();
        }

        // initialize stuff
            protected void init() {
                p = new Paint();
                p.setColor(Color.GRAY);
                p.setStyle( Paint.Style.STROKE );
                p.setStrokeWidth(8);
                clock.postDelayed(timerTick,1000);

            }
        //update clock view every one second to get accurate time
        Runnable timerTick = new Runnable() {
            @Override
            public void run() {
                invalidate();
                clock.postDelayed( this, 1000 );

            }
        };
        @Override
        protected void onDraw(Canvas canvas) {
            p.setColor(Color.BLACK);
            int centerX = getWidth()/2;
            int centerY = getHeight()/2;//get the center coordinates

            //draw the clock circle
            canvas.drawCircle(centerX,centerY,450,p);//draw clock rim
            //for loop draws the increments of the clock
            for (int i= 0; i<360; i=i+6){ //since androids y is negative this is how i can get it to start at 90
                //degrees and tick clockwise
                p.setStrokeWidth(4);
                if(i%30 == 0){
                    p.setStrokeWidth(12);
                }
                float x1 = centerX + (float)(400*Math.cos(Math.toRadians(i)));
                float y1 = centerY + (float)(400*Math.sin(Math.toRadians(-i)));
                float x2 = centerX + (float)(450*Math.cos(Math.toRadians(i)));
                float y2 = centerY + (float)(450*Math.sin(Math.toRadians(-i)));
                canvas.drawLine(x1,y1,x2,y2,p);
            }
            p.setStrokeWidth(8);
            //get hour, minute and second information
            Calendar cal = Calendar.getInstance();
            int hour = cal.get(Calendar.HOUR);
            int minute = cal.get(Calendar.MINUTE);
            int second = cal.get(Calendar.SECOND);
            //draw the hour hand
            float hourX = centerX+ (float)(150*Math.cos(Math.toRadians(hour*30 - 90)));
            float hourY = centerY + (float)(150*Math.sin(Math.toRadians(hour*-30 - 90)));
            canvas.drawLine(centerX,centerY,hourX,hourY,p);
            //draw the minute hand
            float minuteX = centerX+ (float)(250*Math.cos(Math.toRadians(minute*6 - 90)));
            float minuteY = centerY + (float)(250*Math.sin(Math.toRadians(minute*-6 - 90)));
            canvas.drawLine(centerX,centerY,minuteX,minuteY,p);
            //draw the second hand
            p.setColor(Color.RED);
            p.setStrokeWidth(4);
            float secondX = centerX+ (float)(300*Math.cos(Math.toRadians(second*6 - 90)));
            float secondY = centerY + (float)(300*Math.sin(Math.toRadians(second*-6 - 90)));
            canvas.drawLine(centerX,centerY,secondX,secondY,p);

            Runnable timer = new Runnable() {
                public void run() {
                    invalidate();
                }
            };

        }

    }



