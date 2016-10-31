import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class weapon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Weapon extends PlayerStatus
{
    GreenfootImage g2 = new GreenfootImage("player-subW_.png");
    GreenfootImage g1 = new GreenfootImage("player-subW.png");
    private int swap;
    private SubPlayerBullet ball;
    
    public void setlocation(int x, int y)
    {
         setLocation(x,y);
    }
    public void atkCmd(int angle)
    {
         ball = new SubPlayerBullet(20,angle);
         getWorld().addObject(ball,getX(),getY());
    }

    public void act()
    {
        setRotation(getRotation() + 1);
        if(swap == 1)
        {         
            setImage(g1);
            swap--;
        }
        else
        {
            setImage(g2);
            swap++;
        }
    }
}