/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Implementation of a directed graph as an adjacency list.
 */
public class DirectedGraph<T extends Comparable<? super T>> implements GraphInterface<T> {
    private LinkedDictionary<T, Vertex<T>> vertices;
    private int edgeCount;
    /**
     * Construct a new empty digraph.
     */
    public DirectedGraph() {
        vertices = new LinkedDictionary<>();
        edgeCount = 0;
    }
    /**
     * Adds a vertex with label {@code label} to the graph.
     *
     * @param label a unique label for the vertex in the graph.
     * @return true if the vertex is added; otherwise, false.
     */
    @Override
    public boolean addVertex(T label) {
        if (!vertices.contains(label)) {
            return vertices.add(label, new Vertex<T>(label)) == null;
        }
        return true;
    }
    /**
     * Retrieve the Vertex for the given label
     * 
     * @param label - key to look up the Vertex in the graph
     * @return Vertex
     */    
    public Vertex<T> getVertex(T label) {
        return vertices.getValue(label);        
    }
    
    /**
     * Adds an edge from vertex {@code begin} to vertex {@code end} with weight
     * {@code weight}.
     *
     * @param begin the label of the origin vertex.
     * @param end the label of the destination vertex.
     * @param weight the weight of the edge.
     * @return true if the edge is added; otherwise, false.
     */
    @Override
    public boolean addEdge(T begin, T end, double weight) {
        Vertex<T> beginV = vertices.getValue(begin);
        Vertex<T> endV = vertices.getValue(end);
        // If we can't find one of the end points, we can't have an edge.
        if (beginV == null || endV == null) {
            return false;
        }
        // Connect the edge.
        if (beginV.connect(endV, weight)) {
            edgeCount++;
            return true;
        }
        return false;
    }
    /**
     * Adds an edge from vertex {@code begin} to vertex {@code end} with weight
     * {@code weight}.
     *
     * @param begin the label of the origin vertex.
     * @param end the label of the destination vertex.
     * @return true if the edge is added; otherwise, false.
     */
    @Override
    public boolean addEdge(T begin, T end) {
        return addEdge(begin, end, 0);
    }
    /**
     * Determines if there is an edge from {@code begin} to {@code end}.
     *
     * @return true if there is an edge from {@code begin} to {@code end};
     * otherwise, false.
     */
    @Override
    public boolean hasEdge(T begin, T end) {
        Vertex<T> beginV;
        if (vertices.isEmpty()) {
            return false;
        }
        beginV = vertices.getValue(begin);
        if (beginV != null) {
            return beginV.hasEdge(end);
        }
        return false;
    }
    /**
     * This method determines if there is a path from the starting vertex to the end vertex.
     * 
     * @param start T starting vertex 
     * @param end T ending vertex
     * @return LinkedList<String>, empty if there is no path. A linked list of string vertex otherwise
     */
    public LinkedList<T> getPath(T start, T end){
        //linked list to be returned
        LinkedList<T> BFS = new LinkedList<>(); 
        if(start == null || end == null){//return an empty linked list if the start is null
            return BFS;
        }
        else if(start == end){//if the start and end are the same, then there is no path to return
            BFS.insert(0, end);
            return BFS;
        }
        else if(vertices.isEmpty()){
            return BFS;
        }
        else{
            QueueInterface<T> vertexQueue = new LinkedQueue<>();
            LinkedDictionary<T, T> path = new LinkedDictionary<>();
            Vertex<T> frontVertex = vertices.getValue(start);
            frontVertex.setVisited();
            vertexQueue.enqueue(frontVertex.getLabel()); //enqueues the front vertex 
            while(!vertexQueue.isEmpty()){
                try{
                    frontVertex = vertices.getValue(vertexQueue.dequeue());
                }catch(EmptyQueueException ex){
                    System.out.println(ex.getMessage());
                }
                ListInterface<VertexInterface> neighbors = frontVertex.getNeighbors();//get the neighbors 
                for(int i = 0; i < neighbors.getLength(); i++){//traverse
                    VertexInterface<T> neighbor = neighbors.getEntry(i);
                    //System.out.println(neighbor.getLabel());
                    if(!vertices.getValue(neighbor.getLabel()).isVisited()){//if it is not visited then set it as visited and enqueue and add
                        vertices.getValue(neighbor.getLabel()).setVisited();
                        vertexQueue.enqueue(neighbor.getLabel());
                        path.add(neighbor.getLabel(), frontVertex.getLabel());
                    }
                    if(vertices.getValue(neighbor.getLabel()).equals(end)){//if the vertex is the end then clear the queue to exit 
                        vertexQueue.clear();//exits out of the first while 
                        break;//breaks out of the 2nd while
                    }
                }
            }
            //construct traversal using the path linked dictionary
            BFS.insert(0, end);//inser the last vertex
            T vertex = end;//start at the end
            while(vertex != null){
                vertex = path.getValue(vertex);//get the predecessor using the key of the last vertex
                BFS.insert(0, vertex);//insert it at the beginning to reverse the order
            }
            //check if the list only has null and the end by checking the length, if its two it only has null and the end
            if(BFS.getLength() == 2){
                return BFS = new LinkedList<>();
            }
            return BFS;
        }
    }
    /**
     * Determines if the graph is empty.
     *
     * @return true if the graph is empty; otherwise, false.
     */
    @Override
    public boolean isEmpty() {
        return vertices.isEmpty();
    }
    /**
     * Get the number of vertices in the graph.
     *
     * @return the number of vertices in the graph (greater than or equal to
     * zero).
     */
    @Override
    public int getNumberOfVertices() {
        return vertices.getSize();
    }
    /**
     * Get the number of edges in the graph.
     *
     * @return the number of edges in the graph (greater than or equal to zero).
     */
    @Override
    public int getNumberOfEdges() {
        return edgeCount;
    }
    /**
     * Remove all vertices and edges from the graph.
     */
    @Override
    public void clear() {
        vertices.clear();
        edgeCount = 0;
    }
    
}
