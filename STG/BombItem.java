import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BombItem here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BombItem extends Items
{
    @Override
    protected void addItem()
    {
            baseWorld.player.bombAttack.addBomb();
    }   
}
