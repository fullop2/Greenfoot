import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnemyBulletRing here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyBulletBigBlue extends EnemyBullet
{
    public EnemyBulletBigBlue(int speed, int angle,int rot)
    {
        super(speed,0,rot);
    }   
    public EnemyBulletBigBlue(EnemyBullet enemyBullet)
    {
        super(enemyBullet);
        rigidBody = 16;
    }   
}
