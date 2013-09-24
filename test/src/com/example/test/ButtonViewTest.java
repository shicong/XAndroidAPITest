package com.example.test;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

public class ButtonViewTest extends Button
{

    public ButtonViewTest(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public ButtonViewTest(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public ButtonViewTest(Context context)
    {
        super(context);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        // TODO Auto-generated method stub
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                Log.e("shicong", "ButtonViewTest ACTION_DOWN" + event.getX() + "/" + event.getY());
                break;
            case MotionEvent.ACTION_UP:
                Log.e("shicong", "ButtonViewTest ACTION_UP" + event.getX() + "/" + event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
//                Log.e("shicong", "ButtonViewTest ACTION_MOVE");
                break;
            default:
                break;
        }
        
        return super.onTouchEvent(event);
    }
}
