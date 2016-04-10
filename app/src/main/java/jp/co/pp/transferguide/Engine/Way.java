package jp.co.pp.transferguide.Engine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liujin on 2016/4/10.
 */
public class Way {

    public int id;
    public int lineID;
    public List<String> stationIDList;

    public Way(int paramInt, String paramString)
    {
        String[] paramStrArr = paramString.split(",", -1);
        this.id = paramInt;
        this.lineID = Integer.parseInt(paramStrArr[0]);
        this.stationIDList = new ArrayList(paramStrArr.length - 1);
        paramInt = 1;
        while (paramInt < paramStrArr.length)
        {
            this.stationIDList.add(paramStrArr[paramInt]);
            paramInt += 1;
        }
    }
}
