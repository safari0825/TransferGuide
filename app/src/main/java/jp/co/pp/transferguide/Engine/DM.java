package jp.co.pp.transferguide.Engine;

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

    //public static List<Link> linkList;
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
     * @param subFolder
     * @return
     */
    private static String[] getCsv(String dataName) {
        return FileUtil.GetFileString("nj\\"+dataName).split("\r\n", -1);
    }



    private static void loadStation(String paraStaName) {
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
            stationMap.put(tmpStation.stationId,tmpStation);
        }
    }


    private static void loadLine() {
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
            lineMap.put(tmpLine.lineId,tmpLine);
        }
    }



    private static void loadWay() {
        if (wayMap == null) {
            wayMap = new HashMap<String,Way>();
        }

        String[] arrayOfString = getCsv("way");

        for(int i = 0 ; i < arrayOfString.length;i++) {
            //
            Way tmpWay = new Way(i,arrayOfString[i]);
            wayMap.put(Integer.toString(tmpWay.id),tmpWay);
        }
    }
}