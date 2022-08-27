package Graph;

import java.util.ArrayList;
import java.util.Scanner;

public class CycleDetection_inDirectedGraph_usingDFS {
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
        boolean[] vis = new boolean[numberOfNodes+1];
        boolean[] dfsVis = new boolean[numberOfNodes+1];    // In this array we have to perform backtracking. When there is no cycle found so unmark the nodes.

        // Now run loop for all nodes
        for(int node=1 ; node<=numberOfNodes ; node++){
            // if node is not visited then visit it
            if(!vis[node]){
                if(checkCycle(node, adjacencyList, vis, dfsVis)){
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean checkCycle(int node, ArrayList<ArrayList<Integer>> adjacencyList, boolean[] vis, boolean[] dfsVis){
        // mark the node visited in both vis and dfsVis array
        vis[node] = true;
        dfsVis[node] = true;

        // Now check for all adjacent nodes of current node
        for(int adjacentNode : adjacencyList.get(node)){
            // if adjacent node is not visited then visit it
            if(!vis[adjacentNode]){
                if(checkCycle(adjacentNode, adjacencyList, vis, dfsVis)){
                    return true;
                }
            }
            // if adjacent node is already visited that means it is visited in the same DFS call,
            // so it's a case of cycle formation. So return true
            else if(dfsVis[adjacentNode]){
                return true;
            }
        }
        dfsVis[node] = false;   // backtracking step
        return false;
    }
}
