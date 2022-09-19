package Graph;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 * TC = O(N+E)
 * SC = O(N+E) + O(N) + O(N) (for transposeGraph, Visited array, Stack)
 * Given a graph and the number of nodes present in the graph.
 * We have to find all the strongly connected components.
 * Applications: SCC algorithms can be used as a first step in many graph
 * algorithms that work only on strongly connected graph. In social networks,
 * a group of people are generally strongly connected (For example, students
 * of a class or any other common place). Many people in these groups generally
 * like some common pages or play common games. The SCC algorithms can be used
 * to find such groups and suggest the commonly liked pages or games to the people
 * in the group who have not yet liked commonly liked a page or played a game.
 */
public class KosarajuAlgorithm {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of testcases:");
        int numberOfTestcases = sc.nextInt();

        while (numberOfTestcases-- > 0) {
            ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>();

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

                // taking input of the particular adjacent nodes to the node i
                System.out.println("Enter adjacent nodes:");
                for (int j = 0; j < countOfAdjacentNodes; j++) {

                    // taking input of adjacent node
                    int adjacentNode = sc.nextInt();
                    // adding the adjacent node to the particular node i
                    adjacencyList.get(i).add(adjacentNode);
                }
            }

            // method calling
            kosarajuAlgo(adjacencyList, numberOfNodes);
            System.out.println();
        }
    }

    public static void kosarajuAlgo(ArrayList<ArrayList<Integer>> adjacencyList, int numberOfNodes) {
        // STEP 1 : Perform topological sort in the given graph
        boolean[] visited = new boolean[numberOfNodes + 1]; // for marking the nodes as visited
        Stack<Integer> topoSort = new Stack<>();    // stack for DFS : storing topological sorting of the nodes
        // calling DFS
        for (int node = 1; node <= numberOfNodes; node++) {
            if (!visited[node]) {   // if the node is not visited then run DFS call
                dfs(node, topoSort, adjacencyList, visited);
            }
        }

        // STEP 2 : Transpose the given graph (Reverse the direction of the edges)
        ArrayList<ArrayList<Integer>> transposeGraph = new ArrayList<>();
        for (int i = 1; i <= numberOfNodes + 1; i++) {
            transposeGraph.add(new ArrayList<>());
        }
        // visiting all nodes in the graph
        for (int node = 1; node <= numberOfNodes; node++) {
            visited[node] = false;  // unvisit the node because it is already visited in first step so remove it
            for (Integer adjacentNode : adjacencyList.get(node)) {
                transposeGraph.get(adjacentNode).add(node); // reversing the direction of the edge
            }
        }

        // STEP 3 : Run reverse DFS according to the topological sort
        while (!topoSort.isEmpty()) {
            int currentNode = topoSort.pop();
            // if the node is not visited
            if (!visited[currentNode]) {
                System.out.print("Strong connected component : ");
                reverseDFS(currentNode, transposeGraph, visited);   // calling reverse DFS
                System.out.println();
            }
        }
    }

    // Normal DFS method
    public static void dfs(int node, Stack<Integer> topoSort, ArrayList<ArrayList<Integer>> adjacencyList, boolean[] visited) {
        visited[node] = true;
        for (Integer adjacentNode : adjacencyList.get(node)) {
            if (!visited[adjacentNode]) {
                dfs(adjacentNode, topoSort, adjacencyList, visited);
            }
        }
        topoSort.push(node);
    }

    // Reversed DFS method for transpose graph
    public static void reverseDFS(int currentNode, ArrayList<ArrayList<Integer>> transposeGraph, boolean[] visited) {
        visited[currentNode] = true;

        // printing elements of the SCC
        System.out.print(currentNode + " ");
        for (Integer adjacentNode : transposeGraph.get(currentNode)) {
            if (!visited[adjacentNode]) {
                reverseDFS(adjacentNode, transposeGraph, visited);
            }
        }
    }
}
