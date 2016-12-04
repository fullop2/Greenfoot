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
    protected ShotBullet shotBullets[][];
    private int patternBCNumber[];
    protected int health[];
    private boolean randomMov;
    private int nowPattern;
    
    public Boss(int stage)
    {
        setImage("Boss"+ stage + ".png");
    }
    
    public void Init() 
    {
    }
    
    public void InitSpell()
    {
        for(int i = 0; i < patternBCNumber[nowPattern]; i++)
        {
            getWorld().addObject(shotBullets[nowPattern][i],getX(),getY());
            shotBullets[nowPattern][i].setLocation(getX(),getY());
            }
    }
    
    public void setBoss(int health[], ShotBullet shotBullets[][],int patternNumber, int patternBCNumber[], boolean randomMov)
    {
        this.health = health;
        this.shotBullets = shotBullets;
        this.randomMov = randomMov;
        this.patternNumber = patternNumber;
        this.patternBCNumber = patternBCNumber;
    }
    
    public void act()
    {
        if(idleTime == 0 && nowPattern < patternNumber)
        {
            InitSpell();
        }
        if(--idleTime < 0 && !chkAlive() && nowPattern == patternNumber)
         {  
             remove();
             getWorld().removeObject(this);
             StatusManager.GetInstance().StageChange();
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
          if( nowPattern == patternNumber)
              getWorld().addObject(new LifeItem(),getX(),getY());
          else
            getWorld().addObject(new BombItem(),getX(),getY());
          baseWorld.bulletClear();  
    }
    
    private boolean spellBreak()
    {
          remove();
          for(int i = 0; i < patternBCNumber[nowPattern]; i++)
          getWorld().removeObject(shotBullets[nowPattern][i]);
          ++nowPattern;
          idleTime = 120;
          return false;
    }
}
