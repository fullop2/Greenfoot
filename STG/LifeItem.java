import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LifeItem here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LifeItem extends Items
{
    @Override
    protected void addItem()
    {
       baseWorld.player.addLife();
    }
}
