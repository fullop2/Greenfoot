import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * Write a description of class IntegerBoard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IntegerBoard extends GUI
{
    protected int field;
    protected String s_field;
    
    
    public IntegerBoard() 
    {
    }
    
    public IntegerBoard(int field)
    {
        this.field = field;
    }
    
    public void act()
    {
        UpdateImage();
    }
    
    public String getField()
    {
        return Integer.toString(field);
    }
    
    public void UpdateImage()
    {
        setImage(new GreenfootImage(String.format("%,d",field), 30, Color.WHITE,new Color(0,0,0,0)));
    }
    
    public void UpdateField(int field)
    {
        this.field = field;
        s_field = Integer.toString(field);
    }
}
