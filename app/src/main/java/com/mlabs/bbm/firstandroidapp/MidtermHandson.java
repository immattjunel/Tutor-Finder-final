package com.mlabs.bbm.firstandroidapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MidtermHandson extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.on_touch_two);

        final ImageView imageView=(ImageView)findViewById(R.id.imageView);
        final EditText x1y1=(EditText)findViewById(R.id.x1y1Txt);
        final EditText x2y2=(EditText)findViewById(R.id.x2y2Txt);
        final EditText diffxy=(EditText)findViewById(R.id.diffxyTxt);
        final EditText motion=(EditText)findViewById(R.id.MotionTxt);
        final EditText quadrant=(EditText)findViewById(R.id.QuadTxt);

        imageView.setOnTouchListener(new View.OnTouchListener(){
            float initX,initY,finalX,finalY,diffX,diffY;
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int event=motionEvent.getAction();
                String motions;
                switch (event){
                    case MotionEvent.ACTION_DOWN:
                        x1y1.setText("");
                        x2y2.setText("");
                        diffxy.setText("");
                        motion.setText("");
                        quadrant.setText("");
                        initX=motionEvent.getX();
                        initY=motionEvent.getY();
                        x1y1.setText(initX+", "+initY);
                        return true;
                    case MotionEvent.ACTION_UP:
                        int originX=imageView.getRight()/2;
                        int originY=imageView.getBottom()/2;
                        finalX=motionEvent.getX();
                        finalY=motionEvent.getY();
                        x2y2.setText(finalX+", "+finalY);

                        if(finalX>originX&&finalY>originY){
                            quadrant.setText("IV");
                        }if(finalX<originX&&finalY>originY){
                        quadrant.setText("III");
                    }if(finalX<originX&&finalY<originY){
                        quadrant.setText("II");
                    }if(finalX>originX&&finalY<originY){
                        quadrant.setText("I");
                    }
                        diffX=initX-finalX;
                        diffY=initY-finalY;

                        if (diffX==0&&diffY==0){

                            motions="";
                            quadrant.setText("");
                        }
                        else {
                            motions="";
                            if (initX < finalX) {
                                motions += "Left to Right ";
                                diffX = finalX - initX;
                            }
                            if (initX > finalX) {
                                motions += "Right to Left ";
                                diffX = initX - finalX;
                            }
                            if (initY < finalY) {
                                motions += "Up to Down ";
                                diffY = finalY - initY;
                            }
                            if (initY > finalY) {
                                motions += "Down to Up";
                                diffY = initY - finalY;
                            }


                        }
                        motion.setText(motions);
                        diffxy.setText(diffX + ", " + diffY);

                        return true;
                }
                return false;
            }




        });


    }
}
