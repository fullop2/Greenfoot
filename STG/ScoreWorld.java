import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * Write a description of class ScoreWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ScoreWorld extends World
{
    private int timer;
    /**
     * Constructor for objects of class ScoreWorld.
     * 
     */
    public ScoreWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(960, 720, 1); 
    }
    
    public ScoreWorld(int score)
    {
        super(960,720,1);
        showText(new java.text.DecimalFormat("00,0000,0000").format(score),480,360);
        showText("Z Key to Go Main",480,390);
        timer = 60;
    }
    
    public void act()
    {
        if(--timer < 0 && Greenfoot.isKeyDown("Z"))
            Greenfoot.setWorld(new Main());
    }
}
