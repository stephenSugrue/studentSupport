package khs.studentsupport;

import java.util.ArrayList;
import java.util.Calendar;

import  khs.studentsupport.R;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CalendarAdapter extends BaseAdapter
{
	static final int FIRST_DAY_OF_WEEK = 0;
	private Context mContext;
	
	private java.util.Calendar month;
	private Calendar selectedDate;
	private ArrayList<String>items;
	
	public CalendarAdapter(Context c, Calendar monthCalendar)
	{
		month = monthCalendar;
		selectedDate = (Calendar)monthCalendar.clone();
		mContext = c;
		month.set(Calendar.DAY_OF_MONTH, 1);
		this.items = new ArrayList<String>();
		refreshDays();
		
		
	}
	public void setItems(ArrayList<String> items)
	{
		for(int i = 0 ; i !=items.size();i++)
		{
			items.set(i, "0" + items.get(i));
			
		}
		this.items = items;
	}
	

	
	public int getCount() 
	{
		
		return days.length;
	}


	public Object getItem(int position) 
	{
		return null;
	}


	public long getItemId(int position) 
	{
		
		return 0;
	}

	
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View v = convertView;
		TextView dayView;
		
		if(convertView == null)
		{
			LayoutInflater vi = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.calendar_item,null);
		}
		dayView = (TextView)v.findViewById(R.id.date);
		
		if(days[position].equals(""))
		{
			dayView.setClickable(true);
			dayView.setFocusable(true);
		}
		else
		{
			if(month.get(Calendar.YEAR)==selectedDate.get(Calendar.YEAR)&& month.get(Calendar.MONTH)==selectedDate.get(Calendar.MONTH)&&
					days[position].equals(""+selectedDate.get(Calendar.DAY_OF_MONTH)))
			{
				v.setBackgroundResource(R.drawable.item_background_focused);
			}
			else
			{
				v.setBackgroundResource(R.drawable.list_item_background);
			}
			
		}
		dayView.setText(days[position]);
		
		String date = days[position];
		
		if(date.length()==1)
		{
			date = "0"+ date;
		}
		String monthStr = "" + (month.get(Calendar.MONTH)+ 1);
		if(monthStr.length()==1)
		{
			monthStr = "0" + monthStr;
		}
		
		ImageView iw = (ImageView)v.findViewById(R.id.date_icon);
		if(date.length()>0 && items!=null && items.contains(date))
		{
			iw.setVisibility(View.VISIBLE);
		}
		else
		{
			iw.setVisibility(View.INVISIBLE);
		}
		return v;
	}
	
	public void refreshDays()
	{
		items.clear();
		
		int lastDay = month.getActualMaximum(Calendar.DAY_OF_MONTH);
		int firstDay= (int) month.get(Calendar.DAY_OF_WEEK);
		
		if(firstDay==1)
		{
			days = new String[lastDay + (FIRST_DAY_OF_WEEK * 6)];
			
		}
		else
		{
			days = new String[lastDay + firstDay - (FIRST_DAY_OF_WEEK + 1)];
		}
		
		int j= FIRST_DAY_OF_WEEK;
		
		if(firstDay>1)
		{
			for(j=0; j<firstDay-FIRST_DAY_OF_WEEK; j++)
			{
				days[j] = "";
			}
					
		}
		else
		{
			for(j=0; j<FIRST_DAY_OF_WEEK*6; j++)
			{
				days[j]="";
			}
			j = FIRST_DAY_OF_WEEK * 6 + 1;
		}
		int dayNumber = 1;
		for(int i =j-1; i<days.length; i++)
		{
			days[i] = ""+ dayNumber;
			dayNumber++;
		}
	}
	
	public String [] days;
}
