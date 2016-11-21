import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * Write a description of class zeroIntegerBoard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class zeroIntegerBoard extends IntegerBoard
{
    public zeroIntegerBoard() 
    {
    }
    
    public zeroIntegerBoard(int field)
    {
        super(field);
    }
    
        public void UpdateImage()
    {
        setImage(new GreenfootImage(new java.text.DecimalFormat("00,0000,0000").format(field), 30, Color.WHITE,new Color(0,0,0,0)) );
    }
}
