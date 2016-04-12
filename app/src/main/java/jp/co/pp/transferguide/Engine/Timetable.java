package jp.co.pp.transferguide.Engine;

import java.util.ArrayList;
import java.util.List;

public class Timetable {
    public String wayId = "";
    public String stationId = "";
    public String dateType  = "";
    public List<String> depTime ;

    public Timetable() {

    }
    public void setDepTime(String p4) {
        String[] paramStrArr = p4.split(",", -1);
        this.depTime = new ArrayList(paramStrArr.length - 1);
        int paramInt = 0;
        while (paramInt < paramStrArr.length) {
            this.depTime.add(paramStrArr[paramInt]);
            paramInt += 1;
        }
    }
}