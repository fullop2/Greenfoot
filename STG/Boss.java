import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BossA here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Boss extends Enemy
{
    int idleTime = 120;
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
        if(--idleTime < 0)
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
                    deadMotion();
                    w.removeObject(this);
                    StatusManager.GetInstance().StageChange();
                }
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
                  return spellBreak();
            }
       }
       if(StatusManager.GetInstance().isBombOn())
       {
           w = getWorld();
           if((health[nowPattern]-=3) <= 0)
           {
               return spellBreak();
           }
       }
       return true;
    } 
    
    private void deadMotion()
    {
          w.addObject(new PowerItem(),getX(),getY());
          ((BaseWorld)w).bulletClear();        
    }
    private boolean spellBreak()
    {
          deadMotion();
          w.addObject(new BombItem(),getX(),getY());
          w.removeObject(shotBullets[nowPattern]);
          idleTime = 120;
          return false;
    }
}
