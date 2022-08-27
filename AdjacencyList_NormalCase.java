package Graph;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * An adjacency list is given input as it is. ie, line number denotes vertices,
 * and numbers on those lines denote adjacent nodes.
 * Sample Input:
 * The first line contains a single integer n – The number of nodes.
 * Each of the next n lines contains first number J  and followed by J space
 * separated integers. The ith row denote ith node j-th integer in the i-th
 * row denotes an adjacent to that node .
 * 3
 * 2	1 2
 * 1	2
 * 2	0 1
 */
public class AdjacencyList_NormalCase {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        System.out.println("Enter the number of nodes:");
        int numberOfNodes = sc.nextInt();

        for(int i=0 ; i<numberOfNodes ; i++){
            adjList.add(new ArrayList<>());
            int countOfConnectedNodes = sc.nextInt();

            for(int j=0 ; j<countOfConnectedNodes ; j++){
                int nodeValue = sc.nextInt();
                adjList.get(i).add(nodeValue);
            }
        }

        System.out.println("Adjacency list is :");
        for(int i=0 ; i<adjList.size() ; i++){
            System.out.print(i + " : ");
            for(int k : adjList.get(i)){
                System.out.print(k + " ");
            }
            System.out.println();
        }
    }
}
