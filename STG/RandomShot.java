import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RandomShot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RandomShot extends ShotBullet
{
    private int subTimeCount;
    private int subNumCount;
    private int angleRange;
    private int timeRevise;
    
   public RandomShot(Actor enemy,
                            int relX, int relY,
                            int initIdle,
                            int availTime,
                            int bulletType,
                            EnemyBullet obj, 
                            int mainDelay,
                            int subNum,
                            int subDelay,
                            boolean playerAim,
                            int aimAngle,  
                            int angleRange,
                            int initRotate
                            )
    {
        super(enemy,relX,relY,initIdle,availTime,bulletType, obj,0,mainDelay,subNum,subDelay,aimAngle,playerAim,false, 0,0,initRotate);
        this.angleRange = angleRange;
        if(playerAim == true)
        this.aimAngle = 0;
        subTimeCount = 0;
        subNumCount = 0;
    }
    public void act()
    {
        if(--initIdle <= 0)
            {
            if(--time <= 0)
            {
                    Shot();
            }
        }
        additionalProcess();
    }  
    
    private void Shot()
    {
         Effect e = new Effect(effect);
         getWorld().addObject(e,getX(),getY());
         ++timeRevise;
         if(++subTimeCount >= subDelay)
         {
             if(++subNumCount <= subNum)
             {
                 EnemyBullet o = copyEnemyBullet();
                 o.turn(rrot + aimAngle + angleRange / 2 - Greenfoot.getRandomNumber(angleRange));
                 getWorld().addObject(o,getX(),getY());
                 
                }
                else
                {
                    subNumCount = 0;
                    subTimeCount = 0;
                    time = mainDelay - timeRevise;
                    timeRevise = 0;
                }
            }

            Turning();
 
    }  
}
