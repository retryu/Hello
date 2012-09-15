package com.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.EditText;


/*
 * 自定义的TextView控件
 * 
 */

public class myEditText extends   EditText{
	
private  Paint paint;
	public myEditText(Context context,AttributeSet  attrs) {
		 super(context,attrs);
		  paint=getPaint();
		  paint.setColor(Color.RED);
		  paint.setStrokeWidth(10);
		  paint.setStyle(Paint.Style.FILL);
		 setBackgroundColor(VISIBLE);
		// setBackgroundResource(R.drawable.edit_bg_up);
		   
		
	}
	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		 
		super.draw(canvas);
		int w=getWidth();
		int h=getHeight();
//		canvas.drawLine(0, h, w, h, paint);
//		canvas.drawLine(w, 0,w, h, paint);
//		canvas.drawLine(0, 0, 0, h, paint);
//		canvas.drawLine(0, 0, w, 0, paint);
		
	}
	
	
	

}
