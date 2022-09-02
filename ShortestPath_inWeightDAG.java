package Graph;
import java.util.*;

/**
 * Given a graph where we have given nodes with their edge weights.
 * We have to calculate the shortest from the given source node to all the remaining nodes
 * and store those distances into an array named distance.
 * Example:
 * Input : (node, edge weight)
 * 1 -> (2, 2), (5, 1)
 * 2 -> (3, 3)
 * 3 -> (4, 6)
 * 4 -> X
 * 5 -> (3, 2), (6, 4)
 * 6 -> (4, 1)
 * Output :
 * distance array : [0, 2, 3, 6, 1, 5]
 */
// class for storing node with their edge weight
class graphNode{
    final private int node; // node
    final private int edgeWeight;   // edge weight
    
    // constructor
    public graphNode(int node, int edgeWeight){     // constructor
        this.node = node;
        this.edgeWeight = edgeWeight;
    }
    public int getNode(){   // getter method for node
        return this.node;
    }
    public int getEdgeWeight(){ // getter method for edge weight
        return this.edgeWeight;
    }
}
public class ShortestPath_inWeightDAG {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<ArrayList<graphNode>> adjacencyList = new ArrayList<>();  // adjacency list

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

            for(int j=0 ; j<countOfAdjacentNodes ; j++){

                // Taking input of node
                System.out.println("Enter node:");
                int adjacentNode = sc.nextInt();

                // Taking input of edge weight of that node
                System.out.println("Enter edge weight");
                int edgeWeight = sc.nextInt();

                // adding the adjacent node and edge weight into the adjacency list
                adjacencyList.get(i).add(new graphNode(adjacentNode, edgeWeight));
            }
        }

        System.out.println("Enter the source node:");   // inputting source node
        int sourceNode = sc.nextInt();

        shortestPath(sourceNode, adjacencyList, numberOfNodes); // shortest path method calling
    }
    public static void shortestPath(int sourceNode, ArrayList<ArrayList<graphNode>> adjacencyList, int numberOfNodes){
        Stack<Integer> topologicalSortStack = new Stack<>();    // stack for topological sort
        int[] distance = new int[numberOfNodes+1];  // distance array for storing the distances from the source node
        Arrays.fill(distance, Integer.MAX_VALUE);   // fill the array with infinity
        distance[sourceNode] = 0;   // put 0 in the distance[source]
        boolean[] visited = new boolean[numberOfNodes+1];   // visited array

        // Step 1: Topological sort method calling for all nodes
        for(int node=1 ; node<=numberOfNodes ; node++){
            if(!visited[node]){
                topologicalSort(node, topologicalSortStack, adjacencyList, visited);
            }
        }

        // Step 2: Perform other step
        // if distance[currentNode] != infinity then check for the condition
        // distance[currentNode] + adjacentNode.getEdgeWeight() < distance[adjacentNode.getNode() if it is true then add this distance into distance array
        // distance[adjacentNode.getNode()] = distance[currentNode] + adjacentNode.getEdgeWeight()
        while(!topologicalSortStack.isEmpty()){
            int currentNode = topologicalSortStack.pop();
            if(distance[currentNode] != Integer.MAX_VALUE) {
                for (graphNode adjacentNode : adjacencyList.get(currentNode)) {
                    if ((distance[currentNode] + adjacentNode.getEdgeWeight()) < distance[adjacentNode.getNode()]) {
                        distance[adjacentNode.getNode()] = distance[currentNode] + adjacentNode.getEdgeWeight();
                    }
                }
            }
        }

        // At last printing the distance array
        System.out.println("Distance array is :");
        for(int i=1 ; i<distance.length ; i++){
            System.out.print(distance[i] + " ");
        }
    }

    // Method for topological sort using DFS
    public static void topologicalSort(int node, Stack<Integer> stack, ArrayList<ArrayList<graphNode>> adjacencyList, boolean[] visited){
        visited[node] = true;   // put true means visited in the source node

        // now explore all the adjacent nodes of the current node and if they are not
        // visited then visit them using recursion
        for(graphNode adjacentNode : adjacencyList.get(node)){
            if(!visited[adjacentNode.getNode()]){
                topologicalSort(adjacentNode.getNode(), stack, adjacencyList, visited);
            }
        }
        stack.add(node);    // at last add the current node into the stack
    }
}
