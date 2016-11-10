import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class AbsorbItem here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AbsorbItem
{
    public boolean mode;
    public List<Items> ItemList;
    public boolean Line(int ypos)
    {
        if(ypos < 150)
            return true;
        else 
            return false;
    }
    
    public void absorb()
    {
        for(Items item : ItemList)
        {
            item.setAbsorb();
        }
    }
    
}
