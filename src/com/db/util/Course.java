package com.db.util;

public class Course {

	/*
	 * 数据库的实体类
	 * 用来和数据库传输课程信息
	 */

	String courseName; 
	String  day;
	String  place;
	String teacher;
	int week;
	int weekNum;
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}
	public int getWeekNum() {
		return weekNum;
	}
	public void setWeekNum(int weekNum) {
		this.weekNum = weekNum;
	}
	public Course(String courseName,String day,String place,String teacher){
		this.courseName=courseName;
		this.day=day;
		this.place=place;
		this.teacher=teacher;
	}
	public Course(){
		
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String toString(){
		return  "courseName: "+courseName+
				"\nday: "+day+
				"\nplace: "+place+
				"\nteacher: "+teacher+
				"\n week:"+week+
				"\nweekNum" +weekNum+"\n\n";
		      
		
	}

}
