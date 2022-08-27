package Graph;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * TC = O(N+E) : N is time taken to visit N nodes, and E is time taken for visiting through adjacent nodes overall.
 * SC = O(N+E) + O(N) + O(E) : O(N+E) for final data structure, O(N) for boolean array and O(E) for stack(while recursive calls) data structure.
 */
public class DFS {
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

        // calling bfs method
        ArrayList<Integer> dfs = dfsGraph(numberOfNodes, adjacencyList);

        // printing the resultant bfs traversal
        System.out.println("DFS Traversal of the given graph is:");
        for(int node : dfs){
            System.out.print(node + " ");
        }
    }
    public static ArrayList<Integer> dfsGraph(int numberOfNodes, ArrayList<ArrayList<Integer>> adjacencyList){
        ArrayList<Integer> dfs = new ArrayList<>(); // data structure for storing dfs traversal
        boolean[] visited = new boolean[numberOfNodes+1];   // for marking the nodes visited


        // run loop for all nodes
        for(int i=1 ; i<=numberOfNodes ; i++){
            if(!visited[i]){    // check if the node is not already visited
                dfsTraversal(i, adjacencyList, visited, dfs);
            }
        }
        return dfs;
    }
    public static void dfsTraversal(int node, ArrayList<ArrayList<Integer>> adjacencyList, boolean[] visited, ArrayList<Integer> dfs){
        dfs.add(node);  // add the current visiting node in to the final data structure
        visited[node] = true;   // mark current visiting node as visited

        // run recursive call for adjacent nodes of node
        for(int adjacentNode : adjacencyList.get(node)){
            // check if the adjacent is not already visited
            if(!visited[adjacentNode]){
                dfsTraversal(adjacentNode, adjacencyList, visited, dfs);
            }
        }
    }
}
