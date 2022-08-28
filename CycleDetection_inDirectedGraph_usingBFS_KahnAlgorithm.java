package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class CycleDetection_inDirectedGraph_usingBFS_KahnAlgorithm {
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

        if(isCyclic(adjacencyList, numberOfNodes)){
            System.out.println("Graph has cycle.");
        }else{
            System.out.println("Graph has not cycle.");
        }
    }
    public static boolean isCyclic(ArrayList<ArrayList<Integer>> adjacencyList, int numberOfNodes){
        int[] inDegree = new int[numberOfNodes+1];  // for storing indegree of the nodes
        for(ArrayList<Integer> adjacentNodesList : adjacencyList){
            for(int adjacentNode : adjacentNodesList){
                inDegree[adjacentNode]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int node=1 ; node<=numberOfNodes ; node++){ // adding the nodes into the queue with indegree 0
            if(inDegree[node] == 0){
                queue.offer(node);
            }
        }

        int visitedNodes = 0;   // for counting the nodes visited while BFS call
        while (!queue.isEmpty()){
            int currentNode = queue.poll(); // current node
            visitedNodes++;

            // adjacent nodes of the current node
            for(int adjacentNode : adjacencyList.get(currentNode)){
                inDegree[adjacentNode]--;   // decreasing the indegree of the adjacent nodes

                // if the indegree becomes 0 then add that node into the queue data structure
                if(inDegree[adjacentNode] == 0){
                    queue.offer(adjacentNode);
                }
            }
        }

        // if the number of visited nodes is equal to the total number of nodes then it
        // means there is no cycle so return false else return true
        if(visitedNodes == numberOfNodes){
            return false;
        }
        return true;
    }
}
