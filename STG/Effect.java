import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * Write a description of class Effect here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Effect extends Effects
{
    int availTime;
    int size;
    public Effect(Effect e)
    {
        availTime = e.availTime;
        getImage().scale(e.size,e.size);
    }
    public Effect(int time,int s)
    {
        availTime = time;
        size = s;
        getImage().scale(s,s);
    }
    
    public void act()
    {
        if(availTime-- < 0)
            getWorld().removeObject(this);
    }
}
