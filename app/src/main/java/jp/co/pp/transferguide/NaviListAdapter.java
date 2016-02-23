package jp.co.pp.transferguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;

/**
 * Created by softwise024 on 2016/02/22.
 */
public class NaviListAdapter extends ArrayAdapter<NaviListAdapter.BindData> {

    private LayoutInflater inflater;

    int reource;

    public NaviListAdapter(Context paramContext,int resourceId, List<BindData> paramList)
    {
        super(paramContext, resourceId, paramList);
        this.inflater = ((LayoutInflater)paramContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        reource = resourceId;
        //drawer_menu = getContext().getResources().getStringArray(R.array.drawer_menu);
//        for ( int i = 0 ; i< paramList.size();i++) {
//            ((BindData)paramList.get(i)).text = drawer_menu[i];
//        }
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
        LinearLayout listview ;
        if (paramView == null)
        {
            listview = new LinearLayout(getContext());
            this.inflater.inflate(reource, listview, true);
            //paramViewGroup.imageView = ((ImageView)paramView.findViewById(R));

        }else {
            listview = (LinearLayout)paramView;
        }

        BindData localBindData = (BindData)getItem(paramInt);

        TextView naviText = (TextView)listview.findViewById(R.id.listitem);
        naviText.setText(localBindData.text);
        //paramView.setTag(paramViewGroup);


        //paramViewGroup.imageView.setImageResource(localBindData.imageId);
        //paramViewGroup.textView.setText(localBindData.text);
        return listview;


    }

    public static class BindData
    {
        int imageId;
        String text;

        public BindData(int paramInt, String paramString)
        {
            this.imageId = paramInt;
            this.text = paramString;
        }
    }

    public static class BindView
    {
        ImageView imageView;
        TextView textView;
    }
}
