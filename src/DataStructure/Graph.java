package DataStructure;

import java.util.*;

public class Graph {

    private final ArrayList<Node> graph = new ArrayList<>();

    public void addNode(Node node) {

        if(graph.contains(node)) {
            System.out.println("The node is already added to the graph");
        }else {
            graph.add(node);
        }

    }

    public int size() {
        return graph.size();
    }

    public ArrayList<Node> breathFirstTraversal(Node starting) {

        Queue<Node> nodeQueue = new LinkedList<>();
        Set<Node> visited = new HashSet<>();
        ArrayList<Node> nodeSequence = new ArrayList<>();

        nodeQueue.add(starting);

        while(!nodeQueue.isEmpty()) {
            Node current = nodeQueue.poll();

            if(visited.contains(current)) {
                continue;
            }

            visited.add(current);

            nodeSequence.add(current);

            for(Edge n : current.getNeighbour()) {
                nodeQueue.add(n.getEndingNode());
            }

        }

        return nodeSequence;
    }

    public ArrayList<Node> depthFirstTraversal(Node starting) {
        ArrayList<Node> nodeSequence = new ArrayList<>();
        dft(starting, new HashSet<>(), nodeSequence);
        return nodeSequence;
    }


    private void dft(Node node, Set<Node> visited, ArrayList<Node> nodeSequence) {

        if(visited.contains(node)) return;
        nodeSequence.add(node);

        visited.add(node);

        for(Edge n: node.getNeighbour()) {
            dft(n.getEndingNode(), visited, nodeSequence);
        }

    }

    public  void shortestPath(Node starting, Node ending) {
        Map<Node, Integer> distanceMap = new HashMap<>();
        Map<Node, Node> predecessorMap = new HashMap<>();

        for (Node node : breathFirstTraversal(starting)) {
            distanceMap.put(node, Integer.MAX_VALUE);
            predecessorMap.put(node, null);
        }

        distanceMap.replace(starting, 0);

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(distanceMap::get));
        priorityQueue.add(starting);

        while (!priorityQueue.isEmpty()) {
            Node current = priorityQueue.poll();

            for (Edge edge : current.getNeighbour()) {
                Node neighbour = edge.getEndingNode();
                int newDistance = distanceMap.get(current) + edge.getWeight();

                if (newDistance < distanceMap.get(neighbour)) {
                    distanceMap.replace(neighbour, newDistance);
                    predecessorMap.replace(neighbour, current);
                    priorityQueue.add(neighbour);
                }
            }
        }

        List<Node> path = new ArrayList<>();
        Node current = ending;
        while (current != null) {
            path.add(current);
            current = predecessorMap.get(current);
        }

        Collections.reverse(path);

        System.out.println("\n\t\tShortest path from " + starting.getNodeNumber() + " to " + ending.getNodeNumber());
        printTraversal(path);
        System.out.println("\t\tTotal distance: " + distanceMap.get(ending));
    }

    public void printTraversal(List<Node> nodeSequence) {

        System.out.println("\n\t\tTraversal Sequence: ");
        for(int i = 0; i < nodeSequence.size(); i ++) {
            System.out.print( (i == 0 ?  "\t\t\t" : "") + nodeSequence.get(i).getNodeNumber() + (i == nodeSequence.size() - 1 ? " " : " -> "));
        }
        System.out.println();

    }

    public Node getNode(int id) {
        return graph.stream()
                .filter(node -> node.getNodeNumber() == id)
                .findFirst()
                .orElse(null);
    }

}
