import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnemyBulletSeed here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyBulletSeed extends EnemyBullet
{
    public EnemyBulletSeed(int speed, int angle,int rot)
    {
        super(speed,0,rot);  
    }  
    public EnemyBulletSeed(EnemyBullet enemyBullet)
    {
        super(enemyBullet);
        rigidBody = 5;
    }
}
