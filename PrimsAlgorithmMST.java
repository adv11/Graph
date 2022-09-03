package Graph;
import java.util.*;

/**
 * Input :
 * Adjacency List :
 * 1 -> (2, 2), (4, 6)
 * 2 -> (1, 2), (4, 8), (3, 3), (5, 5)
 * 3 -> (2, 3), (5, 7)
 * 4 -> (1, 6), (2, 8)
 * 5 -> (2, 5), (3, 7)
 * Output :
 * MST of the given graph is :
 * Parent of node 1 is : 1
 * Parent of node 2 is : 1
 * Parent of node 3 is : 2
 * Parent of node 4 is : 1
 * Parent of node 5 is : 2
 */
public class PrimsAlgorithmMST {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<ArrayList<Pair2>> adjacencyList = new ArrayList<>();

        // Taking input for number of nodes
        System.out.println("Enter the number of nodes:");
        int numberOfNodes = sc.nextInt();

        // initializing adjacencyList with new ArrayList<>()
        for (int i = 1; i <= numberOfNodes + 1; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        // taking input of adjacent nodes of particular node
        for (int i = 1; i <= numberOfNodes; i++) {
            System.out.println("Enter number of adjacent nodes to the node : " + i);
            int countOfAdjacentNodes = sc.nextInt();

            for (int j = 0; j < countOfAdjacentNodes; j++) {
                // node value
                System.out.println("Enter node:");
                int node = sc.nextInt();

                // edge weight
                System.out.println("Enter edge weight:");
                int edgeWeight = sc.nextInt();

                // adding (node, edge weight) in to the ith index of adjacency list
                adjacencyList.get(i).add(new Pair2(node, edgeWeight));
            }
        }

        primBruteForce(adjacencyList, numberOfNodes);   // Brute force Approach
        primOptimised(adjacencyList, numberOfNodes);    // Optimised Approach
    }

    // Prim's algorithm : Brute Force Approach
    // TC : O(N^2)
    public static void primBruteForce(ArrayList<ArrayList<Pair2>> adjacencyList, int numberOfNodes){
        // for storing the minimum distance
        int[] key = new int[numberOfNodes+1];
        Arrays.fill(key, Integer.MAX_VALUE);
        key[1] = 0;

        // for marking the nodes as visited
        boolean[] mst = new boolean[numberOfNodes+1];
        Arrays.fill(mst, false);

        // for storing the parent of the nodes
        int[] parent = new int[numberOfNodes+1];
        Arrays.fill(parent, -1);

        // Run the loop equal to number of edges because in MST there are (n-1) edges where n is number of nodes
        for(int i=1 ; i<=numberOfNodes-1 ; i++){
            int minDistance = Integer.MAX_VALUE;    // for checking minimum distance node in key array
            int node = 0;   // storing the index of key array which has minimum distance

            // Run loop till numberOfNodes and check if the distance is minimum and the node is not visited
            for(int j=1 ; j<=numberOfNodes ; j++){
                if(!mst[j] && key[j] < minDistance){
                    minDistance = key[j];
                    node = j;
                }
            }

            // now mark the node visited
            mst[node] = true;

            // check for adjacent nodes of the current node
            for(Pair2 adjacentNode : adjacencyList.get(node)){

                // if the adjacent node is not visited and
                // adjacent's edge weight < key[adjacent node] then store the adjacent's edge into the key[adjacent node]
                // and store current node as parent node of adjacent node
                if(!mst[adjacentNode.node] && adjacentNode.edgeWeight < key[adjacentNode.node]){
                    parent[adjacentNode.node] = node;
                    key[adjacentNode.node] = adjacentNode.edgeWeight;
                }
            }
        }

        System.out.println("\n\nMST of the given graph is (Brute Force method):");
        for(int node=1 ; node<=numberOfNodes ; node++){
            if(parent[node] != -1) {
                System.out.println("Parent of node " + node + " is : " + parent[node]);
            }else{
                System.out.println("Parent of node " + node + " is : 1");
            }
        }
    }

    // Prim's Algorithm : Optimised Approach
    // TC : O(N * log N)
    public static void primOptimised(ArrayList<ArrayList<Pair2>> adjacencyList, int numberOfNodes){
        // for storing the minimum distance
        int[] key = new int[numberOfNodes+1];
        Arrays.fill(key, Integer.MAX_VALUE);
        key[1] = 0;

        // for marking the nodes as visited
        boolean[] mst = new boolean[numberOfNodes+1];
        Arrays.fill(mst, false);

        // for storing the parent of the nodes
        int[] parent = new int[numberOfNodes+1];
        Arrays.fill(parent, -1);


        PriorityQueue<Pair2> minPQ = new PriorityQueue<>(numberOfNodes, new Pair2());
        minPQ.add(new Pair2(1, key[1]));

        // Run the loop equal to number of edges because in MST there are (n-1) edges where n is number of nodes
        for(int i=1 ; i<=numberOfNodes-1 ; i++){
            int node = Objects.requireNonNull(minPQ.poll()).node;
            // now mark the node visited
            mst[node] = true;

            // check for adjacent nodes of the current node
            for(Pair2 adjacentNode : adjacencyList.get(node)){
                // if the adjacent node is not visited and
                // adjacent's edge weight < key[adjacent node] then store the adjacent's edge into the key[adjacent node]
                // and store current node as parent node of adjacent node
                if(!mst[adjacentNode.node] && adjacentNode.edgeWeight < key[adjacentNode.node]){
                    parent[adjacentNode.node] = node;
                    key[adjacentNode.node] = adjacentNode.edgeWeight;
                    minPQ.add(new Pair2(adjacentNode.node, key[adjacentNode.node]));
                }
            }
        }

        System.out.println("\n\nMST of the given graph is (Optimised method):");
        for(int node=1 ; node<=numberOfNodes ; node++){
            if(parent[node] != -1) {
                System.out.println("Parent of node " + node + " is : " + parent[node]);
            }else{
                System.out.println("Parent of node " + node + " is : 1");
            }
        }
    }
}
