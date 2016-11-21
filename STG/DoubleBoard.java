import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * Write a description of class DoubleBoard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DoubleBoard extends GUI
{
    private double field;
    private String s_field;
    public DoubleBoard() 
    {
    }
    
    public DoubleBoard(int field)
    {
        this.field = field;
    }
     
    public void act()
    {
        UpdateImage();
    }
    public String getField()
    {
        return Double.toString(field);
    }
    
    public void UpdateImage()
    {
         setImage(new GreenfootImage(String.format("%.2f",field), 30, Color.WHITE,new Color(0,0,0,0)));
    }
    
    public void UpdateField(double field)
    {
        this.field = field;
        s_field = Double.toString(field);
    }
}
