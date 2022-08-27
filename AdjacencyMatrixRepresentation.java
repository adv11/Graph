package Graph;

class Graph{
    private int numOfNodes;
    private boolean directed;
    private boolean weighted;
    private float[][] matrix;

    /*
    This will allow us to safely add weighted graphs in our class since
    we will be able to check whether an edge exists without relying
    on specific special values (like 0)
    */
    private boolean[][] isSetMatrix;

    // constructor
    public Graph(int numOfNodes, boolean directed, boolean weighted){
        this.directed = directed;
        this.weighted = weighted;
        this.numOfNodes = numOfNodes;

        // Simply initializes our adjacency matrix to the appropriate size
        matrix = new float[numOfNodes][numOfNodes];
        isSetMatrix = new boolean[numOfNodes][numOfNodes];
    }

    /*
    Since matrices for directed graphs are symmetrical, we have to add
    [destination][source] at the same time as [source][destination]

    Now, let's write a method that allows us to add edges. We want to make
    sure that in case the graph is weighted and a weight isn't provided we
    set the edge value to 0, and if isn't weighted to simply add 1.
    */
    public void addEdge(int source, int destination){
        int valueToAdd = 1;
        if(weighted){
            valueToAdd = 0;
        }

        matrix[source][destination] = valueToAdd;
        isSetMatrix[source][destination] = true;


        // if the graph is directed then
        if(!directed){
            matrix[destination][source] = valueToAdd;
            isSetMatrix[destination][source] = true;
        }
    }

    /*
    In case the graph isn't weighted and a weight is provided, we simply
    ignore that and set the [source,destination] value to 1, indicating
    that an edge does exist:
    */
    public void addEdge(int source, int destination, float weight){
        float valueToAdd = weight;
        if(!weighted){
            valueToAdd = 1;
        }

        matrix[source][destination] = valueToAdd;
        isSetMatrix[source][destination] = true;

        // if graph is undirected then
        if(!directed){
            matrix[destination][source] = valueToAdd;
            isSetMatrix[destination][source] = true;
        }
    }

    // method for printing adjacency matrix
    public void printMatrix(){
        for(int i=0 ; i<numOfNodes ; i++){
            for(int j=0 ; j<numOfNodes ; j++){
                // We only want to print the values of those positions
                // that have been marked as set
                if(isSetMatrix[i][j]){
                    System.out.format("%8s", String.valueOf(matrix[i][j]));
                }else{
                    System.out.format("%8s", "/ ");
                }
            }
            System.out.println();
        }
    }


    // a convenience method that prints out the edges in a more
    // understandable way
    public void printEdges(){
        for(int i=0 ; i<numOfNodes ; i++){
            System.out.print("Node " + i + " is connected to: ");
            for(int j=0 ; j<numOfNodes ; j++){
                if(isSetMatrix[i][j]){
                    System.out.print(j + " ");
                }
            }
            System.out.println();
        }
    }

    // method for checking if there is an edge or not
    public boolean hasEdge(int source, int destination){
        return isSetMatrix[source][destination];
    }

    // method for getting edge value
    public Float getEdgeValue(int source, int destination){
        if(!weighted || !isSetMatrix[source][destination]){
            return null;
        }
        return matrix[source][destination];
    }
}
public class AdjacencyMatrixRepresentation {
    public static void main(String[] args) {
        Graph graph = new Graph(5, false, true);

        graph.addEdge(0, 2, 19);
        graph.addEdge(0, 3, -2);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3); // The default weight is 0 if weighted == true
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        // printing adjacency matrix
        graph.printMatrix();

        System.out.println();
        System.out.println();

        // printing edges
        graph.printEdges();

        System.out.println();

        // checking edge is present or not
        System.out.println("Does an edge from 1 to 0 exist?");
        if (graph.hasEdge(0,1)) {
            System.out.println("Yes");
        }else{
            System.out.println("No");
        }
    }
}
