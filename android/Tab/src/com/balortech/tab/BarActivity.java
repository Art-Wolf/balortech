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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.os.Build;

public class BarActivity extends Activity {

	public final static String URL = "http://still-peak-1428.herokuapp.com/api/v1/bar/list/";

	TextView response;
	
	private ArrayList<BarList> barList = new ArrayList<BarList>();
	private BarListAdapter blAdapter;
	private ExpandableListView ExpandList;
	private BarListAdapter BarAdapter;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bar);

		response = (TextView) findViewById(R.id.tvResponse);

		if (!isConnected()) {
			response.setText("You are not connected.");
		}
		else {
			new HttpAsyncTask().execute(URL);
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
					BarList bar = new BarList();
					bar.setName((String) jsonBar.get("name"));
					
					BarOptions option1 = new BarOptions();
					option1.setName("Menu");
					
					BarOptions option2 = new BarOptions();
					option2.setName("Location");
					
					BarOptions option3 = new BarOptions();
					option3.setName("History");
					
					ArrayList<BarOptions> barOptList = new ArrayList<BarOptions>();
					
					barOptList.add(option1);
					barOptList.add(option2);
					barOptList.add(option3);
					
					bar.setMenuItems(barOptList);
					
					barList.add(bar);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	ExpandList = (ExpandableListView) findViewById(R.id.lvExp);
        	BarAdapter = new BarListAdapter(BarActivity.this, barList);
        	ExpandList.setAdapter(BarAdapter);
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
