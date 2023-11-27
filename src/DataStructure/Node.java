package DataStructure;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;

public class Node{

    private int nodeNumber;
    private ArrayList<Edge> neighbour = new ArrayList<>() ;

    public Node(int nodeNumber) {
        this.nodeNumber = nodeNumber;
    }


    public void addNeigbhbour(Edge node) {

        if(node == null) {
            return;
        }

        if(neighbour.contains(node)) {
            System.out.println("The node is already added as a Neighbour");
        }else {
            neighbour.add(node);
        }

    }

    public int getNodeNumber() {
        return nodeNumber;
    }

    public void setNodeNumber(int nodeNumber) {
        this.nodeNumber = nodeNumber;
    }

    public ArrayList<Edge> getNeighbour() {
        return neighbour;
    }


}
