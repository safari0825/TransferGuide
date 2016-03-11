package jp.co.pp.transferguide;

import android.app.Application;

import jp.co.pp.transferguide.Logic.*;

public class MMApplication
        extends Application {
    public void onCreate() {
        super.onCreate();
//        LogicCommon.initConfig(this);
          LogicCommon.initData(this);
//        LogicCommon.initParam();
//        LogicCommon.initGA(this);
    }

    public void onTerminate() {
        super.onTerminate();
    }
}