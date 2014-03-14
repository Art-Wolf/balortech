package com.balortech.breeze.test;

import com.balortech.breeze.MainActivity;
import com.balortech.breeze.R;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.widget.Button;

public class MainActivityTest extends ActivityUnitTestCase<MainActivity> {

	private MainActivity activity;

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
		int buttonId = R.id.find_bar;
		assertNotNull(activity.findViewById(buttonId));
		Button view = (Button) activity.findViewById(buttonId);
		assertEquals("Incorrect label of the button", "Find Bar",
				view.getText());
	}

}
