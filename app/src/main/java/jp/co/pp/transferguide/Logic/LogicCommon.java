package jp.co.pp.transferguide.Logic;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.Menu;

import jp.co.pp.transferguide.Engine.*;
import jp.co.pp.transferguide.*;

//import com.google.android.gms.analytics.GoogleAnalytics;
//import com.google.android.gms.analytics.Tracker;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import jp.co.pp.transferguide.util.FileUtil;

public class LogicCommon {
    public static final String KEY_CITY = "KEY_CITY_2015";
    public static final String KEY_LANGUAGE = "KEY_LANGUAGE_2015";
    public static final int MENU_ITEM_1 = 991;
    public static final int MENU_ITEM_2 = 992;
    public static final String PK_NEARBY_KEY = "PK_NEARBY_KEY";
    public static final String PK_STATION_ID = "PK_STATION_ID";
    public static final String PK_STATION_IDS = "PK_STATION_IDS";
    public static final String PK_TYPE = "PK_TYPE";
    public static final String PK_WAY_ID = "PK_WAY_ID";
    public static Date adDate;
    public static String city = "";
    public static String language = "";
    public static List<String> lineList;
    public static List<List<String>> lineStationList;
    public static int routeIndex;

    private static Intent getIntent(Activity paramActivity, String paramString) {
        Intent localIntent = new Intent();
        localIntent.setClassName(paramActivity.getPackageName(), String.format("%s.%s", new Object[]{paramActivity.getPackageName(), paramString}));
        return localIntent;
    }

    public static List<Station> getStation(String staname) {
        ArrayList<Station> stalist = new ArrayList<Station>();
        Station tmpSta;
        Iterator localSta = DM.stationMap.values().iterator();
        while (localSta.hasNext()) {

            tmpSta = (Station)localSta.next();
            //?索条件的StationName是?音的情况
            if(staname.trim().matches("^[a-zA-Z]*")) {
                if(tmpSta.stationNamePY.indexOf(staname.trim()) >= 0) {
                    stalist.add(tmpSta);
                }
            }else {
                if(tmpSta.stationNameCN.indexOf(staname.trim()) >= 0) {
                    stalist.add(tmpSta);
                }
            }
        }
        return stalist;
    }


    public static Line getLine(String staname) {

        Station targetSta = DM.stationMap.get(staname);

        Line rtnLine = null;

        Iterator localline = DM.lineMap.values().iterator();

        while (localline.hasNext()) {

            Line tmpLine  = (Line)localline.next();
            Iterator localSta = tmpLine.stationIDList.iterator();
            while (localSta.hasNext()) {
                Station cmpSta = (Station)localSta.next();
                if(targetSta.stationId.equals(cmpSta.stationId)) {
                    rtnLine = tmpLine;
                    break;
                }
            }

        }
        return rtnLine;
    }

    public static Timetable getTimeTable(String staname,String linename) {

        String wayId = DM.wayMap.get(linename).wayId;

        ArrayList<Station> staList = (ArrayList)getStation(staname);

        Timetable tmpTimetable = null;
        Iterator localSta = staList.iterator();
        while (localSta.hasNext()) {

            Station tmpSta = (Station)localSta.next();
            //if(tmpSta.lineName.equals(linename)) {

                //数据初期化时timetable的内容读入到DM.weekdayList中。
                Iterator localTime = DM.weekdayList.iterator();
                while (localTime.hasNext()) {
                    tmpTimetable = (Timetable)localTime.next();
                    if(tmpTimetable.wayId.equals(wayId) && tmpTimetable.stationId.equals(tmpSta.stationId)){
                        break;
                    }
                }
            //}
        }

        return tmpTimetable;
    }



    public static void initData(Context paramContext) {
        FileUtil.context = paramContext;
        //DM.loadAppLang(language);
        //DM.loadCityList();
        //DM.unloadCity();
        //DM.loadCity(city);
        //srcLang = getSrcLang(language);

        DM.loadStation();
        DM.loadLine();
        DM.loadWay();
        DM.loadTimeTable();
        DM.loadLink();
    }

    public static boolean isDrawerClose(Activity paramActivity) {
        boolean bool = false;
        if ((paramActivity instanceof MainActivity)) {
            if (!((MainActivity) paramActivity).mDrawerLayout.isDrawerOpen(((MainActivity) paramActivity).mDrawerList)) {
                bool = true;
            }
        } else {
            return bool;
        }
        return false;
    }

    public static boolean isGMap() {
        return "gmap".equals("gmap");
    }

    public static void pushMap(Activity paramActivity, int paramInt, String paramString1, String paramString2) {
        Intent localIntent = getIntent(paramActivity, "MapActivity");
        localIntent.putExtra("PK_TYPE", paramInt);
        localIntent.putExtra("PK_STATION_IDS", paramString1);
        localIntent.putExtra("PK_NEARBY_KEY", paramString2);
        paramActivity.startActivity(localIntent);
    }

    public static void pushPhoto(Activity paramActivity, int paramInt) {
        Intent localIntent = getIntent(paramActivity, "PhotoActivity");
        localIntent.putExtra("PK_STATION_ID", paramInt);
        paramActivity.startActivity(localIntent);
    }

    public static void pushResult(Activity paramActivity) {
        paramActivity.startActivity(getIntent(paramActivity, "ResultActivity"));
    }

    public static void pushSettingCity(Activity paramActivity) {
        paramActivity.startActivity(getIntent(paramActivity, "SettingCityActivity"));
    }

    public static void pushSettingLang(Activity paramActivity) {
        paramActivity.startActivity(getIntent(paramActivity, "SettingLangActivity"));
    }

    public static void pushStation(Activity paramActivity, int paramInt) {
        Intent localIntent = getIntent(paramActivity, "StationActivity");
        localIntent.putExtra("PK_TYPE", paramInt);
        paramActivity.startActivity(localIntent);
    }

    public static void pushStationInfo(Activity paramActivity, int paramInt) {
        Intent localIntent = getIntent(paramActivity, "StationInfoActivity");
        localIntent.putExtra("PK_STATION_ID", paramInt);
        paramActivity.startActivity(localIntent);
    }

    public static void pushTime(Activity paramActivity) {
        paramActivity.startActivity(getIntent(paramActivity, "TimeActivity"));
    }

    public static void pushTimetable(Activity paramActivity, int paramInt1, int paramInt2, int paramInt3) {
        Intent localIntent = getIntent(paramActivity, "TimetableActivity");
        localIntent.putExtra("PK_TYPE", paramInt1);
        localIntent.putExtra("PK_STATION_ID", paramInt2);
        localIntent.putExtra("PK_WAY_ID", paramInt3);
        paramActivity.startActivity(localIntent);
    }

    public static void pushWay(Activity paramActivity, int paramInt) {
        Intent localIntent = getIntent(paramActivity, "WayActivity");
        localIntent.putExtra("PK_STATION_ID", paramInt);
        paramActivity.startActivity(localIntent);
    }

    public static void pushWiki(Activity paramActivity, int paramInt) {
        Intent localIntent = getIntent(paramActivity, "WikiActivity");
        localIntent.putExtra("PK_STATION_ID", paramInt);
        paramActivity.startActivity(localIntent);
    }



    private static void restartApp(Activity paramActivity) {
        Object localObject = paramActivity.getBaseContext().getPackageName();
        localObject = paramActivity.getBaseContext().getPackageManager().getLaunchIntentForPackage((String) localObject);
        ((Intent) localObject).addFlags(67108864);
        paramActivity.startActivity((Intent) localObject);
    }


    private static void saveSetting(Context paramContext) {
        PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putString("KEY_CITY_2015", city).putString("KEY_LANGUAGE_2015", language).apply();
    }
}