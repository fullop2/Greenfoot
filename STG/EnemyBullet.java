import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class enemyBall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class EnemyBullet extends Bullet
{
    private int delta = 30;
    private int bombIdle;
    protected int rigidBody;
    private boolean remove = false;
    public boolean grazed = false;
    
   public EnemyBullet(int speed, int angle,int rot)
    {
        super(speed,rot);
        bombIdle = Greenfoot.getRandomNumber(9) + 4;
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

        Destroy();
    } 
    
    public void Remove()
    {
        remove = true;
    }
    
    private void Destroy()
    {
        if(isAtEdge())
        {
            getWorld().removeObject(this);
        }
        else if(remove && --bombIdle < 0)
        {
            getWorld().addObject(new Effect(4,25),getX(),getY());
            getWorld().removeObject(this); 
        }
    }
    
    public boolean checkHit()
    {
        if(!getObjectsInRange(rigidBody, Player.class).isEmpty())
            {return true;}
        else
        return false;
            
    }
}
