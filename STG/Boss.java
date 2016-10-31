import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BossA here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Boss extends Enemy
{
    int t = 5;
    int patternNumber;
    protected ShotBullet shotBullets[] = new ShotBullet[6];
    protected int health[] = new int[6];
    boolean randomMov;
    int nowPattern;
    
    public void setBoss(int health[], ShotBullet shotBullets[],int patternNumber, boolean randomMov)
    {
        this.health = health;
        this.shotBullets = shotBullets;
        this.randomMov = randomMov;
        this.patternNumber = patternNumber;
    }
    
    
    public void act()
    {
            if(chkAlive())
            {
            getWorld().addObject(shotBullets[nowPattern],getX(),getY());
            shotBullets[nowPattern].setLocation(getX(),getY());
        }
        else if(nowPattern < patternNumber)
        {
            if(++nowPattern == patternNumber)
            {
                World w = getWorld();
                w.removeObject(this);
                w.removeObjects(w.getObjects(EnemyBullet.class));
                StatusManager.GetInstance().StageChange();
            }
        }
    }
    
    private boolean chkAlive()
    {
    
       Actor ball = (getOneIntersectingObject(PlayerBullet.class));
       if(ball != null)
       {
            w = getWorld();
           w.removeObject(ball);
           StatusManager.GetInstance().StrikeEnemy();
           if(--health[nowPattern] == 0)
               {  
                  return dead();
            }
       }
       if(StatusManager.GetInstance().isBombOn())
       {
           w = getWorld();
           if((health[nowPattern]-=3) <= 0)
           {
               return dead();
           }
       }
       return true;
    } 
    
    private boolean dead()
    {
          w.addObject(new PowerItem(),getX(),getY());
          w.removeObject(shotBullets[nowPattern]);
          w.removeObjects(w.getObjects(EnemyBullet.class));
          return false;
    }
}
