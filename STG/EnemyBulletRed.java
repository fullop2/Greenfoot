import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnemyBulletRed here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyBulletRed extends EnemyBullet
{
    public EnemyBulletRed(int speed, int angle,int rot)
    {
        super(speed,0,rot);
    }
    public EnemyBulletRed(EnemyBullet enemyBullet)
    {
        super(enemyBullet);
    }
}
