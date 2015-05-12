package com.builtio.advancebuiltquery;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.builtio.advancequerydemo.R;
import com.buitio.advancebuiltquery.ViewHolder.AdvanceQueryListViewHolder;
import com.raweng.built.Built;
import com.raweng.built.BuiltApplication;
import com.raweng.built.BuiltError;
import com.raweng.built.BuiltObject;
import com.raweng.built.BuiltQuery;
import com.raweng.built.userInterface.BuiltListViewResultCallBack;
import com.raweng.built.userInterface.BuiltUIListViewController;

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
public class AdvanceQueryResultListActivity extends Activity{

	private int item;
	private ProgressDialog progressDialog;

	/*
	 * Declaration of BuiltUIListViewController.
	 */
	private BuiltUIListViewController builtUIListViewController;
    private BuiltApplication builtApplication;


	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/*
		 * Initialization of BuiltUIListViewController object and BuiltApplication.
		 */
		builtUIListViewController = new BuiltUIListViewController(this, "bltb69ac12834c6339b","nominees");
        try {
            builtApplication = Built.application(this, "bltb69ac12834c6339b");
        } catch (Exception e) {
            e.printStackTrace();
        }

		/*
		 * Setting the BuiltUIListViewController layout to activity (Initialization of layout to activity).
		 */
		setContentView(builtUIListViewController.getLayout());

		setTitle(getResources().getString(R.string.query_result_title));
		this.getActionBar().setHomeButtonEnabled(true);

		item = getIntent().getExtras().getInt("itemClick");

		progressDialog = new ProgressDialog(this);
		progressDialog.setCancelable(false);
		progressDialog.setMessage(getResources().getString(R.string.pls_wait_msg));

		loadData();
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

	public void loadData(){

		switch (item) {
		case 0:

			/*
			 * Querying to loading list for all BuiltObject of class.
			 */
			loaddata(builtUIListViewController);
			break;

		case 1:

			ArrayList<String> keys = new ArrayList<>();
			keys.add("movie");
			
			/*
			 * Querying to loading list with including reference field details.
			 */
			builtUIListViewController.getBuiltQueryInstance().includeReference(keys);
			loaddata(builtUIListViewController);
			break;

		case 2:

            BuiltQuery builtQueryObject = builtApplication.classWithUid("movie").query();
			builtQueryObject.lessThan("running_time", 120);
			
			/*
			 * Querying to loading list for in condition.
			 */
			builtUIListViewController.getBuiltQueryInstance().inQuery("movie", builtQueryObject);
			loaddata(builtUIListViewController);
			break;

		case 3:

            BuiltQuery builtQueryInstance = builtApplication.classWithUid("movie").query();
			builtQueryInstance.lessThan("running_time", 120);
			
			/*
			 * Querying to loading list for not in condition.
			 */
			builtUIListViewController.getBuiltQueryInstance().notInQuery("movie", builtQueryInstance);
			loaddata(builtUIListViewController);
			break;

		case 4:

            BuiltQuery selectBuiltQueryObject = builtApplication.classWithUid("movie").query();
			selectBuiltQueryObject.lessThan("running_time", 120);

			/*
			 * Querying to loading list for select condition.
			 */
			builtUIListViewController.getBuiltQueryInstance().select("movie_text", selectBuiltQueryObject, "title");
			loaddata(builtUIListViewController);
			break;

		case 5:

            BuiltQuery notSelectBuiltQueryObject = builtApplication.classWithUid("movie").query();
			notSelectBuiltQueryObject.lessThan("running_time", 120);

			/*
			 * Querying to loading list for don't select condition.
			 */
			builtUIListViewController.getBuiltQueryInstance().dontSelect("movie_text", notSelectBuiltQueryObject, "title");
			loaddata(builtUIListViewController);
			break;

		case 6:

			ArrayList<BuiltQuery> orQueryObjects = new ArrayList<>();

			BuiltQuery builtQuery1 = builtApplication.classWithUid("nominees").query();
			builtQuery1.where("born", "United States");

			BuiltQuery builtQuery2 = builtApplication.classWithUid("nominees").query();
			builtQuery2.where("born", "United Kingdom");

			orQueryObjects.add(builtQuery1);
			orQueryObjects.add(builtQuery2);

			/*
			 * Querying to loading list for "or" condition.
			 */
			builtUIListViewController.getBuiltQueryInstance().or(orQueryObjects);
			loaddata(builtUIListViewController);
			break;

		case 7:

			ArrayList<BuiltQuery> andQueryList = new ArrayList<>();
			ArrayList<BuiltQuery> orQueryList = new ArrayList<>();
			
			BuiltQuery query1 = builtApplication.classWithUid("nominees").query();
			query1.where("born", "United States");

			BuiltQuery query2 = builtApplication.classWithUid("nominees").query();
			query2.where("born", "United Kingdom");

			orQueryList.add(query1);
			orQueryList.add(query2);
			
			BuiltQuery query3 = builtApplication.classWithUid("nominees").query();
			query3.or(orQueryList);

			BuiltQuery query4 = builtApplication.classWithUid("nominees").query();
			query4.lessThanOrEqualTo("age", 40);
			
			andQueryList.add(query3);
			andQueryList.add(query4);
			
			/*
			 * Querying to loading list for "and" condition.
			 */
			builtUIListViewController.getBuiltQueryInstance().and(andQueryList);
			loaddata(builtUIListViewController);
			break;

		default:
			break;

		}
	}

	public void loaddata(BuiltUIListViewController builtUIListViewController){
		progressDialog.show();
		
		/*
		 *  Calling load data 
		 */
		builtUIListViewController.loadData(new BuiltListViewResultCallBack() {

			private AdvanceQueryListViewHolder holder;

			@Override
			public void onError(BuiltError error) {
				/// builtErrorObject contains more details of error.
				Toast.makeText(AdvanceQueryResultListActivity.this, "Error "+error.getErrorMessage(), Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onAlways() {

				/// write code here that user want to execute.
				/// regardless of success or failure of the operation.

				if(progressDialog.isShowing()){
					progressDialog.dismiss();
				}
			}

			@Override
			public int getViewTypeCount() {
				try{
					if(progressDialog.isShowing()){
						progressDialog.dismiss();
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				return 0;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent, BuiltObject builtObject) {

				if(progressDialog.isShowing()){
					progressDialog.dismiss();
				}
				///Inflating the row layout in list view.

				if(convertView == null){
					LayoutInflater inflater = LayoutInflater.from(AdvanceQueryResultListActivity.this);
					convertView = inflater.inflate(R.layout.row_query_list_result, parent, false);
					holder = new AdvanceQueryListViewHolder();
					holder.nominationTextView = (TextView)convertView.findViewById(R.id.nameTextView);
					holder.descriptionTextView = (TextView)convertView.findViewById(R.id.priorityTextView);
					convertView.setTag(holder);
				}else{
					holder = (AdvanceQueryListViewHolder)convertView.getTag();
				}

				holder.populateView(builtObject);

				return convertView;
			}

			@Override
			public int getItemViewType(int position) {
				return 0;
			}
		});
	}

}
