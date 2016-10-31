import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class playerBall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerBullet extends Bullet
{
    public PlayerBullet(){}
    public PlayerBullet(int speed, int angle)
    {        
        this.speed = speed;
        turn(angle - 90);
    }
}
