package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class TopologicalSortingBFS_KahnAlgorithm {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>();

        // Taking input for number of nodes
        System.out.println("Enter the number of nodes:");
        int numberOfNodes = sc.nextInt();

        // initializing adjacencyList with new ArrayList<>()
        for(int i=1 ; i<=numberOfNodes+1 ; i++){
            adjacencyList.add(new ArrayList<>());
        }

        // taking input of adjacent nodes of particular node
        for(int i=1 ; i<=numberOfNodes ; i++){
            System.out.println("Enter number of adjacent nodes to the node : " + i);
            int countOfAdjacentNodes = sc.nextInt();

            // taking input of the particular adjacent nodes to the node i
            System.out.println("Enter adjacent nodes:");
            for(int j=0 ; j<countOfAdjacentNodes ; j++){

                // taking input of adjacent node
                int adjacentNode = sc.nextInt();
                // adding the adjacent node to the particular node i
                adjacencyList.get(i).add(adjacentNode);
            }
        }

        // This is a helper method which is used to call to topologicalSort method
        // for printing the topological sort of the given graph
        helperMethod(adjacencyList, numberOfNodes);
    }
    public static void helperMethod(ArrayList<ArrayList<Integer>> adjacencyList, int numberOfNodes){
        int[] inDegree = new int[numberOfNodes+1];  // for storing indegree of the nodes
        // calculating indegree of the nodes
        for(ArrayList<Integer> adjacentNodesList : adjacencyList){
            for(int adjacentNode : adjacentNodesList){
                inDegree[adjacentNode]++;
            }
        }
        Queue<Integer> queue = new LinkedList<>();  // for storing final topological sorting of the graph nodes

        // adding the elements in to the queue data structure whose indegree is 0
        for(int i=1 ; i<=numberOfNodes ; i++){
            if(inDegree[i] == 0){
                queue.offer(i);
            }
        }

        // for storing the topological sorting of the graph
        ArrayList<Integer> topologicalSorting = new ArrayList<>();

        // While queue is not empty
        while(!queue.isEmpty()){
            int currentNode = queue.poll(); // current visiting node

            // now check for adjacent node : decrease the indegree of the adjacent nodes of the current node
            // if the indegree becomes 0 then add that adjacent node in to the queue data structure
            for(int adjacentNode : adjacencyList.get(currentNode)){
                inDegree[adjacentNode]--;   // decreasing the indegree of the adjacent node

                // if the indegree of adjacent node becomes 0 then add it to the queue
                if(inDegree[adjacentNode] == 0){
                    queue.offer(adjacentNode);
                }
            }
            topologicalSorting.add(currentNode);    // finally add the current node into the final topological sorting list
        }

        System.out.println("Topological sorting of the given graph is : " + topologicalSorting);
    }
}
