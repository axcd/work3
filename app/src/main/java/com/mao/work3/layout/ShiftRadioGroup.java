package com.mao.work3.layout;

import android.widget.*;
import android.content.*;
import android.util.*;
import android.view.*;
import com.mao.work3.*;
import android.content.res.*;
import com.mao.work3.config.*;
import com.mao.work3.enum.*;

public class ShiftRadioGroup extends RadioGroup
{
	private int num;
	private int width;
	private int height;
	private int top;
	private int pitch;
	private int percent;
	private int childCount = Shift.size();

	public ShiftRadioGroup(Context context)
	{
		super(context);
	}

	public ShiftRadioGroup(Context context, AttributeSet attrs)
	{
		super(context, attrs);

		//获取xml配置
		TypedArray t = context.obtainStyledAttributes(attrs,R.styleable.ShiftRadioGroup);
		width = t.getInteger(R.styleable.ShiftRadioGroup_width,15);
		percent = t.getInteger(R.styleable.ShiftRadioGroup_percent,80);
		num = t.getInteger(R.styleable.ShiftRadioGroup_num,6);
		top = t.getInteger(R.styleable.ShiftRadioGroup_top,6);
		
		for(int i=0;i<childCount;i++)
		{
			RadioButton rb = null;
			
			rb = (RadioButton)LayoutInflater.from(context).inflate(R.layout.page_two_update_shift_radio_day,null,false);
			
			if(i==1)
				rb = (RadioButton)LayoutInflater.from(context).inflate(R.layout.page_two_update_shift_radio_middle,null,false);
			if(i==2)
				rb = (RadioButton)LayoutInflater.from(context).inflate(R.layout.page_two_update_shift_radio_night,null,false);
			if(i==3)
				rb = (RadioButton)LayoutInflater.from(context).inflate(R.layout.page_two_update_shift_radio_rest,null,false);
			
			rb.setText(Shift.getString(i));
			this.addView(rb);
		}


		childCount = getChildCount();

	}


	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		//获取最大宽度和
		int maxWidth = MeasureSpec.getSize(widthMeasureSpec);

		pitch = (maxWidth-num*width)/(num+1);
		height = width*percent/100;

		int maxHeight = (childCount/num+1)*(height+top)+top; 

		if(childCount%num==0)
			maxHeight -= height+top;
			
		for(int i=0;i<childCount;i++)
		{
			final View child = getChildAt(i);
			int wm = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
			int hm = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
			//设置子类所需宽度和高度
			child.measure(wm,hm);
		}

		// 设置容器所需的宽度和高度
		setMeasuredDimension(maxWidth, maxHeight);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b)
	{
		super.onLayout(changed, l, t, r, b);

		for (int i = 0; i < childCount; i++)
		{
			View child = this.getChildAt(i);
			if (child.getVisibility() != View.GONE)
			{
				child.layout((i%num)*(width+pitch)+pitch, (i/num)*(height+top)+top, (i%num+1)*(width+pitch), (i/num+1)*(height+top));	
			}
		}
	}
}
