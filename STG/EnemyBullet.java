import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class enemyBall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyBullet extends Bullet
{
    int delta = 30;
   public EnemyBullet(int speed, int angle,int rot)
    {
        
        this.speed = speed;
        this.rot = rot;
        
    }
   
    public EnemyBullet(EnemyBullet a)
    {
        this(a.speed,0,a.rot);
    }
    
    public void act() 
    {
        // Add your action code here.
        move(speed);
        turn(rot);
        if(delta > 0)
            delta--;

        else
        {
            delta = 30;
            if(rot < 0)
            rot++;
            else if(rot > 0)
            rot--;
        }
        chkisEdge();
    } 
    
    public void Destroy()
    {
            getWorld().addObject(new PowerItem(),getX(),getY());
            getWorld().removeObject(this);
    }
}
