import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnemyBulletRing here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyBulletRing extends EnemyBullet
{
    public EnemyBulletRing(int speed, int angle,int rot)
    {
        super(speed,0,rot);
    }   
    public EnemyBulletRing(EnemyBullet enemyBullet)
    {
        super(enemyBullet);
        rigidBody = 35;
    }   
}
