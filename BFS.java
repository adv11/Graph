package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * TC = O(N+E) : N is time taken to visit N nodes, and E is time taken for visiting through adjacent nodes overall.
 * Sc = O(N+E) + O(N) + O(E) : O(N+E) for final data structure, O(N) for boolean array and O(E) for queue data structure.
 */
public class BFS {
    public static ArrayList<Integer> bfsGraph(int numberOfNodes, ArrayList<ArrayList<Integer>> adjacencyList){
        int connectedComponents = 0;
        ArrayList<Integer> bfs = new ArrayList<>(); // for storing resultant bfs traversal
        boolean[] visitedNodes = new boolean[numberOfNodes+1];  // for checking if the node is visited or not

        // running loop for all nodes
        for(int i=1 ; i<=numberOfNodes ; i++){

            // checking if node is already visited or not
            if(!visitedNodes[i]){

                // queue data structure for bfs traversal
                Queue<Integer> queue = new LinkedList<>();
                queue.add(i);
                visitedNodes[i] = true;

                // while queue has an element
                // run the loop
                while(!queue.isEmpty()){

                    // this is currently visiting node
                    int currentVisitingNode = queue.poll();
                    // adding it to the final resultant data structure
                    bfs.add(currentVisitingNode);

                    // adding adjacent nodes of currentVisitingNode
                    for(int adjacentNode : adjacencyList.get(currentVisitingNode)){

                        // checking if the adjacent node is already visited or not
                        // if not then visit it otherwise not
                        if(!visitedNodes[adjacentNode]){
                            visitedNodes[adjacentNode] = true;  // marking visited
                            queue.add(adjacentNode);    // adding it to the queue data structure
                        }
                    }
                }
                connectedComponents++;
            }
        }

        System.out.println("Number of connected components in the given graph is : " + connectedComponents);
        return bfs;
    }
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
        ArrayList<Integer> bfs = bfsGraph(numberOfNodes, adjacencyList);

        // printing the resultant bfs traversal
        System.out.println("BFS Traversal of the given graph is:");
        for(int node : bfs){
            System.out.print(node + " ");
        }
    }
}
