package jp.co.pp.transferguide;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by softwise024 on 2016/04/14.
 */
public class WayListAdapter  extends BaseAdapter {

    public List<String> wayList;
    private Context context;

    public String stationName = "";

    public String lineName = "";

    public WayListAdapter(Context c) {
        this.context = c;
    }


    @Override
    public int getCount() {
        return wayList == null ? 0 : wayList.size();
    }

    @Override
    public Object getItem(int position) {
        return wayList == null ? null : wayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout itemView = new LinearLayout(context);
        itemView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        itemView.setOrientation(LinearLayout.VERTICAL);

        TextView wayText = new TextView(context);
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        wayText.setLayoutParams(layoutParams);
        wayText.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        wayText.setPadding(80, 0, 0, 0);
        wayText.setText(wayList.get(position));

        itemView.addView(wayText);
        return itemView;
    }
}
