import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class NormalShot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NormalShot extends ShotBullet
{
    public NormalShot(int availTime,
                            Enemy enemy,
                            int bulletType,
                            EnemyBullet obj, 
                            int mainNum,
                            int mainDelay,
                            int subNum,
                            int subDelay,
                            int aimAngle,
                            boolean playerAim,
                            boolean rotate,
                            int rotVal
                            )
    {
        super(availTime,enemy, bulletType, obj,mainNum,mainDelay,subNum,subDelay,aimAngle,playerAim,rotate,rotVal, 0);
    }
    
                public void act()
    {
        if(--time < 0)
        {
            if(--subTime < 0)
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
                    time = mainDelay; 
                    if(!playerAim)
                       setRotation(getRotation() + 1);
                }
            }
        }   
        
    }
    
    private void Shot()
    {
         Effect e = new Effect(effect);
         getWorld().addObject(e,getX(),getY());
         for(int i = - mainNum / 2; i <= mainNum / 2; i++)
         {
             EnemyBullet o = copyEnemyBullet();
             o.turn(rrot + aimAngle * i);
             getWorld().addObject(o,getX(),getY());
         }
    }   
    

}
