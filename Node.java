public class Node 
{
  private Object item;
  private Node next;  //reference to another node

  public Node(Object item) 
  {
    this.item = item;
    next = null;
  } // end constructor

  public Node(Object item, Node next) 
  {
    this.item = item;
    this.next = next;
  } // end constructor

  public void setItem(Object item) 
  {
    this.item = item;
  } // end setItem

  public Object getItem() 
  {
    return item;
  } // end getItem

  public void setNext(Node next) 
  {
    this.next = next;
  } // end setNext

  public Node getNext() 
  {
    return next;
  } // end getNext

} // end class Node

