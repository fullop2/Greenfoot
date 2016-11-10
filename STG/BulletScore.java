import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BulletScore here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BulletScore extends Items
{
    @Override
    protected void addItem()
    {
        StatusManager.GetInstance().BulletItemGet();
    }
}
