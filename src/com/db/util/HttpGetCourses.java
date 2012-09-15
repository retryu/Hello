package com.db.util;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class HttpGetCourses {
	private String returnText;
  
	 
	public static String  PostCourse(String username,String password) throws Exception{
		 Course course;
    DefaultHttpClient  client=new DefaultHttpClient();
    HttpPost httpPost=new HttpPost("http://shibanyu2.eicp.net:8080/courses/hello");
  //  HttpPost httpPost=new  HttpPost("http://do.jhost.cn/shibanyu/hello");
    List<NameValuePair> list=new ArrayList<NameValuePair>();
    list.add(new BasicNameValuePair("username", username));
    list.add(new BasicNameValuePair("userpass",password));
    Log.e("http", username);
    httpPost.setEntity(new  UrlEncodedFormEntity(list, HTTP.UTF_8));
    HttpResponse response	=client.execute(httpPost);
      
    
   //获得数据
    String  data=EntityUtils.toString(response.getEntity());
    //System.out.println(data);
   
     //Json对象输出
//   JSONArray  ja=JSONArray.fromObject(data);
//    for(int i=0;i<10;i++){
//   course=(Course)JSONObject.toBean(ja.getJSONObject(i),Course.class);
//  Log.e(" data","Course Name"+ course.courseName+"  Place: "+course.getPlace());
//  }
	 
      return data;
    
    
    
    
    
    
    
    
    
    
    
	
	}
	

}
