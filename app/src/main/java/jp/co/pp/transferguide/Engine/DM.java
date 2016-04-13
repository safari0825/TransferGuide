package jp.co.pp.transferguide.Engine;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import jp.co.pp.transferguide.util.FileUtil;
import jp.co.pp.transferguide.util.Util;

public class DM {
//    public static City city;
//    public static List<City> cityList;
//    public static List<SrcConfig> configList;
//    public static List<SrcFare> fareList;
//    public static List<String> holidayList;
//    public static List<String> langCDList;
//    public static List<SrcLang> langList;
//    public static List<String> langNameList;
    public static HashMap<String,Line> lineMap;

    public static List<Link> linkList;
    //public static HashMap<String, String> mainLangList;
    public static HashMap<String,Station> stationMap;


    public static HashMap<String,Way> wayMap;

    //public static HashMap<String, String> subLangList;
//    public static List<Transfer> transferFromList;
//    public static List<Transfer> transferToList;
//    public static List<SrcUNO> unoList;
//    public static List<Way> wayList;
    public static List<Timetable> weekdayList;
    public static List<Timetable> weekendList;

    public static LinkedList<Graph.Node> path ;

    public static void getPath(Graph.Node start, Graph.Node end) {

        //get node info and add node to graph
        ArrayList<Graph.Node> nodes = new ArrayList<Graph.Node>();


        //get edge info and add edge to grash
        ArrayList<Graph.Edge> edges = new ArrayList<Graph.Edge>();

        //get the shortest path
        Graph graph = new Graph(nodes, edges);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        dijkstra.execute(start);
        LinkedList<Graph.Node> path = dijkstra.getPath(end);
    }

    /**
     * get CSV data contance.
     * @param dataName
     * @return
     */
    private static String[] getCsv(String dataName) {
        return FileUtil.GetFileString("nj/"+dataName).split("\r\n", -1);
    }



    public static void loadStation() {
        if (stationMap != null) {
            return;
        }else {
            stationMap = new HashMap<String,Station>();
        }

        String[] arrayOfString = getCsv("station");
        Station tmpStation ;
        for(int i = 0 ; i < arrayOfString.length;i++) {

            tmpStation = new Station();
            tmpStation.stationId = Integer.toString(i);
            tmpStation.stationNameCN = arrayOfString[i];
            stationMap.put(tmpStation.stationNameCN,tmpStation);
        }
    }


    public static void loadLine() {
        if (lineMap == null) {
            lineMap = new HashMap<String,Line>();
        }

        String[] arrayOfString = getCsv("line");

        for(int i = 0 ; i < arrayOfString.length;i++) {
            //
            Line tmpLine = new Line();
            String[] strTmp = arrayOfString[i].split(",");

            tmpLine.lineId = Integer.toString(i);
            tmpLine.lineNameCN = strTmp[0];

            ArrayList stationList = new ArrayList();
            for ( int j =  1; j < strTmp.length;j++) {
                stationList.add(strTmp[j]);
            }
            tmpLine.stationIDList = stationList;
            lineMap.put(tmpLine.lineNameCN,tmpLine);
        }
    }



    public static void loadWay() {
        if (wayMap == null) {
            wayMap = new HashMap<String,Way>();
        }
        String[] arrayOfString = getCsv("way");

        for(int i = 0 ; i < arrayOfString.length;i++) {
            //
            if(!arrayOfString[i].isEmpty()) {
                Way tmpWay = new Way(i, arrayOfString[i]);
                wayMap.put(tmpWay.wayId, tmpWay);
            }
        }
    }


    public static void loadTimeTable() {
        if (weekdayList == null) {
            weekdayList = new ArrayList<Timetable>();
        }

        String[] arrayOfString = getCsv("timetable");

        //create TimeTable
        for(int i = 0 ; i < arrayOfString.length;i++) {

            if (arrayOfString[i].isEmpty() ) continue;

            String[] strTmp = arrayOfString[i].split(",");
            //
            String wayId = strTmp[0];

            //find all station within the way
            if (wayMap != null) {
                Way tempWay = wayMap.get(wayId);
                for(int j = 0 ; j < tempWay.stationIDList.size();j++) {
                    Timetable tempTimeTable = new Timetable();
                    tempTimeTable.wayId = wayId;
                    tempTimeTable.stationId = tempWay.stationIDList.get(j);

                    //发车时间表的作成
                    ArrayList depTime = new ArrayList();

                    String firstTime = strTmp[1];
                    String lastTime = strTmp[2];

                    int timeInt = Integer.parseInt(firstTime.substring(0, 2)) * 60 + Integer.parseInt(firstTime.substring(2,4));

                    int lastTimeInt = Integer.parseInt(lastTime.substring(0,2)) * 60 + Integer.parseInt(lastTime.substring(2,4));

                    if(j > 0 ) {
                        for ( int k = 0 ; k < j - 1 ;k++) {
                            timeInt = timeInt + Integer.parseInt(strTmp[3+k]);
                            lastTimeInt = lastTimeInt + Integer.parseInt(strTmp[3+k]);
                        }
                    }

                    while (timeInt < lastTimeInt) {
                        String timeTmp = timeInt / 60  + ":" + timeInt % 60;
                        depTime.add(timeTmp);

                        //假设发车间隔时间10分钟
                        timeInt += 10;
                    }
                    weekdayList.add(tempTimeTable);
                    // 到此为止一个车站的时刻表做好了
                }
                //到此为止所有车站的时刻表都做好了。
            }
        }
    }


    public static void loadLink() {

        if(linkList == null) {
            linkList = new ArrayList<Link>();
        }

        String[] arrayOfString = getCsv("timetable");

        //create TimeTable
        for(int i = 0 ; i < arrayOfString.length;i++) {

            if (arrayOfString[i].isEmpty() ) continue;

            String[] strTmp = arrayOfString[i].split(",");
            //
            String wayId = strTmp[0];

            //find all station within the way
            if (wayMap != null) {
                Way tempWay = wayMap.get(wayId);
                for(int j = 0 ; j < tempWay.stationIDList.size() - 2;j++) {
                    Link tempLink = new Link();
                    tempLink.wayID = wayId;
                    tempLink.fromStationID = tempWay.stationIDList.get(j);
                    tempLink.toStationID = tempWay.stationIDList.get(j + 1);

                    tempLink.averageTime = Integer.parseInt(strTmp[2 + j]);

                    linkList.add(tempLink);
                }
            }
        }
    }
}