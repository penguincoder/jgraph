public abstract class KeyedItem 
{
  private Comparable searchKey;
  
  public KeyedItem(Comparable key) 
  {
    searchKey = key;
  }  // end constructor

  public Comparable getKey() 
  {
    return searchKey;
  }  // end getKey

  public String toString()
  {
     return searchKey.toString();
  }

}  // end KeyedItem


/*
How to use:
public class CD extends KeyedItem
{
   //title not present here
   String artist;
   ...
   public CD(String title, String artist, double price, int tracks)
   {
      super(title);
      this.artist=artist;
      ...
   }

}
*/