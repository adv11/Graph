package Graph;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Given an undirected graph, and we have to print the
 * edges which are bridges means after removing that
 * edge the graph will be divided into more connected
 * components.
 * Input :
 * Adjacency List :
 * 1 - {2, 4}
 * 2 - {1, 3}
 * 3 - {1, 2}
 * 4 - {1, 5}
 * 5 - {4}
 * Output :
 * Edge is : 1->4
 * Edge is : 4->5
 */
public class BridgesInGraph {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>();

        // Taking input for number of nodes
        System.out.println("Enter the number of nodes:");
        int numberOfNodes = sc.nextInt();

        // initializing adjacencyList with new ArrayList<>()
        for (int i = 1; i <= numberOfNodes + 1; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        // taking input of adjacent nodes of particular node
        for (int i = 1; i <= numberOfNodes; i++) {
            System.out.println("Enter number of adjacent nodes to the node : " + i);
            int countOfAdjacentNodes = sc.nextInt();

            for (int j = 0; j < countOfAdjacentNodes; j++) {
                System.out.println("Enter the adjacent node : ");
                int adjacentNode = sc.nextInt();

                // adding the adjacent node in to the adjacency list
                adjacencyList.get(i).add(adjacentNode);
            }
        }

        boolean[] visited = new boolean[numberOfNodes+1];   // visited array for storing if the node is visited or not
        int[] timeOfInsertion = new int[numberOfNodes+1];   // for storing time of insertion of the node
        int[] minimumInsertionTime = new int[numberOfNodes+1];  // for storing minimum time of insertion of the node

        int timer = 0;  // variable for time of insertion

        // Running dfs for all the nodes
        for(int node=1 ; node<=numberOfNodes ; node++){
            // if the node is not visited then visit it
            if(!visited[node]){
                dfs(node, -1, timeOfInsertion, minimumInsertionTime, adjacencyList, visited, timer);
            }
        }
    }
    public static void dfs(int node, int parentNode, int[] timeOfInsertion, int[] minimumInsertionTime, ArrayList<ArrayList<Integer>> adjacencyList, boolean[] visited, int timer){
        visited[node] = true;   // put true e.g. node is visited
        timeOfInsertion[node] = minimumInsertionTime[node] = timer++;   // store the time

        // visit the adjacent nodes of the current node
        for(int adjacentNode : adjacencyList.get(node)){
            // if the adjacent node == parent node then no need to visit it
            if(adjacentNode == parentNode){
                continue;
            }

            // if the adjacent node is not visited then run the dfs
            if(!visited[adjacentNode]){
                dfs(adjacentNode, node, timeOfInsertion, minimumInsertionTime, adjacencyList, visited, timer);
                // store the minimum time of insertion
                minimumInsertionTime[node] = Math.min(minimumInsertionTime[node], minimumInsertionTime[adjacentNode]);

                // if the minimum of insertion > time of insertion then it's an edge
                if(minimumInsertionTime[adjacentNode] > timeOfInsertion[node]){
                    System.out.println("Edge is : " + adjacentNode + " -> " + node);
                }
            }else{
                minimumInsertionTime[node] = Math.min(minimumInsertionTime[node], timeOfInsertion[adjacentNode]);
            }
        }
    }
}
