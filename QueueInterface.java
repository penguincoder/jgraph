public interface QueueInterface 
{
 /**
  * Determines whether a queue is empty.
  * Precondition: None.
  * Postcondition: Returns true if the queue is empty;
  * otherwise returns false.
  */
  public boolean isEmpty();

 /**
  * Adds an item at the back of a queue.
  * Precondition: item is the item to be inserted. 
  * Postcondition: If the operation was successful, newItem
  * is at the back of the queue. Some implementations
  * may throw QueueException if item cannot be added to the queue.   
  */
  public void enqueue(Object item) throws QueueException;

 /**
  * Retrieves and removes the front of a queue.
  * Precondition: None.
  * Postcondition: If the queue is not empty, the item
  * that was added to the queue earliest is returned and
  * the item is removed. If the queue is empty, the
  * operation is impossible and QueueException is thrown.
  */
  public Object dequeue() throws QueueException;

 /**
  * Removes all items of a queue.
  * Precondition: None.
  * Postcondition: The queue is empty.
  */
  public void dequeueAll();

 /**
  * Retrieves the item at the front of a queue.
  * Precondition: None.
  * Postcondition: If the queue is not empty, the item
  * that was added to the queue earliest is returned. 
  * If the queue is empty, the operation is impossible
  * and QueueException is thrown.
  */
  public Object peek() throws QueueException;

 /**
  * Determines the length of a queue.
  * Precondition: None.
  * Postcondition: Returns the number of items that are
  * currently in the queue.
  * Throws: None.
  */
  public int size();


}  // end QueueInterface