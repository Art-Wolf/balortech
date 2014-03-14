package com.balortech.breeze.test;

import android.content.Intent;
import com.balortech.breeze.R;
import android.test.ActivityUnitTestCase;
import android.widget.Button;
import android.widget.EditText;

import com.balortech.breeze.FindBarActivity;

public class FindBarActivityTest extends ActivityUnitTestCase<FindBarActivity> {

	FindBarActivity activity;
	
	public FindBarActivityTest() {
		super(FindBarActivity.class);
	}
	
	public FindBarActivityTest(Class<FindBarActivity> activityClass) {
		super(activityClass);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		Intent intent = new Intent(getInstrumentation().getTargetContext(),
				FindBarActivity.class);
		startActivity(intent, null, null);
		activity = getActivity();
	}
	
	public void testLayout() {
		EditText barName = (EditText) activity.findViewById(R.id.bar_name_input);
		assertNotNull(barName);
		
		Button searchButton = (Button) activity.findViewById(R.id.button_search);
		assertNotNull(searchButton);
		assertEquals("Incorrect label of the button", "Search",
				searchButton.getText());
		}

}
