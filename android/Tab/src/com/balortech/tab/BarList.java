package com.balortech.tab;

import java.util.ArrayList;

public class BarList {
	private String name;
	
	private ArrayList<BarOptions> menuItems;
	
	public String getName()
    {
        return name;
    }
	
	public void setName(String name)
    {
        this.name = name;
    }
	
	public ArrayList<BarOptions> getMenuItems() {
		return menuItems;
	}
	
	public void setMenuItems(ArrayList<BarOptions> newMenuItems) {
		menuItems = newMenuItems;
	}
}
