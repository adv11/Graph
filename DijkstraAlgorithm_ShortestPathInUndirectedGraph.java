package Graph;
import java.util.*;

/**
 * Input :
 * Adjacency List :
 * 1 -> (2, 2), (4, 1)
 * 2 -> (1, 2), (5, 5), (3, 4)
 * 3 -> (2, 4), (4, 3), (5, 1)
 * 4 -> (1, 1), (3, 3)
 * 5 -> (2, 5), (3, 1)
 * Output :
 * Distance array is:
 * Minimum distance of node 1 from source node 1 is : 0
 * Minimum distance of node 2 from source node 1 is : 2
 * Minimum distance of node 3 from source node 1 is : 4
 * Minimum distance of node 4 from source node 1 is : 1
 * Minimum distance of node 5 from source node 1 is : 5
 */
class Pair2 implements Comparator<Pair2>{
    int node;
    int edgeWeight;

    public Pair2(int node, int edgeWeight) {    // user defined constructor
        this.node = node;
        this.edgeWeight = edgeWeight;
    }

    public Pair2() {    // default constructor

    }

    public int compare(Pair2 p1, Pair2 p2) {    // compare method for comparing edge weights of two pairs
//        if (p1.edgeWeight < p2.edgeWeight) {
//            return -1;
//        }
//        if (p1.edgeWeight > p2.edgeWeight) {
//            return 1;
//        }
//        return 0;

        return Integer.compare(p1.edgeWeight, p2.edgeWeight);
    }
}

public class DijkstraAlgorithm_ShortestPathInUndirectedGraph {
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

        int[] distance = new int[numberOfNodes + 1];    // distance array
        Arrays.fill(distance, Integer.MAX_VALUE);

        System.out.println("Enter the source node:");   // source node
        int sourceNode = sc.nextInt();
        distance[sourceNode] = 0;

        dijkstraAlgorithm(adjacencyList, distance, sourceNode, numberOfNodes);  // method calling


        // printing the distance array
        System.out.println("\n\nDistance array is:");
        for (int node = 1; node <= numberOfNodes; node++) {
            System.out.println("Minimum distance of node " + node + " from source node " + sourceNode + " is : " + distance[node]);
        }
    }

    public static void dijkstraAlgorithm(ArrayList<ArrayList<Pair2>> adjacencyList, int[] distance, int sourceNode, int numberOfNodes) {
        PriorityQueue<Pair2> minPQ = new PriorityQueue<>(numberOfNodes, new Pair2());   // min priority queue
        minPQ.add(new Pair2(sourceNode, 0));    // add new pair(source node, 0)


        // while priority queue is not empty
        while (!minPQ.isEmpty()) {
            Pair2 currentPair = minPQ.poll();   // extracting top pair from the priority queue
            int currentNode = currentPair.node; // this is current node

            // exploring adjacent nodes of the current node
            for (Pair2 adjacentPair : adjacencyList.get(currentNode)) {

                /* if it satisfies this condition:
                   distance[current node] + edge weight(current node, adjacent node) < distance[adjacent node] then
                   add distance[adjacent node] = distance[current node] + edge weight(current node, adjacent node)
                   and add new pair into the min priority queue : new pair(adjacent node, distance[adjacent node])
                 */
                if ((distance[currentNode] + adjacentPair.edgeWeight) < distance[adjacentPair.node]) {
                    distance[adjacentPair.node] = (distance[currentNode] + adjacentPair.edgeWeight);
                    minPQ.add(new Pair2(adjacentPair.node, distance[adjacentPair.node]));
                }
            }
        }
    }
}