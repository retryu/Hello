package com.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.db.util.HttpGetCourses;
import com.widget.myEditText;

public class MyDialog extends Dialog {

	Context context;
	private myEditText username;
	private myEditText password;
	private Button log_in;
	private Button log_off;
	private MainActivity t;
	private Toast toast;

	public MyDialog(Context context) {
		super(context);
		this.context = context;

	}

	public MyDialog(Context context, int theme, MainActivity t) {
		super(context, theme);
		setTitle("请输入 杭电！ <教学管理>账号");
		this.t = t;

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.log);

		username = (myEditText) findViewById(R.id.username);
		password = (myEditText) findViewById(R.id.password);
		log_in = (Button) this.findViewById(R.id.log_in);
		log_off = (Button) this.findViewById(R.id.log_off);

	}

	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		super.dismiss();

		t.myadapter.notifyDataSetChanged();
	}

	@Override
	protected void onStart() {

		super.onStart();
		log_off.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});

		log_in.setOnClickListener(new View.OnClickListener() {

			@Override     
			public void onClick(View v) {
				Toast.makeText(t.getApplicationContext(), "\n\n稍等会儿哦，亲 ~ 正在运同步。。。。\n\n",Toast.LENGTH_LONG).show();
				String u = username.getText().toString();
				String p = password.getText().toString();
				 
				HttpGetCourses httpCourse = new HttpGetCourses();
				LayoutInflater inflater = getLayoutInflater();
				View layout = inflater.inflate(R.layout.cloud, null);
				toast = new Toast(t.getApplicationContext());
				toast.setView(layout);
				toast.setDuration(Toast.LENGTH_LONG);

				Log.e("test2", "" + t.table_is_exit + "" + t.test);
				if (t.db.tableIsExist("qwe",null)) {
					t.db.deletetable();
				}
				 
				try {
					Log.e("exception", "try");

					httpCourse.PostCourse(u, p);
					Log.e("exception", "try2");

					Log.e("exception", "try3");
					toast.show();
					if (t.courseToDB(u, p)) {
						Log.e("登陆成功", "~~");
						 
					} else {
						Toast.makeText(t.getApplicationContext(), "密码错误",
								Toast.LENGTH_LONG).show();
						return;
					}
					dismiss();
					t.myadapter.notifyDataSetChanged();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					Log.e("exception", "catch");
					// toast.show();
					Toast.makeText(t.getApplicationContext(), "同步课表失败 ，请检查网络",
							Toast.LENGTH_LONG).show();
					Log.e("exception", "" + e.toString());
					Log.e("exception", "catch2");
					return;
				}
				t.mycursor = t.db.selectWeek(new String("_id,coursename,place,day,teacher,week,weekNum,tt"),new String[] { "1" });

				t.myadapter.notifyDataSetChanged();
				t.table_is_exit = true;
				Log.e("username", u);
			}
		});

	}

}
