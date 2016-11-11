import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BossA here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Boss extends Enemy
{
    private int idleTime = 120;
    private int t = 5;
    private int patternNumber;
    protected ShotBullet shotBullets[];
    protected int health[];
    private boolean randomMov;
    private int nowPattern;
    
    public void Init() {}
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
                    remove();
                    getWorld().removeObject(this);
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
           getWorld().removeObject(ball);
           StatusManager.GetInstance().StrikeEnemy();
           if(--health[nowPattern] == 0)
               {  
                  return spellBreak();
            }
       }
       if(StatusManager.GetInstance().isBombOn())
       {
           if((health[nowPattern]-=3) <= 0)
           {
               return spellBreak();
           }
       }
       return true;
    } 
    
    private void remove()
    {
          PowerItem powerItem = new PowerItem();
          getWorld().addObject(powerItem,getX(),getY());
          baseWorld.bulletClear();        
    }
    
    private boolean spellBreak()
    {
          remove();
          BombItem bombItem = new BombItem();
          getWorld().addObject(bombItem,getX(),getY());
          getWorld().removeObject(shotBullets[nowPattern]);
          idleTime = 120;
          return false;
    }
}
