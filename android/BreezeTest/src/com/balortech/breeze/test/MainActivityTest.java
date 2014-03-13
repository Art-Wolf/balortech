package com.balortech.breeze.test;

import com.balortech.breeze.FindBar;
import com.balortech.breeze.MainActivity;
import com.balortech.breeze.R;

import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.widget.Button;
import android.widget.TextView;

public class MainActivityTest extends ActivityUnitTestCase<MainActivity> {

	private MainActivity activity;
	private int buttonId;
	private int textViewId;
	
	public MainActivityTest() {
		super(MainActivity.class);
	}
	
	public MainActivityTest(Class<MainActivity> activityClass) {
		super(activityClass);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	  protected void setUp() throws Exception {
	    super.setUp();
	    Intent intent = new Intent(getInstrumentation().getTargetContext(),
	        MainActivity.class);
	    startActivity(intent, null, null);
	    activity = getActivity();
	  }
	
	public void testLayout() {
	    buttonId = R.id.find_bar;
	    assertNotNull(activity.findViewById(buttonId));
	    Button view = (Button) activity.findViewById(buttonId);
	    assertEquals("Incorrect label of the button", "Find Bar", view.getText());
	  }
	
	public void testFindBar() {
		
		buttonId = R.id.find_bar;
	    assertNotNull(activity.findViewById(buttonId));
	    Button btnLaunch = (Button) activity.findViewById(buttonId);
	    btnLaunch.performClick();
	    activity = getActivity();
	    textViewId = R.id.hello;
	    assertNotNull(activity.findViewById(textViewId));
	    TextView textView = (TextView) activity.findViewById(textViewId);

	    assertEquals("Text should be the field value", "Hello world!", textView.getText().toString());
		
	}

}
