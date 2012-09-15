package com.activity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

 

public class Myadapter extends BaseAdapter {
	 MainActivity t;	
	 private LayoutInflater mInflater;
	public final class ViewHoler {
		String  teacher="";
		int   tt;
		int _id=-1;
		TextView week;
		TextView classNum;
		TextView place;
		TextView coursename;
		
	}

      
	public Myadapter(Context context,MainActivity t) {
		this.mInflater = LayoutInflater.from(context);
		 this.t=t;
	}

	@Override
	public int getCount() {
            if(t.db.tableIsExist("qwe",t.w))
            {
            	Log.e("Adapter count return ", "mycursor");
            	return t.mycursor.getCount();
            }
            else 
            	{
            	Log.e("Adapter count return  retrun ", "1");
            	return 1;
            	 
            	}
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Log.e("obtain view", "~");
		ViewHoler holder = null;
		if (convertView == null) {
			holder = new ViewHoler();
		//	Log.e("new ", "view");
			convertView = mInflater.inflate(R.layout.course_list_content,
					null);
			holder.coursename = (TextView) convertView
					.findViewById(R.id.list_course_name);
			holder.place = (TextView) convertView
					.findViewById(R.id.list_course_place);
			holder.week=(TextView)convertView.findViewById(R.id.list_course_week);
			holder.classNum=(TextView)convertView.findViewById(R.id.list_course_weekNum);
				
			convertView.setTag(holder);
		} else {
			//Log.v("getTag", "~");
			holder = (ViewHoler) convertView.getTag();
		}

 

	    if(t.db.tableIsExist("qwe",t.w)){
		t.mycursor.moveToPosition(position);

		holder.coursename.setText(t.mycursor.getString(t.mycursor
				.getColumnIndex("coursename")));
		holder.place.setText(t.mycursor.getString(t.mycursor
				.getColumnIndex("place")));
  holder.week.setText(t.mycursor.getString(t.mycursor.getColumnIndex("week")));
  holder.classNum.setText(t.mycursor.getString(t.mycursor.getColumnIndex("weeknum")));
  holder.tt=t.mycursor.getInt(7);
  holder.teacher=t.mycursor.getString(t.mycursor.getColumnIndex("TEACHER"));
  holder._id=t.mycursor.getInt(t.mycursor.getColumnIndex("_id"));
	    Log.e("teacher", holder.teacher);
	    }
	    else{
	    	holder.week.setText(t.w);
	    	holder.classNum.setText(" ");
	    }
		return convertView;
	}

}