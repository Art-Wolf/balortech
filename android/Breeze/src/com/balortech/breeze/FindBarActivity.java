package com.balortech.breeze;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class FindBarActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_find_bar);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.find_bar, menu);
		return true;
	}
	
	public void barSearch(View view) {
		EditText barNameInput = (EditText) findViewById(R.id.bar_name_input);
		
		String barName = barNameInput.toString();
		
		Intent intent = new Intent(this, BarSearchActivity.class);
		
		intent.putExtra("barName", barName);
		
		startActivity(intent);
	}

}
