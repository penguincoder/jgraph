public class QueueReferenceBased implements QueueInterface 
{
  // circular references
  private Node tail;
  private int size;
  
  public QueueReferenceBased() 
  {
    tail = null;  
    size=0; 
  }  // end default constructor
  
  // queue operations:
  public boolean isEmpty() 
  {
    return tail == null;
  }  // end isEmpty

  public int size()
  {
    return size;
  }

  public void dequeueAll() 
  {
    if (tail!=null)
       tail.setNext(null);

    tail = null;
    size=0;
  }  // end dequeueAll

  public void enqueue(Object item) 
  {
    Node myNode = new Node(item);

    // insert the new node
    if (isEmpty()) 
    {
      // insertion into empty queue
      myNode.setNext(myNode);
    }
    else 
    {
      // insertion into nonempty queue
      myNode.setNext(tail.getNext());
      tail.setNext(myNode);
    }  // end if

    tail = myNode;  // new node is at back
    size++;
  }  // end enqueue

  public Object dequeue() throws QueueException 
  {
    if (!isEmpty()) 
    {
      // queue is not empty; remove front
      Node head = tail.getNext();
      if (head == tail) 
      { // special case?
        tail.setNext(null);
        tail = null;           // yes, one node in queue
      }
      else 
      {
        tail.setNext(head.getNext());
      }  // end if
      size--;
      return head.getItem();
    }
    else 
    {
      throw new QueueException("Queue empty");
    }  // end if
  }  // end dequeue

  public Object peek() throws QueueException 
  {
    if (!isEmpty()) 
    {  
      // queue is not empty; retrieve front
      Node head = tail.getNext();
      return head.getItem();
    }
    else 
    {
      throw new QueueException("Queue empty");
    }  // end if
  }  // end peek
   
} // end QueueCircular