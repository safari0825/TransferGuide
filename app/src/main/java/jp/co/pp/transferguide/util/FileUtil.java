package jp.co.pp.transferguide.util;

import android.content.Context;
import android.provider.ContactsContract;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class FileUtil {
    private static final String ANDROID_PATH_DATA = "data/";
    private static final String ANDROID_SPLIT = "/";
    private static final String MAC_PATH_DATA = "/Users/asusharp/Documents/data/";
    private static final String MAC_SPLIT = "/";
    private static final String WIN_PATH_DATA = "C:\\data\\";
    private static final String WIN_SPLIT = "\\";
    public static int DEVICE_TYPE = 3;   //1:WIN 2:MAC 3:Android
    public static Object context = null;

//    public static int[][] GetFileIntArray(String paramString1, String paramString2, int paramInt1, int paramInt2) {
//        int[][] arrayOfInt = (int[][]) Array.newInstance(Integer.TYPE, new int[]{paramInt1, paramInt2});
//        paramString1 = GetFileString(paramString1, paramString2);
//        int i1 = paramString1.length();
//        int i = 0;
//        int m = 0;
//        int j = 0;
//        int k = 0;
//        if (i < i1) {
//            int i2 = paramString1.charAt(i);
//            paramInt2 = k;
//            int n = j;
//            paramInt1 = m;
//            switch (i2) {
//                default:
//                    paramInt1 = m * 10 + (i2 - 48);
//                    n = j;
//                    paramInt2 = k;
//            }
//            for (; ; ) {
//                i += 1;
//                k = paramInt2;
//                j = n;
//                m = paramInt1;
//                //break;
//
//                arrayOfInt[j][k] = m;
//                paramInt1 = 0;
//                paramInt2 = 0;
//                n = j + 1;
//                //continue;
//                arrayOfInt[j][k] = m;
//                paramInt1 = 0;
//                paramInt2 = k + 1;
//                n = j;
//            }
//        }
//        return arrayOfInt;
//    }

    /**
     * ??刻表文件内容
     * @param dataName
     * @return
     */
    public static String GetFileString(String dataName) {

        //
        ArrayList<String> filePathArr = GetPathData(dataName);
        StringBuilder strBuilder = new StringBuilder();
        for ( int i = 0 ;  i < filePathArr.size();i++) {
            try {
                InputStream fin = ((Context) context).getAssets().open(filePathArr.get(i).toString());
                BufferedReader fileReader = new BufferedReader(new InputStreamReader(fin, "UTF-8"));

                String str = "";
                while ((str = fileReader.readLine()) != null) {
                    strBuilder.append(str+"\r\n");
                }
                fileReader.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return strBuilder.toString();
    }




    /**
     * ?刻表数据文件的路径取得
     * DEVICE_TYPE : 1:WIN 2:MAC 3:Android
     * params : subFolder 数据文件子文件?名。?京；dj
     */
    public static ArrayList GetPathData(String subFolder) {
        String str2 = "";
        String str1 = subFolder;

        if (DEVICE_TYPE == 1) {
            str2 = "C:\\data\\";
            str1 = str1 + "\\";
        }
        if (DEVICE_TYPE == 2) {
            str2 = "/Users/asusharp/Documents/data/";
            str1 = str1 + "/";
        } else if (DEVICE_TYPE == 3) {
            str2 = "data/";  //assert folderに保存している。
            str1 = str1 ;
        }

        ArrayList<String> rtnStr = new ArrayList<String>();
        try {
            /*
            String[] pathList = ((Context) context).getAssets().list(str2 + str1+".csv");
            for (int i = 0; i < pathList.length; i++) {
                    rtnStr.add(str2+str1+"/"+pathList[i]);

            }*/
            rtnStr.add(str2 + str1+".csv");
        }catch (Exception e) {}
        return rtnStr;
    }
}