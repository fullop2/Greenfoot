import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * Write a description of class BackgroundSwap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BackgroundSwap extends Effects
{
    private int fadeOff;
    private int idleTime;
    private int fadeIn;
    private int time;
    private int value;
    private boolean inf;
    private int transparency;
    static int xpos = 320;
    static int ypos = 357;
    
    public BackgroundSwap(int fadeOff,int idleTime, int fadeIn, int transparency, Color color)
    {
        this(fadeOff,idleTime,fadeIn,transparency);
        GreenfootImage image = new GreenfootImage(500,680);
        image.setColor(color);
        image.setTransparency(0);
        image.fill();
        setImage(image);
    }
    public BackgroundSwap(int fadeOff,int idleTime, int fadeIn, int transparency)
    {
        if(fadeOff > 0) this.fadeOff = transparency / fadeOff;
        this.idleTime = idleTime;
        if(fadeIn > 0) this.fadeIn = transparency / fadeIn;
        this.transparency = transparency;
        if(fadeOff == 0 && fadeIn == 0)
        {
            inf = true;
            getImage().setTransparency(120);
        }
        else getImage().setTransparency(0);
    }
        public void act() 
    {
        if(!inf)
            {
            if(value + fadeOff <= transparency && fadeOff > 0)
                value += fadeOff;
            else if(idleTime >= 0)
            {
                idleTime--;
                fadeOff = 0;
            }
            else if(value - fadeIn >= 0)
                value-=fadeIn;
                        
            getImage().setTransparency(value);
            if(value <= fadeIn && fadeOff == 0) getWorld().removeObject(this);
        }
    } 
    
}
