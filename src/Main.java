import DataStructure.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static Graph graph;
    private static Scanner sc = new Scanner(System.in);
    private static String filePath = "src/WeightedUndirectedMatrixGraph.txt";


    public static void main(String[] args) {

        boolean haveReadFile = false;

        while(true) {
            menu();
            switch (getUserInput(">>: ", 1, 5)) {
                case 1 -> haveReadFile = readFileMatrix();
                case 2 -> {
                    if(!haveReadFile) {
                        System.out.println("Please read the Graph Matrix file first");
                        return;
                    }
                    graph.printTraversal(graph.depthFirstTraversal(graph.getNode(getUserInput("What is the starting Node [0-"  + (graph.size()-1) +"]: ", 0, graph.size() - 1))));
                }
                case 3 ->  {
                    if(!haveReadFile) {
                        System.out.println("Please read the Graph Matrix file first");
                        return;
                    }
                    graph.printTraversal(graph.breathFirstTraversal(graph.getNode(getUserInput("What is the starting Node [0-"  + (graph.size()-1) +"]: ", 0, graph.size() - 1))));
                }
                case 4 -> {

                    if(!haveReadFile) {
                        System.out.println("Please read the Graph Matrix file first");
                        return;
                    }

                    int starting = getUserInput("Choose starting Node from [0-"  + (graph.size()-1) +"]: ", 0, graph.size() - 1);
                    int ending = getUserInput("Choose ending Node from [0-"  + (graph.size()-1) +"]: ", 0, graph.size() - 1);
                    graph.shortestPath(graph.getNode(starting), graph.getNode(ending));
                }
                case 5 -> System.exit(0);
            }

        }


    }

    private static int getUserInput(String message, int lowerLimit, int upperLimit) {

        int userInput = -1;

        while(userInput == -1) {

            try{
                System.out.println();
                System.out.print("\t\t"+message);
                userInput = sc.nextInt();

                if(userInput > upperLimit || userInput < lowerLimit) {
                    System.out.println("\n\t\tPlease input a number between " + lowerLimit + " - " + upperLimit);
                    userInput = -1;
                }

            }catch (NumberFormatException exception) {
                System.out.println("\n\t\tPlease enter a Integer number only");
                userInput = -1;
            }

        }

        return userInput;
    }


    private static void menu() {
        System.out.println();
        System.out.println("\t\t1. Load a file containing the graph's data");
        System.out.println("\t\t2. Perform Depth First Traversal of the graph");
        System.out.println("\t\t3. Perform Breath First Traversal of the graph");
        System.out.println("\t\t4. Show the shortest path from one vertex to another");
        System.out.println("\t\t5. Exit");
    }

    private static boolean readFileMatrix() {

        try {
            graph = new Graph();
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            ArrayList<Node> vertices = new ArrayList<>();

            String line = reader.readLine();

            for(int i = 0; i < line.split(" ").length; i++ ) {
                vertices.add(new Node(i));
            }

            int row = 0;

            System.out.println();
            System.out.println("\t\tGraph Matrix");
            while( (line) != null) {

                System.out.println("\t\t\t"+line);

                Node node = vertices.get(row);

                String[] lineArray = line.split(" ");

                for(int i = 0; i < lineArray.length; i++) {

                    int currentNumber = Integer.parseInt(lineArray[i]);

                    if(currentNumber != 0) {
                        node.addNeigbhbour(new Edge(currentNumber, node, vertices.get(i)));
                    }

                }

                graph.addNode(node);
                row++;
                line = reader.readLine();
            }

        } catch (IOException e) {
            System.out.println("There is an error on reading the file");
            return false;
        } catch (Exception e) {
            System.out.println("There is an error in converting the file into a Adjacency List Graph");
            return false;
        }

        System.out.println("\t\tReading the file is successful");
        return true;
    }

}
