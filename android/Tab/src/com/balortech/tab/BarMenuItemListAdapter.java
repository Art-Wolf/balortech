package com.balortech.tab;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class BarMenuItemListAdapter extends BaseExpandableListAdapter {

	private Context context;
	private ArrayList<BarMenuItemList> barMenu;
	
	public BarMenuItemListAdapter(Context context, ArrayList<BarMenuItemList> menu) {
		this.context = context;
		this.barMenu = menu;
	}
	
	public void addItem(BarMenuItem menuItem, BarMenuItemList menuItemList) {
		if (!barMenu.contains(menuItemList)) {
			barMenu.add(menuItemList);
		}

		int index = barMenu.indexOf(menuItemList);
		ArrayList<BarMenuItem> items = barMenu.get(index).getMenuItems();
		items.add(menuItem);
		barMenu.get(index).setMenuItems(items);
	}
	@Override
	public Object getChild(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return barMenu.get(arg0).getMenuItems().get(arg1);
	}

	@Override
	public long getChildId(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return arg1;
	}

	@Override
	public View getChildView(int arg0, int arg1, boolean arg2, View arg3,
			ViewGroup arg4) {
		BarMenuItem barMenuItem = (BarMenuItem) getChild(arg0, arg1);
		if (arg3 == null) {
			LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			arg3 = infalInflater.inflate(R.layout.activity_menuitem , null);
		}
		
		TextView tv = (TextView) arg3.findViewById(R.id.lblItemDescription);
		Log.d("getChildView", "" + barMenuItem.getItemDescription().toString().length());
		tv.setText(barMenuItem.getItemDescription().toString());

		TextView tv2 = (TextView) arg3.findViewById(R.id.lblItemPrice);
		tv2.setText(barMenuItem.getItemPrice().toString());

		return arg3;
	}

	@Override
	public int getChildrenCount(int arg0) {
		// TODO Auto-generated method stub
		return barMenu.get(arg0).getMenuItems().size();
	}

	@Override
	public Object getGroup(int arg0) {
		// TODO Auto-generated method stub
		return barMenu.get(arg0);
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return barMenu.size();
	}

	@Override
	public long getGroupId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getGroupView(int arg0, boolean arg1, View arg2, ViewGroup arg3) {
		// TODO Auto-generated method stub
		BarMenuItemList menu = (BarMenuItemList) getGroup(arg0);
		if (arg2 == null) {
			 LayoutInflater inf = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			arg2 = inf.inflate(R.layout.activity_menuitemlist, null);
		  }
		
		TextView tv = (TextView) arg2.findViewById(R.id.lblItemName);
		
		tv.setText(menu.getName());

		return arg2;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return true;
	}

}
