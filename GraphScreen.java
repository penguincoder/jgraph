import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.DecimalFormat;

/** This class defines a custom JPanel that will draw vertices, edges, and the shortest path of a Graph object. This custom JPanel is used because it is much easier to intercept x and y coordinates in this panel than in the frame and then having to compensate for the other graphical components. */

public class GraphScreen extends JPanel implements MouseListener	{
	/** defines the radius of the vertices in pixels */
	private final int radius = 20;

	/** the path of vertices for the currently displayed shortest path */
	private ArrayList path;

	/** the graph to add vertices/edges to */
	private Graph mygraph;

	/** the status bar in the parent jframe */
	private JLabel information;

	/* a flag to check if you are calculating the shortest path */
	private boolean shortestpath;

	/* a flag used to check if the mouseclick intercepted was the second in the series (add edge, shortest path) */
	private boolean issecondvertex;

	/* the data stored in the first vertex clicked in the series */
	private Comparable firstvertex;
	

	/**
	 * Default constructor for the GraphScreen panel. <BR>
	 * Preconditions: A JLabel status bar in the parent JFrame. <BR>
	 * Postconditions: Initializes all variables. <BR>
	 */
	public GraphScreen ( JLabel information )	{
		super ( true );
		this.information = information;
		addMouseListener ( this );
		mygraph = new Graph();
		mygraph.setShortestPathDisplay ( new ArrayList() );
		information.setText ( " Click to add vertex" );
		issecondvertex = false;
		firstvertex = null;
		shortestpath = false;
	}
	
	/** mousePressed and mouseReleased could be used to move vertices around the graph,
		 but as per explicitly stated in the program requirements, the GraphNodes have no
		 setX ( int x ) and setY ( int y ) methods. */
	public void mousePressed ( MouseEvent event )	{}
	public void mouseReleased ( MouseEvent event )	{}
	
	/** not used, just here to fully implement the MouseListener interface */
	public void mouseEntered ( MouseEvent event )	{}
	public void mouseExited ( MouseEvent event )	{}

	/**
	 * Intercepts a mouse click in the drawing window. <BR>
	 * Preconditions: A mouse click. <BR>
	 * Postconditions: This method could add a vertex, add an edge, or calculate where the shortest path needs to be based upon flags and user interaction. <BR>
	 */
	public void mouseClicked ( MouseEvent event )	{
		/* If it is a right mouse click, then we must add an edge */
		if ( SwingUtilities.isRightMouseButton ( event ) )	{
			/* the node that is contained within radius pixels of the MouseEvent */
			GraphNode temp = findVertex ( event.getX(), event.getY() );
			/* if temp is null then the user clicked in blank space */
			if ( temp != null )	{
				/* if this is the second right mouse click, and it is not the same vertex */
				if ( issecondvertex && firstvertex.compareTo ( temp.getKey() ) != 0 )	{
					/* lets add an edge, and hopefully it will work */
					try	{
						addEdge ( firstvertex, temp.getKey() );
						information.setText ( " Edge added between " + firstvertex.toString() + " and " + temp.getKey().toString() );
					}
					/* error exists between keyboard and chair */
					catch ( GraphException exception )	{
						information.setText ( " Could not add the edge" );
						JOptionPane.showMessageDialog ( null, exception.toString(), "Exception caught", JOptionPane.WARNING_MESSAGE );
					}
					/* we will set all the flags to normal */
					issecondvertex = false;
					firstvertex = null;
				}
				/* this must be the first right click intercepted */
				else	{
					/* save the data for use later */
					firstvertex = temp.getKey();
					issecondvertex = true;
					information.setText ( " Right click on another vertex to create an edge" );
				}
			}
			/* we don't care and set everything to normal since the user clicked in blank space */
			else	{
				firstvertex = null;
				issecondvertex = false;
				information.setText ( " Right click on vertices to add and edge" );
			}
		}
		
		/* not the right mouse button, so this could be shortest path or add a vertex */
		/* if the shortest path flag is set, we must find two different vertices */
		else if ( shortestpath )	{
			/* if there are less than 2 vertices in the graph, it is impossible to find the shortest path */
			if ( mygraph.numVertices() > 2 )	{
				GraphNode temp = findVertex ( event.getX(), event.getY() );
				/* if this is the second click and the user clicked on a node and the node is not the same node */
				if ( issecondvertex && temp != null && firstvertex.compareTo ( temp.getKey() ) != 0 )	{
					/* set the shortest path arraylist to the path in the graph */
					try	{
						mygraph.setShortestPathDisplay ( mygraph.shortestPath ( firstvertex, temp.getKey() ) );
						information.setText ( " Shortest path is displayed in green" );
						repaint();
					}
					catch ( GraphException exception )	{
						JOptionPane.showMessageDialog ( null, exception.toString(), "Exception caught", JOptionPane.WARNING_MESSAGE );
						mygraph.setShortestPathDisplay ( new ArrayList() );
						information.setText ( " Could not create the shortest path" );
					}
					shortestpath = false;
					issecondvertex = false;
					firstvertex = null;
				}
				/* the user is clicking on blank space */
				else if ( temp == null )	{
					information.setText ( " You must first click on vertices" );
					shortestpath = false;
					issecondvertex = false;
					firstvertex = null;
				}
				/* must be the first click in the series then */
				else	{
					firstvertex = temp.getKey();
					issecondvertex = true;
					information.setText ( " Click on the ending vertex of the path" );
				}
			}
			/* not enough vertices to make a shortest path */
			else	{
				information.setText ( " There must be at least 2 vertices in the graph before calculating the shortest path" );
				shortestpath = false;
				firstvertex = null;
				issecondvertex = false;
			}
		}

		/* well, the only thing left is to add a new vertex */
		else	{
			/* if the user clicked in blank space for once */
			if ( findVertex ( event.getX(), event.getY() ) == null )	{
				information.setText ( " Click to add vertex" );
				shortestpath = false;
				issecondvertex = false;
				firstvertex = null;
				/* ask the user what they want to call it */
				String name = JOptionPane.showInputDialog ( null, "Enter name: ","Add new graph node", JOptionPane.QUESTION_MESSAGE );
				/* as long as the name appears somewhat valid anyways */
				if ( name != null && !name.equals ( "" ) )	{
					int x = event.getX(), y = event.getY();
					/* these if's check to make sure the user didn't click too close to the edge */
					if ( x + radius > getWidth() )
						x = getWidth() - radius;
					if ( x - radius < 0 )
						x = radius;
					if ( y - radius > getHeight() )
						y = getHeight() - radius;
					if ( y - radius < 0 )
						y = radius;
					try	{
						mygraph.addVertex ( new GraphNode ( name, x, y ) );
						information.setText ( " Vertex added at (" + x + ", " + y + ")" );
						repaint();
					}
					catch ( Exception exception )	{
						JOptionPane.showMessageDialog ( null, exception.toString(), "Exception caught!", JOptionPane.WARNING_MESSAGE );
						information.setText ( " Vertex could not be added" );
					}
				}
			}
			/* the user clicked on a vertex */
			else	{
				information.setText ( " Vertex already added at location" );
				JOptionPane.showMessageDialog ( null, "Vertex is already at that point!", "Waitaminit!", JOptionPane.WARNING_MESSAGE );
			}
		}
		
	}
	
	/**
	 * Empties the graph and sets all flags to default values. <BR>
	 * Preconditions: None. <BR>
	 * Postconditions: Resets all internally used variables to default values. <BR>
	 */
	public void clearGraph()	{
		mygraph.makeEmpty();
		information.setText ( " Click to add vertex" );
		mygraph.setShortestPathDisplay ( new ArrayList() );
		issecondvertex = false;
		firstvertex = null;
		shortestpath = false;
		repaint();
	}
	
	/**
	 * Used to set the flag to find the shortest path. <BR>
	 * Preconditions: None. <BR>
	 * Postconditions: Sets the shortestpath flag to true. <BR>
	 */
	public void findShortestPath()	{
		shortestpath = true;
		information.setText ( " Click on the starting vertex" );
	}
	
	/**
	 * Attempts to locate a GraphNode within x +/- radius pixels and y +/- radius pixels of the given coordinate. <BR>
	 * Preconditions: Two integers representing coordinates inside the GraphScreen. <BR>
	 * Postconditions: Returns null if no GraphNode is close to the coord. or the GraphNode closest to that point. <BR>
	 */
	private GraphNode findVertex ( int x, int y )	{
		/* look through all of the nodes one by one */
		for ( int i = 0; i < mygraph.numVertices(); i++ )	{
			GraphNode result = mygraph.getVertex ( i );
			int tempx = result.getX();
			int tempy = result.getY();
			if ( tempx + radius >= x && tempx - radius <= x && tempy - radius <= y && tempy + radius >= y )
				return result;
		}
		return null;
	}
	
	/**
	 * Adds an edge between two vertices. <BR>
	 * Preconditions: Two comparable objects that are in the graph. <BR>
	 * Postconditions: Adds an edge between the two vertices containing the comparable objects. <BR>
	 */
	public void addEdge ( Comparable first, Comparable second )	{
		String temp = JOptionPane.showInputDialog ( null, "Enter a weight", "Add an edge", JOptionPane.QUESTION_MESSAGE );
		double result = -1.0;
		if ( temp != null )	{
			try	{
				result = Double.parseDouble ( temp );
				if ( result < 0 )
					throw new GraphException ( "Invalid weight for edge." );
				mygraph.addEdge ( first, second, result );
				information.setText ( " New edge added between vertices {" + (String) first + "} and {" + (String) second + "}" );
				repaint();
			}
			catch ( Exception exception )	{
				information.setText ( " Edge could not be added" );
				JOptionPane.showMessageDialog ( null, exception.toString(), "Exception Caught", JOptionPane.WARNING_MESSAGE );
			}
		}
	}


	/**
	 * Draws the background for the custom JPanel. <BR>
	 */
	public void paint ( Graphics g )	{
		/* draw a black background */
		g.setColor ( Color.black );
		g.fillRect ( 0, 0, this.getWidth(), this.getHeight() );
		mygraph.draw ( g, radius );
	}
}
