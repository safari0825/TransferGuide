package jp.co.pp.transferguide.util;

import android.app.AlertDialog;
import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Util {
    public static String InputStreamToString(InputStream paramInput)
            throws IOException {
        BufferedReader paramInputStream = new BufferedReader(new InputStreamReader(paramInput, "UTF-8"));
        StringBuilder localStringBuilder = new StringBuilder();
        for (; ; ) {
            String str = paramInputStream.readLine();
            if (str == null) {
                break;
            }
            localStringBuilder.append(str);
        }
        paramInputStream.close();
        return localStringBuilder.toString();
    }

    public static String date2string(Date paramDate, String paramString) {
        return new SimpleDateFormat(paramString).format(paramDate);
    }

    public static AlertDialog.Builder getAlert(Context paramContext, String paramString) {
        return new AlertDialog.Builder(paramContext).setTitle(paramString);
    }

    public static String getOSLang() {
        String str1 = Locale.getDefault().getLanguage().toLowerCase();
        String str2 = Locale.getDefault().getCountry().toLowerCase();
        if ("en".equals(str1)) {
            return "en";
        }
        if ("zh".equals(str1)) {
            if ("cn".equals(str2)) {
                return "cn";
            }
            return "tw";
        }
        if ("ja".equals(str1)) {
            return "ja";
        }
        if ("ko".equals(str1)) {
            return "ko";
        }
        if ("ru".equals(str1)) {
            return "ru";
        }
        if ("fr".equals(str1)) {
            return "fr";
        }
        if ("es".equals(str1)) {
            return "es";
        }
        if ("de".equals(str1)) {
            return "de";
        }
        if ("pt".equals(str1)) {
            return "pt";
        }
        if ("it".equals(str1)) {
            return "it";
        }
        return "en";
    }

    public static String getUrlString(String paramString) {
        try {
            paramString = InputStreamToString(((HttpURLConnection) new URL(paramString).openConnection()).getInputStream());
            return paramString;
        } catch (Exception e) {
        }
        return "";
    }

    public static Boolean isEmpty(String paramString) {
        if ((paramString == null) || (paramString.length() == 0)) {
        }
        for (boolean bool = true; ; bool = false) {
            return Boolean.valueOf(bool);
        }
    }

    public static boolean isLocationAvailable(Context paramContext) {
        try {
            LocationManager lm = (LocationManager) paramContext.getSystemService(paramContext.LOCATION_SERVICE);
            if (lm.isProviderEnabled("network")) {
                boolean bool = lm.isProviderEnabled("gps");
                if (bool) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
        }
        return false;
    }

    public static boolean isNetworkAvailable(Context paramContext) {
        try {
            NetworkInfo netInfo = ((ConnectivityManager) paramContext.getSystemService(paramContext.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
            return netInfo != null;
        } catch (Exception e) {
        }
        return false;
    }

    public static String joinInt(List<Integer> paramList, String paramString) {
        StringBuffer localStringBuffer = new StringBuffer();
        if ((paramList != null) && (paramList.size() > 0)) {
            localStringBuffer.append(Integer.toString(((Integer) paramList.get(0)).intValue()));
            int i = 1;
            while (i < paramList.size()) {
                localStringBuffer.append(paramString);
                localStringBuffer.append(Integer.toString(((Integer) paramList.get(i)).intValue()));
                i += 1;
            }
        }
        return localStringBuffer.toString();
    }

    public static String joinStr(List<String> paramList, String paramString) {
        StringBuffer localStringBuffer = new StringBuffer();
        if ((paramList != null) && (paramList.size() > 0)) {
            localStringBuffer.append((String) paramList.get(0));
            int i = 1;
            while (i < paramList.size()) {
                localStringBuffer.append(paramString);
                localStringBuffer.append((String) paramList.get(i));
                i += 1;
            }
        }
        return localStringBuffer.toString();
    }

    public static void showMsg(Context paramContext, String paramString1, String paramString2) {
        getAlert(paramContext, paramString1).setMessage(paramString2).setPositiveButton("OK", null).show();
    }

    public static List<Integer> split2Int(String paramString) {
        ArrayList localArrayList = new ArrayList();
        String[] paramStrArr ;
        if (!isEmpty(paramString).booleanValue()) {
            paramStrArr = paramString.split(",", -1);
            int j = paramStrArr.length;
            int i = 0;
            while (i < j) {
                localArrayList.add(Integer.valueOf(Integer.parseInt(paramStrArr[i])));
                i += 1;
            }
        }
        return localArrayList;
    }
}