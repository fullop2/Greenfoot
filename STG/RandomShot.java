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
    
   public RandomShot(int availTime,
                            Enemy enemy,
                            int bulletType,
                            EnemyBullet obj, 
                            int mainDelay,
                            int subNum,
                            int subDelay,
                            int aimAngle,
                            int angleRange)
    {
        super(availTime,enemy,bulletType, obj,0,mainDelay,subNum,subDelay,aimAngle,false,false, 0,0);
        this.angleRange = angleRange;
    }
    public void act()
    {
        if(--time < 0)
        {
                Shot();
        }
    }  
    
    private void Shot()
    {
         Effect e = new Effect(effect);
         getWorld().addObject(e,getX(),getY());
         
         if(++subTimeCount < subDelay)
         {
             if(++subNumCount < subNum)
             {
                 EnemyBullet o = copyEnemyBullet();
                 o.turn(rrot + aimAngle + angleRange / 2 - Greenfoot.getRandomNumber(angleRange));
                 baseWorld.addObject(o,getX(),getY());
             }
             else
             {
                 subNumCount = 0;
             }
         }
         else
         {
             time = mainDelay;
             subTimeCount = 0;
             
         }
    }
  
   
}
