/**
 *   GraphNode.java, an object that stores information about a graph node for 
 *         Tree Applicatons.<br>
 *   Extends: KeyedItem.<br>
 *   Author: Matt Markham
 */

public class GraphNode extends KeyedItem
{
  private int xCoord, yCoord;
  boolean mark;

  GraphNode (Comparable item)
  {
    super(item);
    mark=false;
  }

  GraphNode (Comparable item, int x, int y)
  {
    super(item);
    xCoord=x;
    yCoord=y;
    mark=false;
  }

/**
 *  Returns the x-coordinate of the current Graph Node.<br>
 *  Preconditions: None.<br>
 *  Postconditions: Returns int value of this nodes x position.<br>
 *  Throws: None
 */


  public int getX()
  {
    return xCoord;
  }

/**
 *   Returns the y-coordiante of the current Graph Node.<br>
 *   Preconditions: None.<br>
 *   Postconditions: Returns int value of this nodes y position.<br>
 *   Throws: None
 */

  public int getY()
  {
    return yCoord;
  }

/**
 *  Returns true if node is marked, false if it is not.<br>
 *  Preconditions: None.<br>
 *  Postconditions: returns true if node is marked, false if it is not.
 *  Throws: None.
 */

  public boolean isMarked()
  {
    return mark;
  }


/**
 *   Sets value of mark to true or false.<br>
 *   Preconditions: Must be passed in a boolean.<br>
 *   Postconditions: Sets mark to passed-in boolean.<br>
 *   Throws: None.
 */

  public void setMarked(boolean setbool)
  {
    mark=setbool;
  }

  public boolean equals (GraphNode node) 
    { 
      return ((node.getX() == xCoord)&&(node.getY()==yCoord) && (node.getKey().compareTo(this.getKey())==0));
    }
}