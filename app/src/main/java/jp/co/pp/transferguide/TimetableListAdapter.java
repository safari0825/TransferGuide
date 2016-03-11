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
 * Created by softwise024 on 2016/03/11.
 */
public class TimetableListAdapter extends BaseAdapter {

    public List<List<String>> timeList;
    private Context context;

    public TimetableListAdapter(Context c) {
        this.context = c;
    }


    @Override
    public int getCount() {
        return timeList == null ? 0 : timeList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
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

        TextView hour = new TextView(context);
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        hour.setLayoutParams(layoutParams);
        hour.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        hour.setPadding(80, 0, 0, 0);
        String time = timeList.get(position).get(0);
         hour.setText(time.substring(0, time.indexOf(":")));
        hour.setTextSize(20);
        hour.setBackgroundColor(Color.GRAY);
        itemView.addView(hour);

        int minCnt = timeList.get(position).size();
        int minLineCnt = minCnt / 10 + 1;
        for (int i = 0 ; i < minLineCnt ; i++) {

            TextView min = new TextView(context);
            AbsListView.LayoutParams minLayoutParams = new AbsListView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            min.setLayoutParams(minLayoutParams);
            min.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
            min.setPadding(80, 0, 0, 0);

            String timeStr = "";
            for (int j = i * 10; j < minCnt; j ++ ) {
                String time1 = timeList.get(position).get(j);
                String minStr = time1.substring(time.indexOf(":") + 1);
                timeStr +=  minStr + " ";
            }
            min.setText(timeStr);
            min.setTextSize(18);
            itemView.addView(min);
        }

        return itemView;
    }
}
