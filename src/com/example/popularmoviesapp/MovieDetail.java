package com.example.popularmoviesapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.popularmoviesapp.utils.GlobalValues;
import com.squareup.picasso.Picasso;

public class MovieDetail extends ActionBarActivity {

	TextView textView_alignment, textView_title, textView_rating, textView_release, textView_plot;
	ImageView imageView_cover;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movie_detail);
		textView_alignment = (TextView)findViewById(R.id.textView_alignment);
		textView_title = (TextView)findViewById(R.id.textView_title);
		textView_rating = (TextView)findViewById(R.id.textView_rating);
		textView_release = (TextView)findViewById(R.id.textView_release);
		imageView_cover = (ImageView)findViewById(R.id.imageView_cover);
		textView_plot = (TextView)findViewById(R.id.textView_plot);

		textView_plot.setText(GlobalValues.movieObject.plot.toString());
		textView_rating.setText(GlobalValues.movieObject.rating.toString());
		textView_title.setText(GlobalValues.movieObject.title.toString());
		textView_release.setText(GlobalValues.movieObject.releaseDate.toString());
		Picasso.with(this).load(GlobalValues.URL_IMAGES+(GlobalValues.movieObject.url)).into(imageView_cover);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.movie_detail, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
