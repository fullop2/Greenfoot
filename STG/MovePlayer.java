import greenfoot.*;

/**
 * Write a description of class MovePlayer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovePlayer 
{
    private int speed = 4; 
    private int tmpSpeed;

    public int HorizontalMove(int x)
    {
       ShiftPushed();
       if(Greenfoot.isKeyDown("left") && x > 75)
            {return x-tmpSpeed;}
       if(Greenfoot.isKeyDown("right") && x < 554)
           {return x+tmpSpeed;}
       return x;
    }
    
    public int VerticalMove(int y)
    {
        ShiftPushed();
       if(Greenfoot.isKeyDown("up") && y > 30)
           {return y-tmpSpeed;}
       if(Greenfoot.isKeyDown("down") && y < 687)
           {return y+tmpSpeed;}
       return y;
    }
    
    private void ShiftPushed()
    {
       if(Greenfoot.isKeyDown("shift"))  { tmpSpeed = speed - 2;}
       else { tmpSpeed = speed;}
    }
}
