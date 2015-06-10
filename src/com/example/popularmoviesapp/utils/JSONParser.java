package com.example.popularmoviesapp.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;


public class JSONParser {

	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";


	public JSONParser() {

	}
	public JSONObject makeHttpRequest(final String url, final String method,
			List<NameValuePair> params) {
		try{ 



			if(method.equals("POST")){

				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(url);
				httpPost.setEntity(new UrlEncodedFormEntity(params));

				HttpResponse httpResponse = httpClient.execute(httpPost);
				HttpEntity httpEntity = httpResponse.getEntity();
				is = httpEntity.getContent();

			}else if(method.equals("GET") ){


				DefaultHttpClient httpClient = new DefaultHttpClient();

				HttpGet httpGet = new HttpGet(url);

				HttpResponse httpResponse = httpClient.execute(httpGet);
				HttpEntity httpEntity = httpResponse.getEntity();
				is = httpEntity.getContent();

			}


		}
		catch(Exception e)
		{
			Log.i("JsonParser", "json"+e);
		}
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			reader.close();
			json = sb.toString();
			jObj = new JSONObject(json);

		}
		catch(Exception e)
		{
			if(json.contains("number"))
			{

				json = "{\"data\":"+json+"}";

				try {
					jObj =  new JSONObject(json);
					return jObj;
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			String str= "{\"data\" : "+json+"}";
			try {
				jObj= new JSONObject(str);
			} 
			catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return jObj;


	}
	public JSONObject getJsonObject(final String url, final String method,
			List<NameValuePair> params) {
		try{ 



			if(method.equals("POST")){

				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(url);


				HttpResponse httpResponse = httpClient.execute(httpPost);
				HttpEntity httpEntity = httpResponse.getEntity();
				is = httpEntity.getContent();

			}else if(method.equals("GET") ){
				// request method is GET

				DefaultHttpClient httpClient = new DefaultHttpClient();

				HttpGet httpGet = new HttpGet(url);

				HttpResponse httpResponse = httpClient.execute(httpGet);
				HttpEntity httpEntity = httpResponse.getEntity();
				is = httpEntity.getContent();

			}


		}
		catch(Exception e)
		{
			Log.i("JsonParser", "json"+e);
		}
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			reader.close();
			json = sb.toString();
			jObj = new JSONObject(json);

		}
		catch(Exception e)
		{
			Log.i("JsonParser", "json"+e);
		}
		return jObj;


	}
	public JSONObject urlStatus(final String url, final String method,
			List<NameValuePair> params) 
	{
		
		String status_str = null;
		JSONObject jobj = null;
		try{ 


		
			if(method.equals("POST")){

				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(url);
				httpPost.setEntity(new UrlEncodedFormEntity(params));

				HttpResponse httpResponse = httpClient.execute(httpPost);
				HttpEntity httpEntity = httpResponse.getEntity();
				is = httpEntity.getContent();

			}else if(method.equals("GET") )
			{
				DefaultHttpClient httpClient = new DefaultHttpClient();

				HttpGet httpGet = new HttpGet(url);

				HttpResponse httpResponse = httpClient.execute(httpGet);
				String str = httpResponse.getStatusLine().toString();
				if(str.contains("200"))
				{
					status_str = "{ value : true}";
					jobj = new JSONObject(status_str);
					
				}
				else
				{
					jobj = null;
					
				}
	
			}
		}
		catch(Exception e)
		{
			Log.i("JsonParser", "json"+e);
		}
		return jobj;
	}

}