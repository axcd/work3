package com.mao.work3.bean;
import com.mao.work3.enum.*;
import java.io.*;

public class Note implements Serializable
{
	//假别
	private Fake fake;
	//倍数
	private Rate rate;
	//时数
	private Hour hour;

	public Note(Fake fake, Rate rate, Hour hour)
	{
		this.fake = fake;
		this.rate = rate;
		this.hour = hour;
	}


	public void setFake(Fake fake)
	{
		this.fake = fake;
	}

	public Fake getFake()
	{
		return fake;
	}

	public void setRate(Rate rate)
	{
		this.rate = rate;
	}

	public Rate getRate()
	{
		return rate;
	}

	public void setHour(Hour hour)
	{
		this.hour = hour;
	}

	public Hour getHour()
	{
		return hour;
	}
	
}
