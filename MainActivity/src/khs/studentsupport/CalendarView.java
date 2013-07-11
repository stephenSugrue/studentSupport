package khs.studentsupport;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

public class CalendarView  extends Activity
{
	public Calendar month;
	public CalendarAdapter adapter;
	public Handler handler;
	public ArrayList<String>items;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendar);
		month = Calendar.getInstance();
		onNewIntent(getIntent());
		
		items = new ArrayList<String>();
		 adapter = new CalendarAdapter(this, month);
		
		GridView gridview = (GridView)findViewById(R.id.gridview);
		gridview.setAdapter(adapter);
		
		handler = new Handler();
		handler.post(calendarUpdater);
		
		TextView title = (TextView)findViewById(R.id.title);
		title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));
		
		TextView previous = (TextView)findViewById(R.id.previous);
		previous.setOnClickListener(new OnClickListener()
		{

		
			public void onClick(View v) 
			{
				if(month.get(Calendar.MONTH)==month.getActualMinimum(Calendar.MONTH))
				{
					month.set((month.get(Calendar.YEAR)-1), month.getActualMaximum(Calendar.MONTH),1);
				}
				else
				{
					month.set(Calendar.MONTH,month.get(Calendar.MONTH)-1);
				}
				refreshCalendar();
				
			}
			
		});
		
		TextView next = (TextView)findViewById(R.id.next);
		 next.setOnClickListener(new OnClickListener()
		{
			

	
			public void onClick(View v) 
			{
				if(month.get(Calendar.MONTH)==month.getActualMaximum(Calendar.MONTH))
				{
					month.set((month.get(Calendar.YEAR)+1), month.getActualMinimum(Calendar.MONTH),1);
				}
				else
				{
					month.set(Calendar.MONTH,month.get(Calendar.MONTH)+1);
				}
				refreshCalendar();
				
			}
			
		});
		
		 gridview.setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> parent, View V, int position, long id)
			{
				TextView date = (TextView)findViewById(R.id.date);
				if(date instanceof TextView && !date.getText().equals(""))
				{
					Intent intent = new Intent();
					String day = date.getText().toString();
					
					if(day.length()==1) 
					{
						day = "0"+day;
					}	
					intent.putExtra("date", android.text.format.DateFormat.format("yyyy-MM", month)+ "-" + day);
					setResult(RESULT_OK, intent);
					finish();
				}
			}
		});
		
	}
	
	public void refreshCalendar()
	{
		TextView title  = (TextView)findViewById(R.id.title);
		
		adapter.refreshDays();
		adapter.notifyDataSetChanged();
		handler.post(calendarUpdater);
		
		title.setText(android.text.format.DateFormat.format("MMMM,  yyyy", month));
		
				
	}
	
	public Runnable calendarUpdater = new Runnable ()
	{
		public void run()
		{
			items.clear();
			for(int i=0; i<31; i++)
			{
				Random r = new Random();
				
				if(r.nextInt(10)>6)
				{
					items.add(Integer.toString(i));
				}
			}
			
			adapter.setItems(items);
			adapter.notifyDataSetChanged();
		}
	};
	
	

}
