import java.util.ArrayList; 
 
public class GraphDriver
{  
   public static void main ( String[] args )
   {  
      Graph mygraph = new Graph();  
      mygraph.addVertex ( new GraphNode ( "A" ) );
      mygraph.addVertex ( new GraphNode ( "B" ) );
      mygraph.addVertex ( new GraphNode ( "C" ) );
      mygraph.addVertex ( new GraphNode ( "D" ) );
      mygraph.addVertex ( new GraphNode ( "E" ) );
      
      mygraph.addEdge ( "A", "D", 9.0 );
      mygraph.addEdge ( "A", "B", 8.0 );
      mygraph.addEdge ( "A", "E", 4.0 );
      mygraph.addEdge ( "B", "C", 1.0 );
      mygraph.addEdge ( "C", "B", 2.0 );
      mygraph.addEdge ( "C", "D", 3.0 );      
      mygraph.addEdge ( "D", "C", 2.0 );      
      mygraph.addEdge ( "D", "E", 7.0 );
      mygraph.addEdge ( "E", "C", 1.0 );
      
      ArrayList bft = mygraph.shortestPath ( "A", "C" );
      for ( int i = 0; i < bft.size(); i++ )
         System.out.println ( bft.get ( i ).toString() );  
   }
}
