package com.mao.work3.layout;

import android.widget.*;
import android.content.*;
import android.util.*;
import android.view.*;
import com.mao.work3.*;
import android.content.res.*;
import com.mao.work3.config.*;

public class FakeRadioGroup extends RadioGroup
{
	private int m;
	private int n;
	private int w;
	private int h;
	//public static int y;
	private int childCount = 8;
	private String[] fakes = {"加班", "调休", "年假", "事假", "病假", "丧假", "陪护假", "育儿假"};

	public FakeRadioGroup(Context context)
	{
		super(context);
	}

	public FakeRadioGroup(Context context, AttributeSet attrs)
	{
		super(context, attrs);

		//获取xml配置
		TypedArray t = context.obtainStyledAttributes(attrs,R.styleable.FakeRadioGroup);
		m = t.getInteger(R.styleable.FakeRadioGroup_m,15);
		n = t.getInteger(R.styleable.FakeRadioGroup_n,6);

		for(int i=0;i<childCount;i++)
		{
			RadioButton rb = null;
			if(i==0){
				rb = (RadioButton)LayoutInflater.from(context).inflate(R.layout.page_two_update_fake_radio,null,false);
				
			}else{
				rb = (RadioButton)LayoutInflater.from(context).inflate(R.layout.page_two_update_fake1_radio,null,false);
			}
			rb.setText(fakes[i]);
			this.addView(rb);
		}


		childCount = getChildCount();

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		//获取最大宽度和
		int maxWidth = MeasureSpec.getSize(widthMeasureSpec);
		w = (maxWidth-m)/n-m;
		h = w*3/5;
		int maxHeight = (h+m)*(int)Math.ceil(childCount*1.0/n)+m;

//		com.mao.work3.config.Config.setScroll(h+m);
		//y = h+m;

		for(int i=0;i<childCount;i++)
		{
			final View child = getChildAt(i);
			int wm = MeasureSpec.makeMeasureSpec(w, MeasureSpec.EXACTLY);
			int hm = MeasureSpec.makeMeasureSpec(h, MeasureSpec.EXACTLY);
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
				child.layout((i%n)*(w+m)+m, (i/n)*(h+m)+m, (i%n+1)*(w+m), (i/n+1)*(h+m));	
			}
		}
	}
}
