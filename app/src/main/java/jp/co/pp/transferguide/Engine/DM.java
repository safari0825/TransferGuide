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

//    public static List<TransferLink> getArrTransferLinks(int paramInt) {
//        ArrayList localArrayList = new ArrayList();
//        Iterator localIterator = linkList.iterator();
//        while (localIterator.hasNext()) {
//            Link localLink = (Link) localIterator.next();
//            if (localLink.toStationID == paramInt) {
//                localArrayList.add(new TransferLink(-1, localLink.id));
//            }
//        }
//        return localArrayList;
//    }

//    private static Timetable getArrWeekday(int paramInt1, int paramInt2, int paramInt3) {
//        if (paramInt2 == paramInt3) {
//            return getWeekday(paramInt2);
//        }
//        int i = (paramInt2 + paramInt3) / 2;
//        int j = paramInt3;
//        paramInt3 = paramInt2;
//        paramInt2 = i;
//        Timetable localTimetable = getWeekday(paramInt2);
//        int k;
//        if (paramInt1 > localTimetable.arrTime) {
//            k = j;
//            i = paramInt2;
//        }
//        for (; ; ) {
//            int m = (i + k) / 2;
//            paramInt2 = m;
//            paramInt3 = i;
//            j = k;
//            if (k - i > 1) {
//                break;
//            }
//            do {
//                localTimetable = getWeekday(m);
//                if (paramInt1 != localTimetable.arrTime) {
//                    break;
//                }
//                return localTimetable;
//                m = paramInt2;
//                i = paramInt3;
//            } while (paramInt1 >= localTimetable.arrTime);
//            i = paramInt3;
//            k = paramInt2;
//        }
//        return getWeekday(i);
//    }
//
//    private static Timetable getArrWeekend(int paramInt1, int paramInt2, int paramInt3) {
//        if (paramInt2 == paramInt3) {
//            return getWeekend(paramInt2);
//        }
//        int i = (paramInt2 + paramInt3) / 2;
//        int j = paramInt3;
//        paramInt3 = paramInt2;
//        paramInt2 = i;
//        Timetable localTimetable = getWeekend(paramInt2);
//        int k;
//        if (paramInt1 > localTimetable.arrTime) {
//            k = j;
//            i = paramInt2;
//        }
//        for (; ; ) {
//            int m = (i + k) / 2;
//            paramInt2 = m;
//            paramInt3 = i;
//            j = k;
//            if (k - i > 1) {
//                break;
//            }
//            do {
//                localTimetable = getWeekend(m);
//                if (paramInt1 != localTimetable.arrTime) {
//                    break;
//                }
//                return localTimetable;
//                m = paramInt2;
//                i = paramInt3;
//            } while (paramInt1 >= localTimetable.arrTime);
//            i = paramInt3;
//            k = paramInt2;
//        }
//        return getWeekend(i);
//    }

//    public static Timetable getBestTimetableFrom(int paramInt1, int paramInt2, Date[] paramArrayOfDate) {
//        Object localObject = getLink(paramInt2);
//        if (paramInt1 != -1) {
//            Transfer localTransfer = getTransferFrom(paramInt1);
//            if (localTransfer.transType != 0) {
//                paramArrayOfDate[0] = Util.addMin(paramArrayOfDate[0], localTransfer.transTime);
//            }
//        }
//        if (Util.diffMin(paramArrayOfDate[0], Condition.searchDT) > 1440) {
//            localObject = null;
//            return (Timetable) localObject;
//        }
//        int i = Util.diffMin(paramArrayOfDate[0], Condition.date2015) % 1440;
//        paramInt1 = i;
//        if (i < 120) {
//            paramInt1 = i + 1440;
//        }
//        if ((((Link) localObject).startWeekdayID == -1) && (((Link) localObject).endWeekdayID == -1) && (((Link) localObject).startWeekendID == -1) && (((Link) localObject).endWeekendID == -1)) {
//            return new Timetable(-1, paramInt2, paramInt1, ((Link) localObject).averageTime + paramInt1);
//        }
//        if ((((Link) localObject).startWeekdayID == -1) || (((Link) localObject).endWeekdayID == -1) || (((Link) localObject).startWeekendID == -1) || (((Link) localObject).endWeekendID == -1)) {
//            if ((!Condition.isHoliday(paramArrayOfDate[0])) && (((Link) localObject).startWeekdayID == -1)) {
//                return null;
//            }
//            if ((Condition.isHoliday(paramArrayOfDate[0])) && (((Link) localObject).startWeekendID == -1)) {
//                return null;
//            }
//        }
//        if (!Condition.isHoliday(paramArrayOfDate[0])) {
//            if (paramInt1 < getWeekday(((Link) localObject).startWeekdayID).depTime) {
//                localObject = getWeekday(((Link) localObject).startWeekdayID);
//                paramArrayOfDate[0] = Util.addMin(paramArrayOfDate[0], ((Timetable) localObject).depTime - paramInt1);
//            }
//            label254:
//            while (Util.diffMin(paramArrayOfDate[0], Condition.searchDT) > 1440) {
//                return null;
//                if (paramInt1 <= getWeekday(((Link) localObject).endWeekdayID).depTime) {
//                    localObject = getDepWeekday(paramInt1, ((Link) localObject).startWeekdayID, ((Link) localObject).endWeekdayID);
//                    paramArrayOfDate[0] = Util.addMin(paramArrayOfDate[0], ((Timetable) localObject).depTime - paramInt1);
//                } else {
//                    if (Condition.isHoliday(Util.addMin(paramArrayOfDate[0], 1440))) {
//                        break label361;
//                    }
//                }
//            }
//            for (localObject = getWeekday(((Link) localObject).startWeekdayID); ; localObject = getWeekend(((Link) localObject).startWeekendID)) {
//                paramArrayOfDate[0] = Util.addMin(paramArrayOfDate[0], ((Timetable) localObject).depTime + 1440 - paramInt1);
//                break label254;
//                break;
//                label361:
//                if (((Link) localObject).startWeekendID == -1) {
//                    return null;
//                }
//            }
//        }
//        if (paramInt1 < getWeekend(((Link) localObject).startWeekendID).depTime) {
//            localObject = getWeekend(((Link) localObject).startWeekendID);
//            paramArrayOfDate[0] = Util.addMin(paramArrayOfDate[0], ((Timetable) localObject).depTime - paramInt1);
//        }
//        for (; ; ) {
//            label419:
//            if (Util.diffMin(paramArrayOfDate[0], Condition.searchDT) > 1440) {
//                return null;
//                if (paramInt1 <= getWeekend(((Link) localObject).endWeekendID).depTime) {
//                    localObject = getDepWeekend(paramInt1, ((Link) localObject).startWeekendID, ((Link) localObject).endWeekendID);
//                    paramArrayOfDate[0] = Util.addMin(paramArrayOfDate[0], ((Timetable) localObject).depTime - paramInt1);
//                } else {
//                    if (Condition.isHoliday(Util.addMin(paramArrayOfDate[0], 1440))) {
//                        break label536;
//                    }
//                    if (((Link) localObject).startWeekdayID == -1) {
//                        return null;
//                    }
//                }
//            }
//        }
//        label536:
//        for (localObject = getWeekday(((Link) localObject).startWeekdayID); ; localObject = getWeekend(((Link) localObject).startWeekendID)) {
//            paramArrayOfDate[0] = Util.addMin(paramArrayOfDate[0], ((Timetable) localObject).depTime + 1440 - paramInt1);
//            break label419;
//            break;
//        }
//    }
//
//    public static Timetable getBestTimetableTo(int paramInt1, int paramInt2, Date[] paramArrayOfDate) {
//        Object localObject = getLink(paramInt2);
//        if (paramInt1 != -1) {
//            Transfer localTransfer = getTransferTo(paramInt1);
//            if (localTransfer.transType != 0) {
//                paramArrayOfDate[0] = Util.addMin(paramArrayOfDate[0], -localTransfer.transTime);
//            }
//        }
//        if (Util.diffMin(Condition.searchDT, paramArrayOfDate[0]) > 1440) {
//            localObject = null;
//            return (Timetable) localObject;
//        }
//        int i = Util.diffMin(paramArrayOfDate[0], Condition.date2015) % 1440;
//        paramInt1 = i;
//        if (i < 120) {
//            paramInt1 = i + 1440;
//        }
//        if ((((Link) localObject).startWeekdayID == -1) && (((Link) localObject).endWeekdayID == -1) && (((Link) localObject).startWeekendID == -1) && (((Link) localObject).endWeekendID == -1)) {
//            return new Timetable(-1, paramInt2, paramInt1 - ((Link) localObject).averageTime, paramInt1);
//        }
//        if ((((Link) localObject).startWeekdayID == -1) || (((Link) localObject).endWeekdayID == -1) || (((Link) localObject).startWeekendID == -1) || (((Link) localObject).endWeekendID == -1)) {
//            if ((!Condition.isHoliday(paramArrayOfDate[0])) && (((Link) localObject).startWeekdayID == -1)) {
//                return null;
//            }
//            if ((Condition.isHoliday(paramArrayOfDate[0])) && (((Link) localObject).startWeekendID == -1)) {
//                return null;
//            }
//        }
//        if (!Condition.isHoliday(paramArrayOfDate[0])) {
//            if (paramInt1 >= getWeekday(((Link) localObject).endWeekdayID).arrTime) {
//                localObject = getWeekday(((Link) localObject).endWeekdayID);
//                paramArrayOfDate[0] = Util.addMin(paramArrayOfDate[0], ((Timetable) localObject).arrTime - paramInt1);
//            }
//            label255:
//            while (Util.diffMin(Condition.searchDT, paramArrayOfDate[0]) > 1440) {
//                return null;
//                if (paramInt1 >= getWeekday(((Link) localObject).startWeekdayID).arrTime) {
//                    localObject = getArrWeekday(paramInt1, ((Link) localObject).startWeekdayID, ((Link) localObject).endWeekdayID);
//                    paramArrayOfDate[0] = Util.addMin(paramArrayOfDate[0], ((Timetable) localObject).arrTime - paramInt1);
//                } else {
//                    if (Condition.isHoliday(Util.addMin(paramArrayOfDate[0], 64096))) {
//                        break label362;
//                    }
//                }
//            }
//            for (localObject = getWeekday(((Link) localObject).endWeekdayID); ; localObject = getWeekend(((Link) localObject).endWeekendID)) {
//                paramArrayOfDate[0] = Util.addMin(paramArrayOfDate[0], ((Timetable) localObject).arrTime - 1440 - paramInt1);
//                break label255;
//                break;
//                label362:
//                if (((Link) localObject).endWeekendID == -1) {
//                    return null;
//                }
//            }
//        }
//        if (paramInt1 >= getWeekend(((Link) localObject).endWeekendID).arrTime) {
//            localObject = getWeekend(((Link) localObject).endWeekendID);
//            paramArrayOfDate[0] = Util.addMin(paramArrayOfDate[0], ((Timetable) localObject).arrTime - paramInt1);
//        }
//        for (; ; ) {
//            label420:
//            if (Util.diffMin(Condition.searchDT, paramArrayOfDate[0]) > 1440) {
//                return null;
//                if (paramInt1 >= getWeekend(((Link) localObject).startWeekendID).arrTime) {
//                    localObject = getArrWeekend(paramInt1, ((Link) localObject).startWeekendID, ((Link) localObject).endWeekendID);
//                    paramArrayOfDate[0] = Util.addMin(paramArrayOfDate[0], ((Timetable) localObject).arrTime - paramInt1);
//                } else {
//                    if (Condition.isHoliday(Util.addMin(paramArrayOfDate[0], 64096))) {
//                        break label537;
//                    }
//                    if (((Link) localObject).endWeekdayID == -1) {
//                        return null;
//                    }
//                }
//            }
//        }
//        label537:
//        for (localObject = getWeekday(((Link) localObject).endWeekdayID); ; localObject = getWeekend(((Link) localObject).endWeekendID)) {
//            paramArrayOfDate[0] = Util.addMin(paramArrayOfDate[0], ((Timetable) localObject).arrTime - 1440 - paramInt1);
//            break label420;
//            break;
//        }
//    }
//
//    public static City getCity(String paramString) {
//        Object localObject2 = null;
//        Iterator localIterator = cityList.iterator();
//        Object localObject1;
//        do {
//            localObject1 = localObject2;
//            if (!localIterator.hasNext()) {
//                break;
//            }
//            localObject1 = (City) localIterator.next();
//        } while (!paramString.equals(((City) localObject1).cityKey));
//        return (City) localObject1;
//    }
//
//    public static List<TransferLink> getConnectFromTransferLinks(int paramInt) {
//        ArrayList localArrayList = new ArrayList();
//        Link localLink1 = getLink(paramInt);
//        if ((localLink1.startTransferFromID != -1) && (localLink1.endTransferFromID != -1)) {
//            paramInt = localLink1.startTransferFromID;
//            while (paramInt <= localLink1.endTransferFromID) {
//                Transfer localTransfer = getTransferFrom(paramInt);
//                Link localLink2 = getLink(localTransfer.toLinkID);
//                if (localLink1.fromStationID != localLink2.toStationID) {
//                    localArrayList.add(new TransferLink(localTransfer.id, localLink2.id));
//                }
//                paramInt += 1;
//            }
//        }
//        return localArrayList;
//    }
//
//    public static List<TransferLink> getConnectToTransferLinks(int paramInt) {
//        ArrayList localArrayList = new ArrayList();
//        Link localLink1 = getLink(paramInt);
//        if ((localLink1.startTransferToID != -1) && (localLink1.endTransferToID != -1)) {
//            paramInt = localLink1.startTransferToID;
//            while (paramInt <= localLink1.endTransferToID) {
//                Transfer localTransfer = getTransferTo(paramInt);
//                Link localLink2 = getLink(localTransfer.fromLinkID);
//                if (localLink1.toStationID != localLink2.fromStationID) {
//                    localArrayList.add(new TransferLink(localTransfer.id, localLink2.id));
//                }
//                paramInt += 1;
//            }
//        }
//        return localArrayList;
//    }


    /**
     * get CSV data contance.
     * @param subFolder
     * @return
     */
    private static String[] getCsv(String subFolder) {

        String curCity = subFolder;
        //TODO: �Ƃ肠�����S�t�@�C����ǂݍ��ށB����x��ƕ������ʂ��܂��B
        return FileUtil.GetFileString(curCity).split("\r\n", -1);
    }

//    private static int[][] getCsv(String paramString, int paramInt1, int paramInt2) {
//        return FileUtil.GetFileIntArray(city.cityKey, getCsvFileName(paramString), paramInt1, paramInt2);
//    }

//    private static String getCsvFileName(String paramString) {
//        StringBuilder localStringBuilder = new StringBuilder().append(paramString);
//        if (paramString.endsWith(".csv")) {
//        }
//        for (paramString = ""; ; paramString = ".csv") {
//            return paramString;
//        }
//    }

//    public static List<TransferLink> getDepTransferLinks(int paramInt) {
//        ArrayList localArrayList = new ArrayList();
//        Iterator localIterator = linkList.iterator();
//        while (localIterator.hasNext()) {
//            Link localLink = (Link) localIterator.next();
//            if (localLink.fromStationID == paramInt) {
//                localArrayList.add(new TransferLink(-1, localLink.id));
//            }
//        }
//        return localArrayList;
//    }

//    private static Timetable getDepWeekday(int paramInt1, int paramInt2, int paramInt3) {
//        if (paramInt2 == paramInt3) {
//            return getWeekday(paramInt2);
//        }
//        int i = (paramInt2 + paramInt3) / 2;
//        int j = paramInt2;
//        paramInt2 = i;
//        Timetable localTimetable = getWeekday(paramInt2);
//        int k;
//        if (paramInt1 > localTimetable.depTime) {
//            i = paramInt3;
//            k = paramInt2;
//        }
//        for (; ; ) {
//            int m = (k + i) / 2;
//            paramInt2 = m;
//            j = k;
//            paramInt3 = i;
//            if (i - k > 1) {
//                break;
//            }
//            paramInt3 = i;
//            do {
//                localTimetable = getWeekday(m);
//                if (paramInt1 != localTimetable.depTime) {
//                    break;
//                }
//                return localTimetable;
//                m = paramInt2;
//            } while (paramInt1 >= localTimetable.depTime);
//            k = j;
//            i = paramInt2;
//        }
//        return getWeekday(paramInt3);
//    }
//
//    private static Timetable getDepWeekend(int paramInt1, int paramInt2, int paramInt3) {
//        if (paramInt2 == paramInt3) {
//            return getWeekend(paramInt2);
//        }
//        int i = (paramInt2 + paramInt3) / 2;
//        int j = paramInt2;
//        paramInt2 = i;
//        Timetable localTimetable = getWeekend(paramInt2);
//        int k;
//        if (paramInt1 > localTimetable.depTime) {
//            i = paramInt3;
//            k = paramInt2;
//        }
//        for (; ; ) {
//            int m = (k + i) / 2;
//            paramInt2 = m;
//            j = k;
//            paramInt3 = i;
//            if (i - k > 1) {
//                break;
//            }
//            paramInt3 = i;
//            do {
//                localTimetable = getWeekend(m);
//                if (paramInt1 != localTimetable.depTime) {
//                    break;
//                }
//                return localTimetable;
//                m = paramInt2;
//            } while (paramInt1 >= localTimetable.depTime);
//            k = j;
//            i = paramInt2;
//        }
//        return getWeekend(paramInt3);
//    }
//
//    public static int getFirstDepTime(int paramInt, String paramString) {
//        int i = -1;
//        boolean bool = holidayList.contains(paramString);
//        paramString = linkList.iterator();
//        while (paramString.hasNext()) {
//            Link localLink = (Link) paramString.next();
//            if (localLink.fromStationID == paramInt) {
//                int j = getFirstTime(localLink, bool);
//                if ((j != -1) && ((i == -1) || (i > j))) {
//                    i = j;
//                }
//            }
//        }
//        return i;
//    }
//
//    private static int getFirstTime(Link paramLink, boolean paramBoolean) {
//        int i = -1;
//        int j;
//        if (getWay(paramLink.wayID).waitTime == 0) {
//            List localList = getConnectFromTransferLinks(paramLink.id);
//            int k = 0;
//            j = i;
//            if (k < localList.size()) {
//                Object localObject2 = (TransferLink) localList.get(k);
//                Object localObject1 = getLink(((TransferLink) localObject2).linkID);
//                localObject2 = getTransferFrom(((TransferLink) localObject2).preTransferID);
//                if (paramBoolean) {
//                }
//                for (localObject1 = getWeekend(((Link) localObject1).startWeekendID); ; localObject1 = getWeekday(((Link) localObject1).startWeekdayID)) {
//                    int m = ((Timetable) localObject1).depTime - ((Transfer) localObject2).transTime - paramLink.averageTime;
//                    if (i != -1) {
//                        j = i;
//                        if (i <= m) {
//                        }
//                    } else {
//                        j = m;
//                    }
//                    k += 1;
//                    i = j;
//                    break;
//                }
//            }
//        } else {
//            if (!paramBoolean) {
//                break label179;
//            }
//            j = i;
//            if (paramLink.startWeekendID != -1) {
//                j = getWeekend(paramLink.startWeekendID).depTime;
//            }
//        }
//        label179:
//        do {
//            return j;
//            j = i;
//        } while (paramLink.startWeekdayID == -1);
//        return getWeekday(paramLink.startWeekdayID).depTime;
//    }
//
//    public static String getL(String paramString) {
//        return (String) mainLangList.get(paramString);
//    }
//
//    public static int getLastArrTime(int paramInt, String paramString) {
//        int i = -1;
//        boolean bool = holidayList.contains(paramString);
//        paramString = linkList.iterator();
//        while (paramString.hasNext()) {
//            Link localLink = (Link) paramString.next();
//            if (localLink.toStationID == paramInt) {
//                int j = getLastTime(localLink, bool);
//                if ((j != -1) && ((i == -1) || (i < j))) {
//                    i = j;
//                }
//            }
//        }
//        return i;
//    }
//
//    private static int getLastTime(Link paramLink, boolean paramBoolean) {
//        int i = -1;
//        int j;
//        if (getWay(paramLink.wayID).waitTime == 0) {
//            List localList = getConnectToTransferLinks(paramLink.id);
//            int k = 0;
//            j = i;
//            if (k < localList.size()) {
//                Object localObject2 = (TransferLink) localList.get(k);
//                Object localObject1 = getLink(((TransferLink) localObject2).linkID);
//                localObject2 = getTransferTo(((TransferLink) localObject2).preTransferID);
//                if (paramBoolean) {
//                }
//                for (localObject1 = getWeekend(((Link) localObject1).endWeekendID); ; localObject1 = getWeekday(((Link) localObject1).endWeekdayID)) {
//                    int m = ((Timetable) localObject1).arrTime + ((Transfer) localObject2).transTime + paramLink.averageTime;
//                    if (i != -1) {
//                        j = i;
//                        if (i >= m) {
//                        }
//                    } else {
//                        j = m;
//                    }
//                    k += 1;
//                    i = j;
//                    break;
//                }
//            }
//        } else {
//            if (!paramBoolean) {
//                break label179;
//            }
//            j = i;
//            if (paramLink.endWeekendID != -1) {
//                j = getWeekend(paramLink.endWeekendID).arrTime;
//            }
//        }
//        label179:
//        do {
//            return j;
//            j = i;
//        } while (paramLink.endWeekdayID == -1);
//        return getWeekday(paramLink.endWeekdayID).arrTime;
//    }

//    public static Line getLine(int paramInt) {
//        return (Line) lineList.get(paramInt);
//    }

//    public static Link getLink(int paramInt) {
//        return (Link) linkList.get(paramInt);
//    }
//
//    public static Link getLink(TransferLink paramTransferLink) {
//        if ((paramTransferLink == null) || (paramTransferLink.linkID == -1)) {
//            return null;
//        }
//        return getLink(paramTransferLink.linkID);
//    }
//
//    public static String getSL(String paramString) {
//        return (String) subLangList.get(paramString);
//    }
//
//    public static SrcUNO getSrcUNO(String paramString) {
//        Object localObject = null;
//        String str = paramString.toUpperCase();
//        Iterator localIterator = unoList.iterator();
//        do {
//            paramString = (String) localObject;
//            if (!localIterator.hasNext()) {
//                break;
//            }
//            paramString = (SrcUNO) localIterator.next();
//        } while (!paramString.uno.equals(str));
//        return paramString;
//    }

//    public static Station getStation(int paramInt) {
//        return (Station) stationList.get(paramInt);
//    }

//    public static Transfer getTransferFrom(int paramInt) {
//        return (Transfer) transferFromList.get(paramInt);
//    }
//
//    public static Transfer getTransferTo(int paramInt) {
//        return (Transfer) transferToList.get(paramInt);
//    }
//
//    public static Way getWay(int paramInt) {
//        return (Way) wayList.get(paramInt);
//    }

//    public static Timetable getWeekday(int paramInt) {
//        loadWeekday();
//        return (Timetable) weekdayList.get(paramInt);
//    }
//
//    public static Timetable getWeekend(int paramInt) {
//        loadWeekend();
//        return (Timetable) weekendList.get(paramInt);
//    }

//    public static void loadAppLang(String paramString) {
//        String[] arrayOfString = getCsv("", "mmlang");
//        langCDList = new ArrayList();
//        langNameList = new ArrayList();
//        mainLangList = new HashMap();
//        subLangList = new HashMap();
//        int i = 0;
//        int m = arrayOfString.length;
//        int k = 0;
//        while (k < m) {
//            Object localObject1 = arrayOfString[k];
//            if (Util.isEmpty((String) localObject1).booleanValue()) {
//                k += 1;
//            } else {
//                localObject1 = ((String) localObject1).split("<,>", -1);
//                Object localObject2 = localObject1[0];
//                int j;
//                if (((String) localObject2).equals("LangCD")) {
//                    langCDList.addAll(Arrays.asList((Object[]) localObject1));
//                    langCDList.remove(0);
//                    j = langCDList.indexOf(paramString) + 1;
//                }
//                for (; ; ) {
//                    mainLangList.put(localObject2, localObject1[j]);
//                    subLangList.put(localObject2, localObject1[1]);
//                    i = j;
//                    break;
//                    j = i;
//                    if (((String) localObject2).equals("LangName")) {
//                        langNameList.addAll(Arrays.asList((Object[]) localObject1));
//                        langNameList.remove(0);
//                        j = i;
//                    }
//                }
//            }
//        }
//    }
//
//    public static void loadCity(String paramString) {
//        city = getCity(paramString);
//        loadConfig();
//        loadHoliday();
//        loadLang();
//        loadUNO();
//        loadFare();
//        loadStation();
//        loadLine();
//        loadWay();
//        loadLink();
//        loadTransferFrom();
//        loadTransferTo();
//    }
//
//    public static void loadCityList() {
//        if ((cityList != null) && (cityList.size() > 0)) {
//            return;
//        }
//        String[] arrayOfString = getCsv("", "mmcity");
//        cityList = new ArrayList(arrayOfString.length);
//        int j = arrayOfString.length;
//        int i = 0;
//        label45:
//        String str;
//        if (i < j) {
//            str = arrayOfString[i];
//            if (!Util.isEmpty(str).booleanValue()) {
//                break label71;
//            }
//        }
//        for (; ; ) {
//            i += 1;
//            break label45;
//            break;
//            label71:
//            cityList.add(new City(str));
//        }
//    }
//
//    private static void loadConfig() {
//        if (configList != null) {
//            return;
//        }
//        String[] arrayOfString = getCsv("config");
//        configList = new ArrayList(arrayOfString.length);
//        int j = arrayOfString.length;
//        int i = 0;
//        label31:
//        String str;
//        if (i < j) {
//            str = arrayOfString[i];
//            if (!Util.isEmpty(str).booleanValue()) {
//                break label57;
//            }
//        }
//        for (; ; ) {
//            i += 1;
//            break label31;
//            break;
//            label57:
//            configList.add(new SrcConfig(str));
//        }
//    }
//
//    private static void loadFare() {
//        if (fareList != null) {
//            return;
//        }
//        String[] arrayOfString = getCsv("fare");
//        fareList = new ArrayList(arrayOfString.length);
//        int j = arrayOfString.length;
//        int i = 0;
//        label31:
//        String str;
//        if (i < j) {
//            str = arrayOfString[i];
//            if (!Util.isEmpty(str).booleanValue()) {
//                break label57;
//            }
//        }
//        for (; ; ) {
//            i += 1;
//            break label31;
//            break;
//            label57:
//            fareList.add(new SrcFare(str));
//        }
//    }
//
//    private static void loadHoliday() {
//        if (holidayList != null) {
//            return;
//        }
//        String[] arrayOfString = getCsv("holiday");
//        holidayList = new ArrayList(arrayOfString.length);
//        int j = arrayOfString.length;
//        int i = 0;
//        label31:
//        String str;
//        if (i < j) {
//            str = arrayOfString[i];
//            if (!Util.isEmpty(str).booleanValue()) {
//                break label57;
//            }
//        }
//        for (; ; ) {
//            i += 1;
//            break label31;
//            break;
//            label57:
//            holidayList.add(str);
//        }
//    }
//
//    private static void loadLang() {
//        if (langList != null) {
//            return;
//        }
//        String[] arrayOfString = getCsv("lang");
//        langList = new ArrayList(arrayOfString.length);
//        int j = arrayOfString.length;
//        int i = 0;
//        label31:
//        String str;
//        if (i < j) {
//            str = arrayOfString[i];
//            if (!Util.isEmpty(str).booleanValue()) {
//                break label57;
//            }
//        }
//        for (; ; ) {
//            i += 1;
//            break label31;
//            break;
//            label57:
//            langList.add(new SrcLang(str));
//        }
//    }


    public static void loadData(String paramString) {

        //paramString ?������
        //?�������iStationName�j �L�\������?���C����?���B�󕶎�?Load�SData
        loadStation(paramString);
    }

//    private static void loadLine() {
//        if (lineList != null) {
//            return;
//        }
//        String[] arrayOfString = getCsv("line");
//        int j = 0;
//        lineList = new ArrayList(arrayOfString.length);
//        int k = arrayOfString.length;
//        int i = 0;
//        label34:
//        String str;
//        if (i < k) {
//            str = arrayOfString[i];
//            if (!Util.isEmpty(str).booleanValue()) {
//                break label61;
//            }
//        }
//        for (; ; ) {
//            i += 1;
//            break label34;
//            break;
//            label61:
//            lineList.add(new Line(j, str));
//            j += 1;
//        }
//    }

//    private static void loadLink() {
//        if (linkList != null) {
//            return;
//        }
//        String[] arrayOfString = getCsv("link");
//        int j = 0;
//        linkList = new ArrayList(arrayOfString.length);
//        int k = arrayOfString.length;
//        int i = 0;
//        label34:
//        String str;
//        if (i < k) {
//            str = arrayOfString[i];
//            if (!Util.isEmpty(str).booleanValue()) {
//                break label61;
//            }
//        }
//        for (; ; ) {
//            i += 1;
//            break label34;
//            break;
//            label61:
//            linkList.add(new Link(j, str));
//            j += 1;
//        }
//    }

    private static void loadStation(String paraStaName) {
        if (stationMap != null) {
            return;
        }else {
            stationMap = new HashMap<String,Station>();
        }

        if (lineMap == null) {
            lineMap = new HashMap<String,Line>();
        }

        if(weekdayList == null) {
            weekdayList = new ArrayList<Timetable>();
        }

        //���������I�����S�s?�o����
        String[] arrayOfString = getCsv("dj");

        //���͐����B
        String stationList = "";
        Line tmpLine = new Line();
        Station tmpStation ;
        for(int i = 0 ; i < arrayOfString.length;i++) {
            //
            String[] strTmp = arrayOfString[i].split("\t");

            //����?��C��������s�����B
            if(strTmp[0].trim().isEmpty()) {

                //LineID?�X?�C�ۑ����OLine�IStationIDList��Line�C�󐴋󓖑OStationList.
                if(!stationList.isEmpty()){
                    tmpLine.setStationList(stationList);
                    lineMap.put(tmpLine.lineId,tmpLine);
                    stationList = "";

                    //LineID?�X?�CMap���ۑ��IStation ?�ቻ
                    tmpLine = new Line();
                }
                continue;
            }

            //���?�C?��Line��?��ۑ�
            tmpLine.lineId = strTmp[0];
            tmpLine.lineNameJP = strTmp[1];


            //��񕔁C?��Station��?��ۑ�
            tmpStation = new Station();
            tmpStation.lineId = strTmp[0];
            tmpStation.lineName = strTmp[1];
            tmpStation.stationId = strTmp[3];
            tmpStation.stationNameJP = strTmp[2];
            tmpStation.stationNameCN = strTmp[4];
            tmpStation.stationNamePY = strTmp[5];
            stationList += tmpStation.stationId + "," ;
            stationMap.put(tmpStation.stationId,tmpStation);


            //��O���C?��TimeTable��?��ۑ�
            Timetable tmpTb = new Timetable();
            tmpTb.lineId = strTmp[0];
            tmpTb.stationId=strTmp[3];
            tmpTb.dateType = "WD";
            String timeList = "";
            for ( int j =  7; j < strTmp.length;j++) {
                if(strTmp[j].trim().isEmpty() || strTmp[j].trim().equals("||") || strTmp[j].trim().equals("レ")) {
                    continue;
                }else {
                    timeList += strTmp[j] + ",";
                }
            }
            tmpTb.setDepTime(timeList);
            weekdayList.add(tmpTb);
        }
    }

//    private static void loadTransferFrom() {
//        if ((transferFromList != null) && (transferFromList.size() > 0)) {
//            return;
//        }
//        String[] arrayOfString = getCsv("transferfrom");
//        int j = 0;
//        transferFromList = new ArrayList(arrayOfString.length);
//        int k = arrayOfString.length;
//        int i = 0;
//        label45:
//        String str;
//        if (i < k) {
//            str = arrayOfString[i];
//            if (!Util.isEmpty(str).booleanValue()) {
//                break label72;
//            }
//        }
//        for (; ; ) {
//            i += 1;
//            break label45;
//            break;
//            label72:
//            transferFromList.add(new Transfer(j, str));
//            j += 1;
//        }
//    }
//
//    private static void loadTransferTo() {
//        if (transferToList != null) {
//            return;
//        }
//        String[] arrayOfString = getCsv("transferto");
//        int j = 0;
//        transferToList = new ArrayList(arrayOfString.length);
//        int k = arrayOfString.length;
//        int i = 0;
//        label34:
//        String str;
//        if (i < k) {
//            str = arrayOfString[i];
//            if (!Util.isEmpty(str).booleanValue()) {
//                break label61;
//            }
//        }
//        for (; ; ) {
//            i += 1;
//            break label34;
//            break;
//            label61:
//            transferToList.add(new Transfer(j, str));
//            j += 1;
//        }
//    }
//
//    private static void loadUNO() {
//        if (unoList != null) {
//            return;
//        }
//        String[] arrayOfString = getCsv("uno");
//        unoList = new ArrayList(arrayOfString.length);
//        int j = arrayOfString.length;
//        int i = 0;
//        label31:
//        String str;
//        if (i < j) {
//            str = arrayOfString[i];
//            if (!Util.isEmpty(str).booleanValue()) {
//                break label57;
//            }
//        }
//        for (; ; ) {
//            i += 1;
//            break label31;
//            break;
//            label57:
//            unoList.add(new SrcUNO(str));
//        }
//    }
//
//    private static void loadWay() {
//        if (wayList != null) {
//            return;
//        }
//        String[] arrayOfString = getCsv("way");
//        int j = 0;
//        wayList = new ArrayList(arrayOfString.length);
//        int k = arrayOfString.length;
//        int i = 0;
//        label34:
//        String str;
//        if (i < k) {
//            str = arrayOfString[i];
//            if (!Util.isEmpty(str).booleanValue()) {
//                break label61;
//            }
//        }
//        for (; ; ) {
//            i += 1;
//            break label34;
//            break;
//            label61:
//            wayList.add(new Way(j, str));
//            j += 1;
//        }
//    }
//
//    private static void loadWeekday() {
//        if (weekdayList != null) {
//        }
//        for (; ; ) {
//            return;
//            int[][] arrayOfInt = getCsv("weekday", city.weekdayCount, 2);
//            int j = 0;
//            weekdayList = new ArrayList(arrayOfInt.length);
//            int k = arrayOfInt.length;
//            int i = 0;
//            while (i < k) {
//                int[] arrayOfInt1 = arrayOfInt[i];
//                weekdayList.add(new Timetable(j, arrayOfInt1));
//                j += 1;
//                i += 1;
//            }
//        }
//    }
//
//    private static void loadWeekend() {
//        if (weekendList != null) {
//        }
//        for (; ; ) {
//            return;
//            if (city.weekendCount == 0) {
//                loadWeekday();
//                weekendList = weekdayList;
//                return;
//            }
//            int[][] arrayOfInt = getCsv("weekend", city.weekendCount, 2);
//            int j = 0;
//            weekendList = new ArrayList(arrayOfInt.length);
//            int k = arrayOfInt.length;
//            int i = 0;
//            while (i < k) {
//                int[] arrayOfInt1 = arrayOfInt[i];
//                weekendList.add(new Timetable(j, arrayOfInt1));
//                j += 1;
//                i += 1;
//            }
//        }
//    }
//
//    public static void unloadCity() {
//        city = null;
//        if (configList != null) {
//            configList.clear();
//            configList = null;
//        }
//        if (holidayList != null) {
//            holidayList.clear();
//            holidayList = null;
//        }
//        if (langList != null) {
//            langList.clear();
//            langList = null;
//        }
//        if (unoList != null) {
//            unoList.clear();
//            unoList = null;
//        }
//        if (fareList != null) {
//            fareList.clear();
//            fareList = null;
//        }
//        if (stationList != null) {
//            stationList.clear();
//            stationList = null;
//        }
//        if (lineList != null) {
//            lineList.clear();
//            lineList = null;
//        }
//        if (wayList != null) {
//            wayList.clear();
//            wayList = null;
//        }
//        if (linkList != null) {
//            linkList.clear();
//            linkList = null;
//        }
//        if (transferFromList != null) {
//            transferFromList.clear();
//            transferFromList = null;
//        }
//        if (transferToList != null) {
//            transferToList.clear();
//            transferToList = null;
//        }
//        unloadTimetable();
//    }
//
//    public static void unloadTimetable() {
//        if (weekdayList != null) {
//            weekdayList.clear();
//            weekdayList = null;
//        }
//        if (weekendList != null) {
//            weekendList.clear();
//            weekendList = null;
//        }
//    }

}


/* Location:              C:\ljn\apk hack\jd-gui-windows-1.4.0\jd-gui-windows-1.4.0\classes-dex2jar.jar!\com\xinlukou\engine\DM.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */