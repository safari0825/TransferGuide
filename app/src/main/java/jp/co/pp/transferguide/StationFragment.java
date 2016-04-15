package jp.co.pp.transferguide;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.pp.transferguide.Engine.DM;
import jp.co.pp.transferguide.Engine.Line;
import jp.co.pp.transferguide.Engine.Station;
import jp.co.pp.transferguide.Logic.LogicCommon;
import jp.co.pp.transferguide.util.Util;

public class StationFragment extends android.app.Fragment implements AdapterView.OnItemClickListener {

    public static String stationText;
    public int type = 0;
    private EditText editText;
    private List<String> lineList;
    private List<List<String>> lineStationList;
    private ExpandableListView listView;
    //private SectionUtil su;

    private AbsListView.OnScrollListener getScrollListener() {
        return new AbsListView.OnScrollListener() {
            public void onScroll(AbsListView paramAnonymousAbsListView, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {
            }

            public void onScrollStateChanged(AbsListView paramAnonymousAbsListView, int paramAnonymousInt) {
                switch (paramAnonymousInt) {
                    case 0:
                    case 2:
                    default:
                }
                StationFragment.this.hideKeyboard();
            }
        };
    }

    private TextWatcher getTextWatcher() {
        return new TextWatcher() {
            public void afterTextChanged(Editable paramAnonymousEditable) {
            }

            public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {
            }

            public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {
                StationFragment.this.refreshData();
                //StationFragment.this.refreshListView();
            }
        };
    }

    private void hideKeyboard() {
        if (getActivity().getCurrentFocus() == null) {
            return;
        }
        ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 2);
    }

    private void refreshData() {
        StationListAdapter stationListAdapter = new StationListAdapter(getActivity());

        String str1 = this.editText.getText().toString().trim().toLowerCase();
        if (str1.isEmpty()) {
            stationListAdapter.lineList =null;
            stationListAdapter.stationList = null;
        }else {
            List<Station> staList = LogicCommon.getStation(str1);

            stationListAdapter.lineList = new ArrayList();
            stationListAdapter.stationList = new ArrayList();

            Iterator staIte = staList.iterator();
            ArrayList tmpStaList = new ArrayList();
            String preLineName = "";
            while (staIte.hasNext()) {
                Station tmpSta = (Station) staIte.next();

                //
                Line tmpLine = LogicCommon.getLine(tmpSta.stationNameCN);
                String tmpLineName = tmpLine.lineNameCN;

                //
                if (preLineName.equals(tmpLineName)) {
                    //same line
                    tmpStaList.add(tmpSta.stationNameCN);

                } else { // line changed or first data
                    stationListAdapter.lineList.add(tmpLineName);
                    if (!preLineName.isEmpty()) {
                        //line changed , should commit line's stationlist
                        tmpStaList = new ArrayList();
                    }
                    tmpStaList.add(tmpSta.stationNameCN);
                    stationListAdapter.stationList.add(tmpStaList);
                    preLineName = tmpLineName;
                }
            }
        }
        this.listView.setAdapter(stationListAdapter);
        this.listView.setOnChildClickListener(new OnStationClickListener());
    }



    private class OnStationClickListener implements ExpandableListView.OnChildClickListener {

        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
            Intent toWayList = new Intent(getActivity().getBaseContext(),WayListActivity.class);

            ExpandableListAdapter adapter = parent.getExpandableListAdapter();
            toWayList.putExtra("PK_STATION_NAME", (adapter.getChild(groupPosition,childPosition).toString()));
            toWayList.putExtra("PK_LINE_NAME", adapter.getGroup(groupPosition).toString());

            //指定迁移先画面---时刻表Activity
            getActivity().startActivity(toWayList);
            return false;
        }
    }

    public void onActivityCreated(Bundle paramBundle) {
        super.onActivityCreated(paramBundle);
        setHasOptionsMenu(true);
    }

    public void onCreateOptionsMenu(Menu paramMenu, MenuInflater paramMenuInflater) {
//        if (LogicCommon.isDrawerClose(getActivity())) {
//            paramMenu.clear();
//            LogicCommon.setActionBar(getActivity(), DM.getL("Station"));
//        }
//        for (; ; ) {
//            super.onCreateOptionsMenu(paramMenu, paramMenuInflater);
//            return;
//            if ((getActivity() instanceof StationActivity)) {
//                paramMenu.clear();
//                LogicCommon.setActionBar(getActivity(), DM.getL("Station"));
//            }
//        }
    }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {


        View stationFragmentLayout = paramLayoutInflater.inflate(R.layout.fragment_station, paramViewGroup, false);
        //LogicCommon.saveScreen(getClass().getSimpleName());
        this.editText = ((EditText) stationFragmentLayout.findViewById(R.id.editText));
        this.listView = ((ExpandableListView) stationFragmentLayout.findViewById(R.id.listView));
        this.editText.setHint("请输入站名");
        if (this.type == 0) {
            this.editText.setText(stationText);
        }
        this.editText.addTextChangedListener(getTextWatcher());
        this.listView.setOnScrollListener(getScrollListener());
        this.listView.setOnItemClickListener(this);
        refreshData();
        //refreshListView();
        return stationFragmentLayout;
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong) {
        hideKeyboard();
//        int i = this.su.getSectionIndex(paramInt);
//        paramInt = this.su.getRowIndex(paramInt);
//        paramInt = ((Integer) ((List) this.lineStationList.get(i)).get(paramInt)).intValue();
//        if (this.type == 0) {
//            LogicCommon.pushStationInfo(getActivity(), paramInt);
//        }
//        do {
//            return;
//            if (this.type == 1) {
//                LogicCommon.searchDep = LogicCommon.getStationUNO(paramInt);
//                getActivity().finish();
//                return;
//            }
//        } while (this.type != 2);
//        LogicCommon.searchArr = LogicCommon.getStationUNO(paramInt);
//        getActivity().finish();
    }

    public boolean onOptionsItemSelected(MenuItem paramMenuItem) {
        return super.onOptionsItemSelected(paramMenuItem);
    }

    public void onPause() {
        super.onPause();
        hideKeyboard();
        if (this.type == 0) {
            stationText = this.editText.getText().toString().trim();
        }
    }

    public void onResume() {
        super.onResume();
    }

    public void onSaveInstanceState(Bundle paramBundle) {
        super.onSaveInstanceState(paramBundle);
    }
}
