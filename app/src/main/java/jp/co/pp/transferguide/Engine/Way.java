package jp.co.pp.transferguide.Engine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liujin on 2016/4/10.
 */
public class Way {

    public String wayId;
    public String lineID;
    public String wayName = "";
    public List<String> stationIDList;

    public Way(int paramInt, String paramString)
    {
        String[] paramStrArr = paramString.split(",", -1);
        this.wayId = Integer.toString(paramInt);
        this.lineID = paramStrArr[0];
        this.wayName = paramStrArr[1];
        this.stationIDList = new ArrayList(paramStrArr.length - 2);
        paramInt = 2;
        while (paramInt < paramStrArr.length)
        {
            this.stationIDList.add(paramStrArr[paramInt]);
            paramInt += 1;
        }
    }
}
