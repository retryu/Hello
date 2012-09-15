package com.activity;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.activity.Myadapter.ViewHoler;
import com.db.util.HttpGetCourses;
import com.db.util.dbUtil;

public class MainActivity extends Activity {

	private Button mondayBtn;
	private Button tuesdayBtn;
	private Button thursdayBtn;
	private Button firdaayBtn;
	private Button wedayBtn;
	private String data;
	private ListView list;
   String w="1";
	
	Cursor mycursor;
	Cursor CourseCur;
	dbUtil db;
	Myadapter myadapter;
	ViewHoler myHolder;
	int test = 9;
	private Button log_btn;
	boolean table_is_exit = false;

	protected void onCreate(Bundle savedInstanceState) {

		// private Course mycursor;
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.course);
		initBtn();
//		log_btn = (Button) findViewById(R.id.log_btn);

		list = (ListView) findViewById(R.id.myListView);

		db = new dbUtil(this);

		table_is_exit = db.tableIsExist("qwe",null);
		Log.e("test2", "" + table_is_exit);
		if (table_is_exit) {// �ж��Ƿ����table
			Log.e("table", ": qwe  is exist");
			mycursor = db.selectWeek(new String("_id,coursename,place,day,teacher,week,weekNum,tt"),new String[] { "1" });
			mycursor.moveToFirst();
//			Log.e("Lsit Value",
//					"_id:" + mycursor.getString(0) + "  _coursename"
//							+ mycursor.getString(1) + "  place: "
//							+ mycursor.getString(2) + " day:"
//							+ mycursor.getString(3) + " teacher:"
//							+ mycursor.getString(4) + "  week:"
//							+ mycursor.getInt(5) + "  weekNum:"
//							+ mycursor.getString(6) + "  tt"
//							+ mycursor.getString(7));
		} else {
			Log.e("table  is no  exist", "");

		}
		 

		myadapter = new Myadapter(this, MainActivity.this);
		list.setAdapter(myadapter);
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int arg2,
					long arg3) {
				myHolder = (ViewHoler) view.getTag();
//				Log.e("myHolder coursename", "is :" + myHolder.coursename.getText()==null?"null":" not null"+"tt: is "+myHolder.coursename.getText());
				CourseAddDialog addDialog = new CourseAddDialog(MainActivity.this,
						R.style.MyDialog, MainActivity.this);
				addDialog.setTitle(null); 
				addDialog.setDay(w);
				addDialog.show();  
				addDialog.setEdi_courseName(myHolder.coursename.getText().toString());
				addDialog.setEdi_place(myHolder.place.getText().toString());
				addDialog.setEdi_teacher(myHolder.teacher);
				addDialog.setEdi_week(myHolder.week.getText().toString());
				addDialog.setEdi_weekNum(myHolder.classNum.getText().toString());
				addDialog.setEdit_tt(myHolder.tt);
				addDialog.setId(myHolder._id);
			}
		});

//		log_btn.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				Dialog dialog = new MyDialog(MainActivity.this, R.style.MyDialog,
//						MainActivity.this);
//				dialog.show();
//
//			}
//		});

	}
@Override
public boolean onCreateOptionsMenu(Menu menu) {
	 menu.add(Menu.NONE, Menu.FIRST + 1, 5, "��ϵ����");

	return super.onCreateOptionsMenu(menu);
}
@Override
public boolean onOptionsItemSelected(MenuItem item) {
    switch(item.getItemId()){
    case Menu.FIRST + 1:dialog();
    
    }
	
	return super.onOptionsItemSelected(item);
}
 public void dialog(){
	 AlertDialog.Builder builder=new Builder(MainActivity.this);
	 builder.setMessage("\n����ϣ�������������Ҫ�Ľ��ĵط�\n���䣺ruanchenyugood@gmail.com\n΢����hdxiaoyu");
	 builder.setPositiveButton("����",new  DialogInterface.OnClickListener() {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
		dialog.dismiss();
			
		}
	});
	 builder.create().show();
 }

	public void initBtn() {
		btnListen ItemClickListener = new btnListen();
		mondayBtn = (Button) findViewById(R.id.monday_btn);
		tuesdayBtn = (Button) findViewById(R.id.tuesday_btn);
		wedayBtn = (Button) findViewById(R.id.wedday_btn);
		thursdayBtn = (Button) findViewById(R.id.thursday_btn);
		firdaayBtn = (Button) findViewById(R.id.firday_btn);

		mondayBtn.setOnClickListener(ItemClickListener);
		tuesdayBtn.setOnClickListener(ItemClickListener);
		wedayBtn.setOnClickListener(ItemClickListener);
		thursdayBtn.setOnClickListener(ItemClickListener);
		firdaayBtn.setOnClickListener(ItemClickListener);
	}

	public boolean courseToDB(String username, String password) {

		Log.e("courseToDb", "runing....");
		HttpGetCourses h = new HttpGetCourses();
		try {
			data = h.PostCourse(username, password);
			Log.e("http!@@@@", data);
			if (data.equals("")) {

				return false;
			}
			JSONArray ja = new JSONArray(data);
			JSONObject jo;
			for (int i = 0; i < ja.length(); i++) {
  
				jo = (JSONObject) ja.get(i);

				db.insert(jo.getString("courseName"), jo.getString("day"),
						jo.getString("place"), jo.getString("teacher"),
						jo.getString("week"), jo.getInt("weekNum"),jo.getInt("tt"));
				Log.e("has insert into",
						jo.get("place") + jo.getString("courseName") + "\n");
			}
		} catch (Exception e) {
			Log.e("http", "exception");
			e.printStackTrace();
			return false;
		}
		return true;

	}

	private class btnListen implements OnClickListener {
		@Override
		public void onClick(View view) {
			resetBtn();
			if (view == mondayBtn) {
				view.setBackgroundResource(R.drawable.btntst_select);
                  mondayBtn.setText("��һ          ");
                  w="1";
				mycursor = db.selectWeek(new String("_id,coursename,place,day,teacher,week,weekNum,tt"),new String[] { "1" });
				myadapter.notifyDataSetChanged();
				Log.e("btn", "asd");
			}
			if (view == tuesdayBtn) {
				 
				tuesdayBtn.setText("�ܶ�          ");
				w="2";
				mycursor = db.selectWeek(new String("_id,coursename,place,day,teacher,week,weekNum,tt"),new String[] { "2" });
				myadapter.notifyDataSetChanged();
				view.setBackgroundResource(R.drawable.btntst_select);

			}
			if (view == wedayBtn) {
			   w="3";
				wedayBtn.setText("����           ");
				mycursor = db.selectWeek(new String("_id,coursename,place,day,teacher,week,weekNum,tt"),new String[] { "3" });
				myadapter.notifyDataSetChanged();
				view.setBackgroundResource(R.drawable.btntst_select);
			}
			if (view == thursdayBtn) {
				w="4";
				mycursor = db.selectWeek(new String("_id,coursename,place,day,teacher,week,weekNum,tt"),new String[] { "4" });
				thursdayBtn.setText("����          ");
				view.setBackgroundResource(R.drawable.btntst_select);
				myadapter.notifyDataSetChanged();
				// db.deletetable();
			}
			if (view == firdaayBtn) {
				w="5";
				view.setBackgroundResource(R.drawable.btntst_select);
				firdaayBtn.setText("����          ");
				mycursor = db.selectWeek(new String("_id,coursename,place,day,teacher,week,weekNum,tt"),new String[] { "5" });
				myadapter.notifyDataSetChanged();

			}

		}
	}

	public void resetBtn() {
		mondayBtn.setText("һ               ");
		tuesdayBtn.setText("��               ");
		wedayBtn.setText("��               ");
		thursdayBtn.setText("��               ");
		firdaayBtn.setText("��               ");
		mondayBtn.setBackgroundResource(R.drawable.btntest);
		tuesdayBtn.setBackgroundResource(R.drawable.btntest);
		wedayBtn.setBackgroundResource(R.drawable.btntest);
		thursdayBtn.setBackgroundResource(R.drawable.btntest);
		firdaayBtn.setBackgroundResource(R.drawable.btntest);

	}

}
