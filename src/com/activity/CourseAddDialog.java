package com.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CourseAddDialog extends Dialog {
	private MainActivity t;

	/*
	 * ����
	 */
	EditText edi_weekNum, 
	/*
	 * �γ�����
	 */
	edi_courseName,
	/*
	 * �Ͽ���ʦ����
	 */
	edi_teacher, 
	/*
	 * �Ͽεص� 
	 */
	edi_place, 
	/*
	 * �γ��ܹ��ϵĽ���
	 */
	edit_total,
	
	/*
	 * �ڼ��ڿ�
	 */
	edi_classNum;

	private String t_courseName = "", t_teacher = "", t_place = "", tt = "",day = "", t_wekNum;
	int totalNum, cn, t_classNum;
	int _id;
	private Button btn_add, btn_delete, btn_update;

	public CourseAddDialog(Context context) {
		super(context);
	}

	public CourseAddDialog(Context context, int theme, MainActivity t) {
		super(context, theme);
		Log.i("courseaddDialog", " ");

		this.t = t;
	}

	// ��ʼ���ؼ�
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.course_add);
		Log.i("oncreate", " ");
		edi_courseName = (EditText) findViewById(R.id.c_name);
		edi_place = (EditText) findViewById(R.id.c_place);
		edi_teacher = (EditText) findViewById(R.id.c_teacher);
		edi_weekNum = (EditText) findViewById(R.id.c_weekNum);
		edi_weekNum.setText(day);
		edi_classNum = (EditText) findViewById(R.id.c_classNum);
		edit_total = (EditText) findViewById(R.id.c_total);
		btn_add = (Button) findViewById(R.id.add_button);
		btn_delete = (Button) findViewById(R.id.delete_button);
		btn_update = (Button) findViewById(R.id.update_button);
	}

	// ������� ���� ɾ���γ̵���Ӧʱ��
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		btn_add.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (checkValue() == false) {
					return;
				}
				cn = Integer.valueOf(t_classNum);
				t.db.insert(t_courseName, "", t_place, t_teacher, t_wekNum,
						t_classNum, totalNum);

				if (!edi_courseName.getText().toString().equals("")) {
					Log.i("begin to insert", " table");
					t.mycursor = t.db
							.selectWeek(
									new String(
											"_id,coursename,place,day,teacher,week,weekNum,tt"),
									new String[] { day });
					t.myadapter.notifyDataSetChanged();
				} else {
					Log.e("������", "�γ�����");
				}
				dismiss();
			}
		});
		btn_delete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				t.db.delete(_id);
				t.mycursor = t.db.selectWeek(new String(
						"_id,coursename,place,day,teacher,week,weekNum,tt"),
						new String[] { edi_classNum.getText().toString() });
				t.myadapter.notifyDataSetChanged();
				dismiss();
			}
		});

		btn_update.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (checkValue() == false) {
					return;
				}
				t.db.update(_id, t_courseName, "", t_place, t_teacher,
						t_wekNum, t_classNum, totalNum);
				t.mycursor = t.db.selectWeek(new String(
						"_id,coursename,place,day,teacher,week,weekNum,tt"),
						new String[] { edi_classNum.getText().toString() });
				t.myadapter.notifyDataSetChanged();
				dismiss();
			}
		});

	}

	// �γ������У���ж�
	public boolean checkValue() {
		String s_weekNum = edi_weekNum.getText().toString();
		if (s_weekNum.trim().equals("")) {
			Toast.makeText(getContext(), "�����������ܼ���,��", Toast.LENGTH_LONG)
					.show();
			return false;
		} else {
			t_wekNum = s_weekNum.trim();
		}

		if (edi_classNum.getText().toString().trim().equals("")) {
			Toast.makeText(getContext(), "�����������ſγ��ǵڼ�����,��", Toast.LENGTH_LONG)
					.show();
			Log.e("className ", "is null");
			return false;
		} else {
			t_classNum = Integer.valueOf(edi_classNum.getText().toString()
					.trim().toString());
		}

		if (edi_place.getText().toString().trim().equals("")) {
			Toast.makeText(getContext(), "�����������Ͽε�ַ��,��", Toast.LENGTH_LONG)
					.show();
			return false;
		} else {
			t_place = edi_place.getText().toString().trim();
		}

		if (edi_teacher.getText().toString().trim().equals("")) {
			Toast.makeText(getContext(), "������������ʦ������,��", Toast.LENGTH_LONG)
					.show();
			return false;
		} else {
			t_teacher = edi_teacher.getText().toString().trim();
		}

		if (edi_courseName.getText().toString().trim().equals("")) {
			Toast.makeText(getContext(), "���������˿γ�������,��", Toast.LENGTH_LONG)
					.show();
			return false;
		} else {
			t_courseName = edi_courseName.getText().toString();
		}

		String s_totalNum = edit_total.getText().toString().trim();
		if (s_totalNum.trim().toString().equals("")) {
			Toast.makeText(getContext(), "���������˿γ̵Ľ�����,��", Toast.LENGTH_LONG)
					.show();
			return false;
		} else {
			totalNum = new Integer(s_totalNum).intValue();
		}
		Log.e("totalNum", "" + totalNum);
		return true;
	}

	public void setEdi_weekNum(String t) {
		Log.e("weekNum", t);
		edi_classNum.setText(t);
	}

	public void setEdi_courseName(String t) {
		Log.e("courseName", t);
		edi_courseName.setText(t);
	}

	public void setEdi_teacher(String t) {
		Log.e("teache", t);
		edi_teacher.setText(t);
	}

	public void setEdi_place(String t) {
		Log.e("place", t);
		edi_place.setText(t);
	}

	public void setEdit_tt(int tt) {
		Log.e("total", "" + tt);
		totalNum = tt;
		edit_total.setText(String.valueOf(tt));
	}

	public void setEdi_week(String t) {
		edi_weekNum.setText(t);
	}

	public void setId(int _id) {
		this._id = _id;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;

		Log.e("day", day);
	}

}
