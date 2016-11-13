import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnemyBulletGreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyBulletGreen extends EnemyBullet
{
    public EnemyBulletGreen(int speed, int angle,int rot)
    {
        super(speed,0,rot);
    }   
    public EnemyBulletGreen(EnemyBullet enemyBullet)
    {
        super(enemyBullet);
       rigidBody = 10;
    }
}
