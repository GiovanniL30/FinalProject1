package DataStructure;

import java.util.Comparator;

public class Edge implements Comparator<Edge> {

    private int weight;
    private Node startingNode;
    private Node endingNode;

    public Edge(int weight, Node startingNode, Node endingNode) {
        this.weight = weight;
        this.startingNode = startingNode;
        this.endingNode = endingNode;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Node getStartingNode() {
        return startingNode;
    }

    public void setStartingNode(Node startingNode) {
        this.startingNode = startingNode;
    }

    public Node getEndingNode() {
        return endingNode;
    }

    public void setEndingNode(Node endingNode) {
        this.endingNode = endingNode;
    }

    @Override
    public int compare(Edge o1, Edge o2) {
        return o1.getWeight() - o2.getWeight();
    }
}
