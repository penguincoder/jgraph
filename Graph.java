import java.util.*;
import java.text.DecimalFormat;
import java.awt.Color;
import java.awt.Graphics;

/**
 *  Graph.java, models graph management and creation.<br>
 *  Implements: GraphInterface.<br>
 *  @author <A HREF="mailto:mem1780@tntech.edu">Matt Markham</A><BR>
 */

public class Graph implements GraphInterface
{
    //size integer variable to keep track of number of nodes in the graph
  private int size;
    //Double two-dimensional array for the adjacency matrix
  private double[][] adjacent;
    //boolean to determine whether a graph is directed
  private boolean directed;
    //Vertex storage list
  private ArrayList vertexList;
    //int to determin generic unweighted value
  private final int UNWEIGHTED_VALUE = 1;
    //temporary Arraylist for dfs
  private ArrayList tempAL=new ArrayList();
    //ArrayList containing the current shortest path
  private ArrayList path;


/**
 *  Default Constructor, creates an undirected graph.<br>
 */

  public Graph ()
  {
     size = 0;
     adjacent = new double[size][size];
     vertexList = new ArrayList();
     directed=false;
     path = new ArrayList();
  }

   
/**
 *  Constructor, takes a boolean to determine directed or undirected.<br>
 */

  public Graph (boolean param)
  {
     size = 0;
     adjacent = new double[size][size];
     vertexList = new ArrayList();
     directed=param;
     path = new ArrayList();
  }

  public void makeEmpty()
  {
    size=0;
    adjacent = new double[size][size];
    vertexList.clear();
  }

  public boolean isEmpty()
  {
    return (size==0);
  }

  
  public int numVertices()
  {
     return size;
  }


  public int numEdges()
  {
    int c=0;
    for(int x=0; x<size;x++)
    {
       for(int y=0;y<size;y++)
       {
          if (!(adjacent[x][y]==(Double.POSITIVE_INFINITY)))
          {
             c++;  //Insert nerdy joke here
          }
       }
     }
  return c;
  }

  public void addVertex (GraphNode myItem) throws GraphException  
  { 
      for(int a=0; a<vertexList.size();a++)  

         if ( ((GraphNode) vertexList.get(a)).getKey().compareTo ( myItem.getKey() ) == 0 )

            throw new GraphException ( "Duplicate entry" );

      size++;  

      vertexList.add(myItem);  

      resizeAdjacent ( vertexList.size() ); 
  }


/**
 *  Method to resize the adjacency matrix.<br>
 *  Preconditions: must be passed a new size value.<br>
 *  Postconditions: resizes adjacency matrix.<br>
 *  Throws: None.
 */

   private void resizeAdjacent ( int size )
   {

      double[][] temp = new double[size][size];  

      for( int x = 0; x < size - 1; x++)

         for ( int y = 0; y < size - 1; y++ )

            temp[x][y] = adjacent[x][y];

         for ( int x = 0; x < size; x++ )
         {

            temp[x][size - 1] = Double.POSITIVE_INFINITY;

            temp[size - 1][x] = Double.POSITIVE_INFINITY;

         }

         adjacent = temp;
   }

  public void addEdge(Comparable searchKey1, Comparable searchKey2, double weight) throws GraphException  
  {  
     for(int x=0; x<size; x++)  
     {  
       if ( ((GraphNode) vertexList.get(x)).getKey().compareTo(searchKey1) == 0 ) 
       {  
          for(int y=0; y<size;y++)  
          {  
             if ( ((GraphNode) vertexList.get(y)).getKey().compareTo(searchKey2) == 0 ) 
             {  
                adjacent[x][y]=weight; 
                return; 
             }//end if get(y)  
          }// end for y
        }//end if get(x)  
     }//end for x  
     throw new GraphException ( "Duplicate found!" ); 
  }

  public void addEdge(Comparable searchKey1, Comparable searchKey2) throws GraphException

  {  
     for(int x=0; x<size; x++)  
     {  
       if ( ((GraphNode) vertexList.get(x)).getKey().compareTo(searchKey1) == 0 ) 
       {  
          for(int y=0; y<size;y++)  
          {  
             if ( ((GraphNode) vertexList.get(y)).getKey().compareTo(searchKey2) == 0 ) 
             {  
                adjacent[x][y]=UNWEIGHTED_VALUE;
                  if(!directed)
                    {adjacent[y][x]=UNWEIGHTED_VALUE;}  
                return; 
             }//end if get(y)  
          }// end for y
        }//end if get(x)  
     }//end for x  
     throw new GraphException ( "Duplicate found!" ); 
  }

  public double getWeight(Comparable searchKey1, Comparable searchKey2) throws GraphException
  {  
     for(int x=0; x<size;x++)  
     {  
        if ( ((GraphNode) vertexList.get(x)).getKey().compareTo(searchKey1) == 0 ) 
        {  
          for(int y=0; y<size;y++)  
          {  
             if ( ((GraphNode) vertexList.get(y)).getKey().compareTo(searchKey2) == 0 ) 
             {  
                return adjacent[x][y]; 
             }//end if get(y)  
          }// end for y
        }//end if get(x)  
     }//end for x  
     throw new GraphException ( "Edge does not exist!"); 
  }

  public GraphNode getVertex(int index) throws GraphException
  {
    if (index>size)
     throw new GraphException ("index out of bounds!");
    else
     return (GraphNode)vertexList.get(index);
  }

  public GraphNode getVertex(Comparable searchKey) throws GraphException
  {
    for (int x=0;x<vertexList.size();x++)
    {
      if ( ((GraphNode) vertexList.get(x)).getKey().compareTo(searchKey) == 0)
      {
         return (GraphNode) vertexList.get(x);
      }
    }
    throw new GraphException ("Vertex not Found in List!");
  }
  
/**
 *  FInds the index of a given searchKey.<br>
 *  Preconditions: Must be passed searchKey.<br>
 *  Postconditions: returns integer index of given searchKey.<br>
 *  Throws:Nonde.
 */

  private int findVertex (Comparable searchKey)
  {
    for (int d=0; d<size;d++)
    {
       if ( ((GraphNode) vertexList.get(d)).getKey().compareTo(searchKey) == 0 )
            { return d; }
    }
       return -1;
  }

/**
 *  Clears all vertices, sets all to be unmarked.<br>
 *  Preconditions: None.<br>
 *  Postconditions: Makes all vertices unmarked.<br>
 *  Throws:None.
 */

  private void clearMarks()
  {
    for (int x=0;x<size;x++)
    {
      ((GraphNode) vertexList.get(x)).setMarked(false);
    }
  }

  public Comparable getSearchKey (int index)
  {
     return ((GraphNode)(vertexList.get(index))).getKey();
  }

  public void removeEdge(Comparable searchKey1, Comparable searchKey2) throws GraphException
  {
    int a=findVertex(searchKey1);
    int b=findVertex(searchKey2);
    if (a==-1 || b==-1)
      throw new GraphException ("Entry not found in list!");
    adjacent[a][b]=Double.POSITIVE_INFINITY;
  }


  public ArrayList bfs (Comparable searchKey) throws GraphException
  {
    GraphNode temp;
    ArrayList searchList = new ArrayList();
    QueueReferenceBased bfsQueue= new QueueReferenceBased();
    bfsQueue.enqueue(vertexList.get(findVertex(searchKey)));
    getVertex(searchKey).setMarked(true);
    searchList.add(getVertex(searchKey));

    while (!bfsQueue.isEmpty())
    {
      temp=(GraphNode)bfsQueue.dequeue();

      for(int g=0;g<size;g++)
      {
        if (adjacent[findVertex(temp.getKey())][g] != Double.POSITIVE_INFINITY && !getVertex(g).isMarked())
        {
          ((GraphNode)vertexList.get(g)).setMarked(true);
          bfsQueue.enqueue(vertexList.get(g));
          searchList.add(getVertex(g));
        }//end if
      }//end for
    }//end while
    clearMarks();
    return searchList;
  }

   public GraphNode removeVertex ( Comparable key ) throws GraphException 
   { 
      int index = -1; 
      for ( int i = 0; i < vertexList.size(); i++ ) 
         if ( ((GraphNode) vertexList.get ( i )).getKey().compareTo ( key ) == 0 ) 
            index = i; 
      if ( index == -1 ) 
         throw new GraphException ( "Vertex not in graph" ); 
      double[][] temp = new double[adjacent.length - 1][adjacent.length - 1]; 
      for ( int row = 0; row < adjacent.length; row++ ) 
      { 
         for ( int col = 0; col < adjacent[0].length; col++ ) 
         { 
            if ( row != index && col != index )
            {
               int newrow = row;
               int newcol = col;
               /* make sure the row/col are not in the row/col of the vertex to be removed */
               if ( row > index )
                  newrow = row - 1;
               if ( col > index )
                  newcol = col - 1;
               temp[newrow][newcol] = adjacent[row][col]; 
            }
         }
      }
      adjacent = temp;
      size--;
      return (GraphNode) vertexList.remove ( index );
   }

  public ArrayList dfs (Comparable searchKey) throws GraphException
  {
    ArrayList dfsList = new ArrayList();
    dfsList=dfsRec(getVertex(searchKey), dfsList);
    clearMarks();
    return dfsList;
  }


/**
 *  Private recursive method called to generate a breadth-first search.<br>
 *  Preconditions: Must be passed a graphNode and an ArrayList.<br>
 *  Postconditions: Returns an Arraylist for the bfs.<br>
 *  Throws:None.
 */

  private ArrayList dfsRec(GraphNode vertex, ArrayList searchRecList)
  {
    searchRecList.add(vertex);
    vertex.setMarked(true);
    int i=vertexList.indexOf(vertex);
      for(int j=0; j<size; j++)
      {
        if (adjacent[i][j]!=(Double.POSITIVE_INFINITY) &&
            !(getVertex(j)).isMarked())
          dfsRec( getVertex(j), searchRecList );
          
       }//end for j
    return searchRecList;
  }

   /**
    * Draws the panel with all of the edges and vertices. <BR>
    * Preconditions: A Graphics object and the radius of the vertecies. <BR>
    * Postconditions: Draws all of the neccessary components onto the GraphScreen. <BR>
    * @author <A HREF="mailto:arc4472@tntech.edu">Andrew Coleman</A><BR>
    */
   public void draw ( Graphics g, int radius )
   {
      /* draw all of the edges in orange */
      g.setColor ( Color.orange );
      for ( int i = 0; i < adjacent.length; i++ )
      {
         int firstx = getVertex ( i ).getX();
         int firsty = getVertex ( i ).getY();
         String firstkey = getVertex ( i ).getKey().toString();
         for ( int j = 0; j < adjacent[i].length; j++ )
         {
            if ( adjacent[i][j] != Double.POSITIVE_INFINITY )
            {
               int secondx = ((GraphNode) getVertex ( j )).getX();
               int secondy = ((GraphNode) getVertex ( j )).getY();
               String secondkey = (String)((GraphNode) getVertex ( j )).getKey();
               DecimalFormat format = new DecimalFormat();
               format.setMaximumFractionDigits ( 3 );
               int difference = 10;
               /* the final x and y coordinates for the string for each edge */
               int xcoord, ycoord;
               if ( firstx < secondx )
                  xcoord = (secondx - firstx) / 2 + firstx;
               else
                  xcoord = (firstx - secondx) / 2 + secondx;
               if ( firsty < secondy )
                  ycoord = (secondy - firsty) / 2 + firsty;
               else
                  ycoord = (firsty - secondy) / 2 + secondy;
               /* if i < j, then the edge goes from j to i */
               if ( i < j )
               {
                  g.drawLine ( firstx, firsty - 10, secondx, secondy - 10 );
                  g.drawString ( firstkey + " to " + secondkey + ", Weight: " + format.format ( adjacent[i][j] ), xcoord, ycoord - 11 );
               }
               /* if i > j, then the edge goes from i to j */
               else if ( i > j )
               {
                  g.drawLine ( firstx, firsty + 10, secondx, secondy + 10 );
                  g.drawString ( firstkey + " to " + secondkey + ", Weight: " + format.format ( adjacent[i][j] ), xcoord, ycoord + 11 );
               }
            }
         }
      }

      /* draw the shortest path stored in green */
      g.setColor ( Color.green );
      for ( int i = 1; i < path.size(); i++ )
      {
         int firstx = ((GraphNode) path.get ( i - 1 )).getX();
         int firsty = ((GraphNode) path.get ( i - 1 )).getY();
         int secondx = ((GraphNode) path.get ( i )).getX();
         int secondy = ((GraphNode) path.get ( i )).getY();
         g.drawLine ( firstx, firsty, secondx, secondy );
      }

      /* draw all of the vertices in dark gray with a light gray border and the comparable data in the middle */
      for ( int i = 0; i < numVertices(); i++ )
      {
         g.setColor ( Color.lightGray );
         GraphNode temp = getVertex ( i );
         g.fillOval ( temp.getX() - radius, temp.getY() - radius, radius * 2, radius * 2 );
         g.setColor ( Color.darkGray );
         int difference = 2;
         g.fillOval ( temp.getX() - radius + difference, temp.getY() - radius + difference, (radius - difference) * 2, (radius - difference) * 2 );
         g.setColor ( Color.white );
         g.drawString ( (String) temp.getKey(), temp.getX(), temp.getY() );
      }
   }

   /**
    * This method is used to set the arraylist containing the shortest path to be displayed. <BR>
    * Preconditions: An arraylist containing a valid shortest path. <BR>
    * Postconditions: Sets the current path to the given arraylist. <BR>
    * @author <A HREF="mailto:arc4472@tntech.edu">Andrew Coleman</A><BR>
    */
   public void setShortestPathDisplay ( ArrayList path )
   {
      this.path = path;
   }

   /**
    * Used to calculate the shortest distance between two vertices using Djisktra's algorithm. <BR>
    * Preconditions: Two comparables representing the data in the two vertices. <BR>
    * Postconditions: Returns an ArrayList containing the GraphNodes in the path. <BR>
    * Throws: Throws a GraphException if the comparables are the same or there is no connecting path. <BR>
    * @author <A HREF="mailto:arc4472@tntech.edu">Andrew Coleman</A><BR>
    */
   public ArrayList shortestPath ( Comparable firstkey, Comparable lastkey ) throws GraphException	{
      if ( firstkey.compareTo ( lastkey ) == 0 )
         throw new GraphException ( "Cannot find shortest path to same vertex!" );
      /* the arraylist containing the raw path array */
      ArrayList path = new ArrayList ( vertexList.size() );
      /* mark the first vertex */
      getVertex ( firstkey ).setMarked ( true );
      /* indexes of the first and last search keys */
      int firstindex = findIndex ( firstkey );
      int secondindex = findIndex ( lastkey );
      /* the row of the adjacency matrix at firstindex */
      double[] weight = new double[vertexList.size()];
      /* initialize the weight/path arrays */
      for ( int i = 0; i < weight.length; i++ )
      {
         path.add ( vertexList.get ( i ) );
         weight[i] = adjacent[firstindex][i];
         /* if the weight is not infinity we must change the path to reflect the changes */
	 if ( weight[i] < Double.POSITIVE_INFINITY )
            path.set ( i, getVertex ( firstkey ) );
      }

      /* loop through all the elements in the graph, must mark them all */
      for ( int i = 1; i < vertexList.size(); i++ )
      {
         /* index of smallest vertex */
         int smallest = -1;
         /* smallest weight thus far that is not marked */
         double smallestweight = Double.POSITIVE_INFINITY;
         for ( int j = 0; j < weight.length; j++ )
            /* run through the weight array and find the smallest weight */
            if ( smallestweight >= weight[j] && !((GraphNode) vertexList.get ( j )).isMarked() )
            {
               /* note smallest vertex */
               smallest = j;
               smallestweight = weight[j];
            }
         /* mark smallest vertex */
	 GraphNode smallnode = (GraphNode) vertexList.get ( smallest );
         smallnode.setMarked ( true );
         /* update the weight/path arrays */
         for ( int j = 0; j < weight.length; j++ )
            /* if a new weight to that vertex is less than the current weight, change the weight in the array and change the path arraylist */
            if ( weight[j] > weight[smallest] + adjacent[smallest][j] )
            {
               weight[j] = weight[smallest] + adjacent[smallest][j];
               path.set ( j, vertexList.get ( smallest ) );
	    }
      }
      /* backwards path */
      ArrayList result = new ArrayList();
      GraphNode node = getVertex ( lastkey );
      int lastindex = findIndex ( lastkey );
      /* while the node is not the first node */
      while ( lastindex != firstindex )
      {
         result.add ( node );
         node = (GraphNode) path.get ( lastindex );
         int newlastindex = findIndex ( node.getKey() );
         /* if the indexes are the same, then there is no way to get to that vertex */
         if ( newlastindex == lastindex )
            throw new GraphException ( "No connecting path!" );
         else
            lastindex = newlastindex;
      }
      /* gotta add the first one */
      result.add ( getVertex ( firstkey ) );
      /* the result to return */
      ArrayList sortedresult = new ArrayList ( result.size() );
      /* reverse the arraylist */
      for ( int i = result.size() - 1; i >= 0; i-- )
         sortedresult.add ( result.get ( i ) );
      return sortedresult;
   }

   /**
    * This method finds the index of a given comparable object. <BR>
    * Preconditions: A valid comparable key in the graph. <BR>
    * Postcondtions: Returns the index of the key, or -1 if the key is not in the graph. <BR>
    */
   private int findIndex ( Comparable key )
   {
      for ( int i = 0; i < vertexList.size(); i++ )
         if ( ((GraphNode) vertexList.get ( i )).getKey().compareTo ( key ) == 0 )
            return i;
      return -1;
   }

}
