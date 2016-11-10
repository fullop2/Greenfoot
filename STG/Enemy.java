import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Obstacle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Enemy extends SmoothMover
{
    protected int x, y;
    protected World w;
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

    
}
