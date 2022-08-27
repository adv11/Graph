package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BipartiteGraph_GraphColouring_DFS {
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

        // method call
        boolean isBipartite = checkBipartite(adjacencyList, numberOfNodes);
        if(isBipartite){
            System.out.println("Graph is Bipartite.");
        }else{
            System.out.println("Graph is not Bipartite.");
        }
    }
    public static boolean checkBipartite(ArrayList<ArrayList<Integer>> adjacencyList, int numberOfNodes){
        int[] color = new int[numberOfNodes+1];     // color array for storing the color of the nodes
        Arrays.fill(color, -1);

        // run dfs for all nodes
        for(int node=1 ; node<=numberOfNodes ; node++){
            if(color[node] == -1){
                if(!dfs(node, adjacencyList, color)){
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean dfs(int node, ArrayList<ArrayList<Integer>> adjacencyList, int[] color){
        // start filling the first node as color 1
        if(color[node] == -1){
            color[node] = 1;
        }

        // now check for the adjacent nodes of the given node
        for(int adjacentNode : adjacencyList.get(node)){
            // if adjacent node's color is -1 then call dfs
            if(color[adjacentNode] == -1){
                color[adjacentNode] = 1 - color[node];  // fill opposite color of node's color
                if(!dfs(adjacentNode, adjacencyList, color)){
                    return false;
                }
            }
            // if adjacent node has already colored then check for similarity of colors of adjacent node and given node
            // if both colors are same then it is not a bipartite graph so return false.
            else if(color[adjacentNode] == color[node]){
                return false;
            }
        }
        return true;
    }
}
