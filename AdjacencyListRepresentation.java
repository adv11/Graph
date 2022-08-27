package Graph;

import java.util.HashMap;
import java.util.LinkedList;

class Node{
    int nodeValue;
    String name;
    Node(int nodeValue, String name){
        this.nodeValue = nodeValue;
        this.name = name;
    }

    public Node() {

    }

    public static void addEdge(Node source, Node destination){
        if(!Graph1.adjacencyMap.keySet().contains(source)){
            Graph1.adjacencyMap.put(source, null);
        }
        if(!Graph1.adjacencyMap.keySet().contains(destination)){
            Graph1.adjacencyMap.put(destination, null);
        }
        addEdgeHelper(source, destination);
        if(!Graph1.directed){
            addEdgeHelper(destination, source);
        }
    }
    public static void addEdgeHelper(Node a, Node b){
        LinkedList<Node> temp = Graph1.adjacencyMap.get(a);
        if(temp != null){
            temp.remove(b);
        }else{
            temp = new LinkedList<>();
        }
        temp.add(b);
        Graph1.adjacencyMap.put(a, temp);
    }

    public static void printEdge(){
        for(Node node : Graph1.adjacencyMap.keySet()){
            System.out.print("The " + node.name + " has an edge towards: ");
            if(Graph1.adjacencyMap.get(node) != null){
                for(Node neighbour : Graph1.adjacencyMap.get(node)){
                    System.out.print(neighbour.name + " ");
                }
                System.out.println();
            }else{
                System.out.println("none");
            }
        }
    }
    public static boolean hasEdge(Node source, Node destination){
        return Graph1.adjacencyMap.containsKey(source) && Graph1.adjacencyMap.get(source).contains(destination);
    }
}

class Graph1 extends Node{
    public static HashMap<Node, LinkedList<Node>> adjacencyMap;
    static boolean directed;
    public Graph1(boolean directed){
        super();
        this.directed = directed;
        adjacencyMap = new HashMap<>();
    }
}
public class AdjacencyListRepresentation {
    public static void main(String[] args) {
        Graph1 graph = new Graph1(true);
        Node a = new Node(0, "A");
        Node b = new Node(1, "B");
        Node c = new Node(2, "C");
        Node d = new Node(3, "D");
        Node e = new Node(4, "E");

        Node.addEdge(a,b);
        Node.addEdge(b,c);
        Node.addEdge(b,d);
        Node.addEdge(c,e);
        Node.addEdge(b,a);

        Node.printEdge();

        System.out.println(Node.hasEdge(a,b));
        System.out.println(Node.hasEdge(d,a));
    }
}
