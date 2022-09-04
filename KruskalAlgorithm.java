package Graph;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Steps for Kruskal's algorithm:
 * 1) Sort all the edges in non-decreasing order of their weight.
 * 2) Pick the smallest edge. Check if it forms a cycle with the spanning tree formed so far. If cycle is not formed, include this edge. Else, discard it.
 * 3) Repeat step#2 until there are (V-1) edges in the spanning tree.
 *
 * Output:
 * costMST = 16
 * 0 – 1
 * 1 – 2
 * 1 – 4
 * 0 – 3
 * Time Complexity: O(E * logE) + O(E * 4*alpha), E*logE for sorting and E*4*alpha for findParent operation ‘E’ times
 * Space Complexity: O(N). Parent array+Rank Array
 */
class kruskalNode{  // class for node which stores u, v and edge weight
    int node1;
    int node2;
    int edgeWeight;

    // constructor
    public kruskalNode(int node1, int node2, int edgeWeight){
        this.node1 = node1;
        this.node2 = node2;
        this.edgeWeight = edgeWeight;
    }
}

// used defined comparator
class sortComparator implements Comparator<kruskalNode>{
    @Override
    public int compare(kruskalNode o1, kruskalNode o2) {
        return Integer.compare(o1.edgeWeight, o2.edgeWeight);
    }
}
public class KruskalAlgorithm {

    // find() operation
    public static int findParent(int node, int[] parent){
        if(node == parent[node]){
            return node;
        }
        return parent[node] = findParent(parent[node], parent);
    }

    // union() operation
    public static void union(int u, int v, int[] parent, int[] rank){
        u = findParent(u, parent);
        v = findParent(v, parent);

        if(rank[u] < rank[v]){
            parent[u] = v;
        }else if(rank[v] < rank[u]){
            parent[v] = u;
        }else{
            parent[v] = u;
            rank[u]++;
        }
    }
    public static void kruskalAlgorithm(ArrayList<kruskalNode> arrayList, int numberOfEdges){
        Collections.sort(arrayList, new sortComparator());  // sorting arraylist according to the edge weight
        int[] parent = new int[numberOfEdges];  // will store parent of the nodes
        int[] rank = new int[numberOfEdges];    // will store the rank of the tree forming

        // initialization of parent and rank array
        for(int i=0 ; i<numberOfEdges ; i++){
            parent[i] = i;
            rank[i] = 0;
        }

        int costMst = 0;    // for storing cost of the final MST
        ArrayList<kruskalNode> mst = new ArrayList<>();     // for storing final MST formed

        // traversing arraylist
        for(kruskalNode node : arrayList){
            // if both nodes are not in the same components then
            // union both nodes and add edge weight of u and v into the costMST variable
            // add both nodes into the mst list
            if(findParent(node.node1, parent) != findParent(node.node2, parent)){
                costMst += node.edgeWeight;
                mst.add(node);
                union(node.node1, node.node2, parent, rank);
            }
        }

        // printing final MST
        System.out.println("\n\nCost of the MST is : " + costMst);
        System.out.println("Final MST will be : ");
        for(kruskalNode node : mst){
            System.out.println(node.node1 + " - " + node.node2);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of edges:");
        int numberOfEdges = sc.nextInt();

        ArrayList<kruskalNode> arrayList = new ArrayList<>();
        System.out.println("Enter data:");
        for(int i=1 ; i<=numberOfEdges ; i++){
            System.out.println("Enter u:");     // inputting u
            int u = sc.nextInt();

            System.out.println("Enter v:");     // inputting v
            int v = sc.nextInt();

            System.out.println("Enter edge weight:");       // inputting edge weight
            int edgeWeight = sc.nextInt();

            arrayList.add(new kruskalNode(u, v, edgeWeight));       // adding into the arraylist
        }

        // calling kruskalAlgorithm() method
        kruskalAlgorithm(arrayList, numberOfEdges);
    }
}
