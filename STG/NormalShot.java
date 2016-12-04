import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class NormalShot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NormalShot extends ShotBullet
{
    private int timeRevise = 0;
    public NormalShot(Actor enemy,
                            int relX, int relY,
                            int initIdle,
                            int availTime,
                            int bulletType,
                            EnemyBullet obj, 
                            int mainNum,
                            int mainDelay,
                            int subNum,
                            int subDelay,
                            int aimAngle,
                            boolean playerAim,
                            boolean rotate,
                            int rotVal,
                            int initRotate)
    {
        super(enemy,relX,relY,initIdle, availTime, bulletType, obj,mainNum,mainDelay,subNum,subDelay,aimAngle,playerAim,rotate,rotVal, 0,initRotate);
    }
    
    public void act()
    {
        if(--initIdle <= 0)
            {
            if(--time <= 0)
            {
                ++timeRevise;
                if(--subTime <= 0)
                {
                    if(--leftCount >= 0)
                    {
                        Turning();
                        Shot();
                        subTime = subDelay;
                    }
                    else
                    {
                        leftCount = subNum;
                        time = mainDelay - timeRevise;
                        timeRevise = 0;
                        if(!playerAim && !rotate)
                           setRotation(getRotation() + 1);
                    }
                }
            }   
        }
        
        additionalProcess();
    }
    
    private void Shot()
    {
         Effect e = new Effect(effect);
         getWorld().addObject(e,getX(),getY());
         for(int i = - mainNum / 2 ; i <= mainNum / 2; i++)
         {
             EnemyBullet o = copyEnemyBullet();
             o.turn(rrot + aimAngle * i);
             getWorld().addObject(o,getX(),getY());
         }
    }  
}
