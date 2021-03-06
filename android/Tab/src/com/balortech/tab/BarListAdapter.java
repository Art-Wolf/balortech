package com.balortech.tab;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class BarListAdapter extends BaseExpandableListAdapter {

	private Context context;
	private ArrayList<BarList> groups;
	
	public BarListAdapter(Context context, ArrayList<BarList> groups) {
		this.context = context;
		this.groups = groups;
	}
	
	public void addItem(BarOptions barOptions, BarList barList) {
		if (!groups.contains(barList)) {
			groups.add(barList);
		}

		int index = groups.indexOf(barList);
		ArrayList<BarOptions> options = groups.get(index).getMenuItems();
		options.add(barOptions);
		groups.get(index).setMenuItems(options);
	}
	@Override
	public Object getChild(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return groups.get(arg0).getMenuItems().get(arg1);
	}

	@Override
	public long getChildId(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return arg1;
	}

	@Override
	public View getChildView(int arg0, int arg1, boolean arg2, View arg3,
			ViewGroup arg4) {
		BarOptions options = (BarOptions) getChild(arg0, arg1);
		if (arg3 == null) {
			LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			arg3 = infalInflater.inflate(R.layout.activity_baroption , null);
		}
		
		TextView tv = (TextView) arg3.findViewById(R.id.lblListItem);
		tv.setText(options.getName().toString());

		
		return arg3;
	}

	@Override
	public int getChildrenCount(int arg0) {
		// TODO Auto-generated method stub
		return groups.get(arg0).getMenuItems().size();
	}

	@Override
	public Object getGroup(int arg0) {
		// TODO Auto-generated method stub
		return groups.get(arg0);
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return groups.size();
	}

	@Override
	public long getGroupId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getGroupView(int arg0, boolean arg1, View arg2, ViewGroup arg3) {
		// TODO Auto-generated method stub
		BarList bar = (BarList) getGroup(arg0);
		if (arg2 == null) {
			 LayoutInflater inf = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			arg2 = inf.inflate(R.layout.activity_barlist, null);
		  }
		
		TextView tv = (TextView) arg2.findViewById(R.id.lblListHeader);
		
		tv.setText(bar.getName());

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
