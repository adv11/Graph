package Graph;
import java.util.Scanner;

/**
 * Case 1(Normal case) :
 * An adjacency matrix is given input as it is, i.e, 1 for adjacent nodes and 0 for non-adjacent nodes.
 *
 * Sample Input :
 * - The first line contains a single integer n denoting the number of nodes present in the graph.
 * - Each of the next n lines contains n space separated integers. The jth integer in the ith row denotes a[i][j].
 *
 * Example :
 * n = 3
 * 0 1 0
 * 1 1 0
 * 0 1 1
 */
public class AdjacencyMatrix_NormalCase {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of nodes:");
        int numberOfNodes = sc.nextInt();


        // adjacent matrix which stores true if the nodes are adjacent else stores false
        // dimension of adjacent matrix should be nxn where n denotes number of nodes in the graph
        boolean[][] adjacencyMatrixBool = new boolean[numberOfNodes][numberOfNodes];
        System.out.println("Enter 1 for adjacent nodes and 0 for non-adjacent nodes:");
        for(int i=0 ; i<numberOfNodes ; i++){
            for(int j=0 ; j<numberOfNodes ; j++){
                adjacencyMatrixBool[i][j] = (sc.nextInt() == 1);
            }
        }

        // printing adjacency matrix
        System.out.println("The adjacency matrix is:");
        for(boolean[] row : adjacencyMatrixBool){
            for(boolean ele : row){
                System.out.print(ele + " ");
            }
            System.out.println();
        }
    }
}
