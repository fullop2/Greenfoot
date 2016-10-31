import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.GreenfootImage;
/**
 * Write a description of class Ball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Bullet extends SmoothMover
{
    public int speed = 30;
    protected int rot = 0;
    /**
     * Act - do whatever the Ball wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Bullet()
    {
    }
    
    public Bullet(Bullet a)
    {
        this.speed = a.speed;
        this.rot = a.rot;
    }
        
   public void act() 
    {
        // Add your action code here.
        move(speed);
        chkisEdge();
    } 
  
}
