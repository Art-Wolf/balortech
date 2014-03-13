package com.balortech.breeze.test;

import junit.framework.Assert;
import android.test.ActivityInstrumentationTestCase2;

import com.balortech.breeze.MainActivity;
import com.robotium.solo.Solo;


public class MainActivityBlackBoxTest extends ActivityInstrumentationTestCase2<MainActivity> {
	private Solo solo;

	  public MainActivityBlackBoxTest() {
	                super(MainActivity.class);
	  }

	  @Override
	  public void setUp() throws Exception {
	        solo = new Solo(getInstrumentation(), getActivity());
	  }
	  
	  @Override
	   public void tearDown() throws Exception {
	        solo.finishOpenedActivities();
	  }
	  
	  public void testMainFindBar() throws Exception {
		  
          solo.sendKey(Solo.MENU);
          solo.clickOnButton("Find Bar");
          Assert.assertTrue(solo.searchText("Hello, world!"));
	  }
}
