package com.mao.work3.bean;

import java.io.*;
import com.mao.work3.*;
import java.util.*;
import com.mao.work3.io.*;

public class Month implements Serializable
{
	private String index;
	private Day[] days;

	public Month(String index)
	{
		this.index = index;
		this.days = new Day[31];
		readDays();
	}

	public void setDay(int i, Day day)
	{
		if(i>0 && i<=days.length+1)
		{
			this.days[i-1] = day;
		}
	}

	public Day getDay(int i)
	{
		Day day = null;
		if(i>0 && i<=days.length+1)
		{
			day = this.days[i-1];
		}
		return day;
	}

	public void setIndex(String index)
	{
		this.index = index;
	}

	public String getIndex()
	{
		return index;
	}

	public void setDays(Day[] days)
	{
		this.days = days;
	}

	public Day[] getDays()
	{
		return days;
	}
	
	//判断本月是否为空
	public boolean isEmpty()
	{
		boolean flag = true;
		
		for(Day day : days)
		{
			if(null!=day)
			{
				flag = false;
				break;
			}
		}
		
		return flag;
	}
	
	//保存days
	public void saveDays()
	{
		ObjectIO<Day[]> io = new ObjectIO<Day[]>();
		
		if(isEmpty())
		{
			io.deleteDir(index);
		}else{
			io.writerToFile(days, index+"/days");
		}
	}
	
	//读取days
	public void readDays()
	{
		Day[] r_days = new ObjectIO<Day[]>().readFromFile(index+"/days");
		if(null!=r_days) 
			this.days = r_days;
	}

}
