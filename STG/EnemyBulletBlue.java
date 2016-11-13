import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnemyBulletBlue here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyBulletBlue extends EnemyBullet
{
    public EnemyBulletBlue(int speed, int angle,int rot)
    {
        super(speed,0,rot);  
    }  
    public EnemyBulletBlue(EnemyBullet enemyBullet)
    {
        super(enemyBullet);
        rigidBody = 10;
    }
}
