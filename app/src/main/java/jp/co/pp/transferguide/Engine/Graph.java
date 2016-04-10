package jp.co.pp.transferguide.Engine;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by liujin on 2016/4/10.
 */
public class Graph {

    public class Node implements Comparator{

        public String wayId = "";

        public String stationId = "";

        public int time = 0; //出发时间

        public int transfer = 0 ; //换乘时间

        public boolean visited = false;

        public Node prev;

        public List<Node> neighboursList;

        public Node(String wayId,String staId) {
            this.wayId = wayId;
            this.stationId = staId;
        }

        public void addNeighbour(Node neighbour) {
            this.neighboursList.add(neighbour);
        }

        @Override
        public int compare(Object lhs, Object rhs) {
            return Integer.compare(((Node)lhs).time,((Node)rhs).time);
        }
    };

    public class Edge {

        public Node from;
        public Node to;
        public int weight = 0 ;

       public Edge(Node f, Node t, int w) {
           this.from = f;
           this.to = t;
           this.weight = w;
       }
    };

    public int getWeight(Node from, Node to) {

        int rtn = 0 ;
        for(int i = 0 ; i< edgeList.size();i++) {
            Edge edgeTmp = (Edge)edgeList.get(i);
            if((from.stationId == edgeTmp.from.stationId) &&
                    (from.wayId == edgeTmp.from.wayId) &&
                    (to.stationId == edgeTmp.to.stationId) &&
                    (to.wayId == edgeTmp.to.wayId)){
                rtn = edgeTmp.weight;
                break;
            }
        }

        return  rtn;
    }

    public List<Node> nodeList;

    public List<Edge> edgeList;

    public Graph() {

        nodeList = new ArrayList<Node>();

        edgeList = new ArrayList<Edge>();
    }

    public void addNode(Node node) {
        nodeList.add(node);
    }

    public void addEdge(Node node, Edge edge) {
        edge.from.neighboursList.add(edge.to);
        edge.to.neighboursList.add(edge.from);
        edgeList.add(edge);
    }


    public void findShortestPath(Node start, Node end,int startTime) {

        start.visited = true;



    }

}
