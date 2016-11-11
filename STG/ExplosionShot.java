import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ExplosionShot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ExplosionShot extends ShotBullet
{

   public ExplosionShot(int availTime,
                            Enemy enemy,
                            EnemyBullet obj, 
                            int mainNum,
                            int mainDelay,
                            int subNum,
                            int subDelay,
                            int aimAngle,
                            boolean playerAim,
                            boolean rotate,
                            int rotVal,
                            int increaseSpeed)
    {
        super(availTime,enemy,obj,mainNum,mainDelay,subNum,subDelay,aimAngle,playerAim,rotate,rotVal,increaseSpeed);
    }
    public void act()
    {
        if(--time < 0)
        {
                Turning();
                Shot();
                time = mainDelay;
                if(!playerAim)
                       setRotation(getRotation() + 1);
        }
    }  
    
    private void Shot()
    {
         Effect e = new Effect(effect);
         getWorld().addObject(e,getX(),getY());
         
         for(int tmp = 0;tmp < subNum; tmp++)
             for(int i = - mainNum / 2; i <= mainNum / 2; i++)
             {
                 EnemyBullet o = new EnemyBullet(obj);
                 o.turn(rrot + aimAngle * i);
                 o.speed = o.speed + tmp*increaseSpeed;
                 getWorld().addObject(o,getX(),getY());
             }
    }
  
   
}
