package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Given a graph and a source vertex src in the graph, find the shortest paths from src to all vertices in the given graph.
 * The graph may contain negative weight edges.
 * We have discussed Dijkstra’s algorithm for this problem. Dijkstra’s algorithm is a Greedy algorithm and the
 * time complexity is O((V+E)LogV) (with the use of the Fibonacci heap). Dijkstra doesn’t work for Graphs with negative weights,
 * Bellman-Ford works for such graphs. Bellman-Ford is also simpler than Dijkstra and suites well for distributed systems.
 * But time complexity of Bellman-Ford is O(V * E), which is more than Dijkstra.
 */
class bellNode {
    int sourceNode, destinationNode, edgeWeight;

    public bellNode() {
    }     // no-argument constructor

    public bellNode(int sourceNode, int destinationNode, int edgeWeight) {       // argument constructor
        this.sourceNode = sourceNode;
        this.destinationNode = destinationNode;
        this.edgeWeight = edgeWeight;
    }
}

class BellmanFordAlgorithm {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of nodes:");   // taking input of number of nodes
        int numberOfNodes = sc.nextInt();

        System.out.println("Enter the number of edges:");   // taking input of number of edges
        int numberOfEdges = sc.nextInt();

        ArrayList<bellNode> edgeList = new ArrayList<>();
        for (int i = 0; i < numberOfEdges; i++) {
            edgeList.add(new bellNode());
        }

        for (int i = 0; i < numberOfEdges; i++) {
            System.out.println("Enter the source node:");   // taking input of source node
            int sourceNode = sc.nextInt();

            System.out.println("Enter the destination node:");  // taking input of destination node
            int destinationNode = sc.nextInt();

            // taking input of edge weight between the source node and destination node
            System.out.println("Enter the edge weight between the source node and destination node:");
            int edgeWeight = sc.nextInt();

            // adding the nodes and edge weight into the edgeList
            edgeList.add(new bellNode(sourceNode, destinationNode, edgeWeight));
        }

        // This is node from where we have to start the algorithm to find the
        // minimum distance of all the remaining nodes in the graph
        System.out.println("Enter the starting source node:");
        int startingSourceNode = sc.nextInt();

        // calling the method
        bellmanFordAlgo(edgeList, numberOfNodes, startingSourceNode);
    }

    public static void bellmanFordAlgo(ArrayList<bellNode> edgeList, int numberOfNodes, int startingSourceNode) {
        int[] distanceArray = new int[numberOfNodes];   // distance array for storing the minimum distance of all remaining nodes other than source node from the source node itself
        Arrays.fill(distanceArray, 10000000);   // fill 10^9 in remaining nodes and 0 in source node
        distanceArray[startingSourceNode] = 0;

        // run the loop for n-1 times where n -> number of nodes in the given graph
        for (int i = 1; i <= numberOfNodes - 1; i++) {
            for (bellNode node : edgeList) {
                int sourceNode = node.sourceNode;
                int destinationNode = node.destinationNode;
                int edgeWeight = node.edgeWeight;

                // formula for calculating minimum distance
                if (distanceArray[sourceNode] + edgeWeight < distanceArray[destinationNode]) {
                    distanceArray[destinationNode] = distanceArray[sourceNode] + edgeWeight;
                }
            }
        }

        // for checking if there is a negative cycle in the graph
        // if we run the same loop and formula for one more time and
        // the distance decreases then it means that there is a -ve cycle
        boolean checkNegativeCycle = false;
        for (bellNode node : edgeList) {
            int sourceNode = node.sourceNode;
            int destinationNode = node.destinationNode;
            int edgeWeight = node.edgeWeight;

            if (distanceArray[sourceNode] + edgeWeight < distanceArray[destinationNode]) {
                checkNegativeCycle = true;
                System.out.println("There is a negative cycle in a graph.");
                break;
            }
        }

        // printing the distance array
        if (!checkNegativeCycle) {
            for (int node = 0; node < distanceArray.length; node++) {
                System.out.println("The minimum distance of node " + node + " from the source node " + startingSourceNode + " is : " + distanceArray[node]);
            }
        }
        System.out.println();
    }
}