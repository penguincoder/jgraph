import java.util.*;

public interface GraphInterface 
{

   //two constructors
   //default constructor makes a directed graph
   //constructor that takes a boolean (true for directed)


/**
 *  Makes the current graph empty.<br>
 *  Preconditions: None.<br>
 *  Postconditions: makes graph empty.<br>
 *  Throws: None.
 */
    public void makeEmpty();

/**
 *  Indicates if graph is empty.<br>
 *  Preconditions: None.<br>
 *  Postconditions: returns boolean true if graph is empty, false if not.<br>
 *  Throws: None.
 */
   public boolean isEmpty();

/**
 *  Returns an integer corresponding to thenumber of vertices in the graph.<br>
 *  Preconditions: None.<br>
 *  Postconditions: returns int.<br>
 *  THrows: None.
 */
   public int numVertices();

/**
 * Returns an integer corresponding to the numberof edges in the graph.<br>
 * Preconditions: None.<br>
 * Postconditions: returns int.<br>
 * Throws: NOne.
 */
   public int numEdges();

/**
 *  Adds a vertex to the graph.<br>
 *  Preconditions: Must be passed GraphNode to be inserted into the graph.<br>
 *  Postconditions: Adds the passed-in node to the graph.<br>
 *  Throws: None.
 */

   //add a vertex to the Graph
   public void addVertex(GraphNode myItem) throws GraphException;

/**
 *Adds an edge to the Graph.<br>
 *Preconditions: must be passed the searchkeys of the nodes that are to be connected.<br>
 *Postconditions: adds edge to graph.<br>
 *Throws: GraphException if searchkeys don't exist.
 */

   public void addEdge(Comparable searchKey1, Comparable searchKey2) throws GraphException;

/**
 * Adds a weighted edge to the graph.<br>
 * Preconditions: must be passed two search keys and a weight.<br>
 * Postconditions: adds weighted edge to the graph.<br>
 * Throws: GraphException if given invalid endpoints or weight value
 */

   public void addEdge(Comparable searchKey1, Comparable searchKey2, double weight) throws GraphException;

/**
 * Retrieves the weight of a given edge. <br>
 * Preconditions: must be given searchkeys of the endpoints of the edge.<br>
 * Postconditions: returns double value for the weight.<br>
 * Throws: GraphException if given invalid endpoints.
 */

   public double getWeight(Comparable searchKey1, Comparable searchKey2) throws GraphException;

/**
 *  Removes an edge from the graph.<br>
 *  Preconditions: must be passed searchKey valued for edge's endpoints.<br>
 *  Postconditions: removes edge from graph.<br>
 *  Throws: GraphException if invalid vertices.
 */

   public void removeEdge(Comparable searchKey1, Comparable searchKey2) throws GraphException;

/**
 *  Removes a vetex from the graph.<br>
 *  Preconditions: must be passed a searchKey to delete.<br>
 *  Postconditions: removes given searchKey.<br>
 *  Throws: GraphException if given invalid searchkey.
 */

   public GraphNode removeVertex(Comparable searchKey) throws GraphException;

/**
 *  Gets vertex from graph.<br>
 *  Preconditions: must be passed a valid searchKey.<br>
 *  Postconditions: returns a graphNode for the given searchKey.<br>
 *  Throws: GraphException if given an invaid searchKey. 
 */

   public GraphNode getVertex(Comparable searchKey) throws GraphException;

/**
 *  Gets vertex from graph.<br>
 *  Preconditions: must be passed integer index of node in list.<br>
 *  Postconditions: returns GraphNode at given index.<br>
 *  Throws: GraphException if given invalid index value.
 */

   public GraphNode getVertex(int index) throws GraphException;

/**
 *  Obtains a depth-first search of the grap from the given starting vertex.<br>
 *  Preconditions: must be passed a searchKey for the starting point of the search.<br>
 *  Postconditions: returns ArrayList of vertices along the search path.<br>
 *  Throws: GraphException if given invalid searchKey
 */

   public ArrayList dfs(Comparable searchKey) throws GraphException;

/**
 *  Obtains a breadth-first search of the graph.<br>
 *  Preconditions: must be passed searchKey of the starring point of the search.<br>
 *  Postconditions: returns an ArrayList of the vertices in the search.<br>
 *  Throws: GraphException if the searchKey is invalid.
 */

   public ArrayList bfs(Comparable searchKey) throws GraphException;

/**
 *  Returns the shortest path between two vertices in the graph.<br>
 *  Preconditions: must be passed searchKeys of the starting and ending vertices.<br>
 *  Postconditions: returns an ArrayList of vertices between the starting and ending search Keys.<br>
 *  Throws: GraphException if enteres searchKeys are invalid.
 */
   public ArrayList shortestPath(Comparable searchKey1, Comparable searchKey2) throws GraphException;

}