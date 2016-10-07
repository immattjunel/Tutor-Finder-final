package com.mlabs.bbm.firstandroidapp;

import android.os.Bundle;
import android.support.annotation.BoolRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class OnTouchActivity extends AppCompatActivity{


        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.on_touch);

                final String tag=OnTouchActivity.class.getSimpleName();
                final ImageView imageView=(ImageView)findViewById(R.id.imageView);

                assert imageView != null;
                imageView.setOnTouchListener(new View.OnTouchListener(){
                        float initX,initY,finalX,finalY;

                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent){
                                int event = motionEvent.getAction();
                                switch (event) {
                                        case MotionEvent.ACTION_DOWN:
                                                initX = motionEvent.getX();
                                                initY = motionEvent.getY();
                                                Toast.makeText(getApplicationContext(), "value of X: " + initX + " value of Y: " + initY, Toast.LENGTH_LONG).show();
                                                return true;
                                        case MotionEvent.ACTION_UP:
                                                finalX = motionEvent.getX();
                                                finalY = motionEvent.getY();

                                                Log.d(tag, "Action was UP");

                                                if (initX < finalX) {
                                                        Log.d(tag, "Left to Right swipe performed");
                                                        Toast.makeText(getApplicationContext(), "Left to Right Swipe performed, value of X: " + finalX + " value of Y: " + finalY, Toast.LENGTH_LONG).show();

                                                }
                                                if (initX > finalX) {
                                                        Log.d(tag, "Right to Left swipe performed");
                                                        Toast.makeText(getApplicationContext(), "Right to Left Swipe performed, value of X: " + finalX + " value of Y: " + finalY, Toast.LENGTH_LONG).show();

                                                }
                                                if (initY < finalY) {
                                                        Log.d(tag, "Up to Down swipe performed");
                                                        Toast.makeText(getApplicationContext(), "Up to Down Swipe performed, value of X: " + finalX + " value of Y: " + finalY, Toast.LENGTH_LONG).show();

                                                }
                                                if (initY > finalY) {
                                                        Log.d(tag, "Down to Up swipe performed");
                                                        Toast.makeText(getApplicationContext(), "Down to Up Swipe performed, value of X: " + finalX + " value of Y: " + finalY, Toast.LENGTH_LONG).show();

                                                }
                                                return true;

                                        }

                                                return false;
                        }
                });
        }
}
