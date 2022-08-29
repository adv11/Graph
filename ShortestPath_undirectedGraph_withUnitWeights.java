package Graph;
import java.util.*;

/**
 * Input : Given an undirected graph and a source node. We have to calculate the shortest distance from the given
 *         source node to all the nodes present in the graph.
 * Output :
 * Distances of all nodes from the source node 1 are as following:
 * Distance of node 1 from source node 1 is : 0
 * Distance of node 2 from source node 1 is : 1
 * Distance of node 3 from source node 1 is : 2
 * Distance of node 4 from source node 1 is : 1
 * Distance of node 5 from source node 1 is : 2
 * Distance of node 6 from source node 1 is : 3
 * Distance of node 7 from source node 1 is : 3
 * Distance of node 8 from source node 1 is : 4
 * Distance of node 9 from source node 1 is : 4
 */
public class ShortestPath_undirectedGraph_withUnitWeights {
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

        // Distance array : fill it with Infinity
        int[] distance = new int[numberOfNodes+1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        // source node
        System.out.println("Enter the source node : ");
        int sourceNode = sc.nextInt();

        // method calling
        modifiedBFS(adjacencyList, sourceNode, distance);

        // printing distances from the source node to the other nodes
        System.out.println("Distances of all nodes from the source node " + sourceNode + " are as following: ");
        for(int node=1 ; node<=numberOfNodes ; node++){
            System.out.println("Distance of node " + node + " from source node " + sourceNode + " is : " + distance[node]);
        }
    }
    public static void modifiedBFS(ArrayList<ArrayList<Integer>> adjacencyList, int sourceNode, int[] distance){
        Queue<Integer> queue = new LinkedList<>();  // queue data structure for BFS traversal
        queue.offer(sourceNode);    // append source node
        distance[sourceNode] = 0;   // add distance of source node to source node as 0

        // Now while queue has an element
        while (!queue.isEmpty()){
            int currentNode = queue.poll(); // current node

            // now perform action for adjacent nodes of current visiting node
            for(int adjacentNode : adjacencyList.get(currentNode)){

                // if the distance[currentNode] + 1 < distance[adjacentNode]
                // then add the distance and add that adjacent node into the queue
                if((distance[currentNode] + 1) < distance[adjacentNode]){
                    distance[adjacentNode] = distance[currentNode] + 1;
                    queue.offer(adjacentNode);
                }
            }
        }
    }
}
