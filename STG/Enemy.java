import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Obstacle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends SmoothMover
{
    protected int x, y;
    protected World w;
    protected  List<PlayerBullet> bullets;
    public void setPosition(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    public int gettX()
    {
        return x;
    }
    public int gettY()
    {
        return y;
    }

    protected void hitDetection()
    { 
      bullets = getIntersectingObjects(PlayerBullet.class);
    }
}
