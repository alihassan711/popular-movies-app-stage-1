package com.example.popularmoviesapp;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

import com.example.popularmoviesapp.adapter.GridViewAdapter;
import com.example.popularmoviesapp.utils.GlobalValues;
import com.example.popularmoviesapp.utils.JSONParser;
import com.example.popularmoviesapp.utils.MovieObject;


public class MainActivity extends ActionBarActivity {

	GridViewAdapter adapter;
	GridView gridView;
	TextView textView_notfound;
	ArrayList<MovieObject> list;
	
	String sortBy = "popularity.desc";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        gridView = (GridView) findViewById(R.id.gridView_movies);
        textView_notfound = (TextView) findViewById(R.id.textView_notfound);
        list = new ArrayList<MovieObject>();
        adapter = new GridViewAdapter(this, list);
        gridView.setAdapter(adapter);
        
        gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				GlobalValues.movieObject = list.get(arg2);
				Intent intent = new Intent(MainActivity.this, MovieDetail.class);
				startActivity(intent);
			}
		});
        
        loadItems(sortBy);
        
    }


    private void loadItems(String sort_by) {
		// TODO Auto-generated method stub
		new AsyncTask<String, Void, JSONObject>(){

			ProgressDialog dialog;
			
			@Override
			protected void onPreExecute() {
				dialog = new ProgressDialog(MainActivity.this);
				dialog.setMessage("Loading Movies...");
				dialog.show();
			}
			
			@Override
			protected JSONObject doInBackground(String... arg0) {
				// TODO Auto-generated method stub
				JSONObject jsonObject = new JSONParser().makeHttpRequest(GlobalValues.URL_GETLIST+arg0[0], "GET", null);
				return jsonObject;
			}
			
			@Override
			protected void onPostExecute(JSONObject result) {
				dialog.dismiss();
				if(result!=null)
				{
					JSONArray resultArray;
					try {
						resultArray = result.getJSONArray("results");
						for(int i=0;i<resultArray.length();i++)
						{
							JSONObject temp = resultArray.getJSONObject(i);
							MovieObject mObj = new MovieObject();
							mObj.title = temp.getString("original_title");
							mObj.releaseDate = temp.getString("release_date");
							mObj.plot = temp.getString("overview");
							mObj.url = temp.getString("poster_path");
							mObj.rating = temp.getString("vote_average");
							list.add(mObj);
						}
						if(resultArray.length()==0)
						{
							textView_notfound.setVisibility(View.VISIBLE);
						}
						adapter.notifyDataSetChanged();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}

		}.execute(sort_by);
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
		textView_notfound.setVisibility(View.GONE);
        switch (id) {
		case R.id.menu_popular:
			list.clear();
			loadItems("popularity.asc");
			return true;
		case R.id.menu_populardsc:
			list.clear();
			loadItems("popularity.desc");
			return true;
		case R.id.menu_rating:
			list.clear();
			loadItems("vote_average.asc");
			return true;
		case R.id.menu_ratingdsc:
			list.clear();
			loadItems("vote_average.desc");
			return true;
		default:
			break;
		}
        
        return super.onOptionsItemSelected(item);
    }
}
