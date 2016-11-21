import greenfoot.*;
import greenfoot.GreenfootImage;

/**
 * Write a description of class bulletController here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class ShotBullet extends SmoothMover
{
    // 복사할 탄막
    private int bulletType;
    protected EnemyBullet obj;
    
    // 따라다닐 적 기체
    protected Enemy enemy;
    
    // 탄막 생성을 위한 변수들
    protected int mainNum;
    protected int subNum;
    protected int mainDelay;
    protected int subDelay;
    protected int time, subTime;
    protected int leftCount;
    protected int aimAngle;
    protected boolean playerAim;
    protected boolean rotate = false;
    protected int rotVal;
    protected int availTime;
    protected int rrot;
    protected Effect effect;
    protected int increaseSpeed;
    
    // 생성자
    protected ShotBullet(int availTime,
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
                            int rotVal,
                            int increaseSpeed)
    {
        this.availTime = availTime;
        this.enemy = enemy;
        this.bulletType = bulletType;
        this.obj = obj;
        this.mainNum = mainNum;
        this.mainDelay = mainDelay;
        this.subNum = subNum;
        this.subDelay = subDelay;
        this.aimAngle = aimAngle;
        this.playerAim = playerAim;
        this.rotate = rotate;
        this.rotVal = rotVal; 
        this.increaseSpeed = increaseSpeed;
        effect = new Effect(2,60);
        
        setRotation(90);
   
    }
    

    
    protected void Turning()
    {
         if(playerAim)
         {
               if(StatusManager.GetInstance().getAlive())
                  turnTowards(baseWorld.player.getX(),baseWorld.player.getY());
         }
         else if(rotate)
         {
               setRotation(getRotation() + rotVal);   
         }
         rrot = getRotation();
    }
    
    protected <T> EnemyBullet copyEnemyBullet()
    {
        EnemyBullet enemyBullet;
        switch(bulletType)
        {
            case 0:
            return new EnemyBulletRed(obj);
            case 1:
            return enemyBullet = new EnemyBulletGreen(obj);
            case 2:
            return enemyBullet = new EnemyBulletBlue(obj);   
            default:
            return enemyBullet = new EnemyBulletRing(obj);
        }
    }
}
