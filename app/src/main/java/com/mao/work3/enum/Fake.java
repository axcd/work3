package com.mao.work3.enum;

import java.io.*;

public enum Fake implements Serializable
{
	//正常出勤,事假,病假,带薪假
	NORMAL("加班"),TAKEOFF("调休"),PAID("年假"),LEAVE("事假"),SICK("病假"), BEREAVEMENT("丧假"), CAREGIVER("护理假"), CHILDCARE("育儿假"), CHILDBIRTH("产假"), PATERNITY("陪产假") ;
	
	private String fake;
	private Fake(String fake){
		this.fake = fake;
	}
	
	public static Fake getByString(String fakeName) {
		for (Fake fake : Fake.values()) {
			if (fake.getFakeName().equals(fakeName)) {
				return fake;
			}
		}
		return null;
	}

	public String getFakeName(){
		return this.fake;
	}
	
	public static int size(){
		return Fake.values().length;
	}
	
	public static String getString(int i){
		return Fake.values()[i].getFakeName();
	}
	
	public static int indexOf(Fake fake) {
		Fake[] fakes = Fake.values();
		for (int i=0;i<fakes.length;i++) {
			Fake tmp = fakes[i];
			if (tmp.equals(fake)) {
				return i;
			}
		}
		return -1;
	}
}
