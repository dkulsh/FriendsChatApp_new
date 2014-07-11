package com.example.friendschatapp;

import java.util.HashSet;
import java.util.Set;

import com.google.gson.Gson;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ExpandableParentLevelAdapter extends BaseExpandableListAdapter {
	
	String[] groupTypes = {"Friends", "Family", "Others"};
	int[] drawables = {R.drawable.friends, R.drawable.family_icon, R.drawable.others};
	
	Set<HashSet<String>> friendGroups = new HashSet<HashSet<String>>();
	Set<HashSet<String>> familyGroups = new HashSet<HashSet<String>>();
	Set<HashSet<String>> othersGroups = new HashSet<HashSet<String>>();
	
	LayoutInflater inflater;
	Context context;

	public ExpandableParentLevelAdapter(Context context) {
		
		this.context = context;
		
		Gson gson = new Gson();
		
		SharedPreferences prefFriend = context.getSharedPreferences(groupTypes[1], Context.MODE_PRIVATE);
		if(prefFriend.contains(groupTypes[1])){
			
			String jsonFriends = prefFriend.getString(groupTypes[1], "");
			gson.fromJson(jsonFriends, Set<>);
		}
		
		SharedPreferences prefFamily = context.getSharedPreferences(groupTypes[1], Context.MODE_PRIVATE);
		
		SharedPreferences prefOther = context.getSharedPreferences(groupTypes[1], Context.MODE_PRIVATE);
	}

	@Override
	public int getGroupCount() {

		return groupTypes.length;
	}

	@Override
	public int getChildrenCount(int groupPosition) {

		return pref.getInt(groupTypes[groupPosition], 0);
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {

		View view = convertView == null ? inflater.inflate(R.layout.list_row, null) : convertView;
		
		ImageView navOptionIcon = (ImageView) view.findViewById(R.id.imgItemIcon);
		TextView navOptionText = (TextView) view.findViewById(R.id.tvRow);
		
		navOptionIcon.setImageResource(drawables[groupPosition]);
		navOptionText.setText(groupTypes[groupPosition]);
		
		return view;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		
		ListView secondLevelList = new ListView(context);
		secondLevelList.setAdapter(new SecondLevelAdapter(groupPosition));
		return secondLevelList;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}
	
	public class SecondLevelAdapter extends BaseAdapter {

		int groupPosition;
		
		public SecondLevelAdapter(int groupPosition) {
			
			this.groupPosition = groupPosition;
		}

		@Override
		public int getCount() {
			SharedPreferences pref = context.getSharedPreferences(groupTypes[groupPosition], Context.MODE_PRIVATE);
			return pref.getInt(groupTypes[groupPosition], 0);
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			View view = convertView == null ? inflater.inflate(R.layout.list_row, null) : convertView;
			
			ImageView navOptionIcon = (ImageView) view.findViewById(R.id.imgItemIcon);
			TextView navOptionText = (TextView) view.findViewById(R.id.tvRow);
			
			navOptionIcon.setImageResource(drawables[groupPosition]);
			navOptionText.setText(groupTypes[groupPosition]);
			
			return view;
		}
		
	}

}
