package com.balortech.tab;

import java.util.ArrayList;

public class ShoppingCart {
	private ArrayList<BarMenuItem> barMenuItemList = new ArrayList<BarMenuItem>();
	
	public BarMenuItem getBarMenuItem(int position) {
		return barMenuItemList.get(position);
	}
	
	public void setBarMenuItem(BarMenuItem barMenuItem) {
		barMenuItemList.add(barMenuItem);
	}
	
	public int getCartSize() {
		return barMenuItemList.size();
	}
}
