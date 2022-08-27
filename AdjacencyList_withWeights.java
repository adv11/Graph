package Graph;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Graph Adjacency List Input Case 2:
 * An adjacency matrix is given input as it is. ie, 1 for adjacent and 0 for non-adjacent.
 *
 * Sample Input:
 * The first line contains a single integer n – The number of nodes.
 * Each of the next n lines contains n*2 space separated integers. They are pairs of adjacent nodes and weights.
 * 3
 * 2	1 13	2 4
 * 1	2 9		3 -4
 * 2	0 -7	1 8
 */
public class AdjacencyList_withWeights {
    static class Edge{
        int destinationNode, edgeWeight;

        // constructor
        public Edge(int destinationNode, int edgeWeight){
            this.destinationNode = destinationNode;
            this.edgeWeight = edgeWeight;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<ArrayList<Edge>> adjList = new ArrayList<>();
        System.out.println("Enter number of nodes:");
        int numberOfNodes = sc.nextInt();

        for(int i=0 ; i<numberOfNodes ; i++){
            adjList.add(new ArrayList<>());
            System.out.println("Enter count:");
            int count = sc.nextInt();
            for(int j=0 ; j<count ; j++) {
                System.out.println("Enter destination node:");
                int destinationNode = sc.nextInt();
                System.out.println("Enter edge weight :");
                int edgeWeight = sc.nextInt();

                adjList.get(i).add(new Edge(destinationNode, edgeWeight));
            }
        }
        printAdjacency(adjList);
    }
    public static void printAdjacency(ArrayList<ArrayList<Edge>> adjList) {
        for (int i = 0; i < adjList.size(); i++) {
            System.out.print(i + " : ");
            for (Edge k : adjList.get(i)) {
                System.out.print(k.destinationNode + " " + k.edgeWeight);
            }
            System.out.println();
        }
    }
}
