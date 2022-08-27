package Graph;

import java.util.Arrays;
import java.util.Scanner;

/**
 * A weighted Edge List is given input with multiple edges. ie, A single pair of nodes can have multiple
 * edges between them, and we want to keep only min weight edge.
 * Sample Input:
 * The first line of each test case contains two space-separated integers N (total vertices) and M (total edges).
 * Each of the next M lines contains three space-separated integers u, v and w denoting that vertices u and v are
 * connected by an edge having weight w.
 * 3 5
 * 0 3 10
 * 1 5 2
 * 3 7 -2
 * 0 3 2
 * 2 4 6
 */
public class AdjacencyMatrix_WeightCase_withMultipleEdgeBetweenNodes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfNodes = 7;
        int numberOfEdges = 5;

        // dimension will be nxn where n denotes max value of node
        int[][] adjMatrix = new int[numberOfNodes+1][numberOfNodes+1];

        //adjMatrix initialize with Infinity
        for (int[] matrix : adjMatrix) {
            Arrays.fill(matrix, Integer.MAX_VALUE);
        }

        System.out.println("Enter the values:");
        for (int i = 0; i < numberOfEdges; i++) {
            int from = sc.nextInt(), to = sc.nextInt(), cost = sc.nextInt();
            adjMatrix[from][to] = Math.min(adjMatrix[from][to], cost);

            // if undirected: add following
            adjMatrix[to][from] = adjMatrix[from][to];
        }

        System.out.println("The adjacency matrix is:");
        for(int[] matrix : adjMatrix){
            for(int ele : matrix){
                System.out.print(ele + " ");
            }
            System.out.println();
        }
    }
}
