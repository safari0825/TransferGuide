package jp.co.pp.transferguide;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StationListAdapter extends BaseExpandableListAdapter {

    private Context context;
    public List<String> lineList;

    public List<List<String>> stationList;

    public StationListAdapter(Context c) {
        context = c;
    }
    @Override
    public int getGroupCount() {
        return lineList==null ? 0 : lineList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return lineList==null ? 0 : stationList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return lineList==null ? null : lineList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return lineList==null ? null : stationList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        TextView myText = null;
        if (convertView != null) {
            myText = (TextView)convertView;
            myText.setText(lineList.get(groupPosition));

        } else {
            myText = createView(lineList.get(groupPosition));
            myText.setBackgroundColor(Color.GRAY);
        }
        return myText;
    }


    private TextView createView(String content) {
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView myText = new TextView(context);
        myText.setLayoutParams(layoutParams);
        myText.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        myText.setPadding(80, 0, 0, 0);
        myText.setText(content);
        myText.setTextSize(20);
        return myText;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        TextView myText = null;
        if (convertView != null) {
            myText = (TextView)convertView;
            myText.setText(stationList.get(groupPosition).get(childPosition));
        } else {
            myText = createView(stationList.get(groupPosition).get(childPosition));
            myText.setBackgroundColor(Color.WHITE);
        }
        //myText.setOnClickListener(new OnStationClickListener());
        return myText;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}