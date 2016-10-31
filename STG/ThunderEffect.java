import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class thunder here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ThunderEffect extends Effects
{
    private int timer = 20;
    public void act() 
    {
       if(--timer < 0)
            getWorld().removeObject(this);
    }    
}
