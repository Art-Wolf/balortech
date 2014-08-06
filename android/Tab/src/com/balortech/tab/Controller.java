package com.balortech.tab;

import java.util.ArrayList;

import android.app.Application;
import android.util.Log;

public class Controller extends Application {
	private ArrayList<BarMenuItem> barMenuItemList = new ArrayList<BarMenuItem>();
	private ShoppingCart shoppingCart = new ShoppingCart();
	
	public BarMenuItem getBarMenuItem(int position) {
		return barMenuItemList.get(position);
	}
	
	public void setBarMenuItem(BarMenuItem barMenuItem) {
		Log.d("Controller.setBarMenuItem", barMenuItem.getItemName());
		barMenuItemList.add(barMenuItem);
	}
	
	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}
	
	public int getBarMenuItemListCount() {
		return barMenuItemList.size();
	}
	

}
