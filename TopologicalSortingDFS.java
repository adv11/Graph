package Graph;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class TopologicalSortingDFS {
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
        boolean[] visitedNodes = new boolean[numberOfNodes+1];  // visited array
        Stack<Integer> stack = new Stack<>();   // Stack data structure for storing topological sorting

        // Run loop for all nodes
        for(int node=1 ; node<=numberOfNodes ; node++){
            // If not visited then call for topologicalSort method
            if(!visitedNodes[node]){
                topologicalSort(node, adjacencyList, visitedNodes, stack);
            }
        }

        // Topological sorting of the given graph is printed here
        System.out.println("Topological sorting of the given graph is :");
        while(!stack.isEmpty()){
            System.out.print(stack.pop() + " ");
        }
    }
    public static void topologicalSort(int node, ArrayList<ArrayList<Integer>> adjacencyList, boolean[] visitedNodes, Stack<Integer> stack){
        visitedNodes[node] = true;  // mark the current node visited

        // now check for all adjacent nodes of the current node
        for(int adjacentNode : adjacencyList.get(node)){
            // if not visited then call method
            if(!visitedNodes[adjacentNode]){
                topologicalSort(adjacentNode, adjacencyList, visitedNodes, stack);
            }
        }
        stack.add(node);    // at last add the current node into the stack data structure
    }
}
