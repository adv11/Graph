package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * TC = O(N+E)
 * SC = O(N+E) + O(N) + O(N)
 */

// class for creating a pair of <node, node's parent node> for storing it to the queue data structure
class Pair{
    int node, parentNode;

    // constructor
    public Pair(int node, int parentNode){
        this.node = node;
        this.parentNode = parentNode;
    }
}
public class CycleDetection_inUndirectedGraphUsingBFS {
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
        // boolean array for checking if the node is visited or not
        boolean[] visitedNodes = new boolean[numberOfNodes+1];

        // running loop for all nodes
        for(int i=1 ; i<=numberOfNodes ; i++){
            // if not visited then go for recursive call
            if(!visitedNodes[i]){
                if(checkCycleInGraph(i, adjacencyList, visitedNodes)){
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean checkCycleInGraph(int node, ArrayList<ArrayList<Integer>> adjacencyList, boolean[] visitedNodes){
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(node, -1));    // parent node of first node is always -1 because there is no parent node of first node
        visitedNodes[node] = true;

        while(!queue.isEmpty()){
            int currentVisitingNode = queue.peek().node;    // this is the current visiting node
            int currentVisitingNodesParentNode = queue.peek().parentNode;    // this is parent of the current visiting node
            queue.poll();


            // checking for all the adjacent nodes of the current visiting node
            for(int adjacentNode : adjacencyList.get(currentVisitingNode)){
                // if adjacent node is not visited then visit it
                if(!visitedNodes[adjacentNode]){
                    queue.offer(new Pair(adjacentNode, currentVisitingNodesParentNode));
                    visitedNodes[adjacentNode] = true;
                }
                // if the adjacent node is visited, but it is not equal to the parent of the current
                // visiting node so how this adjacent node is visited before that means there is a cycle
                // that's why the adjacent node is already visited. So, return true.
                else if(currentVisitingNodesParentNode != adjacentNode){
                    return true;
                }
            }
        }
        return false;
    }
}
