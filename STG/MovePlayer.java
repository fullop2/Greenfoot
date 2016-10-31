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
    

    public int HorizontalMove()
    {
       ShiftPushed();
       if(Greenfoot.isKeyDown("left") && Player.getInstance().getX() > 75)
            {return -tmpSpeed;}
       if(Greenfoot.isKeyDown("right") && Player.getInstance().getX() < 554)
           {return +tmpSpeed;}
       return 0;
    }
    
    public int VerticalMove()
    {
        ShiftPushed();
       if(Greenfoot.isKeyDown("up") && Player.getInstance().getY() > 30)
           {return -tmpSpeed;}
       if(Greenfoot.isKeyDown("down") && Player.getInstance().getY() < 687)
           {return +tmpSpeed;}
       return 0;
    }
    
    private void ShiftPushed()
    {
       if(Greenfoot.isKeyDown("shift"))  { tmpSpeed = speed - 1;}
       else { tmpSpeed = speed;}
    }
}
