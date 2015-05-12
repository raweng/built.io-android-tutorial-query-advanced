package com.builtio.advancebuiltquery;

import com.builtio.advancequerydemo.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * This is built.io android tutorial.
 * 
 * Short introduction of some classes with some methods.
 * Contain classes: 
 * 1. BuiltQuery
 * 
 * For quick start with built.io refer "http://docs.built.io/quickstart/index.html#android"
 * 
 * @author raw engineering, Inc
 *
 */
public class AdvanceQueryList extends Activity {

	private ListView conditionsListView;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(getResources().getString(R.string.conditions_title));
		setContentView(R.layout.activity_query_list_layout);
		this.getActionBar().setHomeButtonEnabled(true);
		
		conditionsListView =(ListView) findViewById(R.id.array_list_of_conditions);
		
		AdvanceQueryAdapter arrayAdapter = new AdvanceQueryAdapter(this,getResources().getStringArray(R.array.array_list_of_conditions), getResources().getStringArray(R.array.array_list_of_description));
		conditionsListView.setAdapter(arrayAdapter);

		conditionsListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				
				/**
				 * Starting activity which load BuiltObjects using selected query.
				 */
				startActivity( new Intent(AdvanceQueryList.this,AdvanceQueryResultListActivity.class).putExtra("itemClick", position));
			}
		});
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

class AdvanceQueryAdapter extends ArrayAdapter<String>{

	Context context;
	String[] conditionArray;
	String[] descriptionArray;
	private LayoutInflater inflater;
	
	public AdvanceQueryAdapter(Context context, String[] conditionArray, String[] descriptionArray) {
		super(context, 0);
		
		this.context        = context;
		this.conditionArray = conditionArray;
		this.descriptionArray = descriptionArray;
		inflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		return conditionArray.length;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		convertView = inflater.inflate(R.layout.row_query_list_layout, parent, false);
		
		TextView conditionTextView   = (TextView)convertView.findViewById(R.id.query_list_text);
		TextView descriptionTextView = (TextView)convertView.findViewById(R.id.query_list_text2);
		conditionTextView.setText(conditionArray[position].toString());
		descriptionTextView.setText(descriptionArray[position].toString());
		
		return convertView;
	}
}
