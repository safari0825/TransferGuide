package jp.co.pp.transferguide.Engine;

/**
 * Created by liujin on 2016/4/10.
 */
public class Link {

    public int averageTime;
    public int distance;
    public int endWeekdayID;
    public int endWeekendID;
    public int fromStationID;
    public int id;
    public int startWeekdayID;
    public int startWeekendID;
    public int toStationID;
    public int wayID;

    public Link(int paramInt, String paramString)
    {
        String[] paramStrArr = paramString.split(",", -1);
        this.id = paramInt;
        this.wayID = Integer.parseInt(paramStrArr[0]);
        this.fromStationID = Integer.parseInt(paramStrArr[1]);
        this.toStationID = Integer.parseInt(paramStrArr[2]);
        this.averageTime = Integer.parseInt(paramStrArr[3]);
        this.distance = Integer.parseInt(paramStrArr[4]);
        this.startWeekdayID = Integer.parseInt(paramStrArr[5]);
        this.endWeekdayID = Integer.parseInt(paramStrArr[6]);
        this.startWeekendID = Integer.parseInt(paramStrArr[7]);
        this.endWeekendID = Integer.parseInt(paramStrArr[8]);
    }
}
