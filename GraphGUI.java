import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

/** This is the parent window in the graph, it only sets flags based on menu choices and provides a place for the GraphScreen to draw itself */
public class GraphGUI extends JFrame	{
	
	/** the height of the frame */
	private final int HEIGHT = 550;
	
	/** the width of the frame */
	private final int WIDTH = 640;
	
	/** the JPanel that does all of the work */
	private GraphScreen graphscreen;
	
	/** the status bar */
	private JLabel information;
	
	/**
	 * The default constructor. <BR>
	 * Preconditions: None. <BR>
	 * Postconditions: Sets up the window and displays everything. <BR>
	 */
	public GraphGUI()	{
		super ( "GraphGUI" );
		setSize ( WIDTH, HEIGHT );
		getContentPane().setLayout ( new BorderLayout() );
		
		addWindowListener ( new WindowAdapter()	{
			public void windowClosing ( WindowEvent event )	{
				System.exit ( 0 );
			}
		});
		
		/* Center the window in the middle of the screen */
		Dimension screensize = getToolkit().getScreenSize();
		int screenwidth = screensize.width;
		int screenheight = screensize.height;
		setLocation ( screenwidth / 2 - WIDTH / 2, screenheight / 2 - HEIGHT / 2);
      
		/* These are the individual menu items in the menu bar. */
		JMenuItem cleargraph = new JMenuItem ( new ImageIcon ( "ClearGraph.jpg" ) );
		cleargraph.setBackground ( Color.black );
		cleargraph.setToolTipText ( "Makes a new, empty, pretty graph." );
		cleargraph.addActionListener ( new ActionListener()	{
			public void actionPerformed ( ActionEvent event )	{
				graphscreen.clearGraph();
			}
		});
		JMenuItem shortestpath = new JMenuItem ( new ImageIcon ( "ShortestPath.jpg" ) );
		shortestpath.setBackground ( Color.black );
		shortestpath.setToolTipText ( "Determines the shortest path between two vertices." );
		shortestpath.addActionListener ( new ActionListener()	{
			public void actionPerformed ( ActionEvent event )	{
				graphscreen.findShortestPath();
			}
		});
		JMenuItem quit = new JMenuItem ( new ImageIcon ( "Quit.jpg" ) );
		quit.setBackground ( Color.black );
		quit.setToolTipText ( "All your base are belong to us." );
		quit.addActionListener ( new ActionListener()	{
			public void actionPerformed ( ActionEvent event )	{
				System.exit ( 0 );
			}
		});
		
		/* the menu that keeps the menuitems */
		JMenu graph = new JMenu ( "GraphGUI" );
		graph.add ( cleargraph );
		graph.add ( shortestpath );
		graph.add ( quit );
		JMenuBar toolbar = new JMenuBar();
		toolbar.add ( graph );
		setJMenuBar ( toolbar );
      
		/* set up the two remaining graphical components */
		information = new JLabel ( "Ready" );
		graphscreen = new GraphScreen ( information );
      
		getContentPane().add ( graphscreen, BorderLayout.CENTER );
		getContentPane().add ( information, BorderLayout.SOUTH );
      
		setResizable ( false );
		setVisible ( true );
	}
	
	/** Start the party */
	public static void main ( String[] args )	{
		GraphGUI mygraph = new GraphGUI();
	}
}
