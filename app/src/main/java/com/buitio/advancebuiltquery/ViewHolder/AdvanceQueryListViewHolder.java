package com.buitio.advancebuiltquery.ViewHolder;

import android.widget.TextView;

import com.raweng.built.BuiltObject;

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
public class AdvanceQueryListViewHolder {


	public TextView nominationTextView;
	public android.widget.TextView descriptionTextView;

	public void populateView(BuiltObject builtObject) {

		/*
		 * Extracting the data from builtObject instance.
		 */
		if(builtObject.has("name")){
			nominationTextView.setText((String)builtObject.get("name"));
		}

		if(builtObject.has("movie_text")){ 
			if(builtObject.getAllObjects("movie", "movie") != null){
				
				for (int i = 0; i < builtObject.getAllObjects("movie", "movie").size(); i++) {
					descriptionTextView.setText("Movie Name : "+ builtObject.get("movie_text")+" || Time : "+builtObject.getAllObjects("movie", "movie").get(i).getInt("running_time"));
				}
				
			}else{
			descriptionTextView.setText("Movie Name : "+ builtObject.get("movie_text"));
			}
		}
	}

}
