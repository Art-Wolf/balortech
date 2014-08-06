package com.balortech.tab;

import java.util.ArrayList;

public class BarMenuItemList {
	private String name;
	
	private ArrayList<BarMenuItem> menuItems;
	
	public String getName()
    {
        return name;
    }
	
	public void setName(String name)
    {
        this.name = name;
    }
	
	public ArrayList<BarMenuItem> getMenuItems() {
		return menuItems;
	}
	
	public void setMenuItems(ArrayList<BarMenuItem> menuItems) {
		this.menuItems = menuItems;
	}
}
