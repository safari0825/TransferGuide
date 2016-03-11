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
//    public static List<UIRoute> routeList;
//    public static SrcUNO searchArr;
//    public static SrcUNO searchDep;
//    public static int searchOption;
//    public static Date searchTime;
//    public static int searchType;
//    public static SrcLang srcLang = null;
//    private static Tracker mTracker;
//
//    public static void addMenuItem(Menu paramMenu, String paramString) {
//        addMenuItem(paramMenu, paramString, );
//    }
//
//    public static void addMenuItem(Menu paramMenu, String paramString, int paramInt) {
//        paramMenu.add(0, paramInt, 0, DM.getL(paramString)).setShowAsAction(1);
//    }

//    public static String getAppB() {
//        return String.format("ca-app-pub-1765285543134589/%s", new Object[]{getAppCity().androidB});
//    }
//
//    public static City getAppCity() {
//        return DM.getCity("dj");
//    }
//
//    public static String getAppGA() {
//        return getAppCity().androidGA;
//    }
//
//    public static String getAppI() {
//        return String.format("ca-app-pub-1765285543134589/%s", new Object[]{getAppCity().androidI});
//    }
//
//    public static String getAppMapKey() {
//        if (isGMap()) {
//            return getAppCity().androidGMap;
//        }
//        return getAppCity().androidAMap;
//    }
//
//    public static String getAppXG() {
//        return getAppCity().androidXG;
//    }
//
//    public static String getAppXGKey() {
//        return getAppCity().androidXGKey;
//    }

    private static Intent getIntent(Activity paramActivity, String paramString) {
        Intent localIntent = new Intent();
        localIntent.setClassName(paramActivity.getPackageName(), String.format("%s.%s", new Object[]{paramActivity.getPackageName(), paramString}));
        return localIntent;
    }

//    public static Line getLine(String paramString) {
//        Object localObject = null;
//        String str = paramString.toUpperCase();
//        Iterator localIterator = DM.lineList.iterator();
//        do {
//            paramString = (String) localObject;
//            if (!localIterator.hasNext()) {
//                break;
//            }
//            paramString = (Line) localIterator.next();
//        } while (!paramString.uno.equals(str));
//        return paramString;
//    }

//    public static String getLineLang(int paramInt) {
//        return getUnoLang(DM.getLine(paramInt).uno);
//    }
//
//    public static String getLineSubLang(int paramInt) {
//        return getUnoSubLang(DM.getLine(paramInt).uno);
//    }
//
//    public static SrcUNO getLineUNO(int paramInt) {
//        return getSrcUNO(DM.getLine(paramInt).uno);
//    }
//
//    public static String getMainLang(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10, String paramString11) {
//        if (srcLang.main.equals("en")) {
//            return paramString1;
//        }
//        if (srcLang.main.equals("cn")) {
//            return paramString2;
//        }
//        if (srcLang.main.equals("tw")) {
//            return paramString3;
//        }
//        if (srcLang.main.equals("ja")) {
//            return paramString4;
//        }
//        if (srcLang.main.equals("ko")) {
//            return paramString5;
//        }
//        if (srcLang.main.equals("ru")) {
//            return paramString6;
//        }
//        if (srcLang.main.equals("fr")) {
//            return paramString7;
//        }
//        if (srcLang.main.equals("es")) {
//            return paramString8;
//        }
//        if (srcLang.main.equals("de")) {
//            return paramString9;
//        }
//        if (srcLang.main.equals("pt")) {
//            return paramString10;
//        }
//        if (srcLang.main.equals("it")) {
//            return paramString11;
//        }
//        return paramString1;
//    }
//
//    public static SrcLang getSrcLang(String paramString) {
//        Object localObject2 = null;
//        Iterator localIterator = DM.langList.iterator();
//        Object localObject1;
//        do {
//            localObject1 = localObject2;
//            if (!localIterator.hasNext()) {
//                break;
//            }
//            localObject1 = (SrcLang) localIterator.next();
//        } while (!paramString.equals(((SrcLang) localObject1).lang));
//        return (SrcLang) localObject1;
//    }
//
//    public static SrcUNO getSrcUNO(String paramString) {
//        Object localObject = null;
//        String str = paramString.toUpperCase();
//        Iterator localIterator = DM.unoList.iterator();
//        do {
//            paramString = (String) localObject;
//            if (!localIterator.hasNext()) {
//                break;
//            }
//            paramString = (SrcUNO) localIterator.next();
//        } while (!paramString.uno.equals(str));
//        return paramString;
//    }
//
//    public static Station getStation(String paramString) {
//        Object localObject = null;
//        String str = paramString.toUpperCase();
//        //Iterator localIterator = DM.stationList.iterator();
//        do {
//            paramString = (String) localObject;
//            if (!localIterator.hasNext()) {
//                break;
//            }
//            paramString = (Station) localIterator.next();
//        } while (!paramString.uno.equals(str));
//        return paramString;
//    }

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

    public static Timetable getTimeTable(String staname,String linename) {


        ArrayList<Station> staList = (ArrayList)getStation(staname);

        Timetable tmpTimetable = null;
        Iterator localSta = staList.iterator();
        while (localSta.hasNext()) {

            Station tmpSta = (Station)localSta.next();
            if(tmpSta.lineName.equals(linename)) {
                Iterator localTime = DM.weekdayList.iterator();
                while (localTime.hasNext()) {
                    tmpTimetable = (Timetable)localTime.next();
                    if(tmpTimetable.lineId.equals(tmpSta.lineId) && tmpTimetable.stationId.equals(tmpSta.stationId)){
                        break;
                    }
                }
            }
        }

        return tmpTimetable;
    }

//    public static String getStationLang(int paramInt) {
//        return getUnoLang(DM.getStation(paramInt).uno);
//    }
//
//    public static String getStationSubLang(int paramInt) {
//        return getUnoSubLang(DM.getStation(paramInt).uno);
//    }
//
//    public static SrcUNO getStationUNO(int paramInt) {
//        return getSrcUNO(DM.getStation(paramInt).uno);
//    }
//
//    public static String getSubLang(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10, String paramString11) {
//        if (srcLang.sub.equals("")) {
//            return "";
//        }
//        if (srcLang.sub.equals("null")) {
//            return "";
//        }
//        if (srcLang.sub.equals("en")) {
//            return paramString1;
//        }
//        if (srcLang.sub.equals("cn")) {
//            return paramString2;
//        }
//        if (srcLang.sub.equals("tw")) {
//            return paramString3;
//        }
//        if (srcLang.sub.equals("ja")) {
//            return paramString4;
//        }
//        if (srcLang.sub.equals("ko")) {
//            return paramString5;
//        }
//        if (srcLang.sub.equals("ru")) {
//            return paramString6;
//        }
//        if (srcLang.sub.equals("fr")) {
//            return paramString7;
//        }
//        if (srcLang.sub.equals("es")) {
//            return paramString8;
//        }
//        if (srcLang.sub.equals("de")) {
//            return paramString9;
//        }
//        if (srcLang.sub.equals("pt")) {
//            return paramString10;
//        }
//        if (srcLang.sub.equals("it")) {
//            return paramString11;
//        }
//        return paramString1;
//    }
//
//    public static String getUnoLang(String paramString) {
//        paramString = getSrcUNO(paramString);
//        return getMainLang(paramString.english, paramString.simplified, paramString.traditional, paramString.japanese, paramString.korean, paramString.russian, paramString.french, paramString.spanish, paramString.german, paramString.portuguese, paramString.italian);
//    }
//
//    public static String getUnoSubLang(String paramString) {
//        paramString = getSrcUNO(paramString);
//        return getSubLang(paramString.english, paramString.simplified, paramString.traditional, paramString.japanese, paramString.korean, paramString.russian, paramString.french, paramString.spanish, paramString.german, paramString.portuguese, paramString.italian);
//    }
//
//    public static String getUnoWikiLang(String paramString) {
//        localObject2 = "";
//        SrcUNO localSrcUNO = getSrcUNO(paramString);
//        Object localObject1 = localObject2;
//        try {
//            if (!language.equals("cn")) {
//                localObject1 = localObject2;
//                if (!language.equals("tw")) {
//                }
//            } else {
//                paramString = (String) localObject2;
//                localObject1 = localObject2;
//                if (!localSrcUNO.wikiCN.equals("null")) {
//                    localObject1 = localObject2;
//                    paramString = String.format("%s%s", new Object[]{DM.getL("LangWiki"), URLEncoder.encode(localSrcUNO.wikiCN, "UTF-8")});
//                }
//            }
//            for (; ; ) {
//                localObject1 = paramString;
//                localObject2 = paramString;
//                if (!Util.isEmpty(paramString).booleanValue()) {
//                    break;
//                }
//                localObject1 = paramString;
//                localObject2 = paramString;
//                if (localSrcUNO.wikiEN.equals("null")) {
//                    break;
//                }
//                localObject1 = paramString;
//                return String.format("%s%s", new Object[]{DM.getSL("LangWiki"), URLEncoder.encode(localSrcUNO.wikiEN, "UTF-8")});
//                localObject1 = localObject2;
//                if (language.equals("ja")) {
//                    paramString = (String) localObject2;
//                    localObject1 = localObject2;
//                    if (!localSrcUNO.wikiJA.equals("null")) {
//                        localObject1 = localObject2;
//                        paramString = String.format("%s%s", new Object[]{DM.getL("LangWiki"), URLEncoder.encode(localSrcUNO.wikiJA, "UTF-8")});
//                    }
//                } else {
//                    localObject1 = localObject2;
//                    if (language.equals("ko")) {
//                        paramString = (String) localObject2;
//                        localObject1 = localObject2;
//                        if (!localSrcUNO.wikiKO.equals("null")) {
//                            localObject1 = localObject2;
//                            paramString = String.format("%s%s", new Object[]{DM.getL("LangWiki"), URLEncoder.encode(localSrcUNO.wikiKO, "UTF-8")});
//                        }
//                    } else {
//                        paramString = (String) localObject2;
//                        localObject1 = localObject2;
//                        if (language.equals("ru")) {
//                            paramString = (String) localObject2;
//                            localObject1 = localObject2;
//                            if (!localSrcUNO.wikiRU.equals("null")) {
//                                localObject1 = localObject2;
//                                paramString = String.format("%s%s", new Object[]{DM.getL("LangWiki"), URLEncoder.encode(localSrcUNO.wikiRU, "UTF-8")});
//                            }
//                        }
//                    }
//                }
//            }
//            return (String) localObject2;
//        } catch (Exception paramString) {
//            paramString.printStackTrace();
//            localObject2 = localObject1;
//        }
//    }
//
//    public static Way getWay(String paramString) {
//        Object localObject = null;
//        String str = paramString.toUpperCase();
//        Iterator localIterator = DM.wayList.iterator();
//        do {
//            paramString = (String) localObject;
//            if (!localIterator.hasNext()) {
//                break;
//            }
//            paramString = (Way) localIterator.next();
//        } while (!paramString.uno.equals(str));
//        return paramString;
//    }
//
//    public static String getWayLang(int paramInt) {
//        return getUnoLang(DM.getWay(paramInt).uno);
//    }
//
//    public static String getWaySubLang(int paramInt) {
//        return getUnoSubLang(DM.getWay(paramInt).uno);
//    }
//
//    public static SrcUNO getWayUNO(int paramInt) {
//        return getSrcUNO(DM.getWay(paramInt).uno);
//    }
//
//    public static void initConfig(Context paramContext) {
//        SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(paramContext);
//        if (!Util.isEmpty(localSharedPreferences.getString("KEY_CITY_2015", "")).booleanValue()) {
//            if ((!isCN()) && (!"dj".equals(localSharedPreferences.getString("KEY_CITY_2015", "")))) {
//                city = "dj";
//                saveSetting(paramContext);
//            }
//            city = localSharedPreferences.getString("KEY_CITY_2015", "");
//            language = localSharedPreferences.getString("KEY_LANGUAGE_2015", "");
//            return;
//        }
//        if (isCN()) {
//        }
//        for (String str = "sh"; ; str = "dj") {
//            city = str;
//            language = Util.getOSLang();
//            saveSetting(paramContext);
//            break;
//        }
//    }

    public static void initData(Context paramContext) {
        FileUtil.context = paramContext;
        //DM.loadAppLang(language);
        //DM.loadCityList();
        //DM.unloadCity();
        //DM.loadCity(city);
        //srcLang = getSrcLang(language);

        DM.loadData("");
    }

//    public static void initGA(Context paramContext) {
//        if (mTracker == null) {
//            mTracker = GoogleAnalytics.getInstance(paramContext).newTracker(getAppGA());
//        }
//    }
//
//    public static void initParam() {
//        searchDep = null;
//        searchArr = null;
//        searchTime = null;
//        searchType = 4;
//        searchOption = 0;
//        routeList = null;
//        routeIndex = -1;
//        com.xinlukou.metromandj.RoutemapFragment.routemapState = null;
//        com.xinlukou.metromandj.StationFragment.stationText = "";
//        com.xinlukou.metromandj.MapFragment.mapPosition = null;
//        lineList = new ArrayList(DM.lineList.size());
//        lineStationList = new ArrayList(DM.lineList.size());
//        Iterator localIterator1 = DM.lineList.iterator();
//        while (localIterator1.hasNext()) {
//            Line localLine = (Line) localIterator1.next();
//            if (!Util.isEmpty(getSrcUNO(localLine.uno).py).booleanValue()) {
//                ArrayList localArrayList = new ArrayList();
//                Iterator localIterator2 = localLine.stationIDList.iterator();
//                while (localIterator2.hasNext()) {
//                    int i = ((Integer) localIterator2.next()).intValue();
//                    if (!localArrayList.contains(Integer.valueOf(i))) {
//                        localArrayList.add(Integer.valueOf(i));
//                    }
//                }
//                lineList.add(Integer.valueOf(localLine.id));
//                lineStationList.add(localArrayList);
//            }
//        }
//        adDate = null;
//    }
//
//    public static boolean isCN() {
//        return "dj".equals("cn");
//    }

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

//    public static void pushRoute(Activity paramActivity) {
//        int i = 0;
//        while (i < ((UIRoute) routeList.get(routeIndex)).detail.size()) {
//            ((UIDetail) ((UIRoute) routeList.get(routeIndex)).detail.get(i)).count = "0";
//            i += 1;
//        }
//        paramActivity.startActivity(getIntent(paramActivity, "RouteActivity"));
//    }

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

//    public static void replaceFragment(Activity paramActivity, Fragment paramFragment) {
//        paramActivity = paramActivity.getFragmentManager().beginTransaction();
//        paramActivity.replace(2131427349, paramFragment);
//        paramActivity.commit();
//    }

//    public static void resetAppCity(Activity paramActivity, String paramString) {
//        city = paramString;
//        saveSetting(paramActivity);
//        initConfig(paramActivity);
//        initData(paramActivity);
//        initParam();
//        restartApp(paramActivity);
//    }

//    public static void resetAppLang(Activity paramActivity, String paramString) {
//        language = paramString;
//        saveSetting(paramActivity);
//        initConfig(paramActivity);
//        initData(paramActivity);
//        initParam();
//        restartApp(paramActivity);
//    }

    private static void restartApp(Activity paramActivity) {
        Object localObject = paramActivity.getBaseContext().getPackageName();
        localObject = paramActivity.getBaseContext().getPackageManager().getLaunchIntentForPackage((String) localObject);
        ((Intent) localObject).addFlags(67108864);
        paramActivity.startActivity((Intent) localObject);
    }

//    public static void saveEvent(String paramString1, String paramString2, String paramString3) {
//        if (mTracker != null) {
//            mTracker.send(new HitBuilders.EventBuilder().setCategory(paramString1).setAction(paramString2).setLabel(paramString3).build());
//        }
//    }
//
//    public static void saveScreen(String paramString) {
//        if (mTracker != null) {
//            mTracker.setScreenName(paramString);
//            mTracker.send(new HitBuilders.ScreenViewBuilder().build());
//        }
//    }

    private static void saveSetting(Context paramContext) {
        PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putString("KEY_CITY_2015", city).putString("KEY_LANGUAGE_2015", language).apply();
    }

//    public static void setActionBar(Activity paramActivity, String paramString) {
//        paramActivity = paramActivity.getActionBar();
//        paramActivity.setNavigationMode(0);
//        paramActivity.setDisplayShowTitleEnabled(true);
//        paramActivity.setDisplayHomeAsUpEnabled(true);
//        if (paramString != null) {
//            paramActivity.setTitle(paramString);
//        }
//    }
}


/* Location:              C:\ljn\apk hack\jd-gui-windows-1.4.0\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\xinlukou\logic\LogicCommon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */