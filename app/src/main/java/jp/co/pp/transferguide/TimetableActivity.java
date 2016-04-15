package jp.co.pp.transferguide;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.pp.transferguide.Engine.Timetable;
import jp.co.pp.transferguide.Logic.LogicCommon;

public class TimetableActivity extends AppCompatActivity {

    private ListView listView;

    private String stationName = "";

    private String wayName = "";



    private void refreshData() {

        TimetableListAdapter adapter = new TimetableListAdapter(this);
        adapter.timeList = new ArrayList<List<String>>();

        Timetable timetable = LogicCommon.getTimeTable(this.stationName,this.wayName);

        Iterator depTimeIta = timetable.depTime.iterator();
        ArrayList perHourList = new ArrayList();
        String preHour = "";
        while (depTimeIta.hasNext()) {
            String depTime = (String)depTimeIta.next();
            if(depTime.isEmpty()) continue;
            String curHour = depTime.substring(0,depTime.indexOf(":"));
            if(!preHour.isEmpty()) {
                if(!curHour.equals(preHour)) {
                    adapter.timeList.add(perHourList);
                    perHourList = new ArrayList();
                    perHourList.add(depTime);
                    preHour = curHour;
                }else {
                    perHourList.add(depTime);
                    preHour = curHour;
                }
            }else {
                perHourList.add(depTime);
                preHour = curHour;
            }

        }

        this.listView.setAdapter(adapter);
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_timetable);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_time);
        // Set an OnMenuItemClickListener to handle menu item clicks
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Handle the menu item
                return true;
            }
        });
// Inflate a menu to be displayed in the toolbar

        toolbar.inflateMenu(R.menu.menu_main);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        this.stationName = getIntent().getStringExtra("PK_STATION_NAME");
        this.wayName = getIntent().getStringExtra("PK_WAY_NAME");

        getSupportActionBar().setTitle(this.stationName);
        this.listView = (ListView) findViewById(R.id.timeTblListView);
        refreshData();
    }

    public boolean onCreateOptionsMenu(Menu paramMenu) {
        return super.onCreateOptionsMenu(paramMenu);
    }

    public boolean onOptionsItemSelected(MenuItem paramMenuItem) {
        if (paramMenuItem.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(paramMenuItem);
    }
}