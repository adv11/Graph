package Graph;

import java.util.*;

public class BipartiteGraph_GraphColouring_BFS {
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
        // color array for storing the color of the nodes
        // -1 means Not visited
        // 0 means Black color
        // 1 means White color
        int[] color = new int[numberOfNodes+1];
        Arrays.fill(color, -1);


        // traversing for all nodes
        for(int i=1 ; i<=numberOfNodes ; i++){
            // if not visited then call recursion
            if(color[i] == -1){
                // if recursion call return false then return false else return true
                if(!isBipartiteGraph(i, color, adjacencyList)){
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean isBipartiteGraph(int node, int[] color, ArrayList<ArrayList<Integer>> adjacencyList){
        Queue<Integer> queue = new LinkedList<>();  // Queue data structure for BFS traversal
        queue.offer(node);  // add first node
        color[node] = 1;    // mark first node as white color


        // while the queue contains an element
        while(!queue.isEmpty()){
            int currentNode = queue.poll();     // current visiting node
            int currentNodesColor = color[currentNode];     // color of current visiting node


            // check for all adjacent nodes of current node
            for(int adjacentNode : adjacencyList.get(currentNode)){
                // if adjacent node is not visited then visit it.
                // mark adjacent node with opposite color of current node
                // and add the adjacent node into the queue
                if(color[adjacentNode] == -1){
                    color[adjacentNode] = 1 - currentNodesColor;
                    queue.offer(adjacentNode);
                }
                // if color of adjacent node is same as current node's color then return false
                else if(color[adjacentNode] == currentNodesColor){
                    return false;
                }
            }
        }
        return true;
    }
}
