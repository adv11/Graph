package Graph;

import java.util.ArrayList;
import java.util.Scanner;

public class ArticulationPointInGraph {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of test cases:");
        int numberOfTestcases = sc.nextInt();
        while (numberOfTestcases-- > 0){
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

            // calling the method
            printArticulationPoint(adjacencyList, numberOfNodes);
        }
    }
    public static void printArticulationPoint(ArrayList<ArrayList<Integer>> adjacencyList, int numberOfNodes){
        int[] visited = new int[numberOfNodes+1];   // visiting array for tall the nodes
        int[] timeOfInsertion = new int[numberOfNodes+1];   // time of insertion for all the nodes
        int[] minTimeOfInsertion = new int[numberOfNodes+1];    // minimum time of insertion for all the nodes
        int[] isArticulation = new int[numberOfNodes+1];    // array for marking the node as articulation point
        int timer = 0;  // timer variable for time of insertion

        // calling the DFS function for all the nodes
        for(int node=1 ; node<=numberOfNodes ; node++){
            // if the node is not visited
            if(visited[node] == 0){
                dfs(node, -1, adjacencyList, timeOfInsertion, minTimeOfInsertion, isArticulation, visited, timer);
            }
        }

        // printing the articulation point
        System.out.println("Articulation points in the given graph is :");
        for(int i=1 ; i<=numberOfNodes ; i++){
            if(isArticulation[i] == 1){
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
    public static void dfs(int source, int parent, ArrayList<ArrayList<Integer>> adjacencyList, int[] timeOfInsertion, int[] minTimeOfInsertion, int[] isArticulation, int[] visited, int timer){
        visited[source] = 1;
        timeOfInsertion[source] = minTimeOfInsertion[source] = timer++;
        int child = 0;
        for(Integer adjacentNode : adjacencyList.get(source)){
            // if the adjacent node is itself the parent node
            if(adjacentNode == parent){
                continue;
            }

            // if the adjacent node is not visited the call the DFS
            if(visited[adjacentNode] == 0){
                // call the DFS for the adjacent node
                dfs(adjacentNode, source, adjacencyList, timeOfInsertion, minTimeOfInsertion, isArticulation, visited, timer);
                // update the minimum time of insertion array for all the adjacent node
                minTimeOfInsertion[source] = Math.min(minTimeOfInsertion[source], minTimeOfInsertion[adjacentNode]);

                // condition for articulation point
                if(minTimeOfInsertion[adjacentNode] >= timeOfInsertion[source] && parent != -1){
                    isArticulation[source] = 1;
                }
                child++;    // count the individual child of the parent node
            }else{
                minTimeOfInsertion[source] = Math.min(minTimeOfInsertion[source], timeOfInsertion[adjacentNode]);
            }
        }
        // if the node has multiple children the make it as articulation point
        if(parent != -1 && child > 1){
            isArticulation[source] = 1;
        }
    }
}
