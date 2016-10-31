import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnemyA here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mob extends Enemy
{
    // 탄막 생성
    private ShotBullet shotBullet;
    
    // 체력
    private int healthPoint;
    
    // 이동 관련 변수
    private int speed;
    private Path path[];
    private int nowpath;
    private int MAXpath;
    private double dx,dy;
    private double length;
    
    public Mob()
    {
    }
    
    public void set(Path path[], ShotBullet shotBullet,int MAXpath,int health,int speed)
    {
        this.path = path;
        this.shotBullet = shotBullet;
        this.MAXpath = MAXpath;
        healthPoint = health;
        this.speed = speed;
    }
    
    public void act() 
    {
        if(chkAlive())
            {
                moveAlongPath();
            getWorld().addObject(shotBullet,getX(),getY());
            shotBullet.setLocation(getX(),getY());
        }
    }
    
    // 이동 함수
    private void  moveAlongPath()
    {
        if(nowpath < MAXpath)
        {

            dx = path[nowpath].getX() - getX();
            dy = path[nowpath].getY() - getY();
            length = Math.sqrt(dx*dx+dy*dy);
            if(dx < 5 && dy < 5)
                nowpath++;
            
            dx /= length;
            dy /= length;
        }
        setLocation(getX() + dx*speed,getY() + dy*speed);
    }
    
    // 생존 판단 함수
    private boolean chkAlive()
    {
            if(this != null)
           {
               Actor bullet = (getOneIntersectingObject(PlayerBullet.class));       
           if(bullet != null)
           {
               w = getWorld();
               w.removeObject(bullet);
               StatusManager.GetInstance().StrikeEnemy();
               if(--healthPoint == 0)
               {
                   return dead();
               }
           }
           if( StatusManager.GetInstance().isBombOn())
           {
               w = getWorld();
               if((healthPoint-=3) <= 0)
               {
                   return dead();
               }
           }
        }
        return true;
    } 
    
    protected boolean dead()
    {
          w.addObject(new ScoreItem(),getX(),getY());
          w.removeObject(shotBullet);
          w.removeObject(this);
          return false;
    }
}
