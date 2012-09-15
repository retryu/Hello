package com.db.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/*
 * 数据库访问的工具类
 * 
 */

public class dbUtil extends SQLiteOpenHelper {
	
	

	private final static String DATABASE_NAEM = "my_db2";
	private final static int DATABASE_VERSIO = 4;
	private final static String TABLE_NAME = "qwe";
	private final static String FIELD_ID = "_id";
	public final static String COURSE_NAME = "coursename";
	public final static String PLACE = "place";
	public final static String DAY = "day";
	public final static String TEACHER = "teacher";
	public final static String WEEK = "week";
	public final static String WEEKNUM = "weeknum";
	private final  static String  tt="tt";

	public dbUtil(Context context) {
		super(context, DATABASE_NAEM, null, DATABASE_VERSIO);

		Log.e("init", "dbhelper");
	}
   
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.e("begin", "create table");
		String sql = "Create  table " + TABLE_NAME + " (" + FIELD_ID + " "
				+ " integer primary key autoincrement," + COURSE_NAME
				+ " text, " + PLACE + " text," + DAY + " text," + "TEACHER"
				+ " text," + "week" + " text," + WEEKNUM + " int,"+tt+" integer);";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.i("onUpgrade", "!!@@@");
		String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
		db.execSQL(sql);
          
		onCreate(db);
	}

	public Cursor select() {
		SQLiteDatabase db = this.getReadableDatabase();

		Log.e("DBHELPER", "READABLEDATEBASE");
		Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null,
				"_id desc");
		Log.i("DBHELPER", "query");
		return cursor;

	}

	public Cursor selectWeek(String s, String seleetion[]) {
		Log.e("selection", "" + seleetion[0]);
		SQLiteDatabase db = this.getReadableDatabase();
		final String weekNum = "1";
		final String coursename = "coursename";
		String query = "SELECT  " + s + "  FROM " + TABLE_NAME
				+ " WHERE  week=? order by weekNum";
		Cursor cursor = db.rawQuery(query, seleetion);
		while (cursor.moveToNext()) {
			Log.e("select day",
					"" + cursor.getString(cursor.getColumnIndex("day")));
			Log.e("select cn",
					"" + cursor.getString(cursor.getColumnIndex("coursename")));
			Log.e("select weekNum",
					"" + cursor.getString(cursor.getColumnIndex("weeknum")));
			Log.e("select week",
					"" + cursor.getString(cursor.getColumnIndex("week")));
		}
		return cursor;
	} 
	
	
	

	public long insert(String courseName, String day, String place,
			String teacher, String week, int  weeknum, int tt) {

		Log.i("insert ", "begin");

		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
    
		cv.put(COURSE_NAME, courseName);
		cv.put(DAY, day);
		cv.put(PLACE, place);
		cv.put(TEACHER, teacher);
		cv.put(WEEKNUM, weeknum);
		cv.put(WEEK, week);
		cv.put("tt", tt);
		long row = db.insert(TABLE_NAME, null, cv);
		Log.i("insert ", "sucess!!");
		return row;
	}
	public void  deletetable(){
		SQLiteDatabase  db=this.getReadableDatabase();
		String sql="delete from   qwe;";
		db.execSQL(sql);
		
	}

	public void delete(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		String where = FIELD_ID + "=?";
		String[] whereValue = { Integer.toString(id) };
		db.delete(TABLE_NAME, where, whereValue);
	}

	public void update(int id, String courseName, String day, String place,
			String teacher, String week, int  weeknum,int tt) {
		SQLiteDatabase db = this.getReadableDatabase();
		String where = FIELD_ID + "=?";
		String[] whereValue = { Integer.toString(id) };
		ContentValues cv = new ContentValues();

		cv.put(COURSE_NAME, courseName);
		cv.put(DAY, day);
		cv.put(PLACE, place);
		cv.put(TEACHER, teacher);
		cv.put(WEEKNUM, weeknum);
		cv.put(WEEK, week);
		cv.put("tt", tt);
		db.update(TABLE_NAME, cv, where, whereValue);
	}

	public boolean tableIsExist(String tablename,String week) {
		boolean result = false;
		int count=0;
		String sql;
		SQLiteDatabase db = null;
		Cursor cursor = null;
  
		try {
			db = this.getReadableDatabase();
            if(week==null){
            	sql="select count(*)from qwe";
            }
            else
			  sql="select count(*) from qwe where week="+week;
			cursor = db.rawQuery(sql, null);
			if (cursor.moveToFirst()) {
			 count = cursor.getInt(0);
			 if (count > 0){
					result = true;
//			    Log.e("tabel is Exits", "num="+count);
			 
			 }
//			 else{
//				 Log.e("tabel is Exits", "num="+count);
				 
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
//		Log.e("table  count(*)"," "+count);
       return  result;
	}
  

}
