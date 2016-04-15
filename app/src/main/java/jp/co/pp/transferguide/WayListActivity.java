package jp.co.pp.transferguide;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.pp.transferguide.Engine.Timetable;
import jp.co.pp.transferguide.Engine.Way;
import jp.co.pp.transferguide.Logic.LogicCommon;

public class WayListActivity extends AppCompatActivity {

    private ListView listView;

    private String stationName = "";

    private String lineName = "";

    private TextView lineNameView ;

    private void refreshData() {

        WayListAdapter adapter = new WayListAdapter(this);
        adapter.stationName = this.stationName;
        adapter.lineName = this.lineName;
        adapter.wayList = new ArrayList<String>();

        List<Way> wayList = LogicCommon.getWayByStation(this.stationName);

        Iterator wayIterator = wayList.iterator();
        while (wayIterator.hasNext()) {
            Way tmpWay = (Way)wayIterator.next();
            adapter.wayList.add(tmpWay.wayName);
        }

        this.listView.setAdapter(adapter);
        this.listView.setOnItemClickListener(new OnStationClickListener());
    }

    private class OnStationClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent toTimeTab = new Intent(getApplicationContext(),TimetableActivity.class);

            WayListAdapter adapter = (WayListAdapter)parent.getAdapter();
            toTimeTab.putExtra("PK_STATION_NAME", (adapter.stationName));
            toTimeTab.putExtra("PK_WAY_NAME", adapter.getItem(position).toString());

            //指定迁移先画面---时刻表Activity
            startActivity(toTimeTab);
        }
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_waylist);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_way);
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
        this.lineName = getIntent().getStringExtra("PK_LINE_NAME");

        getSupportActionBar().setTitle(this.stationName);

        this.lineNameView = (TextView)findViewById(R.id.linename);
        this.lineNameView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        this.lineNameView.setBackgroundColor(Color.GRAY);
        this.lineNameView.setTextSize(20);
        this.lineNameView.setText(this.lineName);

        this.listView = (ListView) findViewById(R.id.wayListView);
        refreshData();
    }


    public boolean onCreateOptionsMenu(Menu paramMenu) {
        return super.onCreateOptionsMenu(paramMenu);
    }

    public boolean onOptionsItemSelected(MenuItem paramMenuItem) {
        if (paramMenuItem.getItemId() == android.R.id.home) {
            finish();
            return  true;
        }
        return super.onOptionsItemSelected(paramMenuItem);
    }
}
