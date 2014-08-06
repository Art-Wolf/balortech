package com.balortech.tab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class BarMenuActivity extends Activity {

	public final static String URL = "http://still-peak-1428.herokuapp.com/api/v1/menu/";
	public final static String BAR_NAME = "com.balortech.tab.BAR_NAME";
	
	TextView response;
	
	private ArrayList<BarMenuItemList> barMenuItemList = new ArrayList<BarMenuItemList>();
	private ExpandableListView ExpandList;
	private BarMenuItemListAdapter barMenuItemListAdapter;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_barmenu);

		Intent intent = getIntent();
		String barName = intent.getStringExtra(BarActivity.BAR_NAME);
		
		response = (TextView) findViewById(R.id.tvResponse);

		if (!isConnected()) {
			response.setText("You are not connected.");
		}
		else {
			Log.d("BarMenuActivity.onCreate", "URL being used: " + URL + "/" + barName);
			new HttpAsyncTask().execute(URL +  barName.replace(" ", "%20"));
		}

		
		// Show the Up button in the action bar.
		setupActionBar();
	}

	public boolean isConnected() {
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

		if (networkInfo != null && networkInfo.isConnected())
			return true;
		else
			return false;
	}

	private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return GET(urls[0]);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
        	JSONParser parser=new JSONParser();
        	try {
				Object obj=parser.parse(result);
				JSONArray jsonBarList=(JSONArray)obj;
				for(int i = 0; i < jsonBarList.size(); i++) {
					JSONObject jsonBar=(JSONObject) jsonBarList.get(i);
					BarMenuItemList mtList = new BarMenuItemList();
					mtList.setName((String) jsonBar.get("item_name"));
					
					BarMenuItem item1 = new BarMenuItem();
					item1.setItemName((String) jsonBar.get("item_name"));
					item1.setItemDescription((String) jsonBar.get("item_description"));
					item1.setItemPrice((String) jsonBar.get("item_price"));
					
					ArrayList<BarMenuItem> itemList = new ArrayList<BarMenuItem>();
					
					itemList.add(item1);
					
					mtList.setMenuItems(itemList);
					
					barMenuItemList.add(mtList);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	ExpandList = (ExpandableListView) findViewById(R.id.lvExp);
        	barMenuItemListAdapter = new BarMenuItemListAdapter(BarMenuActivity.this, barMenuItemList);
        	ExpandList.setAdapter(barMenuItemListAdapter);
        	
        	ExpandList.setOnChildClickListener(new OnChildClickListener() {
				@Override
				public boolean onChildClick(ExpandableListView arg0, View arg1,
						int arg2, int arg3, long arg4) {
					Intent intent = null;
					
					switch(arg3)  {
						case 0: // Menu
							intent = new Intent(BarMenuActivity.this, MainActivity.class);
							break;
						case 1:
							intent = new Intent(BarMenuActivity.this, MainActivity.class);
							break;
						case 2:
							intent = new Intent(BarMenuActivity.this, MainActivity.class);
							break;
					}
					
					if (intent != null) {
						intent.putExtra(BAR_NAME, barMenuItemList.get(arg2).getName().toString());
				    	startActivity(intent);
					}
					
					return false;
				}
        	});
       }
    }
	
	public static String GET(String url){
		InputStream inputStream = null;
		String result = "";
		try {

			// create HttpClient
			HttpClient httpclient = new DefaultHttpClient();

			// make GET request to the given URL
			HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

			// receive response as inputStream
			inputStream = httpResponse.getEntity().getContent();

			// convert inputstream to string
			if(inputStream != null)
				result = convertInputStreamToString(inputStream);
			else
				result = "Did not work!";

		} catch (Exception e) {
			
		}

		return result;
	}
	
	private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }
	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bar, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
