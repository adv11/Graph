package Graph;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * TC = O(N+E)
 * SC = O(N+E) + O(N) + O(N)
 */
public class CycleDetection_inUndirectedGraphUsingDFS {
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

        // Method call
        boolean isCyclePresent = isCycle(numberOfNodes, adjacencyList);
        if(isCyclePresent){
            System.out.println("\nResult : Graph contains the cycle.");
        }else{
            System.out.println("\nResult : Graph doesn't contain the cycle.");
        }
    }
    public static boolean isCycle(int numberOfNodes, ArrayList<ArrayList<Integer>> adjacencyList){
        boolean[] visitedNodes = new boolean[numberOfNodes+1];  // for checking if the node is visited or not

        // running loop for all nodes
        for(int i=1 ; i<=numberOfNodes ; i++){
            if(!visitedNodes[i]){
                // for first node, the parent node will always be -1 because there is no parent node for
                // first node
                if(checkForCycle(i, -1, visitedNodes, adjacencyList)){
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean checkForCycle(int currentNode, int parentNode, boolean[] visitedNodes, ArrayList<ArrayList<Integer>> adjacencyList){
        visitedNodes[currentNode] = true;   // mark the current node visited

        // now check for all adjacent nodes of the current node
        for(int adjacentNode : adjacencyList.get(currentNode)){
            // if the adjacent node is not visited then call recursive function
            if(!visitedNodes[adjacentNode]){
                if(checkForCycle(adjacentNode, currentNode, visitedNodes, adjacencyList)){
                    return true;
                }
            }
            // if adjacentNode is not equal to parentNode then it means that it is already visited by some other path,
            // so it's a cycle. So return true
            else if(adjacentNode != parentNode){
                return true;
            }
        }
        return false;
    }
}
