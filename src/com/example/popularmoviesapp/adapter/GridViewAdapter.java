package com.example.popularmoviesapp.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.popularmoviesapp.R;
import com.example.popularmoviesapp.utils.GlobalValues;
import com.example.popularmoviesapp.utils.MovieObject;
import com.example.popularmoviesapp.utils.PosterViewHolder;
import com.squareup.picasso.Picasso;
public class GridViewAdapter extends BaseAdapter{
	private Context mContext;
	float height,width;
	LinearLayout layout;
	
	private final ArrayList<MovieObject> list;
	
	public GridViewAdapter(Context c,ArrayList<MovieObject> list ) {
		mContext = c;
		this.list = list;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}
	
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		PosterViewHolder viewHolder;
		
		if(convertView==null)
		{
			viewHolder = new PosterViewHolder();
			LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.single_grid_item, parent, false);
			
			viewHolder.poster = (ImageView) convertView.findViewById(R.id.imageView_grid);
			
			convertView.setTag(viewHolder);
		}
		else{
			viewHolder = (PosterViewHolder) convertView.getTag();
		}

		Picasso.with(mContext)
		.load(GlobalValues.URL_IMAGES+((MovieObject)getItem(position)).url)
		.placeholder(R.drawable.loading)
		.error(R.drawable.error)
		.into(viewHolder.poster);
		
//		DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
//		height = metrics.heightPixels;
//		width = metrics.widthPixels;
//		viewHolder.poster.getLayoutParams().height = (int) width/2;
//		viewHolder.poster.getLayoutParams().width = (int) width/2;

		return convertView;
	}
	public String getPath(Uri uri) {
		String[] projection = { MediaStore.Images.Media.DATA };
		Cursor cursor = mContext.getContentResolver().query(uri, projection, null, null, null);
		int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		String temp = null;
		cursor.moveToFirst();
		try
		{
			temp = cursor.getString(column_index);
		}
		catch(Exception e)
		{

		}
		if(temp!=null)
			return temp;
		else
			return "";
	}
}
