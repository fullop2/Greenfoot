/**
 * Write a description of class Path here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Path  
{
     private int x;
     private int y;
     private int delay;
     
     Path(int x, int y, int delay)
     {
         this.x = x;
         this.y = y;
         this.delay = delay;
        }
      public int getX()
      {
          return x;
        }
        public int getY()
        {
            return y;
        }

        public int getDelay()
     {
         return delay--;
    }
}
