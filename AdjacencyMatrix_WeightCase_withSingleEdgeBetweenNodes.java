package Graph;

import java.util.Scanner;

/**
 * An adjacency matrix is given input with edge weights. ie, Some value for adjacent and 0 for non-adjacent.
 *
 * Sample Input:
 * The first line contains a single integer n – The number of nodes.
 * Each of the next n lines contains n space separated integers. The j-th integer in the i-th row denotes a[i][j].
 * 3
 * 0 10 5
 * 2 7 9
 * 3 2 0
 */
public class AdjacencyMatrix_WeightCase_withSingleEdgeBetweenNodes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of nodes:");
        int numberOfNodes = sc.nextInt();

        // Adjacency matrix creation with dimension nxn
        int[][] adjacencyMatrix = new int[numberOfNodes][numberOfNodes];


        // inputting weights
        // if nodes are adjacent then put the edge weight else put 0
        System.out.println("Enter the edge weight if non-adjacent then put 0:");
        for(int i=0 ; i<numberOfNodes ; i++){
            for(int j=0 ; j<numberOfNodes ; j++){
                adjacencyMatrix[i][j] = sc.nextInt();
            }
        }

        System.out.println("The adjacent matrix is:");
        for(int[] row : adjacencyMatrix){
            for(int ele : row){
                System.out.print(ele + " ");
            }
            System.out.println();
        }
    }
}
