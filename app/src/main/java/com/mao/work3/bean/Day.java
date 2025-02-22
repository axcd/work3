package com.mao.work3.bean;

import com.mao.work3.enum.*;
import java.io.*;
import java.util.*;

public class Day implements Serializable
{
	//日期 2020/09/09
	private String index;
	//班别
	private Shift shift;
	//假条
	private List<Note> notes;

	public Day(String index, Shift shift)
	{
		this.index = index;
		this.shift = shift;
		this.notes = new Vector<Note>();
	}

	public void update( Shift shift, Fake fake,Rate rate, Hour hour)
	{
		this.shift = shift;
		Note note = new Note(fake, rate, hour);
		this.updateNote(note);
	}
	
	public Note findNote()
	{
		Note note = null;
		
		if(this.notes.size()>0)
		{
			note = this.notes.get(this.notes.size()-1);
		}
		/*
		if(this.notes.size()>1)
		{
			for(Note n : this.notes)
			{
				if(n.getFake().equals(Fake.NORMAL))
				{
					note = n;
				}
			}
		}
		*/
		return note;
	}
	
	public void updateNote(Note note)
	{
		Note fnote = null;
		
		for(Note tmp : this.notes)
		{
			if(tmp.getFake().equals(Fake.NORMAL) || tmp.getFake().equals(Fake.TAKEOFF)){

				if(tmp.getFake().equals(note.getFake()) &&  tmp.getRate().equals(note.getRate()))
				{
					fnote = tmp;
					break;
				}
			}else if(tmp.getFake().equals(note.getFake())){
				fnote = tmp;
			}
		}
		
		this.notes.remove(fnote);
		this.notes.add(note);
		
	}
	
	/*
	public void add(Note note)
	{
		this.notes.remove(note);
		this.notes.add(note);
	}
	
	public void remove(Note note)
	{
		this.notes.remove(note);
	}
	*/
	
	public void setNotes(List<Note> notes)
	{
		this.notes = notes;
	}

	public List<Note> getNotes()
	{
		return notes;
	}

	public void setIndex(String index)
	{
		this.index = index;
	}

	public String getIndex()
	{
		return index;
	}

	public void setShift(Shift shift)
	{
		this.shift = shift;
	}

	public Shift getShift()
	{
		return shift;
	}

}
