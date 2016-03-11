package jp.co.pp.transferguide.Engine;

import java.util.ArrayList;
import java.util.List;

public class Line {
    public String lineId = "";

    public String lineNameJP= "";

    public String lineNamePY= "";

    public String lineNameCN = "";

    public String startStationId = "";

    public String endStationId = "";

    public String direction = "";

    public String speedType = "";

    public List<String> stationIDList;

    public Line() {

    }

    public void setStationList(String paramString) {
        String[] paramStrArr = paramString.split(",", -1);
        this.stationIDList = new ArrayList(paramStrArr.length - 1);
        int paramInt = 0;
        while (paramInt < paramStrArr.length) {
            this.stationIDList.add(paramStrArr[paramInt]);
            paramInt += 1;
        }
    }
}