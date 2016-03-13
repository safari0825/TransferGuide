package jp.co.pp.transferguide;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.pp.transferguide.Engine.Timetable;
import jp.co.pp.transferguide.Logic.LogicCommon;

public class TimetableActivity extends Activity {

    private ListView listView;

    private String stationName = "";

    private String lineName = "";



    private void refreshData() {

        TimetableListAdapter adapter = new TimetableListAdapter(this);
        adapter.timeList = new ArrayList<List<String>>();

        Timetable timetable = LogicCommon.getTimeTable(this.stationName,this.lineName);

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

//    private void showDialog() {
//        String str1 = DM.getL("Weekdays");
//        String str2 = DM.getL("Holidays");
//        AlertDialog.Builder localBuilder = Util.getAlert(this, DM.getL("Timetable"));
//        DialogInterface.OnClickListener local1 = new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
//                TimetableActivity.access$002(TimetableActivity.this, paramAnonymousInt);
//                TimetableActivity.this.listView.setAdapter(TimetableActivity.this.getAdapter());
//                paramAnonymousDialogInterface.dismiss();
//            }
//        };
//        localBuilder.setSingleChoiceItems(new CharSequence[]{str1, str2}, -1, local1).setNegativeButton(DM.getL("Cancel"), null).show();
//    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_timetable);


        this.stationName = getIntent().getStringExtra("PK_STATION_NAME");
        this.lineName = getIntent().getStringExtra("PK_LINE_NAME");

        this.listView = (ListView) findViewById(R.id.timeTblListView);
        refreshData();
    }

    public boolean onCreateOptionsMenu(Menu paramMenu) {
//        LogicCommon.setActionBar(this, LogicCommon.getStationLang(this.stationID));
//        LogicCommon.addMenuItem(paramMenu, "Timetable");
        return super.onCreateOptionsMenu(paramMenu);
    }

    public boolean onOptionsItemSelected(MenuItem paramMenuItem) {
        if (paramMenuItem.getItemId() == 16908332) {
            finish();
        }
//        for (; ; ) {
//            return super.onOptionsItemSelected(paramMenuItem);
//            if (paramMenuItem.getItemId() == 991) {
//                showDialog();
//            }
//        }
        return super.onOptionsItemSelected(paramMenuItem);
    }
}